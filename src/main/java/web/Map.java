package web;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doa.*;
import metier.GestionMetier;


@WebServlet("/Map")
public class Map extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Map() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GestionMetier gm= new GestionMetier();
		String aeroportD = request.getParameter("arpDep");
		String aeroportA = request.getParameter("arpArv");
		Vol vol = new Vol();
		ArrayList<Aeroport> chemin = vol.calculateRoute(aeroportD, aeroportA);
		for(Aeroport a:chemin)
		{
			System.out.println("kkk");
			System.out.println(a.getLatitude());
		}
		request.setAttribute("chemin", chemin);
		this.getServletContext().getRequestDispatcher("/simulationAvion.jsp").forward(request, response);
	}

}
