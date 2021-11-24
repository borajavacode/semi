package board.model.vo;

public class BdComment {
	private int bdNo;
	private String bdContent;
	private String bdWriter;
	private int boardRef;
	private String bdDate;
	public BdComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BdComment(int bdNo, String bdContent, String bdWriter, int boardRef, String bdDate) {
		super();
		this.bdNo = bdNo;
		this.bdContent = bdContent;
		this.bdWriter = bdWriter;
		this.boardRef = boardRef;
		this.bdDate = bdDate;
	}
	public int getBdNo() {
		return bdNo;
	}
	public void setBdNo(int bdNo) {
		this.bdNo = bdNo;
	}
	public String getBdContent() {
		return bdContent;
	}
	public void setBdContent(String bdContent) {
		this.bdContent = bdContent;
	}
	public String getBdContentBr() {
		return bdContent.replaceAll("\r\n", "<br>");
	}
	public String getBdWriter() {
		return bdWriter;
	}
	public void setBdWriter(String bdWriter) {
		this.bdWriter = bdWriter;
	}
	public int getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}
	public String getBdDate() {
		return bdDate;
	}
	public void setBdDate(String bdDate) {
		this.bdDate = bdDate;
	}
	
	
}
