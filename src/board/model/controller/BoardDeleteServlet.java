package board.model.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet(name = "BoardDelete", urlPatterns = { "/boardDelete" })
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
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
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		//3. 비즈니스로직
		BoardService service = new BoardService();
		Board b = service.getOneBoard(boardNo);
		int result = service.deleteBoard(boardNo);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(b.getBoardFilepath()!=null) {
				String root = getServletContext().getRealPath("/");
				String file = root+"upload/notice/"+b.getBoardFilepath();
				File delFile=new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제완료!");
			request.setAttribute("loc", "/boardList?reqPage=1&listFind=1");
		}else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("loc", "boardView?boardNo="+boardNo);
		}
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
