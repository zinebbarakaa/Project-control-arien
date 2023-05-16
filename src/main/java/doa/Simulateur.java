package doa;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import metier.GestionMetier;

public class Simulateur {
	 private List<Avion> airplanes;
	 private List<Vol> vols;
	private long startTime; 
    private long currentTime;
    private boolean flightEnd;
    private final int timeStep = 1000; // time step in milliseconds
    private final int maxSimulationTime = 20; // maximum simulation time in 
    public Simulateur(List<Vol> vols) {
        this.startTime = System.currentTimeMillis();
    	//this.airplanes=airplanes;
    	this.vols=vols;
    	airplanes = new CopyOnWriteArrayList<>();

    	
    	
    	
    }
//    public void run(int minutes) {
//        System.out.println("Taxiing to the runway...");
//        System.out.println("Vitesse"+"       Fuel          Altitude");
//     //   avion.setFuelLevel(12000);
//        avion.alertFuelLow();
//        // simulate takeoff
//        avion.ajouterAvionPiste(avion);
//        avion.takeOff(avion);
//    	System.out.println("L'avion est en vol ");
//
//        for (int i = 0; i < minutes; i++) {
//        	avion.setAltitude(32000);
//        	System.out.print(avion.getAltitude()+"       ");
//        //	avion.updateVelocityByAltitude(32000);
//        	System.out.println(avion.getVitesse()+"       ");
//        	avion.consumeFuel(avion.getVitesse()); // use current velocity to update fuel consumption
//     
//        	avion.alertFuelLow();
//            if(avion.getFuelLevel()<=0) {
//              break;
//            }
//        }
//        if(avion.getFuelLevel()>0) {
//        	avion.land(avion);
//            System.out.println(" \n Flight completed successfully!");
//        } else {
//            System.out.println("Out of fuel, landing now!");
//        }
//    }
//    
//    
//    public void runSimulation1() {
//    	avion.ajouterAvionPiste(avion);
//    	avion.takeOff(avion);
//    	System.out.println("L'avion est en vol ");
//        while(!flightEnd){
//            // simulate the flight 
//        	
//            currentTime = System.currentTimeMillis(); 
//            long elapsedTime = (currentTime - startTime)/1000; // in seconds
//            if(elapsedTime > maxSimulationTime){
//                flightEnd = true;
//                break;
//            }
//            avion.alertFuelLow();
//            // update the flight parameters
//           	avion.setAltitude(32000);
//        	System.out.print(avion.getAltitude()+"       ");
//        	avion.consumeFuel(avion.getVitesse()); // use current velocity to update fuel consumption
//        //	avion.updateVelocityByAltitude(32000);
//        	System.out.println(avion.getVitesse()+"       ");
//        	avion.alertFuelLow();
//            if(avion.getFuelLevel()<=0) {
//              break;
//            } 
//       
//            try{
//                Thread.sleep(timeStep);
//            }catch(InterruptedException e){
//            	System.out.println(e);            }
//        }
//        if(avion.getFuelLevel()>0) {
//        	avion.land(avion);
//            System.out.println(" \n Flight completed successfully!");
//        } else {
//            System.out.println("Out of fuel, landing now!");
//        }
//    }
//    
    public void runSimulationVol() {
    	List<Thread> threads = new ArrayList<>();


        for (Vol vol : vols) {
        	
            Thread thread = new Thread(() -> {
            	int i=0;
            	List<Coordinate> path = GestionMetier.getIntermediatePoints1(vol.getAeroportDepart().getLatitude(), vol.getAeroportDepart().getLongtitude(),
            			vol.getAeroportArrivee().getLatitude(), vol.getAeroportArrivee().getLongtitude(), maxSimulationTime-1);
            	
            	
            	Avion avion = vol.getAvion();
            	avion.ajouterAvionPiste(avion);
            	avion.takeOff(avion, i, path);
            	System.out.println("L'avion  "+avion.getNom()+"  est en vol ");
  
            	while(!flightEnd){
                    // simulate the flight 
                    currentTime = System.currentTimeMillis(); 
                    long elapsedTime = (currentTime - startTime)/1000; // in seconds
                    if(elapsedTime > maxSimulationTime){
                        flightEnd = true;
                        break;
                    }
                    avion.alertFuelLow();
                    // update the flight parameters
                   	avion.setAltitude(32000);
                   	
                	System.out.print(avion.getNom()+"   "+avion.getAltitude()+"m       ");
                //	avion.updateVelocityByAltitude(32000);
                	System.out.print(avion.getVitesse()+"km/h       ");
                	avion.consumeFuel(avion.getVitesse()); // use current velocity to update fuel consumption

            		System.out.println(path.get(i).getLat() +" , " +path.get(i).getLon() );
                	i++;

                	avion.alertFuelLow();
                    if(avion.getFuelLevel()<=0) {
                      break;
                      
                    } 
                    for (Avion otherAvion : airplanes) {
                        if (avion != otherAvion && ConflictDetector.isConflict(avion, otherAvion)) {
                            // handle the conflict 
                        	ConflictDetector.resolveConflict(avion, otherAvion);
                        }
                    }
               
                    try{
                    	
                        Thread.sleep(timeStep);

                    }catch(InterruptedException e){
                    	System.out.println(e);            }
                }
            	if(avion.getFuelLevel()>0) {
                	avion.land(vol, path, maxSimulationTime);
                } else {
                    System.out.println("Out of fuel, landing now!");
                }
                airplanes.remove(avion);

            });
            
            thread.start();
            threads.add(thread);
        }
        for (Thread t : threads) {
            try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
//    public void runSimulation() {
//        for (Avion avion : airplanes) {
//            Thread thread = new Thread(() -> {
//            	avion.ajouterAvionPiste(avion);
//            	avion.takeOff(avion);
//            	System.out.println("L'avion  "+avion.getNom()+"  est en vol ");
//            	
//            	while(!flightEnd){
//                    // simulate the flight 
//                	
//                    currentTime = System.currentTimeMillis(); 
//                    long elapsedTime = (currentTime - startTime)/1000; // in seconds
//                    if(elapsedTime > maxSimulationTime){
//                        flightEnd = true;
//                        break;
//                    }
//                    avion.alertFuelLow();
//                    // update the flight parameters
//                   	avion.setAltitude(32000);
//                	System.out.print(avion.getNom()+"   "+avion.getAltitude()+"       ");
//                	avion.consumeFuel(avion.getVitesse()); // use current velocity to update fuel consumption
//                //	avion.updateVelocityByAltitude(32000);
//                	System.out.println(avion.getVitesse()+"       ");
//                	avion.alertFuelLow();
//                    if(avion.getFuelLevel()<=0) {
//                      break;
//                    } 
//               
//                    try{
//                        Thread.sleep(timeStep);
//                    }catch(InterruptedException e){
//                    	System.out.println(e);            }
//                }
//            	if(avion.getFuelLevel()>0) {
//                	avion.land(avion);
//                    System.out.println(" \n "+avion.getNom()+"   Flight completed successfully!");
//                } else {
//                    System.out.println("Out of fuel, landing now!");
//                }
//                airplanes.remove(avion);
//            });
//            thread.start();
//        }
//    }
    
}
