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
 * Servlet implementation class FaqWriteServlet
 */
@WebServlet(name = "FaqWrite", urlPatterns = { "/faqWrite" })
public class FaqWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqWriteServlet() {
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
		Faq f=new Faq();
		f.setFaqTitle(request.getParameter("faqTitle"));
		f.setFaqWriter(request.getParameter("faqWriter"));
		f.setFaqContent(request.getParameter("faqContent"));
		f.setFaqStatus(Integer.parseInt(request.getParameter("faqStatus")));
		//3. 비즈니스 로직
		int result = new FaqService().insertFaq(f);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "faqList?reqPage=1");
		if(result>0) {
			request.setAttribute("msg", "질문 등록완료");
		}else {
			request.setAttribute("msg", "질문할 제목과 내용을 확인해 주세요");
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
