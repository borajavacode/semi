package event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.service.EventService;
import event.model.vo.EventComment;

/**
 * Servlet implementation class EventInsertCommentServlet
 */
@WebServlet(name = "EventInsertComment", urlPatterns = { "/eventInsertComment" })
public class EventInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventComment ec = new EventComment();
		ec.setEcContent(request.getParameter("ecContent"));
		ec.setEcWriter(request.getParameter("ecWriter"));
		ec.setEcReg(Integer.parseInt(request.getParameter("ecReg")));
		int result = new EventService().insetComment(ec);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글등록성공!");
		}else {
			request.setAttribute("msg", "댓글등록실패!");
		}
		request.setAttribute("loc", "/eventView?eventNo="+ec.getEcReg());
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
