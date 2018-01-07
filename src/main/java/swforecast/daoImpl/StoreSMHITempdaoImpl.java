package swforecast.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import swforecast.dao.StoreSMHITempdao;
import swforecast.entities.SMHIData;
import swforecast.entities.SMHIPoints;

@Repository
@Transactional
public class StoreSMHITempdaoImpl implements StoreSMHITempdao {

	@Autowired
	SessionFactory sessionFactory;

	public List<SMHIData> list(SMHIPoints points) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from SMHIData where lon= :lon and lat = :lat order by timestamp desc");
		query.setParameter("lon", points.getLon());
		query.setParameter("lat", points.getLat());
		List<SMHIData> timestamp = query.list();
		return timestamp;
	}

	public List<SMHIData> getforecast(SMHIPoints points) {
		System.out.println("HI");
		Date sdate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(sdate);
		c.add(Calendar.DATE, 1);
		Date edate = c.getTime();
		System.out.println(sdate + "  " + edate);

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from SMHIData where lon= :lon and lat = :lat and timestamp between :sdate and :edate order by timestamp asc");
		query.setParameter("lon", points.getLon());
		query.setParameter("lat", points.getLat());
		query.setParameter("sdate", sdate);
		query.setParameter("edate", edate);
		List<SMHIData> timestamp = query.list();
		return timestamp;
	}

	public List<SMHIPoints> listpoints() {
		List<SMHIPoints> points = sessionFactory.getCurrentSession().createQuery("from SMHIPoints order by rank")
				.list();
		return points;
	}

	public SMHIPoints getcorrdinates(String city) {
		Query query = sessionFactory.getCurrentSession().createQuery("from SMHIPoints where city=:city");
		query.setParameter("city", city);
		SMHIPoints points = (SMHIPoints) query.list().get(0);
		return points;
	}

	public boolean save(List<SMHIData> data, Date lastupdatedate) {
		System.out.println("IN MODEL");
		Session session = sessionFactory.openSession();
		System.out.println("Session opened");
		int status = 1;
		try {
			session.beginTransaction();
			System.out.println("Begin Transaction");

			int i = 0;
			for (SMHIData row : data) {
				System.out.println(row.getTimestamp() + "  lat:" + row.getLat() + "  lon:" + row.getLon());
				if (lastupdatedate == null) {
					// FIRST INSERTION. WHEN DATABASE IS EMPTY
					i++;
					row.setRecid(
							new SimpleDateFormat("yyyyMMddHHmm").format(row.getTimestamp()) + row.getLocation() + i);
					// System.out.println(row.getTimestamp());
					SQLQuery insertQuery = session.createSQLQuery(
							"INSERT INTO smhidata(recid,timestamp,location,lat,lon,temperature,windspeed)VALUES(?,?,?,?,?,?,?)");
					insertQuery.setParameter(0, row.getRecid());
					insertQuery.setParameter(1, row.getTimestamp());
					insertQuery.setParameter(2, row.getLocation());
					insertQuery.setParameter(3, row.getLat());
					insertQuery.setParameter(4, row.getLon());
					insertQuery.setParameter(5, row.getT());
					insertQuery.setParameter(6, row.getWs());
					status = status * insertQuery.executeUpdate();
				} else if (lastupdatedate != null && lastupdatedate.compareTo(row.getTimestamp()) > 1) {
					// UPDATE DATABASE IS WITH NEW DATA
					i++;
					row.setRecid(
							new SimpleDateFormat("yyyyMMddHHmm").format(row.getTimestamp()) + row.getLocation() + i);

					SQLQuery insertQuery = session.createSQLQuery(
							"INSERT INTO smhidata(recid,timestamp,location,lat,lon,temperature,windspeed)VALUES(?,?,?,?,?,?,?)");
					insertQuery.setParameter(0, row.getRecid());
					insertQuery.setParameter(1, row.getTimestamp());
					insertQuery.setParameter(2, row.getLocation());
					insertQuery.setParameter(3, row.getLat());
					insertQuery.setParameter(4, row.getLon());
					insertQuery.setParameter(5, row.getT());
					insertQuery.setParameter(6, row.getWs());
					status = status * insertQuery.executeUpdate();
				} else
					break;
			}
		} catch (Exception e) {
			System.out.println("Failed to connect to database");
		}

		session.getTransaction().commit();
		session.close();
		if (status == 1)
			return true;
		else
			return false;
	}

}
