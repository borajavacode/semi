package location.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import location.service.LocationService;
import location.vo.Location;

/**
 * Servlet implementation class UpdateLocationServlet
 */
@WebServlet(name = "UpdateLocation", urlPatterns = { "/updateLocation" })
public class UpdateLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locName=request.getParameter("updateLocName");
		String locAddr=request.getParameter("updateLocAddr");
		String locPhone=request.getParameter("updateLocPhone");
		Location loc=new Location(locName, locAddr, locPhone);
		int result=new LocationService().updateLocation(loc);
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
