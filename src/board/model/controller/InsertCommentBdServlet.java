package board.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BdComment;

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "InsertCommentBd", urlPatterns = { "/insertCommentBd" })
public class InsertCommentBdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentBdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		BdComment bd = new BdComment();
		bd.setBdContent(request.getParameter("bdContent"));
		bd.setBdWriter(request.getParameter("bdWriter"));
		bd.setBoardRef(Integer.parseInt(request.getParameter("boardRef")));
		//3. 비즈니스 로직
		int result = new BoardService().insertComment(bd);
		System.out.println(bd.getBdWriter());
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글 등록 성공");
		}else {
			request.setAttribute("msg", "댓글 등록 실패");
		}
		request.setAttribute("loc", "/boardView?boardNo="+bd.getBoardRef());
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
