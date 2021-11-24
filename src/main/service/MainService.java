package main.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import event.model.dao.EventDao;
import event.model.vo.Event;
import main.dao.MainDao;
import notice.model.vo.Notice;
import thema.vo.Thema;

public class MainService {

	public ArrayList<Thema> popThema() {
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<Thema> list=new MainDao().popThema(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Event> mainEvent() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Event> list = new MainDao().mainEvent(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Notice> mainNotice() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Notice> list = new MainDao().mainNotice(conn);
		JDBCTemplate.close(conn);
		return list;
	}
}
