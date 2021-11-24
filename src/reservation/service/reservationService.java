package reservation.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import reservation.dao.reservationDao;
import reservation.vo.Reservation;
import reservation.vo.Thema;

public class reservationService {

	public static ArrayList<Reservation> rvCheck(String date, String thema) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Reservation> rvList = new reservationDao().rvCheck(conn,date,thema);
		JDBCTemplate.close(conn);
		return rvList;
	}

	public ArrayList<Thema> themaLoad() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Thema> tList = new reservationDao().themaLoad(conn);
		JDBCTemplate.close(conn);
		return tList;
	}

	public ArrayList<Thema> themaLoad(String point) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Thema> tList = new reservationDao().themaLoad(conn,point);
		JDBCTemplate.close(conn);
		return tList;
	}

	public String selectFilepath(String rvThema) {
		Connection conn = JDBCTemplate.getConnection();
		String filepath = new reservationDao().selectFilepath(conn,rvThema);
		JDBCTemplate.close(conn);
		return filepath;
	}

	public int insertReservtion(Reservation rv) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new reservationDao().insertReservation(conn,rv);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<Reservation> myReservation(String rvPhone, String rvName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Reservation> rvList = new reservationDao().myReservation(conn,rvPhone,rvName);
		JDBCTemplate.close(conn);
		
		return rvList;
	}

	public ArrayList<Reservation> selectAllRv() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Reservation> rvList = new reservationDao().selectAllRv(conn);
		JDBCTemplate.close(conn);
		
		return rvList;
	}

	public int deleteReservation(int rvNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new reservationDao().deleteReservation(conn,rvNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
