package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.vo.MemberPageDate;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet(name = "SearchMember", urlPatterns = { "/searchMember" })
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		int reqPage = 0;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		} catch (NumberFormatException e) {
			System.out.println("reqPage 예외발생");
			reqPage =1;
		}
		MemberPageDate mpd = new MemberService().searchMember(reqPage,type,keyword);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/searchMember.jsp");
		request.setAttribute("list", mpd.getList());
		request.setAttribute("pageNavi", mpd.getPageNavi());
		request.setAttribute("start", mpd.getStart());
		//검색키워드 남기기 위해서 type,keyword를 넘긴다.
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
