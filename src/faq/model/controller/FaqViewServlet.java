package faq.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FaqService;
import faq.model.vo.Faq;
import faq.model.vo.FaqViewData;

/**
 * Servlet implementation class FaqViewServlet
 */
@WebServlet(name = "FaqView", urlPatterns = { "/faqView" })
public class FaqViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. view에서 데이터 추출
		int faqNo = Integer.parseInt(request.getParameter("faqNo"));
		//3. 비즈니스로직
		FaqViewData fvd = new FaqService().selectOneFaq(faqNo);
		if(fvd != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/faq/faqView.jsp");
			request.setAttribute("f", fvd.getF());
			request.setAttribute("list", fvd.getList());
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "조회오류");
			request.setAttribute("loc", "/faqList?reqPage=1");
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
