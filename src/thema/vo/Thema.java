package thema.vo;

public class Thema {
	private int themaNo;
	private String themaName;
	private String themaAddr;
	private int difficult;
	private String themaContent;
	private String filepath;

	public Thema() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Thema(int themaNo, String themaName, String themaAddr, int difficult, String themaContent, String filepath) {
		super();
		this.themaNo = themaNo;
		this.themaName = themaName;
		this.themaAddr = themaAddr;
		this.difficult = difficult;
		this.themaContent = themaContent;
		this.filepath = filepath;
	}

	public int getThemaNo() {
		return themaNo;
	}

	public void setThemaNo(int themaNo) {
		this.themaNo = themaNo;
	}

	public String getThemaName() {
		return themaName;
	}

	public void setThemaName(String themaName) {
		this.themaName = themaName;
	}

	public String getThemaAddr() {
		return themaAddr;
	}

	public void setThemaAddr(String themaAddr) {
		this.themaAddr = themaAddr;
	}

	public int getDifficult() {
		return difficult;
	}

	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}

	public String getThemaContent() {
		return themaContent;
	}
	
	public String getThemaContentBr() {
		return themaContent.replaceAll("\r\n", "<br>");
	}

	public void setThemaContent(String themaContent) {
		this.themaContent = themaContent;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
