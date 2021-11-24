package faq.model.vo;

public class Faq {
	private int faqNo;
	private String faqTitle;
	private String faqContent;
	private String regDate;
	private int faqStatus;
	private String faqWriter;
	private String filepath;
	private int faqCount;
	
	public Faq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Faq(int faqNo, String faqTitle, String faqContent, String regDate, int faqStatus, String faqWriter,
			String filepath, int faqCount) {
		super();
		this.faqNo = faqNo;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
		this.regDate = regDate;
		this.faqStatus = faqStatus;
		this.faqWriter = faqWriter;
		this.filepath = filepath;
		this.faqCount = faqCount;
	}

	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getFaqStatus() {
		return faqStatus;
	}

	public void setFaqStatus(int faqStatus) {
		this.faqStatus = faqStatus;
	}

	public String getFaqWriter() {
		return faqWriter;
	}

	public void setFaqWriter(String faqWriter) {
		this.faqWriter = faqWriter;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getFaqCount() {
		return faqCount;
	}

	public void setFaqCount(int faqCount) {
		this.faqCount = faqCount;
	}

	
	
}
