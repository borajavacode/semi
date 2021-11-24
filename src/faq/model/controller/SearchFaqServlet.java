package faq.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FaqService;
import faq.model.vo.FaqPageData;

/**
 * Servlet implementation class SearchFaqServlet
 */
@WebServlet(name = "SearchFaq", urlPatterns = { "/searchFaq" })
public class SearchFaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFaqServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		int reqPage = 0;
		try {
			reqPage=Integer.parseInt(request.getParameter("reqPage"));
		} catch (NumberFormatException e) {
			reqPage=1;
		}
		//3.비즈니스로직
		FaqPageData fpd = new FaqService().searchFaq(reqPage,type,keyword);
		//4. 결과처리
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/faq/faqList.jsp");
		request.setAttribute("list", fpd.getList());
		request.setAttribute("pageNavi", fpd.getPageNavi());
		request.setAttribute("start", fpd.getStart());
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
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
