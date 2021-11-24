package reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import reservation.vo.Reservation;
import reservation.vo.Thema;

public class reservationDao {

	public static ArrayList<Reservation> rvCheck(Connection conn, String date, String thema) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reservation> rvList =  new ArrayList<Reservation>();
		String query = "select rv_time from reservation where rv_thema=? and rv_date = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, thema);
			pstmt.setString(2, date);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Reservation rv = new Reservation();
				
				rv.setRvTime(rset.getString("rv_time"));
				rvList.add(rv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return rvList;
	}

	public ArrayList<Thema> themaLoad(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Thema> tList= new ArrayList<Thema>();
		String query = "select thema_addr from thema group by thema_addr order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			
			rset= pstmt.executeQuery();
			while(rset.next()) {
				Thema t = new Thema();
				
				t.setThemaAddr(rset.getString("thema_addr"));
				
				tList.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return tList;
	}

	public ArrayList<Thema> themaLoad(Connection conn, String point) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Thema> tList= new ArrayList<Thema>();
		String query = "select * from thema where thema_addr=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, point);
			rset= pstmt.executeQuery();
			while(rset.next()) {
				Thema t = new Thema();
				t.setThemaNo(rset.getInt("thema_no"));
				t.setThemaName(rset.getString("thema_name"));
				t.setThemaAddr(rset.getString("thema_addr"));
				t.setFilepath(rset.getString("filepath"));
				tList.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return tList;
	}

	public String selectFilepath(Connection conn, String rvThema) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String filepath = null;
		String query = "select filepath from thema where thema_name=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rvThema);
			rset= pstmt.executeQuery();
			if(rset.next()) {
				filepath = rset.getString("filepath");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return filepath;
	}

	public int insertReservation(Connection conn, Reservation rv) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into reservation values(rv_seq.nextval,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rv.getRvName());
			pstmt.setString(2, rv.getRvPhone());
			pstmt.setString(3, rv.getRvEmail());
			pstmt.setString(4, rv.getRvDate());
			pstmt.setString(5, rv.getRvTime());
			pstmt.setString(6, rv.getRvThema());
			pstmt.setString(7, rv.getRvPoint());
			pstmt.setInt(8, rv.getRvPerson());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Reservation> myReservation(Connection conn, String rvPhone, String rvName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reservation> rvList =  new ArrayList<Reservation>();
		String query = "select * from (select rownum as rnum,r.* from(select * from reservation where rv_phone=? and rv_name=? order by 1 desc)r) where rnum between 1 and 10";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rvPhone);
			pstmt.setString(2, rvName);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Reservation rv = new Reservation();
				rv.setRvNo(rset.getInt("rv_no"));
				rv.setRvPerson(rset.getInt("rv_person"));
				rv.setRvThema(rset.getString("rv_thema"));
				rv.setRvDate(rset.getString("rv_date"));
				rv.setRvPoint(rset.getString("rv_point"));
				rv.setRvTime(rset.getString("rv_time"));
				rvList.add(rv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return rvList;
	}

	public ArrayList<Reservation> selectAllRv(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reservation> rvList =  new ArrayList<Reservation>();
		String query = "select * from reservation order by 1 desc";
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Reservation rv = new Reservation();
				rv.setRvNo(rset.getInt("rv_no"));
				rv.setRvName(rset.getString("rv_name"));
				rv.setRvPhone(rset.getString("rv_phone"));
				rv.setRvPerson(rset.getInt("rv_person"));
				rv.setRvThema(rset.getString("rv_thema"));
				rv.setRvDate(rset.getString("rv_date"));
				rv.setRvPoint(rset.getString("rv_point"));
				rv.setRvTime(rset.getString("rv_time"));
				rvList.add(rv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return rvList;
	}

	public int deleteReservation(Connection conn, int rvNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from reservation where rv_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rvNo);
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
