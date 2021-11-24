package photo.model.vo;

/**
 * @author cookie
 *
 */
public class PhotoComment {
	private int pcNo;
	private String pcWriter;
	private String pcContent;
	private String pcDate;
	private int pcRef;
	public PhotoComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhotoComment(int pcNo, String pcWriter, String pcContent, String pcDate, int pcRef) {
		super();
		this.pcNo = pcNo;
		this.pcWriter = pcWriter;
		this.pcContent = pcContent;
		this.pcDate = pcDate;
		this.pcRef = pcRef;
	}
	public int getPcNo() {
		return pcNo;
	}
	public void setPcNo(int pcNo) {
		this.pcNo = pcNo;
	}
	public String getPcWriter() {
		return pcWriter;
	}
	public void setPcWriter(String pcWriter) {
		this.pcWriter = pcWriter;
	}
	public String getPcContentBr() {
		return pcContent.replaceAll("\r\n", "<br>");
	}
	public String getPcContent() {
		return pcContent;
	}
	public void setPcContent(String pcContent) {
		this.pcContent = pcContent;
	}
	public String getPcDate() {
		return pcDate;
	}
	public void setPcDate(String pcDate) {
		this.pcDate = pcDate;
	}
	public int getPcRef() {
		return pcRef;
	}
	public void setPcRef(int pcRef) {
		this.pcRef = pcRef;
	}
	
}
