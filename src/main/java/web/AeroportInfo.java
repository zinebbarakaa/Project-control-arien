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

/**
 * Servlet implementation class Aeroport
 */
@WebServlet("/AeroportInfo")
public class AeroportInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AeroportInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GestionMetier gm= new GestionMetier();
		//gm.init();
		Aeroport aeroport = gm.AeroprtParId(Integer.parseInt(request.getParameter("id")));
		System.out.println(aeroport.toString());
		request.setAttribute("aeroport", aeroport);
		request.setAttribute("pays", request.getParameter("pays"));

		this.getServletContext().getRequestDispatcher("/aeroports.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
