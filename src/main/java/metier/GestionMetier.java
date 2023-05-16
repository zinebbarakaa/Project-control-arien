package metier;

import java.sql.*

;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.lang.Math;

import doa.*;
import util.ConnectionSingleton;

public class GestionMetier {

	public static void ajouterAvion(Avion avion )
	{
	Connection con = ConnectionSingleton.getConnection();

		try {
			PreparedStatement pst=con.prepareStatement("insert into avion(idAvion,idAeroport,nom,type,CapaciteCarburant,distanceVolMax,croisiereAltitude,landingAltitude,cruisingSpeed,landingSpeed,fuelConsumptionPerSecond,photo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
			pst.setInt(1, avion.getIdAvion());
			pst.setInt(2, avion.getAeroportActuel().getIdAeroport());
			pst.setString(3, avion.getNom());
			pst.setString(4, avion.getType());
			pst.setInt(5, avion.getCapaciteCarburant());
			pst.setInt(6, avion.getDistanceVolMax());
			pst.setInt(7, avion.getCroisiereAltitude());
			pst.setInt(8, avion.getLandingAltitude());
			pst.setDouble(9, avion.getCruisingSpeed());
			pst.setDouble(10, avion.getLandingSpeed());
			pst.setDouble(11, avion.getFuelConsumptionPerSecond());
			pst.setString(12, avion.getPhoto());

			pst.executeUpdate();
			
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	public Map<String ,Double> recupererListeDistances(Aeroport a )
	{
	Connection con = ConnectionSingleton.getConnection();
	Map<String ,Double> distances = new HashMap<>();	
		try {
			PreparedStatement pst=con.prepareStatement("Select codeOACI,distance from route,aeroport where distance.aeroportArivee=aeroport.idAeroport and aeroportDepart=?;");
			pst.setInt(1, a.getIdAeroport());
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				distances.put(rs.getString(1), rs.getDouble(2));
			}
			
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return distances;
	}
	
	public static void ajouterAeroport(Aeroport a )
	{
	Connection con = ConnectionSingleton.getConnection();
		
		try {
			PreparedStatement pst=con.prepareStatement("insert into aeroport(idAeroport,codeOACI,nomAeroport,longtitude,latitude,nbrPiste,nbrStation,delaiAttSol,tempsAccesPiste,delaiAnticolission,tempsDecolage,tempsArretissage,boucleAttVol,photoPays,photo,pays) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			pst.setInt(1,a.getIdAeroport() );
			pst.setString(2, a.getCodeOACI());
			pst.setString(3, a.getNomAeroport());
			pst.setFloat(4, a.getLongtitude());
			pst.setFloat(5, a.getLatitude());
			pst.setInt(6, a.getNbrPiste());
			pst.setInt(7, a.getNbrStation());
			pst.setInt(8, a.getDelaiAttSol());
			pst.setInt(9, a.getTempsAccesPiste());
			pst.setInt(10, a.getDelaiAnticolission());
			pst.setInt(11, a.getTempsDecolage());
			pst.setInt(12, a.getTempsArretissage());
			pst.setInt(13, a.getBoucleAttVol());
			pst.setString(14, a.getPhotoPays());
			pst.setString(15, a.getPhoto());
			pst.setString(16, a.getPays());

			pst.executeUpdate();
			
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	// ajouter une distance 
	public static void ajouterRoute(Route d)
	{
		{
			Connection con = ConnectionSingleton.getConnection();
				
				try {
					PreparedStatement pst=con.prepareStatement("insert into route(aeroportDepart,aeroportArivee,distance,disponiblite) VALUES(?,?,?,?);");
					pst.setInt(1, d.getAeroportDepart().getIdAeroport());
					pst.setInt(2, d.getAeroportArivee().getIdAeroport());
					pst.setDouble(3,d.getDistance());
					pst.setInt(4, d.getDisponiblite());

					pst.executeUpdate();
					
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
		
	}
	
	public static double deg2rad(double deg )
	{
	      return deg * (Math.PI/180);
		
	}
	
	public double  distance(double long1,double lat1,double long2,double lat2 )
	{
		double long1R,long2R,lat1R,lat2R,midlat,midlong;
		double distance ;
		// conversion des valeurs de degre vers la radian 
		long1R=deg2rad(long1);
		long2R=deg2rad(long2);
		lat1R=deg2rad(lat1);
		lat2R=deg2rad(lat2);
		double latSin =  Math.sin((lat1R-lat2R)/2);
		double longSin =  Math.sin((long1R-long2R)/2);
		distance = 2*Math.asin(Math.sqrt(latSin*latSin+ Math.cos(lat1R)*Math.cos(lat1R)*(longSin*longSin)));
		distance =distance *6378.137;// le rayon de la terre 
		return distance;
		
	}
	
	public double distance2 (Coordinate c1,Coordinate c2 )
	{
	    int R = 6371    ;            // Radius of the earth in km
	    double  dLat = deg2rad(c1.getLat() - c2.getLat());
	    double dLng = deg2rad(c1.getLon() - c2.getLon());
	    double A =  Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(c1.getLat())) * Math.cos(deg2rad(c2.getLat())) * Math.sin(dLng/2) * Math.sin(dLng/2) ;
	    double C = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1-A)) ;
	    
	    double D = R * C ;                // Distance in km
	    return D;
	
	
	}
	public static double distance2 (double long1,double lat1,double long2,double lat2  )
	{
	    int R = 6371    ;            // Radius of the earth in km
	    double  dLat = deg2rad(lat1 - lat2);
	    double dLng = deg2rad(long1 - long2);
	    double A =  Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLng/2) * Math.sin(dLng/2) ;
	    double C = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1-A)) ;
	    
	    double D = R * C ;                // Distance in km
	    return D;
	
	
	}
	public ArrayList<ArrayList<Integer>> constriureGraph2()
	{
		Connection con = ConnectionSingleton.getConnection();
		int taille=0;
		PreparedStatement pst;
		ResultSet rs;
		ArrayList<ArrayList<Integer>> graph= new ArrayList<>();
		
		try {
				pst = con.prepareStatement("SELECT DISTINCT COUNT(idAeroport) FROM aeroport;");
				rs = pst.executeQuery();
			while(rs.next())
			{
				 taille = rs.getInt(1);
			}
			for(int i=1;i<=taille;i++)
			{
				pst = con.prepareStatement("Select * from route where aeroportDepart=?");
				pst.setInt(1, i);
				rs=pst.executeQuery();
				ArrayList<Integer> sgraph= new ArrayList<>();

					while(rs.next())
					{
						if(rs.getInt(4)==1)
						sgraph.add(0);
						else
						sgraph.add(rs.getInt(3));

					}
				graph.add(sgraph);
			}
			return graph;

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		
		
		
		
	}
	
	public int[][] Array2Table(ArrayList<ArrayList<Integer>> graph)
	{
		Object[][] mt = new Object[graph.size()][graph.get(0).size()];
		int [][] mt1 = new int [graph.size()][graph.get(0).size()];

		for(int i = 0 ; i < graph.size(); i++)
		 //copier les valeurs dans chaque ligne du tableau
		 mt[i] = graph.get(i).toArray();
		 
		for(int i=0; i < mt.length; i++){
		 for(int j=0; j < mt[i].length; j++)
		 {
			 mt1[i][j]=(int) mt[i][j];
			 }
		 }
		return mt1;

	}
	
	public ArrayList<Aeroport> recupererListeAeroports() 
	{
		Connection con = ConnectionSingleton.getConnection();
		ArrayList<Aeroport> aeroports = new ArrayList<>() ;
			try {
				PreparedStatement pst=con.prepareStatement("Select * from aeroport");
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					aeroports.add(new Aeroport(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getString(16)));

				}
				
			}
			 catch (SQLException e) {
			e.printStackTrace();
			}
			return aeroports;
	}
	public static Aeroport recupererAeroportById(int arrivalId) 
	{
		Connection con = ConnectionSingleton.getConnection();
		Aeroport aeroport = new Aeroport();
			try {
				PreparedStatement pst=con.prepareStatement("Select * from aeroport where idAeroport=?");
				pst.setInt(1, arrivalId);
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					aeroport= new Aeroport(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getString(16));

				}
				
			}
			 catch (SQLException e) {
			e.printStackTrace();
			}
			return aeroport;
	}
	
	public static Avion recupererAvionById(int id) 
	{
		Connection con = ConnectionSingleton.getConnection();
		Avion avion= new Avion();
		try {
				PreparedStatement pst=con.prepareStatement("SELECT idAvion,idAeroport,nom,type,CapaciteCarburant,distanceVolMax,"
						+ "croisiereAltitude,landingAltitude,cruisingSpeed,landingSpeed,fuelConsumptionPerSecond,photo"
						+ " FROM avion  WHERE avion.idAvion=?");
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					Aeroport a = new Aeroport();
					a.setIdAeroport(rs.getInt(2));
					
					 avion =new Avion(rs.getInt(1),a,rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getDouble(11),rs.getString(12));
					
				}
				
			}
			 catch (SQLException e) {
			e.printStackTrace();
			}
			return avion;
	}
	public ArrayList<Avion> recupererListeAvionsDuAer() 
	{
		Connection con = ConnectionSingleton.getConnection();
		ArrayList<Avion> avions = new ArrayList<>() ;
			try {
				PreparedStatement pst=con.prepareStatement("SELECT idAvion,idAeroport,nom,type,CapaciteCarburant,distanceVolMax,"
						+ "croisiereAltitude,landingAltitude,cruisingSpeed,landingSpeed,fuelConsumptionPerSecond"
						+ " FROM avion ,aeroport WHERE avion.id_aeroport=aeroport.id_aeroport");
				ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					Aeroport a = new Aeroport();
					a.setCodeOACI(rs.getString(2));
					
					avions.add(new Avion(rs.getInt(1),a,rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getDouble(12)));
					
				}
				
			}
			 catch (SQLException e) {
			e.printStackTrace();
			}
			return avions;
	}
	
	public ArrayList<Avion> recupererListeAvions() 
	{
		Connection con = ConnectionSingleton.getConnection();
		ArrayList<Avion> avions = new ArrayList<>() ;
			try {
				PreparedStatement pst=con.prepareStatement("SELECT *,Count(*)From avion Group By nom");
				ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					Aeroport a = new Aeroport();
					a.setIdAeroport(rs.getInt(2));
					
					avions.add(new Avion(rs.getInt(1),rs.getString(3),rs.getInt(13)));
					
				}
				
			}
			 catch (SQLException e) {
			e.printStackTrace();
			}
			return avions;
	}
	
	public static void init ()
	{
		Aeroport aeroport1 = new Aeroport(1,"GMMN","Casablanca Mohamed 5",-7.58997F,33.3675F,2,8,0,1200,0,120,120,0,"pays/Flag_of_Morocco.svg.png","aeroportsPho/GMMN.jpg","Maroc");
		Aeroport aeroport2 = new Aeroport(2,"LFPG","Paris-Charles de Gaulle",2.547925F,49.009691F,2,8,0,1200,0,120,120,0,"pays/france.png","aeroportsPho/LFPG.jpg","France");
		Aeroport aeroport3 = new Aeroport(3,"GMAD","Aéroport international Agadir-Al Massira", -9.4102F, 30.3299F,2,8,0,1200,0,120,120,0,"pays/Flag_of_Morocco.svg.png","aeroportsPho/GMAD.jpg","Maroc");
		Aeroport aeroport4 = new Aeroport(4,"LFPO","Aéroport de Paris-Orly", 2.401373F, 48.745193F,2,8,0,1200,0,120,120,0,"pays/france.png","aeroportsPho/LFPO.jpg","France");
		Aeroport aeroport5 = new Aeroport(5,"LIRF","Aéroport Léonard-de-Vinci de Rome Fiumicino", 12.246238F, 41.799887F,2,8,0,1200,0,120,120,0,"pays/Italie.png","aeroportsPho/LIRF.jpg","Italie");

		
		

		ajouterAeroport(aeroport1);
		ajouterAeroport(aeroport2);
		ajouterAeroport(aeroport3);
		ajouterAeroport(aeroport4);
		ajouterAeroport(aeroport5);
		Avion avion1 = new  Avion(1, aeroport1, "A340","moyen", 137000,15500, 12500, 80, 910 , 100,2);
		Avion avion2 = new Avion(2, aeroport2, "A340","moyen", 137000,15500, 12500, 80, 910 , 100,2);
		Avion avion3 = new Avion(3, aeroport2, "A320","court", 23000 ,6000, 12000, 300, 850 , 137,0.3);


		ajouterAvion(avion3);
		ajouterAvion(avion2);
		ajouterAvion(avion1);

		
		
		
		
		Route d1= new Route(aeroport5,aeroport1,distance2(aeroport5.getLongtitude(),aeroport5.getLatitude(),aeroport1.getLongtitude(), aeroport1.getLatitude()));
		d1.setDisponiblite(d1.getDistance());
        
		Route d2= new Route(aeroport5,aeroport2,distance2(aeroport5.getLongtitude(),aeroport5.getLatitude(),aeroport3.getLongtitude(), aeroport2.getLatitude()));
		d2.setDisponiblite(d2.getDistance());
		
		
		Route d3= new Route(aeroport5,aeroport3,distance2(aeroport5.getLongtitude(),aeroport5.getLatitude(),aeroport3.getLongtitude(), aeroport3.getLatitude()));
		d3.setDisponiblite(d3.getDistance());

		Route d4= new Route(aeroport5,aeroport4,distance2(aeroport5.getLongtitude(),aeroport5.getLatitude(),aeroport4.getLongtitude(), aeroport4.getLatitude()));
		d4.setDisponiblite(d4.getDistance());

		Route d5= new Route(aeroport5,aeroport5,distance2(aeroport5.getLongtitude(),aeroport5.getLatitude(),aeroport5.getLongtitude(), aeroport5.getLatitude()));
		d5.setDisponiblite(d5.getDistance());

		ajouterRoute(d1);
		ajouterRoute(d2);
		ajouterRoute(d3);
		ajouterRoute(d4);
		ajouterRoute(d5);
//		
		//
		Route d11= new Route(aeroport1,aeroport1,distance2(aeroport1.getLongtitude(),aeroport1.getLatitude(),aeroport1.getLongtitude(), aeroport1.getLatitude()));
		d11.setDisponiblite(d11.getDistance());
		
		Route d21= new Route(aeroport1,aeroport2,distance2(aeroport1.getLongtitude(),aeroport1.getLatitude(),aeroport2.getLongtitude(), aeroport2.getLatitude()));
        d21.setDisponiblite(d21.getDistance());		
		
        Route d31= new Route(aeroport1,aeroport3,distance2(aeroport1.getLongtitude(),aeroport1.getLatitude(),aeroport3.getLongtitude(), aeroport3.getLatitude()));
		d31.setDisponiblite(d31.getDistance());
		
		Route d41= new Route(aeroport1,aeroport4,distance2(aeroport1.getLongtitude(),aeroport1.getLatitude(),aeroport4.getLongtitude(), aeroport4.getLatitude()));
		d41.setDisponiblite(d41.getDistance());
		
		Route d51= new Route(aeroport1,aeroport5,distance2(aeroport1.getLongtitude(),aeroport1.getLatitude(),aeroport5.getLongtitude(), aeroport5.getLatitude()));
		d51.setDisponiblite(d51.getDistance());
		
		ajouterRoute(d11);
		ajouterRoute(d21);
		ajouterRoute(d31);
		ajouterRoute(d41);
		ajouterRoute(d51);
		
		Route d12= new Route(aeroport2,aeroport1,distance2(aeroport2.getLongtitude(),aeroport2.getLatitude(),aeroport1.getLongtitude(), aeroport1.getLatitude()));
		d12.setDisponiblite(d12.getDistance());        
		Route d22= new Route(aeroport2,aeroport2,distance2(aeroport2.getLongtitude(),aeroport2.getLatitude(),aeroport2.getLongtitude(), aeroport2.getLatitude()));
		d22.setDisponiblite(d22.getDistance());        
		
		
		Route d32= new Route(aeroport2,aeroport3,distance2(aeroport2.getLongtitude(),aeroport2.getLatitude(),aeroport3.getLongtitude(), aeroport3.getLatitude()));
		d32.setDisponiblite(d32.getDistance());        

		Route d42= new Route(aeroport2,aeroport4,distance2(aeroport2.getLongtitude(),aeroport2.getLatitude(),aeroport4.getLongtitude(), aeroport4.getLatitude()));
		d42.setDisponiblite(d42.getDistance());        

		Route d52= new Route(aeroport2,aeroport5,distance2(aeroport2.getLongtitude(),aeroport2.getLatitude(),aeroport5.getLongtitude(), aeroport5.getLatitude()));
		d52.setDisponiblite(d52.getDistance());        

		ajouterRoute(d12);
		ajouterRoute(d22);
		ajouterRoute(d32);
		ajouterRoute(d42);
		ajouterRoute(d52);
		
		Route d13= new Route(aeroport3,aeroport1,distance2(aeroport3.getLongtitude(),aeroport3.getLatitude(),aeroport1.getLongtitude(), aeroport1.getLatitude()));
		d13.setDisponiblite(d13.getDistance());
		
		Route d23= new Route(aeroport3,aeroport2,distance2(aeroport3.getLongtitude(),aeroport3.getLatitude(),aeroport2.getLongtitude(), aeroport2.getLatitude()));
		d23.setDisponiblite(d23.getDistance());        
		
		
		Route d33= new Route(aeroport3,aeroport3,distance2(aeroport3.getLongtitude(),aeroport3.getLatitude(),aeroport3.getLongtitude(), aeroport3.getLatitude()));
		d33.setDisponiblite(d33.getDistance());        

		Route d43= new Route(aeroport3,aeroport4,distance2(aeroport3.getLongtitude(),aeroport3.getLatitude(),aeroport4.getLongtitude(), aeroport4.getLatitude()));
		d43.setDisponiblite(d43.getDistance());        

		Route d53= new Route(aeroport3,aeroport5,distance2(aeroport3.getLongtitude(),aeroport3.getLatitude(),aeroport5.getLongtitude(), aeroport5.getLatitude()));
		d53.setDisponiblite(d53.getDistance());        

		ajouterRoute(d13);
		ajouterRoute(d23);
		ajouterRoute(d33);
		ajouterRoute(d43);
		ajouterRoute(d53);
		
		Route d14= new Route(aeroport4,aeroport1,distance2(aeroport4.getLongtitude(),aeroport4.getLatitude(),aeroport1.getLongtitude(), aeroport1.getLatitude()));
		d14.setDisponiblite(d14.getDistance());        

		Route d24= new Route(aeroport4,aeroport2,distance2(aeroport4.getLongtitude(),aeroport4.getLatitude(),aeroport2.getLongtitude(), aeroport2.getLatitude()));
		d24.setDisponiblite(d24.getDistance());        

		
		
		Route d34= new Route(aeroport4,aeroport3,distance2(aeroport4.getLongtitude(),aeroport4.getLatitude(),aeroport3.getLongtitude(), aeroport3.getLatitude()));
		d34.setDisponiblite(d34.getDistance());        


		Route d44= new Route(aeroport4,aeroport4,distance2(aeroport4.getLongtitude(),aeroport4.getLatitude(),aeroport4.getLongtitude(), aeroport4.getLatitude()));
		d44.setDisponiblite(d44.getDistance());        


		Route d54= new Route(aeroport4,aeroport5,distance2(aeroport4.getLongtitude(),aeroport4.getLatitude(),aeroport5.getLongtitude(), aeroport5.getLatitude()));
		d54.setDisponiblite(d54.getDistance());        


		ajouterRoute(d14);
		ajouterRoute(d24);
		ajouterRoute(d34);
		ajouterRoute(d44);
		ajouterRoute(d54);
		
	}
	
	public ArrayList<Aeroport> nbrAeroprtsPays()
	{
		Connection con = ConnectionSingleton.getConnection();
		ArrayList<Aeroport> aeroports = new ArrayList<>() ;
			try {
				PreparedStatement pst=con.prepareStatement("SELECT photoPays,pays ,COUNT(*) as number_of_airports FROM aeroport GROUP BY pays;\n");
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					aeroports.add(new Aeroport(rs.getString(1),rs.getString(2),rs.getInt(3)));

				}
				
			}
			 catch (SQLException e) {
			e.printStackTrace();
			}
			return aeroports;
	}
	public ArrayList<Aeroport> AeroprtParPays(String pays)
	{
		Connection con = ConnectionSingleton.getConnection();
		ArrayList<Aeroport> aeroports = new ArrayList<>() ;
			try {
				PreparedStatement pst=con.prepareStatement("SELECT *  FROM aeroport where pays=? ;\n");
				pst.setString(1, pays);
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					aeroports.add(new Aeroport(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getString(16)));

				}
				
			}
			 catch (SQLException e) {
			e.printStackTrace();
			}
			return aeroports;
	}
	public Aeroport AeroprtParId(int id)
	{
		Aeroport a= new Aeroport();
		Connection con = ConnectionSingleton.getConnection();
			try {
				PreparedStatement pst=con.prepareStatement("SELECT *  FROM aeroport where idAeroport=? ;\n");
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				rs.next();
					Aeroport b = new  Aeroport(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getString(16));
					a=b;
					return a;

				
			}
			 catch (SQLException e) {
			e.printStackTrace();
			}
			return null;
	}
	
	public  static ArrayList<Coordinate> getIntermediatePoints1(double lat1, double lon1, double lat2, double lon2, int nbPoints) {
        ArrayList<Coordinate> points = new ArrayList<Coordinate>();
        // convertir les degrés en radians
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // formule de Haversine pour le calcul de la distance
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double R = 6371; // rayon de la terre
        double distance = R * c;

        // calculer les intermédiaires points
        for (int i = 0; i < nbPoints; i++) {
            double f = (double) i / (nbPoints + 1);
            double A = Math.sin((1 - f) * c) / Math.sin(c);
            double B = Math.sin(f * c) / Math.sin(c);
            double x = A * Math.cos(lat1) * Math.cos(lon1) + B * Math.cos(lat2) * Math.cos(lon2);
            double y = A * Math.cos(lat1) * Math.sin(lon1) + B * Math.cos(lat2) * Math.sin(lon2);
            double z = A * Math.sin(lat1) + B * Math.sin(lat2);
            double lat = Math.atan2(z, Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
            double lon = Math.atan2(y, x);
            
            points.add(i,new Coordinate(Math.toDegrees(lat), Math.toDegrees(lon)));
        }
        points.add(0,new Coordinate(Math.toDegrees(lat1),Math.toDegrees(lon1)));
        points.add(new Coordinate(Math.toDegrees(lat2),Math.toDegrees(lon2)));
       
        return points;
    }
	public static ArrayList<Vol> generateRandomFlights(int numFlights) {
		
		Connection con = ConnectionSingleton.getConnection();
		ArrayList<Vol> flights = new ArrayList<>();
		Random rand = new Random();
		try {
            Statement stmt = con.createStatement();
            for (int i = 0; i < numFlights; i++) {
                // Get a random aircraft
                int aircraftId = rand.nextInt(3) + 1;
                Avion a =recupererAvionById(aircraftId);
                System.out.println("L'avion utilise est : "+ a.getNom());

                a.setFuelLevel(20000);
                // Get a random departure airport
                
                int departureId = rand.nextInt(5) + 1;
                Aeroport Ad= recupererAeroportById(departureId);
                System.out.println("L'avion decole de l'areoport "+ Ad.getNomAeroport());


                // Get a random arrival airport
                int arrivalId =1;
                while(arrivalId==departureId)
                {
                	arrivalId=rand.nextInt(5) + 1;
                }
                Aeroport Aa = recupererAeroportById(arrivalId);
                System.out.println("L'avion va atterrir  dans l'areoport "+ Aa.getNomAeroport());
                

                // Generate a random departure date and time
                String Date = "2022-01-01";
                String departureTime = String.format("%02d:%02d:%02d", rand.nextInt(24), rand.nextInt(60), rand.nextInt(60));

                // Generate a random arrival date and time
                String arrivalTime = String.format("%02d:%02d:%02d", rand.nextInt(24), rand.nextInt(60), rand.nextInt(60));

             // Insert the flight into the database
                stmt.executeUpdate("INSERT INTO vol (id_avion, aeroport_depart, aeroport_arrivee, date, departure_time, arrival_time) VALUES ('" + aircraftId + "', '" + departureId + ""
                		+ "', '" + arrivalId + "', '" + Date + "', '" + departureTime + "', '" + arrivalTime + "')");
             // Create a Flight object
                Vol flight = new Vol(a,Date, Ad, Aa, departureTime, arrivalTime);
                Ad.setAvionsEnVol(new LinkedBlockingQueue());
                Aa.setAvionsEnVol(new LinkedBlockingQueue());

                Ad.getAvionsEnVol().add(flight.getAvion());
                Aa.getAvionsEnVol().add(flight.getAvion());
                // Add the flight to the list
                flights.add(flight);
            }
            // Close the connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of flights
        return flights;


}
	public static List<Station> RecupereStationDunAeroport(int id)
	{
		Connection con = ConnectionSingleton.getConnection();
		List <Station> stations = new ArrayList();
			
		try {
			PreparedStatement pst=con.prepareStatement("SELECT * FROM station where id_aeroport=?;\n");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Aeroport a = new Aeroport();
				a.setIdAeroport(rs.getInt("id_aeroport"));
				stations.add(new Station(rs.getInt("id_station"),a,rs.getInt("numero"),rs.getBoolean("statut")));
			}
			
		}
		 catch (SQLException e) {
		e.printStackTrace();
		}
		return stations;
		
	}
	public static void ChangerStationEtat(int id)
	{
		Connection con = ConnectionSingleton.getConnection();
			
		try {
			PreparedStatement pst=con.prepareStatement("UPDATE  station set statut=1 where id_station =?");
			pst.setInt(1, id);
			 pst.executeUpdate();
			
		}
		 catch (SQLException e) {
		e.printStackTrace();
		}
		
	}
	
	public static List<Coordinate> getIntermediatePoints(double lat1, double lon1, double lat2, double lon2, int nbPoints) {
        // convertir les degrés en radians
        lat1 = (lat1 * Math.PI) / 180;
        lon1 = (lon1 * Math.PI) / 180;
        lat2 = (lat2 * Math.PI) / 180;
        lon2 = (lon2 * Math.PI) / 180;

        // formule de Haversine pour le calcul de la distance
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double R = 6371; // rayon de la terre
        double distance = R * c;

        // calculer les intermédiaires points
        List<Coordinate> points = new ArrayList<Coordinate>();
        for (int i = 1; i <= nbPoints; i++) {
            double f = i / (nbPoints + 1);
            double A = Math.sin((1 - f) * c) / Math.sin(c);
            double B = Math.sin(f * c) / Math.sin(c);
            double x = A * Math.cos(lat1) * Math.cos(lon1) + B * Math.cos(lat2) * Math.cos(lon2);
            double y = A * Math.cos(lat1) * Math.sin(lon1) + B * Math.cos(lat2) * Math.sin(lon2);
            double z = A * Math.sin(lat1) + B * Math.sin(lat2);
            double lat = Math.atan2(z, Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
            double lon = Math.atan2(y, x);
            points.add(new Coordinate((lat * 180) / Math.PI, (lon * 180) / Math.PI));
            }
            return points;
            }
	
  }

            
            
            
	
