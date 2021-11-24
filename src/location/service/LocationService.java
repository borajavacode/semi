package location.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import location.dao.LocationDao;
import location.vo.Location;

public class LocationService {

	public ArrayList<Location> selectLocationList() {
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<Location> list=new LocationDao().selectLocationList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public int insertLocation(Location loc) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new LocationDao().insertLocation(conn,loc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Location selectOneLocation(String LocName) {
		Connection conn=JDBCTemplate.getConnection();
		Location loc=new LocationDao().selectOneLocation(conn,LocName);
		JDBCTemplate.close(conn);
		return loc;
	}

	public int updateLocation(Location loc) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new LocationDao().updateLocation(conn,loc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteLocation(String locName) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new LocationDao().deleteLocation(conn,locName);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Location> selectSearchLoc(String areaInfo, String keyword) {
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<Location> list=new LocationDao().selectSearchLoc(conn,areaInfo,keyword);
		JDBCTemplate.close(conn);
		return list;
	}

}
