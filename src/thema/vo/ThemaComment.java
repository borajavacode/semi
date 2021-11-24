package thema.vo;

public class ThemaComment {
	private int tcNo;
	private String tcContent;
	private String tcDate;
	private String tcWriter;
	private int themaRef;

	public ThemaComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThemaComment(int tcNo, String tcContent, String tcDate, String tcWriter, int themaRef) {
		super();
		this.tcNo = tcNo;
		this.tcContent = tcContent;
		this.tcDate = tcDate;
		this.tcWriter = tcWriter;
		this.themaRef = themaRef;
	}

	public int getTcNo() {
		return tcNo;
	}

	public void setTcNo(int tcNo) {
		this.tcNo = tcNo;
	}

	public String getTcContent() {
		return tcContent;
	}
	
	public String getTcContentBr() {
		return tcContent.replaceAll("\r\n", "<br>");
	}

	public void setTcContent(String tcContent) {
		this.tcContent = tcContent;
	}

	public String getTcDate() {
		return tcDate;
	}

	public void setTcDate(String tcDate) {
		this.tcDate = tcDate;
	}

	public String getTcWriter() {
		return tcWriter;
	}

	public void setTcWriter(String tcWriter) {
		this.tcWriter = tcWriter;
	}

	public int getThemaRef() {
		return themaRef;
	}

	public void setThemaRef(int themaRef) {
		this.themaRef = themaRef;
	}

}
