package board.model.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
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
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		//3. 비즈니스로직
		Board b = new BoardService().getOneBoard(boardNo);
		//4.결과 처리
		//파일위치 지정 
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/board/";
		String file = saveDirectory+b.getBoardFilepath();
		//서버 물리공간에서 서블릿을 파일 불러옴
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);//서브
		
		//클라이언트로 파일 보내주기
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		String lastFilename =""; //최종 다운로드 파일이름
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1 
				|| request.getHeader("user-agent").indexOf("Trident") !=-1;
		System.out.println("IE 여부: "+bool);
		if(bool) { //IE인경우
			lastFilename = URLEncoder.encode(b.getBoardFilename(),"UTF-8");
			lastFilename = lastFilename.replaceAll("\\\\", "%20"); //역슬래시를 $20로 인코딩
		}else {
			lastFilename = new String(b.getBoardFilename().getBytes("UTF-8"),"ISO-8859-1");
		}
		//파일 다운로드를위한 HTTP header설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+lastFilename);
		
		//파일전송
		while(true) {
			int read = bis.read();
			if(read!=-1){
				//내보내기
				bos.write(read);
			}else {
				break;
			}
		}
		bis.close();
		bos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
