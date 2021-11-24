package event.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.service.EventService;
import event.model.vo.Event;

/**
 * Servlet implementation class EventDeleteServlet
 */
@WebServlet(name = "EventDelete", urlPatterns = { "/eventDelete" })
public class EventDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int eventNo = Integer.parseInt(request.getParameter("eventNo"));
		EventService service = new EventService();
		Event e = service.getEvent(eventNo);
		int result = service.deleteEvent(eventNo);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			if (e.getMainEvent() != null) {
				String root = getServletContext().getRealPath("/");
				String file = root + "upload/event/" + e.getMainEvent();
				System.out.println(file);
				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제성공");
			request.setAttribute("loc", "/eventList");
		} else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("loc", "/eventView?eventNo=" + eventNo);
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
