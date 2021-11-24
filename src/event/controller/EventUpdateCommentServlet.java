package event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.service.EventService;

/**
 * Servlet implementation class EventUpdateCommentServlet
 */
@WebServlet(name = "EventUpdateComment", urlPatterns = { "/eventUpdateComment" })
public class EventUpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventUpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ecNo = Integer.parseInt(request.getParameter("ecNo"));
		int eventNo = Integer.parseInt(request.getParameter("eventNo"));
		String ecContent = request.getParameter("ecContent");
		int result = new EventService().updateComment(ecNo,ecContent);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글수정완료");
		}else {
			request.setAttribute("msg", "댓글수정실패");
		}
		request.setAttribute("loc", "/eventView?eventNo="+eventNo);
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
