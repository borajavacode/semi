package notice.model.vo;

import java.util.ArrayList;


public class NoticeViewDate {
	private ArrayList<NoticeComment> list;
	private Notice n;
	public NoticeViewDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeViewDate(ArrayList<NoticeComment> list, Notice n) {
		super();
		this.list = list;
		this.n = n;
	}
	public ArrayList<NoticeComment> getList() {
		return list;
	}
	public void setList(ArrayList<NoticeComment> list) {
		this.list = list;
	}
	public Notice getN() {
		return n;
	}
	public void setN(Notice n) {
		this.n = n;
	}
}
