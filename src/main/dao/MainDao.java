package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import event.model.vo.Event;
import notice.model.vo.Notice;
import thema.vo.Thema;

public class MainDao {

	public ArrayList<Thema> popThema(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Thema> list = new ArrayList<Thema>();
		String query = "select tt.*,(select count(*) from thema_comment where thema_ref=tt.thema_no) as \"tc_count\" from (select rownum as rnum, t.* from (select * from thema order by thema_no desc)t)tt where rownum<=8";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Thema t = new Thema();
				t.setThemaNo(rset.getInt("thema_no"));
				t.setThemaName(rset.getString("thema_name"));
				t.setDifficult(rset.getInt("difficult"));
				t.setThemaAddr(rset.getString("thema_addr"));
				t.setThemaContent(rset.getString("thema_content"));
				t.setFilepath(rset.getString("filepath"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Event> mainEvent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Event> list = new ArrayList<Event>();
		String query = "select * from(select rownum as rnum,e.* from(select * from event order by 1 desc)e) where to_date(event_finishdate)>sysdate and rnum<=5";
		try {
			pstmt = conn.prepareStatement(query);
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
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Notice> mainNotice(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<>();
		String query = "select nn.*, (select count(*) from notice_comment where nc_reg=nn.notice_no) as \"nc_count\" from (select rownum as rnum, n.* from(select * from notice order by notice_no desc)n)nn where rnum<=5";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setNoticeReadCount(rset.getInt("notice_readcount"));
				n.setRegDate(rset.getString("reg_date"));
				n.setNoticeFilename(rset.getString("notice_filename"));
				n.setNoticeFilepath(rset.getString("notice_filepath"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNcCount(rset.getInt("nc_count"));
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
}
