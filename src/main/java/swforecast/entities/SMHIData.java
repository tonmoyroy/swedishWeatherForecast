package swforecast.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smhidata")
public class SMHIData {
	@Id
	@Column(name = "recid")
	private String recid;

	@Column(name = "timestamp")
	private Date timestamp;
	
	@Column(name = "location")
	private String location;

	@Column(name = "lat")
	private double lat;
	
	@Column(name = "lon")
	private double lon;

	@Column(name = "temperature")
	private double t;

	@Column(name = "windspeed")
	private double ws;

	public Date getTimestamp() {
		return timestamp;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public double getWs() {
		return ws;
	}

	public void setWs(double ws) {
		this.ws = ws;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}


}
