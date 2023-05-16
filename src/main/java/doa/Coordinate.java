package doa;

public class Coordinate {

	private double lat,lon;

	@Override
	public String toString() {
		return "[ "+lat  +" " + lon + " ]";
	}

	public Coordinate(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
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
