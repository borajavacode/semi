package board.model.controller;

import java.io.File;
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
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet(name = "BoardUpdate", urlPatterns = { "/boardUpdate" })
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "enctype오류");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}
		//1) 파일업로드 경로설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root +"upload/board";
		//2) 파일크기
		int maxSize = 10*1024*1024;
		//3)MultipartRequest로변환 
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		Board b = new Board();
		b.setBoardNo(Integer.parseInt(mRequest.getParameter("boardNo")));
		b.setBoardTitle(mRequest.getParameter("boardTitle"));
		b.setBoardContent(mRequest.getParameter("boardContent"));
		b.setDeadLine(mRequest.getParameter("deadLine"));
		//파일명저장
		b.setBoardFilename(mRequest.getOriginalFileName("upfile"));
		b.setBoardFilepath(mRequest.getFilesystemName("upfile"));
		//기존파일명
		String oldFilename=mRequest.getParameter("oldFilename");
		String oldFilepath=mRequest.getParameter("oldFIlepath");
		//삭제여부 판단용
		int status = Integer.parseInt(mRequest.getParameter("status"));
		if(status==2) {//기존파일 지움
			//기존파일 경로찾기
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			//기존파일 삭제
			delFile.delete();
		}else if(oldFilename != null) {
			b.setBoardFilename(oldFilename);
			b.setBoardFilepath(oldFilepath);
		}
		//3. 비즈니스로직
		int result = new BoardService().updateBoard(b);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "자유게시판 수정 완료");
		}else {
			request.setAttribute("msg", "수정 실패");
		}
		request.setAttribute("loc", "/boardView?boardNo="+b.getBoardNo());
		view.forward(request, response);
	}

}
