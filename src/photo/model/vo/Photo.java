package photo.model.vo;

public class Photo {
	private int photoNo;
	private String photoWriter;
	private String photoTitle;
	private String photoContent;
	private String photoFilepath;
	private String photoFilename;
	private int readCount;
	private String regDate;
	private int pcCount;
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Photo(int photoNo, String photoWriter, String photoTitle, String photoContent, String photoFilepath,
			String photoFilename, int readCount, String regDate, int pcCount) {
		super();
		this.photoNo = photoNo;
		this.photoWriter = photoWriter;
		this.photoTitle = photoTitle;
		this.photoContent = photoContent;
		this.photoFilepath = photoFilepath;
		this.photoFilename = photoFilename;
		this.readCount = readCount;
		this.regDate = regDate;
		this.pcCount = pcCount;
	}
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public String getPhotoWriter() {
		return photoWriter;
	}
	public void setPhotoWriter(String photoWriter) {
		this.photoWriter = photoWriter;
	}
	public String getPhotoTitle() {
		return photoTitle;
	}
	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}
	public String getPhotoContentBr() {
		return photoContent.replaceAll("\r\n", "<br>");
	}
	public String getPhotoContent() {
		return photoContent;
	}
	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}
	public String getPhotoFilepath() {
		return photoFilepath;
	}
	public void setPhotoFilepath(String photoFilepath) {
		this.photoFilepath = photoFilepath;
	}
	public String getPhotoFilename() {
		return photoFilename;
	}
	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
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
	public int getPcCount() {
		return pcCount;
	}
	public void setPcCount(int pcCount) {
		this.pcCount = pcCount;
	}
	
}
