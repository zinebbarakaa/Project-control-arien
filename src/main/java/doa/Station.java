package doa;

public class Station {
	private Aeroport aeroport;
	private int id_station;
	private Integer numero;
	private Boolean statut;
	public int getId_station() {
		return id_station;
	}
	public void setId_station(int id_station) {
		this.id_station = id_station;
	}
	
	public Station(int id_station,Aeroport aeroport, Integer numero, Boolean statut) {
		super();
		this.aeroport = aeroport;
		this.id_station = id_station;
		this.numero = numero;
		this.statut = statut;
	}
	public Station() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Aeroport getAeroport() {
		return aeroport;
	}
	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Boolean getStatut() {
		return statut;
	}
	public void setStatut(Boolean statut) {
		this.statut = statut;
	}
	
	
}
