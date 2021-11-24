package faq.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FaqService;
import faq.model.vo.FaqCo;

/**
 * Servlet implementation class InsertCommentFaqServlet
 */
@WebServlet(name = "InsertCommentFaq", urlPatterns = { "/insertCommentFaq" })
public class InsertCommentFaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentFaqServlet() {
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
		FaqCo fc = new FaqCo();
		fc.setFcContent(request.getParameter("fcContent"));
		fc.setFcWriter(request.getParameter("fcWriter"));
		fc.setFaqRef(Integer.parseInt(request.getParameter("faqRef")));
		fc.setFcLevel(Integer.parseInt(request.getParameter("fcLevel")));
		//3. 비즈니스로직
		int result = new FaqService().insertComment(fc);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글 등록 성공!");
		}else {
			request.setAttribute("msg", "댓글 등록 실패!");
		}
		request.setAttribute("loc", "/faqView?faqNo="+fc.getFaqRef());
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
