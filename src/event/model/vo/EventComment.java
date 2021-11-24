package event.model.vo;

public class EventComment {
	private int ecNo;
	private String ecContent;
	private String ecDate;
	private String ecWriter;
	private int ecReg;
	public EventComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventComment(int ecNo, String ecContent, String ecDate, String ecWriter, int ecReg) {
		super();
		this.ecNo = ecNo;
		this.ecContent = ecContent;
		this.ecDate = ecDate;
		this.ecWriter = ecWriter;
		this.ecReg = ecReg;
	}
	public int getEcNo() {
		return ecNo;
	}
	public void setEcNo(int ecNo) {
		this.ecNo = ecNo;
	}
	public String getecContentBr() {
		return ecContent.replaceAll("\r\n", "<br>");
	}
	public String getEcContent() {
		return ecContent;
	}
	public void setEcContent(String ecContent) {
		this.ecContent = ecContent;
	}
	public String getEcDate() {
		return ecDate;
	}
	public void setEcDate(String ecDate) {
		this.ecDate = ecDate;
	}
	public String getEcWriter() {
		return ecWriter;
	}
	public void setEcWriter(String ecWriter) {
		this.ecWriter = ecWriter;
	}
	public int getEcReg() {
		return ecReg;
	}
	public void setEcReg(int ecReg) {
		this.ecReg = ecReg;
	}
}
