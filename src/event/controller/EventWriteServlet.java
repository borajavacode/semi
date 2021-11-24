package event.controller;

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

import event.model.service.EventService;
import event.model.vo.Event;

/**
 * Servlet implementation class EventWriteServlet
 */
@WebServlet(name = "EventWrite", urlPatterns = { "/eventWrite" })
public class EventWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "게시물작성 오류");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/event";
		System.out.println("파일저장경로 : "+saveDirectory);
		int maxSize = 10*1024*1024;
		MultipartRequest mRequset = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
		Event e = new Event();
		e.setEventContent(mRequset.getParameter("eventContent"));
		e.setEventTitle(mRequset.getParameter("eventTitle"));
		e.setEventWriter(mRequset.getParameter("eventWriter"));
		e.setMainEvent(mRequset.getOriginalFileName("mainEvent"));
		e.setEventFinishDate(mRequset.getParameter("eventFinishDate"));
		int result = new EventService().insertEvent(e);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/eventList");
		if(result>0) {
			request.setAttribute("msg", "이벤트 등록 성공");
		}else {
			request.setAttribute("msg", "이벤트 등록 실패");
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
