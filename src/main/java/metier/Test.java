package metier;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import doa.Aeroport;
import doa.Avion;
import doa.Coordinate;
import doa.Simulateur;
import doa.Station;
import doa.Vol;

public class Test {

	public static void main(String[] args) {
//		Aeroport A = new Aeroport();
//		A.getStationsEtat().put(new Station(), false);
//		Avion a = new Avion(1, A, "A340" ,"court", 24400,8000, 12000, 0, 32000, 1000,0.0,1.0);
//		Avion b = new Avion(1, A, "A320" ,"court", 24400,8000, 12000, 0, 32000, 1000,0.0,1.0);
//		
//		List<Avion> Avions= new ArrayList<>();
//		Avions.add(a);
//		Avions.add(b);
		
		ArrayList<Vol> vols = GestionMetier.generateRandomFlights(2);
		
		Simulateur sm = new Simulateur(vols);
		
		
		Thread simulationThread = new Thread(new Runnable() {
            @Override
            public void run() {
            	LocalDateTime takeoffTime = LocalDateTime.now();
            	// code de simulation
                sm.runSimulationVol();
                LocalDateTime landingTime = LocalDateTime.now();
            	Duration flightDuration = Duration.between(takeoffTime, landingTime);
            	System.out.println("Duration of flight: " + flightDuration.toSeconds() + " secondes");
            }
        });
        simulationThread.start();
        }
}


