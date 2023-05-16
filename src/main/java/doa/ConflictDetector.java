package doa;

import java.util.Random;

import metier.GestionMetier;

public class ConflictDetector {

	private Avion[] avions;
	
	private static GestionMetier gm = new GestionMetier();
	public Avion[] getAvions()
	{
		return avions;
	}

	public void setAvions(Avion[] avions) {
		this.avions = avions;
	}
	public void detectConflict() {
        for (int i = 0; i < avions.length; i++) {
            for (int j = i + 1; j < avions.length; j++) {
                if (isConflict(avions[i], avions[j])) {
                    resolveConflict(avions[i], avions[j]);
                }
            }
        }
    }

	static void resolveConflict(Avion a1, Avion a2) {
		final int ALTITUDE_CHANGE = 1000; // in feet
	    // random number generator
	    Random rand = new Random();

	    // choose randomly which airplane to change
	    if (rand.nextBoolean()) {
	        // change altitude of a1
	        a1.setAltitude(a1.getAltitude() + ALTITUDE_CHANGE);
	    } else {
	        // change heading of a1
	        a2.setAltitude(a1.getAltitude() + ALTITUDE_CHANGE);
	    }
	    //log the action taken
	    System.out.println("Conflict resolved: airplane " + a1.getNom() + " changed altitude to avoid collision with airplane " + a2.getNom());
	}

	static boolean isConflict(Avion a1, Avion a2) {
		final int DISTANCE_THRESHOLD = 1000; // in meters
	    final int SPEED_THRESHOLD = 50; // in knots

	    // Calculate distance between a1 and a2
	    double distance = gm.distance2(a1.getPosition(), a2.getPosition());
	    // Calculate heading difference between a1 and a2

	    // Calculate speed difference between a1 and a2
	    double speedDiff = Math.abs(a1.getVitesse() - a2.getVitesse());

	    // Compare calculated values with threshold values
	    if (distance < DISTANCE_THRESHOLD  && speedDiff < SPEED_THRESHOLD) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
}
