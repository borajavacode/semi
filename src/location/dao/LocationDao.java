package location.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import location.vo.Location;

public class LocationDao {

	public ArrayList<Location> selectLocationList(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Location> list=new ArrayList<>();
		String query="select * from location";
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Location loc=new Location();
				loc.setLocNo(rset.getInt("loc_no"));
				loc.setLocName(rset.getString("loc_name"));
				loc.setLocAddr(rset.getString("loc_addr"));
				loc.setLocPhone(rset.getString("loc_phone"));
				list.add(loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int insertLocation(Connection conn, Location loc) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="insert into location values (loc_seq.nextval,?,?,?)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, loc.getLocName());
			pstmt.setString(2, loc.getLocAddr());
			pstmt.setString(3, loc.getLocPhone());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Location selectOneLocation(Connection conn, String locName) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Location loc=null;
		String query="select * from location where loc_name=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, locName);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				loc=new Location();
				loc.setLocName(rset.getString("loc_name"));
				loc.setLocAddr(rset.getString("loc_addr"));
				loc.setLocPhone(rset.getString("loc_phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return loc;
	}

	public int updateLocation(Connection conn, Location loc) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="update location set loc_name=?,loc_addr=?, loc_phone=? where loc_name=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, loc.getLocName());
			pstmt.setString(2, loc.getLocAddr());
			pstmt.setString(3, loc.getLocPhone());
			pstmt.setString(4, loc.getLocName());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteLocation(Connection conn, String locName) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="delete from location where loc_name=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, locName);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Location> selectSearchLoc(Connection conn, String areaInfo, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Location> list=new ArrayList<>();
		String query="select * from location where loc_addr like ? and (loc_addr like ? and loc_name like ?)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+areaInfo+"%");
			pstmt.setString(2, "%"+areaInfo+"%");
			pstmt.setString(3, "%"+keyword+"%");
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Location loc=new Location();
				loc.setLocNo(rset.getInt("loc_no"));
				loc.setLocName(rset.getString("loc_name"));
				loc.setLocAddr(rset.getString("loc_addr"));
				loc.setLocPhone(rset.getString("loc_phone"));
				list.add(loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

}
