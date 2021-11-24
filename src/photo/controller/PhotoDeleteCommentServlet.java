package photo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photo.model.service.PhotoService;

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet(name = "PhotoDeleteComment", urlPatterns = { "/photoDeleteComment" })
public class PhotoDeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoDeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pcNo=Integer.parseInt(request.getParameter("pcNo"));
		int photoNo=Integer.parseInt(request.getParameter("photoNo"));
		int result=new PhotoService().deleteComment(pcNo);
		RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글 삭제 성공");
		}else {
			request.setAttribute("msg", "댓글 삭제 실패");
		}
		request.setAttribute("loc", "/photoView?photoNo="+photoNo);
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
