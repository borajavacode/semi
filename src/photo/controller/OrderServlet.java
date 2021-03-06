package photo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photo.model.dao.PhotoDao;
import photo.model.service.PhotoService;
import photo.model.vo.PhotoPageData;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(name = "Order", urlPatterns = { "/order" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type=Integer.parseInt(request.getParameter("type"));
		int reqPage=0;
		try {
	        reqPage = Integer.parseInt(request.getParameter("reqPage"));
	     }catch(NumberFormatException e) {
	        System.out.println("reqPage μμΈλ°μ");
	        reqPage=1;
	     }
		 PhotoPageData ppd=new PhotoService().orderPhoto(reqPage,type);
		 RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/photo/photoList.jsp");
		request.setAttribute("list", ppd.getList());
		request.setAttribute("pageNavi", ppd.getPageNavi());
		request.setAttribute("start", ppd.getStart());
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
