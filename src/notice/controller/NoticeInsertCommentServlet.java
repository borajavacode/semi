package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.NoticeComment;

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "NoticeInsertComment", urlPatterns = { "/noticeInsertComment" })
public class NoticeInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeInsertCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		NoticeComment nc = new NoticeComment();
		nc.setNcContent(request.getParameter("ncContent"));
		nc.setNcWriter(request.getParameter("ncWriter"));
		nc.setNcReg(Integer.parseInt(request.getParameter("ncReg")));
		// 3. 비즈니스로직
		int result = new NoticeService().insertComment(nc);
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			request.setAttribute("msg", "댓글등록성공!");
		} else {
			request.setAttribute("msg", "댓글등록실패");
		}
		request.setAttribute("loc", "/noticeView?noticeNo=" + nc.getNcReg());
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
