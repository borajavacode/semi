package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

public class NoticeDao {

	public ArrayList<Notice> selectNoticeList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<>();
		String query = "select nn.*, (select count(*) from notice_comment where nc_reg=nn.notice_no) as \"nc_count\" from (select rownum as rnum, n.* from(select * from notice order by notice_no desc)n)nn where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from notice";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set notice_readcount = notice_readcount+1 where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Notice selectOneNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		String query = "select * from notice where notice_no = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setNoticeReadCount(rset.getInt("notice_readcount"));
				n.setRegDate(rset.getString("reg_date"));
				n.setNoticeFilename(rset.getString("notice_filename"));
				n.setNoticeFilepath(rset.getString("notice_filepath"));
				n.setNoticeWriter(rset.getString("notice_writer"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return n;
	}

	public ArrayList<NoticeComment> selectCommentList(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		String query = "select * from notice_comment where nc_reg=? order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setNcNo(rset.getInt("nc_no"));
				nc.setNcContent(rset.getString("nc_content"));
				nc.setNcDate(rset.getString("nc_date"));
				nc.setNcWriter(rset.getString("nc_writer"));
				nc.setNcReg(rset.getInt("nc_reg"));
				list.add(nc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice where notice_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set notice_title=?, notice_content=?,notice_filename=?,notice_filepath=? where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeFilename());
			pstmt.setString(4, n.getNoticeFilepath());
			pstmt.setInt(5, n.getNoticeNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertComment(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice_comment values(nc_seq.nextval,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nc.getNcContent());
			pstmt.setString(2, nc.getNcWriter());
			pstmt.setInt(3, nc.getNcReg());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice values(notice_seq.nextval,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeFilename());
			pstmt.setString(4, n.getNoticeFilepath());
			pstmt.setString(5, n.getNoticeWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateComment(Connection conn, int ncNo, String ncContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice_comment set nc_content=? where nc_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ncContent);
			pstmt.setInt(2, ncNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int ncNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice_comment where nc_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ncNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Notice> selectSearchNotice(Connection conn, int start, int end, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<>();
		String query = "";
		if (type.equals("title")) {
			query = "select * from (select rownum as rnum, n.*from(select * from notice where notice_title like ? order by notice_no desc)n) where rnum between ? and ?";
		} else {
			query = "select * from (select rownum as rnum, n.*from(select * from notice where notice_writer like ? order by notice_no desc)n) where rnum between ? and ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
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
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectTotalCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "";
		if (type.equals("title")) {
			query = "select count(*) as cnt from notice where notice_title like ?";
		} else {
			query = "select count(*) as cnt from notice where notice_writer like ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
