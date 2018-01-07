package swforecast.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smhidata")
public class Notification {
	@Id
	@Column(name = "n_id")
	private double n_id;

	@Column(name = "userid")
	private double userid;

	@Column(name = "cityname")
	private double cityname;

	public double getN_id() {
		return n_id;
	}

	public void setN_id(double n_id) {
		this.n_id = n_id;
	}

	public double getUserid() {
		return userid;
	}

	public void setUserid(double userid) {
		this.userid = userid;
	}

	public double getCityname() {
		return cityname;
	}

	public void setCityname(double cityname) {
		this.cityname = cityname;
	}
}
