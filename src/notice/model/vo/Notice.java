package notice.model.vo;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private int noticeReadCount;
	private String regDate;
	private String noticeFilename;
	private String noticeFilepath;
	private String noticeWriter;
	private int ncCount;
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getNcCount() {
		return ncCount;
	}

	public void setNcCount(int ncCount) {
		this.ncCount = ncCount;
	}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, int noticeReadCount, String regDate,
			String noticeFilename, String noticeFilepath, String noticeWriter, int ncCount) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeReadCount = noticeReadCount;
		this.regDate = regDate;
		this.noticeFilename = noticeFilename;
		this.noticeFilepath = noticeFilepath;
		this.noticeWriter = noticeWriter;
		this.ncCount = ncCount;
	}

	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeReadCount() {
		return noticeReadCount;
	}
	public void setNoticeReadCount(int noticeReadCount) {
		this.noticeReadCount = noticeReadCount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getNoticeFilename() {
		return noticeFilename;
	}
	public void setNoticeFilename(String noticeFilename) {
		this.noticeFilename = noticeFilename;
	}
	public String getNoticeFilepath() {
		return noticeFilepath;
	}
	public void setNoticeFilepath(String noticeFilepath) {
		this.noticeFilepath = noticeFilepath;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
}
