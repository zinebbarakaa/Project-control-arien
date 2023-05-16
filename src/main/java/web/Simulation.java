package web;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doa.Simulateur;
import doa.Vol;
import metier.GestionMetier;




@WebServlet("/Simulation")
public class Simulation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
	
    public Simulation() {
        super();
    }



    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	ArrayList<Vol> vols = GestionMetier.generateRandomFlights(1);
		
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
        
		this.getServletContext().getRequestDispatcher("/simulation.jsp").forward(request, response);

	}



	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
