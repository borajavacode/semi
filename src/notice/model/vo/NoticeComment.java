package notice.model.vo;

public class NoticeComment {
	private int ncNo;
	private String ncContent;
	private String ncDate;
	private String ncWriter;
	private int ncReg;
	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeComment(int ncNo, String ncContent, String ncDate, String ncWriter, int ncReg) {
		super();
		this.ncNo = ncNo;
		this.ncContent = ncContent;
		this.ncDate = ncDate;
		this.ncWriter = ncWriter;
		this.ncReg = ncReg;
	}
	public int getNcNo() {
		return ncNo;
	}
	public void setNcNo(int ncNo) {
		this.ncNo = ncNo;
	}
	public String getNcContentBr() {
		return ncContent.replaceAll("\r\n", "<br>");
	}
	public String getNcContent() {
		return ncContent;
	}
	public void setNcContent(String ncContent) {
		this.ncContent = ncContent;
	}
	public String getNcDate() {
		return ncDate;
	}
	public void setNcDate(String ncDate) {
		this.ncDate = ncDate;
	}
	public String getNcWriter() {
		return ncWriter;
	}
	public void setNcWriter(String ncWriter) {
		this.ncWriter = ncWriter;
	}
	public int getNcReg() {
		return ncReg;
	}
	public void setNcReg(int ncReg) {
		this.ncReg = ncReg;
	}
}
