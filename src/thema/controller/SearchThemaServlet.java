package thema.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thema.service.ThemaService;
import thema.vo.Thema;

/**
 * Servlet implementation class SearchThemaServlet
 */
@WebServlet(name = "SearchThema", urlPatterns = { "/searchThema" })
public class SearchThemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchThemaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("type");
		
		int totalCount = new ThemaService().searchTotalCount(type, keyword);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/thema/themaList.jsp");
		request.setAttribute("keyword", keyword);
		request.setAttribute("type", type);
		request.setAttribute("totalCount", totalCount);
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
