package thema.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thema.dao.ThemaDao;
import thema.service.ThemaService;
import thema.vo.Thema;

/**
 * Servlet implementation class DeleteThemaServlet
 */
@WebServlet(name = "DeleteThema", urlPatterns = { "/deleteThema" })
public class DeleteThemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteThemaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int themaNo = Integer.parseInt(request.getParameter("themaNo"));
		ThemaService service = new ThemaService();
		Thema t = service.selectOneThema(themaNo);
		int result = service.deleteThema(t);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(t.getFilepath() != null) { //해당 게시글에 첨부파일이 있는 경우
				String root = getServletContext().getRealPath("/");
				String file = root + "img/thema/"+t.getFilepath();
				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제 성공");
			request.setAttribute("loc", "/themaList");
		}else {
			request.setAttribute("msg", "삭제 실패");
			request.setAttribute("loc", "/themaView?themaNo="+t.getThemaNo());
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
