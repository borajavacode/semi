package photo.model.vo;

import java.util.ArrayList;

public class PhotoViewData {
	private ArrayList<PhotoComment> list;
	private Photo p;
	public PhotoViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhotoViewData(ArrayList<PhotoComment> list, Photo p) {
		super();
		this.list = list;
		this.p = p;
	}
	public ArrayList<PhotoComment> getList() {
		return list;
	}
	public void setList(ArrayList<PhotoComment> list) {
		this.list = list;
	}
	public Photo getP() {
		return p;
	}
	public void setP(Photo p) {
		this.p = p;
	}
	
}
