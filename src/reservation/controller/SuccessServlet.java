package reservation.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.vo.Member;
import reservation.service.reservationService;
import reservation.vo.Reservation;

/**
 * Servlet implementation class SucessServlet
 */
@WebServlet(name = "Success", urlPatterns = { "/success" })
public class SuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("m");
		String rvThema = request.getParameter("thema");
		String rvTime = request.getParameter("time");
		String rvDate = request.getParameter("date");
		String rvPoint = request.getParameter("point");
		int rvPerson = Integer.parseInt(request.getParameter("person"));
		
		String rvName = m.getMemberName();
		String rvEmail = m.getEmail();
		System.out.println(rvName+" 예약완료");
		String rvPhone = m.getPhone();
		String filepath = request.getParameter("filepath");
		Reservation rv = new Reservation(0, rvName, rvPhone, rvEmail, rvDate, rvTime, rvThema, rvPoint,rvPerson);
		int result = new reservationService().insertReservtion(rv);
		
		if(result>0) {
			RequestDispatcher view =  request.getRequestDispatcher("WEB-INF/views/reservation/success.jsp");
			request.setAttribute("rv", rv);
			request.setAttribute("filepath", filepath);
			view.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
