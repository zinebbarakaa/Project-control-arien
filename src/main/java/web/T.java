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


@WebServlet("/T")
public class T extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public T() {
        super();
        // TODO Auto-generated constructor stub
    }



    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GestionMetier gm= new GestionMetier();
		gm.init();
		ArrayList<Aeroport> aeroports = gm.AeroprtParPays(request.getParameter("pays"));
		request.setAttribute("aeroports", aeroports);
		request.setAttribute("pays", request.getParameter("pays"));

		this.getServletContext().getRequestDispatcher("/aeroportsDunPays.jsp").forward(request, response);
	}



	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
