package reservation.vo;

public class Thema {
	private int themaNo;
	private String themaName;
	private String themaAddr;
	private String filepath;
	public Thema() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Thema(int themaNo, String themaName, String themaAddr, String filepath) {
		super();
		this.themaNo = themaNo;
		this.themaName = themaName;
		this.themaAddr = themaAddr;
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
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
