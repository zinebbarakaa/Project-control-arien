package doa;



public class Route {
	private Aeroport aeroportDepart;
	private double distance ;
	private int disponiblite ;
	private Aeroport aeroportArivee;
	public Aeroport getAeroportDepart() {
		return aeroportDepart;
	}
	public void setAeroportDepart(Aeroport aeroportDepart) {
		this.aeroportDepart = aeroportDepart;
	}
	public Aeroport getAeroportArivee() {
		return aeroportArivee;
	}
	public void setAeroportArivee(Aeroport aeroportArivee) {
		this.aeroportArivee = aeroportArivee;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Route(Aeroport aeroportDepart, Aeroport aeroportArivee, double distance) {
		super();
		this.aeroportDepart = aeroportDepart;
		this.aeroportArivee = aeroportArivee;
		this.distance = distance;
	}
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDisponiblite() {
		return disponiblite;
	}
	public void setDisponiblite(double distance) {
		if(distance<=1900 && distance!=0)
			this.disponiblite = 1;
		else
			this.disponiblite=0;
	}
}
