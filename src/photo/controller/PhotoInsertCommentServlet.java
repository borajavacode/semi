package photo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photo.model.service.PhotoService;
import photo.model.vo.PhotoComment;

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "PhotoInsertComment", urlPatterns = { "/photoInsertComment" })
public class PhotoInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PhotoComment pc=new PhotoComment();
		pc.setPcContent(request.getParameter("pcContent"));
		pc.setPcRef(Integer.parseInt(request.getParameter("pcRef")));
		pc.setPcWriter(request.getParameter("pcWriter"));
		int result=new PhotoService().insertComment(pc);
		RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글 등록 성공");
		}else {
			request.setAttribute("msg", "댓글 등록 실패");
		}
		request.setAttribute("loc", "/photoView?photoNo="+pc.getPcRef());
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
