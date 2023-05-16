package doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import metier.GestionMetier;
import util.ConnectionSingleton;


public class Avion implements Flayable,FuelConsommable{
	public int getCroisiereAltitude() {
		return croisiereAltitude;
	}
	public void setCroisiereAltitude(int croisiereAltitude) {
		this.croisiereAltitude = croisiereAltitude;
	}
	public int getLandingAltitude() {
		return landingAltitude;
	}
	public void setLandingAltitude(int landingAltitude) {
		this.landingAltitude = landingAltitude;
	}
	public int getCruisingSpeed() {
		return cruisingSpeed;
	}
	public int getLandingSpeed() {
		return landingSpeed;
	}
	
	private int idAvion;
	private Aeroport aeroportActuel ;
	private String nom;
	private String type;// court , moyen , long 
	private int CapaciteCarburant; // par litre 
	private int distanceVolMax ;//par Km 
	private int croisiereAltitude;
	private int landingAltitude;
	private  int cruisingSpeed ; // 400 kts, about 741 km/h
	private  int landingSpeed ; // 80 kts, about 148 km/h
	private double fuelConsumptionPerSecond;
	private double fuelLevel;	
	private int altitude;
	private double vitesse;
	private Coordinate position;
	public void setCruisingSpeed(int cruisingSpeed) {
		this.cruisingSpeed = cruisingSpeed;
	}
	public void setLandingSpeed(int landingSpeed) {
		this.landingSpeed = landingSpeed;
	}

