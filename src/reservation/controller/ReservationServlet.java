package reservation.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import reservation.vo.Thema;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet(name = "Reservation", urlPatterns = { "/reservation" })
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("m");
		if(m!=null) {
			ArrayList<Thema> tList = new reservationService().themaLoad();
			
			RequestDispatcher view =  request.getRequestDispatcher("WEB-INF/views/reservation/reservation.jsp");
			request.setAttribute("tList", tList);
			if(request.getParameter("tm")!=null) {
				String tm = request.getParameter("tm");
				String pt = request.getParameter("pt");
				request.setAttribute("tm", tm);
				request.setAttribute("pt", pt);
			}
			view.forward(request, response);
		}else {
			RequestDispatcher view =  request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "로그인 하고 가실게요 ^^");
			request.setAttribute("loc", "/loginFrm");
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
