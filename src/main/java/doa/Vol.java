package doa;

import java.sql.Time;
import java.util.ArrayList;

import metier.Dijkstra;
import metier.GestionMetier;
import metier.Graphe;

import java.sql.Date;

public class Vol implements Navigable{

	private int idVol;
	private Avion avion;
	private String dateVol;
	private Aeroport aeroportDepart;
	private Aeroport aeroportArrivee;
	private int capacite;
	private String departureTime, arrivalTime;
	public int getIdVol() {
		return idVol;
	}
	
	public void setIdVol(int idVol) {
		this.idVol = idVol;
	}
	public Avion getAvion() {
		return avion;
	}
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	public String getDateVol() {
		return dateVol;
	}
	public void setDateVol(String dateVol) {
		this.dateVol = dateVol;
	}
	
	public Aeroport getAeroportDepart() {
		return aeroportDepart;
	}
	public void setAeroportDepart(Aeroport aeroportDepart) {
		this.aeroportDepart = aeroportDepart;
	}
	public Aeroport getAeroportArrivee() {
		return aeroportArrivee;
	}

	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getHeurePrivessionelle() {
		return arrivalTime;
	}
	public void setHeurePrivessionelle(String heurePrivessionelle) {
		this.arrivalTime = heurePrivessionelle;
	}
	
	
	
	public Vol( Avion avion, String date, Aeroport aeroportDepart, Aeroport aeroportArrivee,
			String departureTime, String arrivalTime) {
		super();
		this.avion = avion;
		this.dateVol = date;
		this.aeroportDepart = aeroportDepart;
		this.aeroportArrivee = aeroportArrivee;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setAeroportArrivee(Aeroport aeroportArrivee) {
		this.aeroportArrivee = aeroportArrivee;
	}

	public Vol() {
		super();
	}

	@Override
	public void setDestination(Aeroport destination) {
		this.aeroportArrivee=destination;
	}
	

	@Override
	public ArrayList<Aeroport> calculateRoute(String Ad, String Aa) {
			GestionMetier gm =new GestionMetier();
		ArrayList<ArrayList<Integer>> graph =gm.constriureGraph2();
		
		int [][] mt1 = new int [graph.size()][graph.get(0).size()];
		mt1=gm.Array2Table(graph);
		Graphe g0 = new Graphe(mt1);
		Dijkstra dj= new Dijkstra(Integer.valueOf(Ad)-1,g0);
		ArrayList<Aeroport> chemin = dj.afficheChemin(Integer.valueOf(Aa)-1);	
		return chemin;
	}}