	private String photo;
	
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Coordinate getPosition() {
		return position;
	}
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	public Avion(int idAvion, Aeroport aeroportActuel, String nom, String type, int capaciteCarburant,
			int distanceVolMax, int croisiereAltitude, int landingAltitude, int cruisingSpeed, int landingSpeed,
			double fuelConsumptionPerSecond) {
		super();
		this.idAvion = idAvion;
		this.aeroportActuel = aeroportActuel;
		this.nom = nom;
		this.type = type;
		CapaciteCarburant = capaciteCarburant;
		this.distanceVolMax = distanceVolMax;
		this.croisiereAltitude = croisiereAltitude;
		this.landingAltitude = landingAltitude;
		this.cruisingSpeed = cruisingSpeed;
		this.landingSpeed = landingSpeed;
		this.fuelConsumptionPerSecond = fuelConsumptionPerSecond;
	}
	
	
	public Avion(int idAvion, Aeroport aeroportActuel, String nom, String type, int capaciteCarburant,
			int distanceVolMax, int croisiereAltitude, int landingAltitude, int cruisingSpeed, int landingSpeed,
			double fuelConsumptionPerSecond,String photo) {
		super();
		this.idAvion = idAvion;
		this.aeroportActuel = aeroportActuel;
		this.nom = nom;
		this.type = type;
		CapaciteCarburant = capaciteCarburant;
		this.distanceVolMax = distanceVolMax;
		this.croisiereAltitude = croisiereAltitude;
		this.landingAltitude = landingAltitude;
		this.cruisingSpeed = cruisingSpeed;
		this.landingSpeed = landingSpeed;
		this.fuelConsumptionPerSecond = fuelConsumptionPerSecond;
		this.photo=photo;
	}
	


	
	public Avion(int idAvion, Aeroport aeroportActuel, String nom, String type, int capaciteCarburant,
			int distanceVolMax, double fuelLevel, int altitude, int croisiereAltitude, int landingAltitude,
			double vitesse, double fuelConsumptionPerSecond) {
		super();
		this.idAvion = idAvion;
		this.aeroportActuel = aeroportActuel;
		this.nom = nom;
		this.type = type;
		CapaciteCarburant = capaciteCarburant;
		this.distanceVolMax = distanceVolMax;
		this.fuelLevel = fuelLevel;
		this.altitude = altitude;
		this.croisiereAltitude = croisiereAltitude;
		this.landingAltitude = landingAltitude;
		this.vitesse = vitesse;
		this.fuelConsumptionPerSecond = fuelConsumptionPerSecond;
	}
	public int getIdAvion() {
		return idAvion;
	}
	public void setFuelLevel(double fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}
	public Aeroport getAeroportActuel() {
		return aeroportActuel;
	}
	public void setAeroportActuel(Aeroport aeroportActuel) {
		this.aeroportActuel = aeroportActuel;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCapaciteCarburant() {
		return CapaciteCarburant;
	}
	public void setCapaciteCarburant(int capaciteCarburant) {
		CapaciteCarburant = capaciteCarburant;
	}
	public int getDistanceVolMax() {
		return distanceVolMax;
	}
	public void setDistanceVolMax(int distanceVolMax) {
		this.distanceVolMax = distanceVolMax;
	}
	public Avion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Avion(int idAvion, String nom, int capaciteCarburant) {
		super();
		this.idAvion = idAvion;
		this.nom = nom;
		CapaciteCarburant = capaciteCarburant;
	}
	@Override
	public void takeOff(Avion a,int i,List<Coordinate> path) {
		// ajouter l'avions a les avions en vol et la suprimer de la file d attenete 
		
		a.getAeroportActuel().getAvionsEnVol().add(a);
		System.out.println("L' avion "+a.getNom()+" entre dans la piste");
		
		a.getAeroportActuel().getPistesAttente().remove(a);
		System.out.println("L' avion "+a.getNom()+"  sort  de la piste  ");
		ascend(path, i);


	}
	@Override
	public void fly() {
		
		this.setAltitude(32000);
		
		      }
		    
		  
		
	
	@Override
	public void ajouterAvionPiste(Avion a) {
		Aeroport A= GestionMetier.recupererAeroportById(a.getAeroportActuel().getIdAeroport());
		A.setPistesAttente(new LinkedBlockingQueue());
		A.getPistesAttente().add(a);
		System.out.println("L'avion  "+a.getNom()+" entre dans la liste d'attente de piste  ");
		
	}
	@Override
	public void land(Vol v,List<Coordinate> path ,int i) {
		
		   // Recherche d'une station libre
		List<Station> stations = GestionMetier.RecupereStationDunAeroport(v.getAeroportArrivee().getIdAeroport());
		for(Station s :stations)
		{
			System.out.println(s.getStatut());
		}
	    Station stationLibre = null;
	    for (Station s : stations) {
	      if (s.getStatut()) {
	        stationLibre = s;
	        break;
	      }
	    }

	    // Si une station est disponible, on place l'avion dessus et on marque la station comme occupée
	    if (stationLibre != null) {
	    System.out.println(" \n Recherche d'une station libre pour "+v.getAvion().getNom());
	    GestionMetier.ChangerStationEtat(stationLibre.getId_station());
	    v.getAeroportDepart().getAvionsEnVol().remove(v.getAvion());
	      descend(path, i);
	      System.out.println(" \n L' avion a atterit "+v.getAvion().getNom());
          System.out.println(" \n "+v.getAvion().getNom()+"   Flight completed successfully!");

	    }
		
	}
	@Override
	public double getFuelLevel() {
	    return fuelLevel;
	}
	@Override
	public double getFuelConsumptionPerSecond() {
	    return fuelConsumptionPerSecond;
	}
	@Override
	public void setFuelConsumptionPerSecond(double consumption) {
		this.fuelConsumptionPerSecond=consumption;

	}
	@Override
	public void alertFuelLow() {
		if (fuelLevel <= 10) {
		      System.out.println("Fuel level low!");
		    }			
	}
	@Override

	public void consumeFuel(double velocity) {
		fuelConsumptionPerSecond = velocity * 0.1;
	    fuelLevel -= fuelConsumptionPerSecond;
	    if (fuelLevel < 0) {
	        fuelLevel = 0;
	    }		
	    System.out.print(fuelLevel+" litre        ");
	}
	
	
	
	public double updateVelocityByAltitude( int targetAltitude) {
	    if (targetAltitude > altitude) {
	        // if the target altitude is higher than the current altitude, increase velocity
	    	vitesse= vitesse + (targetAltitude - altitude) / 1000;
		    System.out.println("hi");

	        consumeFuel(vitesse);
	        return vitesse;

	 
	    } 
	    else if (targetAltitude==altitude) {
	    	System.out.println("cccc");
	    	vitesse= vitesse + (targetAltitude - altitude) / 1000;
	        return vitesse;

		}
	    else {
	        // if the target altitude is lower than the current altitude, decrease velocity
	    	vitesse = vitesse - (altitude - targetAltitude) / 1000;
	    	System.err.println();
	        consumeFuel(vitesse);
	        return vitesse;


	    }
	    
	    // update the altitude of the airplane

	}
	
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public double getVitesse() {
		return vitesse;
	}
	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}
	@Override
	public void ascend(List<Coordinate> path,int i) {
		System.out.println("entrain d'ascendere...");
		while (altitude < croisiereAltitude || vitesse < cruisingSpeed)
		{ altitude += 1000;
    	vitesse += 20;
        consumeFuel(vitesse);

     //  Double v=  updateVelocityByAltitude(croisiereAltitude);
		System.out.print(altitude+" m        ");
		System.out.print(vitesse+" Km/h        ");
		System.out.println(path.get(i).getLat() +" , " +path.get(i).getLon() );
		}
	}
	
	@Override
	public void descend(List<Coordinate> path,int i) {
		System.out.println("entrain de descend....");
		while ((altitude > landingAltitude || vitesse > landingSpeed ) && altitude>0) {
            altitude -= 500;	
        	vitesse -= 10;

	        consumeFuel(vitesse);

          // double v= updateVelocityByAltitude(landingAltitude);
            System.out.print(altitude+" m 		");
    		System.out.print(vitesse+" km/h       ");
    		System.out.println(path.get(i).getLat() +" , " +path.get(i).getLon() );

}	
}
}