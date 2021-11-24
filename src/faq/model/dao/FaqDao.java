package faq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import faq.model.vo.Faq;
import faq.model.vo.FaqCo;

public class FaqDao {

	public int insertFaq(Connection conn, Faq f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into faq values(faq_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?,null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getFaqTitle());
			pstmt.setString(2, f.getFaqContent());
			pstmt.setInt(3, f.getFaqStatus());
			pstmt.setString(4, f.getFaqWriter());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Faq> selectFaqList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = new ArrayList<Faq>();
		String query = "SELECT FF.*,(select count (*) from FAQ_CO where FAQ_ref=FF.FAQ_NO) AS \"FAQ_COUNT\"FROM (SELECT ROWNUM AS RNUM, F.*FROM (SELECT * FROM FAQ ORDER BY FAQ_NO DESC)F)FF WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Faq f = new Faq();
				f.setFaqNo(rset.getInt("faq_no"));
				f.setFaqTitle(rset.getString("faq_title"));
				f.setFaqContent(rset.getString("faq_content"));
				f.setFaqWriter(rset.getString("faq_writer"));
				f.setRegDate(rset.getString("reg_date"));
				f.setFaqStatus(rset.getInt("faq_status"));
				f.setFilepath(rset.getString("filepath"));
				f.setFaqCount(rset.getInt("faq_count"));
				list.add(f);
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

	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query ="select count(*) as cnt from faq";
		try {
			pstmt= conn.prepareStatement(query);
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

	public Faq selectOneFaq(Connection conn, int faqNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Faq f = null;
		String query = "select * from faq where faq_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				f=new Faq();
				f.setFaqNo(rset.getInt("faq_no"));
				f.setFaqTitle(rset.getString("faq_title"));
				f.setFaqContent(rset.getString("faq_content"));
				f.setFaqWriter(rset.getString("faq_writer"));
				f.setRegDate(rset.getString("reg_date"));
				f.setFaqStatus(rset.getInt("faq_status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return f;
	}

	public ArrayList<FaqCo> selectCommentList(Connection conn, int faqNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FaqCo> list = new ArrayList<FaqCo>();
		String query = "select*from faq_co where faq_ref=? order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				FaqCo fc = new FaqCo();
				fc.setFaqCoNo(rset.getInt("faq_cono"));
				fc.setFcContent(rset.getString("fc_content"));
				fc.setFcWriter(rset.getString("fc_writer"));
				fc.setFcDate(rset.getString("fc_date"));
				fc.setFaqRef(rset.getInt("faq_ref"));
				fc.setFcLevel(rset.getInt("fc_level"));
				list.add(fc);
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

	public int deleteFaq(Connection conn, int faqNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from faq where faq_no=?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateFaq(Connection conn, Faq f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="update faq set faq_title=?, faq_content=?,faq_status=? where faq_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, f.getFaqTitle());
			pstmt.setString(2, f.getFaqContent());
			pstmt.setInt(3, f.getFaqStatus());
			pstmt.setInt(4, f.getFaqNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Faq> selectSearchFaq(Connection conn, int start, int end, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = new ArrayList<Faq>();
		String query ="";
		if(type.equals("title")) {
			query ="SELECT * FROM(SELECT  ROWNUM AS RNUM,F.*FROM (SELECT*FROM FAQ WHERE FAQ_TITLE LIKE ? ORDER BY FAQ_NO DESC)F) WHERE RNUM BETWEEN ? AND ?";
		}else {
			query ="SELECT * FROM(SELECT  ROWNUM AS RNUM,F.*FROM (SELECT*FROM FAQ WHERE FAQ_WRITER LIKE ? ORDER BY FAQ_NO DESC)F) WHERE RNUM BETWEEN ? AND ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Faq f =new Faq();
				f.setFaqNo(rset.getInt("faq_no"));
				f.setFaqTitle(rset.getString("faq_title"));
				f.setFaqContent(rset.getString("faq_content"));
				f.setFaqWriter(rset.getString("faq_writer"));
				f.setRegDate(rset.getString("reg_date"));
				f.setFaqStatus(rset.getInt("faq_status"));
				f.setFilepath(rset.getString("filepath"));
				list.add(f);
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

	public int selectTotalCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int result = 0;
		String query ="";
		if(type.equals("title")) {
			query="select count(*) as cnt from faq where faq_title like ?";
		}else {
			query="select count(*) as cnt from faq where faq_writer like ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result =rset.getInt("cnt");
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

	public int insertComment(Connection conn, FaqCo fc) {
		PreparedStatement pstmt =  null;
		int result =0;
		String query = "insert into faq_co values(faq_co_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fc.getFaqRef());
			pstmt.setString(2, fc.getFcWriter());
			pstmt.setString(3, fc.getFcContent());
			pstmt.setInt(4, fc.getFcLevel());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public int deleteCommentFc(Connection conn, int faqCoNo) {
		PreparedStatement pstmt=null;
		int result = 0;
		String query = "delete from faq_co where faq_cono=?";
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, faqCoNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateCommentFaq(Connection conn, int faqCoNo, String fcContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update faq_co set fc_content=? where faq_cono=?";
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setString(1, fcContent);
			pstmt.setInt(2, faqCoNo);
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
