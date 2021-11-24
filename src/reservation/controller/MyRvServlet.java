package reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import member.vo.Member;
import oracle.sql.CHAR;
import reservation.service.reservationService;
import reservation.vo.Reservation;

/**
 * Servlet implementation class MyRvServlet
 */
@WebServlet(name = "MyRv", urlPatterns = { "/myRv" })
public class MyRvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRvServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("m");
		String rvPhone = m.getPhone(); 
		
		
		String rvName = m.getMemberName();
		
		ArrayList<Reservation> rvList = new reservationService().myReservation(rvPhone,rvName);
		
		RequestDispatcher view =  request.getRequestDispatcher("WEB-INF/views/reservation/myRv.jsp");
		
		request.setAttribute("rvList", rvList);
		
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
