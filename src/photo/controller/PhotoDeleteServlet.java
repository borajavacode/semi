package photo.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photo.model.service.PhotoService;
import photo.model.vo.Photo;

/**
 * Servlet implementation class PhotoDeleteServlet
 */
@WebServlet(name = "PhotoDelete", urlPatterns = { "/photoDelete" })
public class PhotoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int photoNo=Integer.parseInt(request.getParameter("photoNo"));
		PhotoService service=new PhotoService();
		Photo p=service.getPhoto(photoNo);
		int result=service.deletePhoto(photoNo);
		RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(p.getPhotoFilepath()!=null) {
				String root=getServletContext().getRealPath("/");
				String file=root+"upload/photo/"+p.getPhotoFilepath();
				File delFile=new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제 완료");
			request.setAttribute("loc", "/photoList?reqPage=1");
		}else {
			request.setAttribute("msg", "삭제 실패");
			request.setAttribute("loc", "/photoView?photoNo="+photoNo);
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
