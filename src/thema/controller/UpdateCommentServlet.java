package thema.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thema.service.ThemaService;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet(name = "UpdateCommentTh", urlPatterns = { "/updateCommentTh" })
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int tcNo = Integer.parseInt(request.getParameter("tcNo"));
		int themaNo = Integer.parseInt(request.getParameter("themaNo"));
		String tcContent = request.getParameter("tcContent");
		
		int result = new ThemaService().updateComment(tcNo, tcContent);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정 성공");
		}else {
			request.setAttribute("msg", "수정 실패");
		}
		request.setAttribute("loc", "/themaView?themaNo="+themaNo);
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
