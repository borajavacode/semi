package board.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BoardViewData;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet(name = "BoardView", urlPatterns = { "/boardView" })
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
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
		BoardViewData bvd = new BoardService().selectOneBoard(boardNo);
		//4. 결과처리
		if(bvd != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp");
			request.setAttribute("b", bvd.getB());
			request.setAttribute("list", bvd.getList());
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "조회오류");
			request.setAttribute("loc", "/boardList?reqPage=1");
			view.forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
