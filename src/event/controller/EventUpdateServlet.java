package event.controller;

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

import event.model.service.EventService;
import event.model.vo.Event;

/**
 * Servlet implementation class EventUpdateServlet
 */
@WebServlet(name = "EventUpdate", urlPatterns = { "/eventUpdate" })
public class EventUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "오류");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}
		String root = getServletContext().getRealPath("/");		
		String saveDirectory = root + "upload/event";
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
		Event e = new Event();
		e.setEventNo(Integer.parseInt(mRequest.getParameter("eventNo")));
		e.setEventTitle(mRequest.getParameter("eventTitle"));
		e.setEventContent(mRequest.getParameter("eventContent"));
		e.setMainEvent(mRequest.getOriginalFileName("upfile"));
		e.setEventFinishDate(mRequest.getParameter("eventFinishDate"));
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		int status = Integer.parseInt(mRequest.getParameter("status"));
		if (status == 2) {
			File delFile = new File(saveDirectory + "/" + oldFilepath);
			delFile.delete();
		} else if (oldFilename != null) {
			e.setMainEvent(oldFilename);
		}
		int result = new EventService().updateEvent(e);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			request.setAttribute("msg", "이벤트 수정 완료");
		} else {
			request.setAttribute("msg", "이벤트 수정 실패");
		}
		request.setAttribute("loc", "/eventView?eventNo=" + e.getEventNo());
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
