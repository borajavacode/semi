package board.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardUploadImageServlet
 */
@WebServlet(name = "BoardUploadImage", urlPatterns = { "/boardUploadImage" })
public class BoardUploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUploadImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값 추출
		//파일 업로드
		String root = getServletContext().getRealPath("/");
		String saveDriectory = root+"upload/boardImage";
		int maxSize = 10* 1024 * 1024;
		MultipartRequest mRequest = new MultipartRequest(request,saveDriectory,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		String filepath = mRequest.getFilesystemName("file");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print("/upload/boardImage/"+filepath);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
