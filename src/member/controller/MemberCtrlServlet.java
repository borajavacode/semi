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
 * Servlet implementation class MemberCtrlServlet
 */
@WebServlet(name = "MemberCtl", urlPatterns = { "/memberCtl" })
public class MemberCtrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCtrlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		//로직
		MemberPageDate mpd = new MemberService().selectAllMemberList(reqPage);
		int totalCount = new MemberService().totalCount();
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/memberCtrl.jsp");
		request.setAttribute("list", mpd.getList());
		request.setAttribute("pageNavi", mpd.getPageNavi());
		request.setAttribute("start", mpd.getStart());
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

