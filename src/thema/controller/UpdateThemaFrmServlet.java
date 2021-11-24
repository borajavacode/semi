package thema.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thema.service.ThemaService;
import thema.vo.Thema;

/**
 * Servlet implementation class UpdateThemaFrmServlet
 */
@WebServlet(name = "UpdateThemaFrm", urlPatterns = { "/updateThemaFrm" })
public class UpdateThemaFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateThemaFrmServlet() {
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
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/thema/themaUpdateFrm.jsp");
		request.setAttribute("t", t);
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
