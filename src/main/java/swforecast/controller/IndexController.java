package swforecast.controller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import swforecast.entities.SMHIData;
import swforecast.entities.SMHIPoints;
import swforecast.entities.User;
import swforecast.services.StoreSMHITempServices;
import swforecast.services.UserServices;

@Controller
@RequestMapping("/")
@SessionAttributes({ "username", "useremail" })
public class IndexController {
	@Autowired
	UserServices userservices;

	@Autowired
	StoreSMHITempServices storeSmhiTempServices;

	@Autowired
	private JavaMailSender mailSenderObj;

	@RequestMapping
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/update")
	public String updateSMHIData(HttpSession session) throws JsonParseException, JsonMappingException, IOException {

		List<SMHIPoints> smhipoints = storeSmhiTempServices.listpoints();

		final ObjectMapper mapper = new ObjectMapper();

		final String USER_AGENT = "Mozilla/5.0";

		session.setMaxInactiveInterval(2 * 60 * 60);

		for (SMHIPoints points : smhipoints) {

			String url = "http://opendata-download-metfcst.smhi.se/api/category/pmp2g/version/2/geotype/point/lon/"
					+ points.getLon() + "/lat/" + points.getLat() + "/data.json";

			System.out.println(url);

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			/* Gets the data from SMHI */
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			List<SMHIData> values = new ArrayList<SMHIData>(); // Holds the completed lines of data

			/* Parse the JSON for the values we need */
			JsonNode root = mapper.readValue(response.toString(), JsonNode.class);
			JsonNode dataNode = root.path("timeSeries");
			Iterator<JsonNode> elements = dataNode.elements();

			LocalDateTime midnightNextDay = LocalDateTime
					.of(LocalDate.now(ZoneId.of("Europe/Stockholm")), LocalTime.MIDNIGHT).plusDays(1);
			LocalDateTime beforeMidnightDayAfterNextDay = midnightNextDay.plusHours(23);
			while (elements.hasNext()) {
				JsonNode node = elements.next();
				LocalDateTime timestamp = LocalDateTime.parse(node.path("validTime").asText().substring(0, 19));
				timestamp = timestamp.plusHours(2); // Set to Sweden's timezone
				if (timestamp.isBefore(midnightNextDay)) {
					continue;
				} else if (timestamp.isAfter(beforeMidnightDayAfterNextDay)) {
					break;
				}
				SMHIData data = new SMHIData();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				data.setTimestamp(Date.from(timestamp.atZone(defaultZoneId).toInstant()));
				JsonNode parameterList = node.path("parameters");
				Iterator<JsonNode> parameters = parameterList.elements();
				while (parameters.hasNext()) {
					JsonNode parameter = parameters.next();
					if (parameter.path("name").asText().equalsIgnoreCase("t")) { // air temperature
						data.setT(parameter.path("values").elements().next().asDouble());
					}
					if (parameter.path("name").asText().equalsIgnoreCase("ws")) { // wind speed
						data.setWs(parameter.path("values").elements().next().asDouble());
					}
				}
				data.setLat(points.getLat()); // LATITUDE
				data.setLon(points.getLon()); // LONGITUDE
				data.setLocation(points.getCity()); // CITY
				values.add(data);
			}

			List<SMHIData> list = storeSmhiTempServices.list(points);
			Date lastTimeStamp = null;

			if (list.size() != 0)
				lastTimeStamp = list.get(0).getTimestamp();
			System.out.println("data collected");
			Collections.reverse(values);
			System.out.println("data reversed");
			storeSmhiTempServices.save(values, lastTimeStamp);
			System.out.println("data inserted");
			con.disconnect();
		}
		return "index";
	}

