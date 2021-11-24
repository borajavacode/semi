package thema.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import thema.dao.ThemaDao;
import thema.vo.Thema;
import thema.vo.ThemaComment;

public class ThemaService {

	public int insertThema(Thema t) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ThemaDao().insertThema(conn, t);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int selectTotalCount() {
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new ThemaDao().selectTotalCount(conn);
		JDBCTemplate.close(conn);
		return totalCount;
	}

	public ArrayList<Thema> morePhoto(int start, String keyword, String type) {
		Connection conn = JDBCTemplate.getConnection();
		int length = 8; //한번에 추가로 가지고 올 게시물 수
		int end = start + length - 1;
		ArrayList<Thema> list = new ThemaDao().morePhoto(conn, start, end, keyword, type);
		JDBCTemplate.close(conn);
		return list;
	}

	public Thema selectOneThema(int themaNo) {
		Connection conn = JDBCTemplate.getConnection();
		Thema t = new ThemaDao().selectOneThema(conn, themaNo);
		JDBCTemplate.close(conn);
		return t;
	}

	public int updateThema(Thema t) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ThemaDao().updateThema(conn, t);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteThema(Thema t) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ThemaDao().deleteThema(conn, t);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<ThemaComment> selectThemaComment(int themaNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ThemaComment> list = new ThemaDao().selectThemaComment(conn, themaNo);
		JDBCTemplate.close(conn);
		return list;
	}

	public int updateComment(int tcNo, String tcContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ThemaDao().updateComment(conn, tcNo, tcContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteComment(int tcNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ThemaDao().deleteComment(conn, tcNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int searchTotalCount(String type, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new ThemaDao().searchTotalCount(conn, type, keyword);
		JDBCTemplate.close(conn);
		return totalCount;
	}

	public int insertComment(ThemaComment tc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ThemaDao().insertComment(conn, tc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


}
