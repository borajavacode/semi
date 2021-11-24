package board.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BoardPageData;

/**
 * Servlet implementation class SearchBoardServlet
 */
@WebServlet(name = "SearchBoard", urlPatterns = { "/searchBoard" })
public class SearchBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBoardServlet() {
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
		 String type = request.getParameter("type");
		 String keyword = request.getParameter("keyword");
		 int listFind = Integer.parseInt(request.getParameter("listFind"));
		 int reqPage= 0;
		 try {
			 reqPage= Integer.parseInt(request.getParameter("reqPage"));			
		} catch (NumberFormatException e) {
			reqPage=1;
		}
		//3. 비즈니스로직
		BoardPageData bpd = new BoardService().searchBoard(reqPage,type,keyword,listFind);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp");
		System.out.println(bpd.getList().size());
		request.setAttribute("list", bpd.getList());
		request.setAttribute("pageNavi", bpd.getPageNavi());
		request.setAttribute("start", bpd.getStart());
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
		request.setAttribute("listFind", listFind);
		System.out.println("keyword: "+keyword);
		System.out.println("type: "+type);
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
