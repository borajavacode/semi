package faq.model.vo;

public class FaqCo {
	private int faqCoNo;
	private int faqRef;
	private String fcWriter;
	private String fcContent;
	private String fcDate;
	private int fcLevel;
	public FaqCo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaqCo(int faqCoNo, int faqRef, String fcWriter, String fcContent, String fcDate,int fcLevel) {
		super();
		this.faqCoNo = faqCoNo;
		this.faqRef = faqRef;
		this.fcWriter = fcWriter;
		this.fcContent = fcContent;
		this.fcDate = fcDate;
		this.fcLevel=fcLevel;
	}
	public int getFaqCoNo() {
		return faqCoNo;
	}
	public void setFaqCoNo(int faqCoNo) {
		this.faqCoNo = faqCoNo;
	}
	public int getFaqRef() {
		return faqRef;
	}
	public void setFaqRef(int faqRef) {
		this.faqRef = faqRef;
	}
	public String getFcWriter() {
		return fcWriter;
	}
	public void setFcWriter(String fcWriter) {
		this.fcWriter = fcWriter;
	}
	public String getFcContent() {
		return fcContent;
	}
	public void setFcContent(String fcContent) {
		this.fcContent = fcContent;
	}
	public String getFcContentBr() {
		return fcContent.replaceAll("\r\n", "<br>");
	}
	public String getFcDate() {
		return fcDate;
	}
	public void setFcDate(String fcDate) {
		this.fcDate = fcDate;
	}
	public int getFcLevel() {
		return fcLevel;
	}
	public void setFcLevel(int fcLevel) {
		this.fcLevel = fcLevel;
	}
	
}
