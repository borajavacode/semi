package photo.controller;

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
 * Servlet implementation class PhotoWriteServlet
 */
@WebServlet(name = "PhotoWrite", urlPatterns = { "/photoWrite" })
public class PhotoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoWriteServlet() {
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
		String root=getServletContext().getRealPath("/");
		String saveDirectory=root+"upload/photo";
		int maxSize=10*1024*1024;
		MultipartRequest mRequest=new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		Photo p=new Photo();
		p.setPhotoTitle(mRequest.getParameter("photoTitle"));
		p.setPhotoWriter(mRequest.getParameter("photoWriter"));
		p.setPhotoContent(mRequest.getParameter("photoContent"));
		p.setPhotoFilename(mRequest.getOriginalFileName("upfile"));
		p.setPhotoFilepath(mRequest.getFilesystemName("upfile"));
		int result=new PhotoService().insertPhoto(p);
		
		RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/photoList?reqPage=1");
		if(result>0) {
			request.setAttribute("msg", "등록 성공!");
		}else {
			request.setAttribute("msg", "게시글 작성 오류");
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
