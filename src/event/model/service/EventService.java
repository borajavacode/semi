package event.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import event.model.dao.EventDao;
import event.model.vo.Event;
import event.model.vo.EventComment;
import event.model.vo.EventViewData;

public class EventService {

	public int totalCount() {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventDao().totalCount(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertEvent(Event e) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventDao().insertEvent(conn,e);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Event> moreEvent(int start) {
		Connection conn = JDBCTemplate.getConnection();
		int length = 5;
		int end = start+length-1;
		ArrayList<Event> list = new EventDao().moreEvent(conn,start,end);
		JDBCTemplate.close(conn);
		return list;
	}

	public EventViewData selectOneEvent(int eventNo) {
		Connection conn = JDBCTemplate.getConnection();
		EventDao dao = new EventDao();
		int result = dao.updateReadCount(conn, eventNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Event e = dao.selectOneEvent(conn,eventNo);
		ArrayList<EventComment> list = dao.selectCommentList(conn,eventNo);
		EventViewData evd = new EventViewData(list, e);
		JDBCTemplate.close(conn);
		return evd;
	}

	public int insetComment(EventComment ec) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventDao().insetComment(conn,ec);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateComment(int ecNo, String ecContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventDao().updateComment(conn,ecNo,ecContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteComment(int ecNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventDao().deleteComment(conn,ecNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Event getEvent(int eventNo) {
		Connection conn = JDBCTemplate.getConnection();
		Event e = new EventDao().selectOneEvent(conn, eventNo);
		JDBCTemplate.close(conn);
		return e;
	}

	public int updateEvent(Event e) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventDao().updateEvent(conn,e);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteEvent(int eventNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventDao().deleteEvent(conn,eventNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