	@RequestMapping(value = "/writejson")
	public String writejson(HttpSession session) throws IOException {
		List<SMHIPoints> smhipoints = storeSmhiTempServices.listpoints();
		int i = 1;
		JSONArray points = new JSONArray();

		// ENCODE ALL DATA IN JSON FORMAT
		for (SMHIPoints point : smhipoints) {
			List<SMHIData> smhidata = storeSmhiTempServices.getforecast(point);
			JSONArray temperature = new JSONArray();
			JSONArray timestamp = new JSONArray();
			for (SMHIData data : smhidata) {
				temperature.add(data.getT());
				timestamp.add(data.getTimestamp().getHours() + ":" + data.getTimestamp().getMinutes());
			}
			System.out.println(i);
			JSONObject obj = new JSONObject();
			obj.put("id", i);
			obj.put("city", point.getCity());
			obj.put("temperature", temperature);
			obj.put("timestamp", timestamp);
			obj.put("lon", point.getLon());
			obj.put("lat", point.getLat());
			obj.put("propertyType", "single-home");
			System.out.println("JSON Object: " + obj);
			points.add(obj);
			i++;
		}

		// WRITE TEMPERATURE DATA AND CORRDINATES IN JSON FILE
		String jsonmarker = session.getServletContext().getRealPath("/") + "resources/ajax/smhipoints.json";

		FileWriter file = new FileWriter(jsonmarker);
		try {
			file.write(points.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.flush();
			file.close();
		}
		return "index";
	}

	@RequestMapping(value = "/dashboard")
	public String dashboard(ModelMap model) {
		if (model.containsAttribute("username") && model.containsAttribute("useremail")) {
			return "dashboard";
		} else
			return "index";
	}

	static final String emailFromRecipient = "dummyking2211@gmail.com";

	@RequestMapping(value = "/settings", method = RequestMethod.POST)
	public String settings(ModelMap model, HttpServletRequest request, HttpSession session) {
		if (model.containsAttribute("username") && model.containsAttribute("useremail")) {
			String cityname = request.getParameter("cityname");
			SMHIPoints smhipoints = storeSmhiTempServices.getcorrdinates(cityname);
			List<SMHIData> smhidata = storeSmhiTempServices.getforecast(smhipoints);
			String emailToRecipient = session.getAttribute("useremail").toString();
			String emailSubject = "Weather Forecast of " + cityname;
			String msg = "     TimeStamp              Temperature\n";
			;
			for (SMHIData data : smhidata) {
				msg = msg + data.getTimestamp().toString() + "         " + data.getT() + "\n";
			}
			final String emailMessage = msg;
			System.out.println(emailMessage);

			mailSenderObj.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {

					MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					mimeMsgHelperObj.setTo(emailToRecipient);
					mimeMsgHelperObj.setFrom(emailFromRecipient);
					mimeMsgHelperObj.setText(emailMessage);
					mimeMsgHelperObj.setSubject(emailSubject);
				}
			});

			return "dashboard";
		} else
			return "index";
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String setnotifications(ModelMap model) {
		if (model.containsAttribute("username") && model.containsAttribute("useremail")) {
			List<SMHIPoints> smhipoints = storeSmhiTempServices.listpoints();
			ArrayList<String> citylist = new ArrayList<String>();
			for (SMHIPoints points : smhipoints) {
				citylist.add(points.getCity());
			}
			model.addAttribute("citylist", citylist);
			return "settings";
		} else
			return "index";
	}

	@RequestMapping(value = "/userauth", method = RequestMethod.POST)
	public String userauthentication(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {

		if (user.getSubmit().equals("Login")) {
			List<User> userlist = userservices.list(user);

			if (userlist.size() == 1) {
				System.out.println("User Login Successfull");

				for (User u : userlist) {
					model.addAttribute("username", u.getUsername());
					model.addAttribute("useremail", u.getEmail());
				}
				return "redirect:/dashboard";
			}
		} else if (user.getSubmit().equals("Register")) {

			if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
				return "index";
			}

			if (user.getRepassword().equals(user.getPassword())) {
				if (userservices.saveOrUpdate(user)) {
					System.out.println("User Registration Successfull");
					model.addAttribute("username", user.getUsername());
					model.addAttribute("useremail", user.getEmail());
				}
				return "redirect:/dashboard";
			} else {
				return "index";
			}
		}

		return "index";
	}

	@RequestMapping("/logout")
	public String userLogout(HttpSession session, ModelMap model) {
		System.out.println("Logout Controller");
		session.invalidate();
		if (model.containsAttribute("username") || model.containsAttribute("useremail"))
			model.clear();
		return "index";
	}

}
