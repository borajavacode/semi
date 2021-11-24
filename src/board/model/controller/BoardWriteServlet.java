package board.model.controller;

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

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet(name = "BoardWrite", urlPatterns = { "/boardWrite" })
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
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
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			view.forward(request, response);
			return;
		}
		String root = getServletContext().getRealPath("/"); 
		String saveDirectory = root + "upload/board";
		int maxSize= 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		Board b= new Board();
		b.setBoardTitle(mRequest.getParameter("boardTitle"));
		b.setBoardWriter(mRequest.getParameter("boardWriter"));
		b.setBoardContent(mRequest.getParameter("boardContent"));
		b.setBoardLevel(Integer.parseInt(mRequest.getParameter("boardLevel")));
		b.setBoardFilename(mRequest.getOriginalFileName("upfile"));
		b.setBoardFilepath(mRequest.getFilesystemName("upfile"));
		b.setDeadLine(mRequest.getParameter("deadLine"));
		//3. 비즈니스로직
		int result = new BoardService().insertBoard(b);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/boardList?reqPage=1&listFind=1");
		if(result>0) {
			request.setAttribute("msg", "게시판 등록완료");
		}else {
			request.setAttribute("msg", "게시할 제목과 내용을 확인해주세요");
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
