package thema.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import thema.service.ThemaService;
import thema.vo.Thema;

/**
 * Servlet implementation class ThemaWriteServlet
 */
@WebServlet(name = "ThemaWrite", urlPatterns = { "/themaWrite" })
public class ThemaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemaWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 작성 오류[enctype확인]");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}
		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "img/thema/";
		int maxSize = 10*1024*1024;
		
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		Thema t = new Thema();
		t.setThemaName(mRequest.getParameter("themaName"));
		t.setDifficult(Integer.parseInt(mRequest.getParameter("diff")));
		t.setThemaAddr(mRequest.getParameter("themaAddr"));
		t.setThemaContent(mRequest.getParameter("themaContent"));
		t.setFilepath(mRequest.getFilesystemName("upfile"));
		
		int result = new ThemaService().insertThema(t);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/themaList");
		if(result>0) {
			request.setAttribute("msg", "등록 성공!");
		}else {
			request.setAttribute("msg", "등록 실패");
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
