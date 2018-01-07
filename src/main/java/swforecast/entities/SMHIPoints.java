package swforecast.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smhipoints")
public class SMHIPoints {
	@Id
	@Column(name = "rank")
	private int rank;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "population")
	private double population;
	
	@Column(name = "lat")
	private double lat;

	@Column(name = "lon")
	private double lon;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(double population) {
		this.population = population;
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
