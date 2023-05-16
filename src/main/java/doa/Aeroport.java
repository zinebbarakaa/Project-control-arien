package doa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Aeroport {

	
	
	public Aeroport() {
		super();
		this.pistesAttente = new LinkedBlockingQueue<>();
		this.stationsEtat = new HashMap<>();
		this.avionsEnVol =new LinkedBlockingQueue<>();
		
		// TODO Auto-generated constructor stub
	}
	public Aeroport(int idAeroport, String codeOACI, String nomAeroport, float longtitude, float latitude, int nbrPiste,
			int nbrStation, int delaiAttSol, int tempsAccesPiste, int delaiAnticolission, int tempsDecolage,
			int tempsArretissage, int boucleAttVol) {
		super();
		this.idAeroport = idAeroport;
		this.codeOACI = codeOACI;
		this.nomAeroport = nomAeroport;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.nbrPiste = nbrPiste;
		this.nbrStation = nbrStation;
		this.delaiAttSol = delaiAttSol;
		this.tempsAccesPiste = tempsAccesPiste;
		this.delaiAnticolission = delaiAnticolission;
		this.tempsDecolage = tempsDecolage;
		this.tempsArretissage = tempsArretissage;
		this.boucleAttVol = boucleAttVol;
		
	}
	public Aeroport(float longtitude, float latitude) {
		this.latitude=latitude;
		this.longtitude= longtitude;
		// TODO Auto-generated constructor stub
	}
	private int idAeroport;
	public Aeroport(int idAeroport, String codeOACI, String nomAeroport, float longtitude, float latitude, int nbrPiste,
			int nbrStation, int delaiAttSol, int tempsAccesPiste, int delaiAnticolission, int tempsDecolage,
			int tempsArretissage, int boucleAttVol, String photoPays, String photo) {
		super();
		this.idAeroport = idAeroport;
		this.codeOACI = codeOACI;
		this.nomAeroport = nomAeroport;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.nbrPiste = nbrPiste;
		this.nbrStation = nbrStation;
		this.delaiAttSol = delaiAttSol;
		this.tempsAccesPiste = tempsAccesPiste;
		this.delaiAnticolission = delaiAnticolission;
		this.tempsDecolage = tempsDecolage;
		this.tempsArretissage = tempsArretissage;
		this.boucleAttVol = boucleAttVol;
		this.photoPays = photoPays;
		this.photo = photo;
		
	}
	private String codeOACI;
	private String nomAeroport;
	private float longtitude;
	private float latitude;
	private int nbrPiste;
	
	private int nbrStation;
	private int delaiAttSol;
	private int tempsAccesPiste;//en s
	private int delaiAnticolission;//en s
	private int tempsDecolage;
	private int tempsArretissage;
	private int boucleAttVol;
	private Queue<Avion> pistesAttente;
	Map<Station, Boolean> stationsEtat;
	private Queue<Avion> avionsEnVol;
	private String photoPays;
	private String photo;
	private String pays;
	public String getPays() {
		return pays;
	}
	public Aeroport(String photoPays, String pays,int nbr) {
		super();
		this.photoPays = photoPays;
		this.pays = pays;
		this.nbrPiste=nbr;
	}
	public Aeroport(int idAeroport, String codeOACI, String nomAeroport, float longtitude, float latitude, int nbrPiste,
			int nbrStation, int delaiAttSol, int tempsAccesPiste, int delaiAnticolission, int tempsDecolage,
			int tempsArretissage, int boucleAttVol, String photoPays, String photo, String pays) {
		super();
		this.idAeroport = idAeroport;
		this.codeOACI = codeOACI;
		this.nomAeroport = nomAeroport;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.nbrPiste = nbrPiste;
		this.nbrStation = nbrStation;
		this.delaiAttSol = delaiAttSol;
		this.tempsAccesPiste = tempsAccesPiste;
		this.delaiAnticolission = delaiAnticolission;
		this.tempsDecolage = tempsDecolage;
		this.tempsArretissage = tempsArretissage;
		this.boucleAttVol = boucleAttVol;
		this.photoPays = photoPays;
		this.photo = photo;
		this.pays = pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getIdAeroport() {
		return idAeroport;
	}
	public void setIdAeroport(int idAeroport) {
		this.idAeroport = idAeroport;
	}
	public String getCodeOACI() {
		return codeOACI;
	}
	public String getPhotoPays() {
		return photoPays;
	}
	public void setPhotoPays(String photoPays) {
		this.photoPays = photoPays;
	}
	public Queue<Avion> getPistesAttente() {
		return pistesAttente;
	}
	public void setPistesAttente(Queue<Avion> pistesAttente) {
		this.pistesAttente = pistesAttente;
	}
	public Map<Station, Boolean> getStationsEtat() {
		return stationsEtat;
	}
	public void setStationsEtat(Map<Station, Boolean> stationsEtat) {
		this.stationsEtat = stationsEtat;
	}
	
	public Queue<Avion> getAvionsEnVol() {
		return avionsEnVol;
	}
	public void setAvionsEnVol(Queue<Avion> avionsEnVol) {
		this.avionsEnVol = avionsEnVol;
	}
	
	public void setCodeOACI(String codeOACI) {
		this.codeOACI = codeOACI;
	}
	public String getNomAeroport() {
		return nomAeroport;
	}
	public void setNomAeroport(String nomAeroport) {
		this.nomAeroport = nomAeroport;
	}
	public float getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public int getNbrPiste() {
		return nbrPiste;
	}
	public void setNbrPiste(int nbrPiste) {
		this.nbrPiste = nbrPiste;
	}
	public int getNbrStation() {
		return nbrStation;
	}
	public void setNbrStation(int nbrStation) {
		this.nbrStation = nbrStation;
	}
	public int getDelaiAttSol() {
		return delaiAttSol;
	}
	public void setDelaiAttSol(int delaiAttSol) {
		this.delaiAttSol = delaiAttSol;
	}
	public int getTempsAccesPiste() {
		return tempsAccesPiste;
	}
	public void setTempsAccesPiste(int tempsAccesPiste) {
		this.tempsAccesPiste = tempsAccesPiste;
	}
	public int getDelaiAnticolission() {
		return delaiAnticolission;
	}
	public void setDelaiAnticolission(int delaiAnticolission) {
		this.delaiAnticolission = delaiAnticolission;
	}
	public int getTempsDecolage() {
		return tempsDecolage;
	}
	public void setTempsDecolage(int tempsDecolage) {
		this.tempsDecolage = tempsDecolage;
	}
	public int getTempsArretissage() {
		return tempsArretissage;
	}
	public void setTempsArretissage(int tempsArretissage) {
		this.tempsArretissage = tempsArretissage;
	}
	public int getBoucleAttVol() {
		return boucleAttVol;
	}
	public void setBoucleAttVol(int boucleAttVol) {
		this.boucleAttVol = boucleAttVol;
	}
	
}
