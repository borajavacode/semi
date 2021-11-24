package photo.model.vo;

import java.util.ArrayList;

public class PhotoPageData {
	private ArrayList<Photo> list;
	private String pageNavi;
	private int start;

	public PhotoPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhotoPageData(ArrayList<Photo> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Photo> getList() {
		return list;
	}
	public void setList(ArrayList<Photo> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
}
