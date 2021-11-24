package reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import reservation.service.reservationService;
import reservation.vo.Reservation;
import reservation.vo.Thema;

/**
 * Servlet implementation class ThemaLoadServlet
 */
@WebServlet(name = "ThemaLoad", urlPatterns = { "/themaLoad" })
public class ThemaLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemaLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String point = request.getParameter("point");
		ArrayList<Thema> tList = new ArrayList<Thema>();
		
		//if(point =="") {
		//	tList = new reservationService().themaLoad();	
		//}else {
			
		//}
		tList = new reservationService().themaLoad(point);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		 
		
		new Gson().toJson(tList,out); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
