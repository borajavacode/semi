package location.vo;

public class Location {
	private int locNo;
	private String locName;
	private String locAddr;
	private String locPhone;
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(int locNo, String locName, String locAddr, String locPhone) {
		super();
		this.locNo = locNo;
		this.locName = locName;
		this.locAddr = locAddr;
		this.locPhone = locPhone;
	}
	public Location(String locName, String locAddr, String locPhone) {
		this.locName = locName;
		this.locAddr = locAddr;
		this.locPhone = locPhone;
	}
	public int getLocNo() {
		return locNo;
	}
	public void setLocNo(int locNo) {
		this.locNo = locNo;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getLocAddr() {
		return locAddr;
	}
	public void setLocAddr(String locAddr) {
		this.locAddr = locAddr;
	}
	public String getLocPhone() {
		return locPhone;
	}
	public void setLocPhone(String locPhone) {
		this.locPhone = locPhone;
	}
	
}
