package thema.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import thema.service.ThemaService;
import thema.vo.Thema;
import thema.vo.ThemaComment;

/**
 * Servlet implementation class ThemaViewServlet
 */
@WebServlet(name = "ThemaView", urlPatterns = { "/themaView" })
public class ThemaViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemaViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int themaNo = Integer.parseInt(request.getParameter("themaNo"));
		
		Thema t = new ThemaService().selectOneThema(themaNo);
		ArrayList<ThemaComment> list = new ThemaService().selectThemaComment(themaNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/thema/themaView.jsp");
		request.setAttribute("t", t);
		request.setAttribute("list", list);
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
