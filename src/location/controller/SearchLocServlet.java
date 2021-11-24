package location.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import location.service.LocationService;
import location.vo.Location;

/**
 * Servlet implementation class SearchLocServlet
 */
@WebServlet(name = "SearchLoc", urlPatterns = { "/searchLoc" })
public class SearchLocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String areaInfo=request.getParameter("areaInfo");
		String keyword=request.getParameter("keyword1");
		System.out.println(areaInfo);
		System.out.println(keyword);
		if(areaInfo.equals("0")) {
			areaInfo="";
		}
		System.out.println(areaInfo);
		ArrayList<Location> list=new LocationService().selectSearchLoc(areaInfo,keyword);
		if(list.isEmpty()) {
			RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "검색 결과가 없습니다.");
			request.setAttribute("loc", "/location");
			view.forward(request, response);
			
		}else {
			RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/location/location.jsp");
			request.setAttribute("list", list);
			request.setAttribute("areaInfo", areaInfo);
			request.setAttribute("keyword", keyword);
			view.forward(request, response);			
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
