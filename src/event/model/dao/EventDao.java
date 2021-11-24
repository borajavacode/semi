package event.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import event.model.vo.Event;
import event.model.vo.EventComment;

public class EventDao {

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from event";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update event set event_readcount = event_readcount+1 where event_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertEvent(Connection conn, Event e) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into event values(event_seq.nextval,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, e.getEventTitle());
			pstmt.setString(2, e.getEventContent());
			pstmt.setString(3, e.getEventWriter());
			pstmt.setString(4, e.getMainEvent());
			pstmt.setString(5, e.getEventFinishDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Event> moreEvent(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Event> list = new ArrayList<Event>();
		String query = "select * from(select rownum as rnum,e.* from(select * from event order by 1 desc)e) where rnum between ? and ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Event e = new Event();
				e.setEventNo(rset.getInt("event_no"));
				e.setEventTitle(rset.getString("event_title"));
				e.setEventContent(rset.getString("event_content"));
				e.setEventReadcount(rset.getInt("event_readcount"));
				e.setRegDate(rset.getString("reg_data"));
				e.setEventWriter(rset.getString("event_writer"));
				e.setMainEvent(rset.getString("main_event"));
				e.setEventFinishDate(rset.getString("event_finishdate"));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public Event selectOneEvent(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Event e = null;
		String query = "select * from event where event_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				e = new Event();
				e.setEventNo(rset.getInt("event_no"));
				e.setEventTitle(rset.getString("event_title"));
				e.setEventContent(rset.getString("event_content"));
				e.setEventReadcount(rset.getInt("event_readcount"));
				e.setRegDate(rset.getString("reg_data"));
				e.setEventWriter(rset.getString("event_writer"));
				e.setMainEvent(rset.getString("main_event"));
				e.setEventFinishDate(rset.getString("event_finishdate"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return e;
	}

	public ArrayList<EventComment> selectCommentList(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<EventComment> list = new ArrayList<EventComment>();
		String query = "select * from event_comment where ec_reg=? order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				EventComment ec = new EventComment();
				ec.setEcNo(rset.getInt("ec_no"));
				ec.setEcContent(rset.getString("ec_content"));
				ec.setEcDate(rset.getString("ec_date"));
				ec.setEcWriter(rset.getString("ec_writer"));
				ec.setEcReg(rset.getInt("ec_reg"));
				list.add(ec);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int insetComment(Connection conn, EventComment ec) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into event_comment values(ec_seq.nextval,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ec.getEcContent());
			pstmt.setString(2, ec.getEcWriter());
			pstmt.setInt(3, ec.getEcReg());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateComment(Connection conn, int ecNo, String ecContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update event_comment set ec_content=? where ec_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ecContent);
			pstmt.setInt(2, ecNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int ecNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from event_comment where ec_no =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ecNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateEvent(Connection conn, Event e) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update event set event_title=?,event_content=?,main_event=?,event_finishdate=? where event_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, e.getEventTitle());
			pstmt.setString(2, e.getEventContent());
			pstmt.setString(3, e.getMainEvent());
			pstmt.setString(4, e.getEventFinishDate());
			pstmt.setInt(5, e.getEventNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteEvent(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from event where event_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
