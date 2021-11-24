package board.model.vo;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int readCount;
	private String regDate;
	private String boardFilepath;
	private String boardWriter;
	private String boardFilename;
	private int boardLevel;
	private int bdCount;
	private String deadLine;
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int boardNo, String boardTitle, String boardContent, int readCount, String regDate,
			String boardFilepath, String boardWriter, String boardFilename, int boardLevel, int bdCount,
			String deadLine) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.readCount = readCount;
		this.regDate = regDate;
		this.boardFilepath = boardFilepath;
		this.boardWriter = boardWriter;
		this.boardFilename = boardFilename;
		this.boardLevel = boardLevel;
		this.bdCount = bdCount;
		this.deadLine = deadLine;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBoardFilepath() {
		return boardFilepath;
	}
	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardFilename() {
		return boardFilename;
	}
	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}
	public int getBoardLevel() {
		return boardLevel;
	}
	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
	}
	public int getBdCount() {
		return bdCount;
	}
	public void setBdCount(int bdCount) {
		this.bdCount = bdCount;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	
}
