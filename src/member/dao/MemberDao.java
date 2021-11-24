package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.vo.AdminChart;
import member.vo.Member;

public class MemberDao {

	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setAddr(rset.getString("addr"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setMemberNo(rset.getInt("member_no"));
				member.setEmail(rset.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public Member selectOneMember(Connection conn, String memberId, String memberPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id=? and member_pw=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setAddr(rset.getNString("addr"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setMemberNo(rset.getInt("member_no"));
				member.setEmail(rset.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MEMBER VALUES(?,?,?,?,?,2,to_char(sysdate,'yyyy-mm-dd'),member_seq.nextval,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddr());
			pstmt.setString(6, m.getEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member findOneId(Connection conn, String memberName, String phone) {
		PreparedStatement pstmt=null;
		ResultSet rset =null;
		Member member=null;
		String query = "select member_id from member where member_name=? and phone=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			pstmt.setString(2, phone);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				member= new Member();
				member.setMemberId(rset.getString("member_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public Member findOnePw(Connection conn, String memberId, String phone) {
		PreparedStatement pstmt=null;
		ResultSet rset =null;
		Member member=null;
		String query = "select member_pw from member where member_id=? and phone=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, phone);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				member= new Member();
				member.setMemberPw(rset.getString("member_pw"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw=?,phone=?,email=?,addr=? where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddr());
			pstmt.setString(5, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		/*String query = "select * from member";*/
		String query = "select * from member ORDER by member_no desc";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setAddr(rset.getString("addr"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setMemberNo(rset.getInt("member_no"));
				m.setEmail(rset.getString("email"));
				list.add(m);
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

	public ArrayList<Member> selectMemberList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT NN.* FROM"
				+ "(SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM MEMBER ORDER BY MEMBER_NO DESC)N)"
				+ "NN WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setAddr(rset.getString("addr"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setMemberNo(rset.getInt("member_no"));
				m.setEmail(rset.getString("email"));
				list.add(m);
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
		String query = "SELECT COUNT(*)AS CNT FROM MEMBER";
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

	public ArrayList<Member> selectSearchMember(Connection conn, int start, int end, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "";
		if(type.equals("id")) {
			query = "SELECT * FROM(SELECT ROWNUM AS RNUM, N.* FROM "
					+ "(SELECT * FROM MEMBER where member_Id like ? ORDER BY MEMBER_NO DESC)N)WHERE RNUM BETWEEN ? AND ?";
		}else {
			query = "SELECT * FROM(SELECT ROWNUM AS RNUM, N.* FROM "
					+ "(SELECT * FROM MEMBER where phone like ? ORDER BY MEMBER_NO DESC)N)WHERE RNUM BETWEEN ? AND ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setAddr(rset.getString("addr"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setMemberNo(rset.getInt("member_no"));
				m.setEmail(rset.getString("email"));
				list.add(m);
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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "";
		if(type.equals("id")) {
			query = "select count(*) as cnt from member where member_Id like ?";
		}else {
			query = "select count(*) as cnt from member where phone like ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
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

	public ArrayList<Member> memberNoList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM "
				+ "(SELECT * FROM MEMBER ORDER BY MEMBER_NO)N) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setAddr(rset.getString("addr"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setMemberNo(rset.getInt("member_no"));
				m.setEmail(rset.getString("email"));
				list.add(m);
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

	public AdminChart selectAdminChart(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		AdminChart chart = null;
		String query = "SELECT (SELECT COUNT(*)AS MONTH FROM FAQ WHERE REG_DATE >= TO_CHAR(SYSDATE-30,'YYYY-MM-DD'))AS FAQ_MONTH,(SELECT COUNT(*) WEEK FROM FAQ WHERE REG_DATE >= TO_CHAR(SYSDATE-7,'YYYY-MM-DD'))AS FAQ_WEEK,\r\n" + 
				"(SELECT COUNT(*)AS TODAY FROM FAQ WHERE REG_DATE >= TO_CHAR(SYSDATE,'YYYY-MM-DD'))AS FAQ_TODAY,(SELECT COUNT(*)AS TOTAL_FAQ FROM FAQ)AS TOTAL_FAQ,\r\n" + 
				"(SELECT COUNT(*)AS MONTH FROM NOTICE WHERE REG_DATE >= TO_CHAR(SYSDATE-30,'YYYY-MM-DD'))AS NO_MONTH,(SELECT COUNT(*) WEEK FROM NOTICE WHERE REG_DATE >= TO_CHAR(SYSDATE-7,'YYYY-MM-DD'))AS NO_WEEK,\r\n" + 
				"(SELECT COUNT(*)AS TODAY FROM NOTICE WHERE REG_DATE >= TO_CHAR(SYSDATE,'YYYY-MM-DD'))AS NO_TODAY,(SELECT COUNT(*)AS TOTAL_NO FROM NOTICE)AS TOTAL_NO, \r\n" + 
				"(SELECT COUNT(*) AS MONTH FROM MEMBER WHERE ENROLL_DATE >= TO_CHAR(SYSDATE-30,'YYYY-MM-DD'))AS MEM_MONTH,(SELECT COUNT(*) WEEK FROM MEMBER WHERE ENROLL_DATE >= TO_CHAR(SYSDATE-7,'YYYY-MM-DD'))AS MEM_WEEK,\r\n" + 
				"(SELECT COUNT(*)AS TODAY FROM MEMBER WHERE ENROLL_DATE >= TO_CHAR(SYSDATE,'YYYY-MM-DD'))AS MEM_TODAY, (SELECT COUNT(*)AS TOTAL_MEM FROM MEMBER)AS TOTAL_MEM,\r\n" + 
				"(SELECT COUNT(*)AS MONTH FROM PHOTO WHERE REG_DATE >= TO_CHAR(SYSDATE-30,'YYYY-MM-DD'))AS PH_MONTH,(SELECT COUNT(*) WEEK FROM PHOTO WHERE REG_DATE >= TO_CHAR(SYSDATE-7,'YYYY-MM-DD'))AS PH_WEEK,\r\n" + 
				"(SELECT COUNT(*)AS TODAY FROM PHOTO WHERE REG_DATE >= TO_CHAR(SYSDATE,'YYYY-MM-DD'))AS PH_TODAY, (SELECT COUNT(*)AS TOTAL_PH FROM PHOTO)AS TOTAL_PH,\r\n" + 
				"(SELECT COUNT(*)AS MONTH FROM RESERVATION WHERE RV_DATE >= TO_CHAR(SYSDATE-30,'YYYY-MM-DD'))AS RV_MONTH,(SELECT COUNT(*) WEEK FROM RESERVATION WHERE RV_DATE >= TO_CHAR(SYSDATE-7,'YYYY-MM-DD'))AS RV_WEEK,\r\n" + 
				"(SELECT COUNT(*)AS TODAY FROM RESERVATION WHERE RV_DATE >= TO_CHAR(SYSDATE,'YYYY-MM-DD'))AS RV_TODAY, (SELECT COUNT(*)AS TOTAL_RV FROM RESERVATION)AS TOTAL_RV,\r\n" + 
				"(SELECT COUNT(*)AS MONTH FROM BOARD WHERE REG_DATE >= TO_CHAR(SYSDATE-30,'YYYY-MM-DD'))AS BO_MONTH,(SELECT COUNT(*) WEEK FROM BOARD WHERE REG_DATE >= TO_CHAR(SYSDATE-7,'YYYY-MM-DD'))BO_WEEK,\r\n" + 
				"(SELECT COUNT(*)AS TODAY FROM BOARD WHERE REG_DATE >= TO_CHAR(SYSDATE,'YYYY-MM-DD'))AS BO_TODAY, (SELECT COUNT(*)AS TOTAL_BO FROM BOARD)AS TOTAL_BO FROM DUAL";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				chart = new AdminChart();
				chart.setFaqMonth(rset.getInt("faq_month"));
				chart.setFaqWeek(rset.getInt("faq_week"));
				chart.setFaqToday(rset.getInt("faq_today"));
				chart.setTotalFaq(rset.getInt("total_faq"));
				chart.setNoMonth(rset.getInt("no_month"));
				chart.setNoWeek(rset.getInt("no_week"));
				chart.setNoToday(rset.getInt("no_today"));
				chart.setTotalNo(rset.getInt("total_no"));
				chart.setMemMonth(rset.getInt("mem_month"));
				chart.setMemWeek(rset.getInt("mem_week"));
				chart.setMemToday(rset.getInt("mem_today"));
				chart.setTotalMem(rset.getInt("total_mem"));
				chart.setPhMonth(rset.getInt("ph_month"));
				chart.setPhWeek(rset.getInt("ph_week"));
				chart.setPhToday(rset.getInt("ph_today"));
				chart.setTotalPh(rset.getInt("total_ph"));
				chart.setRvMonth(rset.getInt("rv_month"));
				chart.setRvWeek(rset.getInt("rv_week"));
				chart.setRvToday(rset.getInt("rv_today"));
				chart.setTotalRv(rset.getInt("total_rv"));
				chart.setBoMonth(rset.getInt("bo_month"));
				chart.setBoWeek(rset.getInt("bo_week"));
				chart.setBoToday(rset.getInt("bo_today"));
				chart.setTotalBo(rset.getInt("total_bo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return chart;
	}

	
}
