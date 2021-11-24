package reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservation.vo.Reservation;



/**
 * Servlet implementation class MailSendServlet
 */
@WebServlet(name = "MailSend", urlPatterns = { "/mailSend" })
public class MailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rvEmail = request.getParameter("email");
		String rvThema = request.getParameter("thema");
		String rvTime = request.getParameter("time");
		String rvDate = request.getParameter("date");
		String rvPoint = request.getParameter("point");
		int rvPerson = Integer.parseInt(request.getParameter("person"));
		//수정해야함
		String rvName = request.getParameter("name");
		
		String rvPhone = request.getParameter("phone");
		String filepath = request.getParameter("filepath");
		Reservation rv = new Reservation(0, rvName, rvPhone, rvEmail, rvDate, rvTime, rvThema, rvPoint,rvPerson);
		int result = new MailSender().mailSend2(rvEmail,rv);
		
		
		PrintWriter out = response.getWriter();
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
