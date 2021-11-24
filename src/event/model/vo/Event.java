package event.model.vo;

public class Event {
	private int eventNo;
	private String eventTitle;
	private String eventContent;
	private int eventReadcount;
	private String regDate;
	private String eventWriter;
	private String mainEvent;
	private String eventFinishDate;
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(int eventNo, String eventTitle, String eventContent, int eventReadcount, String regDate,
			String eventWriter, String mainEvent, String eventFinishDate) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.eventReadcount = eventReadcount;
		this.regDate = regDate;
		this.eventWriter = eventWriter;
		this.mainEvent = mainEvent;
		this.eventFinishDate = eventFinishDate;
	}
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	public int getEventReadcount() {
		return eventReadcount;
	}
	public void setEventReadcount(int eventReadcount) {
		this.eventReadcount = eventReadcount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getEventWriter() {
		return eventWriter;
	}
	public void setEventWriter(String eventWriter) {
		this.eventWriter = eventWriter;
	}
	public String getMainEvent() {
		return mainEvent;
	}
	public void setMainEvent(String mainEvent) {
		this.mainEvent = mainEvent;
	}
	public String getEventFinishDate() {
		return eventFinishDate;
	}
	public void setEventFinishDate(String eventFinishDate) {
		this.eventFinishDate = eventFinishDate;
	}
}
