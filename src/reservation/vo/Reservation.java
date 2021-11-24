package reservation.vo;

public class Reservation {
	private int rvNo;
	private String rvName;
	private String rvPhone;
	private String rvEmail;
	private String rvDate;
	private String rvTime;
	private String rvThema;
	private String rvPoint;
	private int rvPerson;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(int rvNo, String rvName, String rvPhone, String rvEmail, String rvDate, String rvTime,
			String rvThema, String rvPoint, int rvPerson) {
		super();
		this.rvNo = rvNo;
		this.rvName = rvName;
		this.rvPhone = rvPhone;
		this.rvEmail = rvEmail;
		this.rvDate = rvDate;
		this.rvTime = rvTime;
		this.rvThema = rvThema;
		this.rvPoint = rvPoint;
		this.rvPerson = rvPerson;
	}
	public int getRvNo() {
		return rvNo;
	}
	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}
	public String getRvName() {
		return rvName;
	}
	public void setRvName(String rvName) {
		this.rvName = rvName;
	}
	public String getRvPhone() {
		return rvPhone;
	}
	public void setRvPhone(String rvPhone) {
		this.rvPhone = rvPhone;
	}
	public String getRvEmail() {
		return rvEmail;
	}
	public void setRvEmail(String rvEmail) {
		this.rvEmail = rvEmail;
	}
	public String getRvDate() {
		return rvDate;
	}
	public void setRvDate(String rvDate) {
		this.rvDate = rvDate;
	}
	public String getRvTime() {
		return rvTime;
	}
	public void setRvTime(String rvTime) {
		this.rvTime = rvTime;
	}
	public String getRvThema() {
		return rvThema;
	}
	public void setRvThema(String rvThema) {
		this.rvThema = rvThema;
	}
	public String getRvPoint() {
		return rvPoint;
	}
	public void setRvPoint(String rvPoint) {
		this.rvPoint = rvPoint;
	}
	public int getRvPerson() {
		return rvPerson;
	}
	public void setRvPerson(int rvPerson) {
		this.rvPerson = rvPerson;
	}
	
}