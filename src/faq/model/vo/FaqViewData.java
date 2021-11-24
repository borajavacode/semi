package faq.model.vo;

import java.util.ArrayList;

public class FaqViewData {
	private ArrayList<FaqCo> list;
	private Faq f;
	public FaqViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaqViewData(ArrayList<FaqCo> list, Faq f) {
		super();
		this.list = list;
		this.f = f;
	}
	public ArrayList<FaqCo> getList() {
		return list;
	}
	public void setList(ArrayList<FaqCo> list) {
		this.list = list;
	}
	public Faq getF() {
		return f;
	}
	public void setF(Faq f) {
		this.f = f;
	}
	
}
