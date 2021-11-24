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

/**
 * Servlet implementation class FaqUpdateServlet
 */
@WebServlet(name = "FaqUpdate", urlPatterns = { "/faqUpdate" })
public class FaqUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateServlet() {
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
		Faq f = new Faq();
		f.setFaqNo(Integer.parseInt(request.getParameter("faqNo")));
		f.setFaqTitle(request.getParameter("faqTitle"));
		f.setFaqStatus(Integer.parseInt(request.getParameter("faqStatus")));
		f.setFaqContent(request.getParameter("faqContent"));
		//3. 비즈니스로직
		int result = new FaqService().updateFaq(f);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "Q&A 수정 완료");
		}else {
			request.setAttribute("msg", "수정 실패");
		}
		request.setAttribute("loc", "/faqView?faqNo="+f.getFaqNo());
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
