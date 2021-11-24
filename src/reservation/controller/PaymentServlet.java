package reservation.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservation.service.reservationService;
import reservation.vo.Reservation;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet(name = "Payment", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rvDate = request.getParameter("date");
		String rvPoint = request.getParameter("point");
		
		String rvThema = request.getParameter("thema");
		String rvTime = request.getParameter("time");
		String filepath = new reservationService().selectFilepath(rvThema);
		Reservation rv = new Reservation(0, null, null, null, rvDate, rvTime, rvThema, rvPoint,0);
		
		RequestDispatcher view =  request.getRequestDispatcher("WEB-INF/views/reservation/payment.jsp");
		request.setAttribute("rv", rv);
		request.setAttribute("filepath", filepath);
		
		
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
