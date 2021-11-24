package board.model.vo;

import java.util.ArrayList;

public class BoardViewData {
	private ArrayList<BdComment> list;
	private Board b;
	public BoardViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardViewData(ArrayList<BdComment> list, Board b) {
		super();
		this.list = list;
		this.b = b;
	}
	public ArrayList<BdComment> getList() {
		return list;
	}
	public void setList(ArrayList<BdComment> list) {
		this.list = list;
	}
	public Board getB() {
		return b;
	}
	public void setB(Board b) {
		this.b = b;
	}
	
}
