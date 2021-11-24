package photo.controller;

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

import photo.model.service.PhotoService;
import photo.model.vo.Photo;

/**
 * Servlet implementation class PhotoUpdateServlet
 */
@WebServlet(name = "PhotoUpdate", urlPatterns = { "/photoUpdate" })
public class PhotoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "오류! ecptype을 확인하세요.");
			request.setAttribute("loc", "/WEB-INF/views/photo/photoList");
			view.forward(request, response);
			return;
		}
		// 업로드 경로 설정
		String root=getServletContext().getRealPath("/");
		String saveDirectory=root+"upload/photo";
		// 업로드 최대 크기 지정
		int maxSize=10*1024*1024;
		// request -> MultipartRequest로 변환(파일이 업로드 되는 시점)
		MultipartRequest mRequest=new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		Photo p=new Photo();
		p.setPhotoNo(Integer.parseInt(mRequest.getParameter("photoNo")));
		p.setPhotoTitle(mRequest.getParameter("photoTitle"));
		p.setPhotoContent(mRequest.getParameter("photoContent"));
		// 파일명 저장
		p.setPhotoFilename(mRequest.getOriginalFileName("upfile"));
		p.setPhotoFilepath(mRequest.getFilesystemName("upfile"));
		// 기존 파일 정보
		String oldFilename=mRequest.getParameter("oldFilename");
		String oldFilepath=mRequest.getParameter("oldFilepath");
		// 삭제 여부 판단 값
		int status=Integer.parseInt(mRequest.getParameter("status"));
		if(status==2) {	// 기존 파일 삭제
			File delFile=new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename!=null){
			p.setPhotoFilename(oldFilename);
			p.setPhotoFilepath(oldFilepath);
		}
		
		int result=new PhotoService().updatePhoto(p);
		RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/photoView?photoNo="+p.getPhotoNo());
		if(result>0) {
			request.setAttribute("msg", "수정 성공!");
		}else {
			request.setAttribute("msg", "게시글 수정 오류");
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
