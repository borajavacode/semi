package thema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import thema.vo.Thema;
import thema.vo.ThemaComment;

public class ThemaDao {

	public int insertThema(Connection conn, Thema t) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into thema values(THEMA_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getThemaName());
			pstmt.setString(2, t.getThemaAddr());
			pstmt.setInt(3, t.getDifficult());
			pstmt.setString(4, t.getThemaContent());
			pstmt.setString(5, t.getFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from thema";
		
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

	public ArrayList<Thema> morePhoto(Connection conn, int start, int end, String keyword, String type) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Thema> list = new ArrayList<Thema>();
		String query = "";
		int status = 0;
		if(keyword == "") {
			query = "SELECT * FROM (SELECT ROWNUM AS RNUM, T.* FROM (SELECT * FROM thema ORDER BY 1 DESC) T) WHERE RNUM BETWEEN ? AND ?";			
		}else {
			status = 1;
			if(type.equals("thema")) {
				query = "SELECT * FROM (SELECT ROWNUM AS RNUM, T.* FROM (SELECT * FROM thema where thema_name like ? ORDER BY 1 DESC) T) WHERE RNUM BETWEEN ? AND ?";
			}else if(type.equals("loc")) {
				query = "SELECT * FROM (SELECT ROWNUM AS RNUM, T.* FROM (SELECT * FROM thema where thema_addr like ? ORDER BY 1 DESC) T) WHERE RNUM BETWEEN ? AND ?";
			}else {
				query = "SELECT * FROM (SELECT ROWNUM AS RNUM, T.* FROM (SELECT * FROM thema where difficult = ? ORDER BY 1 DESC) T) WHERE RNUM BETWEEN ? AND ?";
				status = 2;
			}
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(status == 0) {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else if(status == 1) {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}else {
				pstmt.setString(1, keyword);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public Thema selectOneThema(Connection conn, int themaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Thema t = null;
		String query = "select * from thema where thema_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, themaNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				t = new Thema();
				t.setThemaNo(rset.getInt("thema_no"));
				t.setThemaName(rset.getString("thema_name"));
				t.setDifficult(rset.getInt("difficult"));
				t.setThemaAddr(rset.getString("thema_addr"));
				t.setThemaContent(rset.getString("thema_content"));
				t.setFilepath(rset.getString("filepath"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return t;
	}

	public int updateThema(Connection conn, Thema t) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update thema set thema_name=?, thema_addr=?, difficult=?, thema_content=?, filepath=? where thema_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getThemaName());
			pstmt.setString(2, t.getThemaAddr());
			pstmt.setInt(3, t.getDifficult());
			pstmt.setString(4, t.getThemaContent());
			pstmt.setString(5, t.getFilepath());
			pstmt.setInt(6, t.getThemaNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteThema(Connection conn, Thema t) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from thema where thema_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, t.getThemaNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<ThemaComment> selectThemaComment(Connection conn, int themaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ThemaComment> list = new ArrayList<ThemaComment>();
		String query = "select * from thema_comment where thema_ref=? order by 1 desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, themaNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ThemaComment tc = new ThemaComment();
				tc.setTcNo(rset.getInt("tc_no"));
				tc.setTcContent(rset.getString("tc_content"));
				tc.setTcDate(rset.getString("tc_date"));
				tc.setTcWriter(rset.getString("tc_writer"));
				tc.setThemaRef(rset.getInt("thema_ref"));
				list.add(tc);
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

	public int updateComment(Connection conn, int tcNo, String tcContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update thema_comment set tc_content=? where tc_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tcContent);
			pstmt.setInt(2, tcNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int tcNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from thema_comment where tc_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tcNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int searchTotalCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		int status = 0;
		String query = "";
		if(keyword == "") {
			query = "select count(*) as cnt from thema";
		}else {
			status = 1;
			if(type.equals("thema")) {
				query = "select count(*) as cnt from thema where thema_name like ?";
			}else if(type.equals("loc")) {
				query = "select count(*) as cnt from thema where thema_addr like ?";
			}else {
				query = "select count(*) as cnt from thema where difficult = ?";
				status = 2;
			}			
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(status == 1) {
				pstmt.setString(1, "%"+keyword+"%");
			}else if(status == 2) {
				pstmt.setString(1, keyword);
			}
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

	public int insertComment(Connection conn, ThemaComment tc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO THEMA_COMMENT VALUES(TC_SEQ.NEXTVAL, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tc.getTcContent());
			pstmt.setString(2, tc.getTcWriter());
			pstmt.setInt(3, tc.getThemaRef());
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
