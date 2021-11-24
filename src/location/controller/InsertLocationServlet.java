package location.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import location.service.LocationService;
import location.vo.Location;

/**
 * Servlet implementation class InputLocaionServlet
 */
@WebServlet(name = "InsertLocation", urlPatterns = { "/insertLocation" })
public class InsertLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertLocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Location loc=new Location();
		loc.setLocName(request.getParameter("locName"));
		loc.setLocAddr(request.getParameter("locAddr"));
		loc.setLocPhone(request.getParameter("locPhone"));
		int result=new LocationService().insertLocation(loc);
		RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "지점 추가 성공!");
		}else {
			request.setAttribute("msg", "지점 추가 실패");
		}
		request.setAttribute("loc", "/location");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
