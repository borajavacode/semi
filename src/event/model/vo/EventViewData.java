package event.model.vo;

import java.util.ArrayList;


public class EventViewData {
	private ArrayList<EventComment> list;
	private Event e;
	public EventViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventViewData(ArrayList<EventComment> list, Event e) {
		super();
		this.list = list;
		this.e = e;
	}
	public ArrayList<EventComment> getList() {
		return list;
	}
	public void setList(ArrayList<EventComment> list) {
		this.list = list;
	}
	public Event getE() {
		return e;
	}
	public void setE(Event e) {
		this.e = e;
	}
	
	
}
