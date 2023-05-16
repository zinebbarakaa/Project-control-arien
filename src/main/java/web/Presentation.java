package web;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import doa.Aeroport;
import metier.GestionMetier;



@WebServlet("/Presentation")
public class Presentation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Presentation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GestionMetier gm= new GestionMetier();
		gm.init();
	//	ArrayList<Aeroport> aeroports = gm.recupererListeAeroports();
		ArrayList<Aeroport> aeroports = gm.nbrAeroprtsPays();
		request.setAttribute("aeroports", aeroports);
		this.getServletContext().getRequestDispatcher("/aeroportsPays.jsp").forward(request, response);
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
