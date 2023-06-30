package vo;

import java.util.Date;

public class NoticeVO {

	private int NOTICE_NUM;
	private String NOTICE_ADMIN;
	private String NOTICE_TITLE;
	private String NOTICE_CONT;
	private String NOTICE_FILE;
	private Date NOTICE_DATE;
	private int NOTICE_READCOUNT;
	private int NOTICE_PIN;
	private String NOTICE_REALFILE;
	
	

	public int getNOTICE_NUM() {
		return NOTICE_NUM;
	}

	public void setNOTICE_NUM(int nOTICE_NUM) {
		this.NOTICE_NUM = nOTICE_NUM;
	}
	public String getNOTICE_ADMIN() {
		return NOTICE_ADMIN;
	}
	public void setNOTICE_ADMIN(String nOTICE_ADMIN) {
		this.NOTICE_ADMIN = nOTICE_ADMIN;
	}
	public String getNOTICE_TITLE() {
		return NOTICE_TITLE;
	}
	public void setNOTICE_TITLE(String nOTICE_TITLE) {
		this.NOTICE_TITLE = nOTICE_TITLE;
	}
	public String getNOTICE_CONT() {
		return NOTICE_CONT;
	}
	public void setNOTICE_CONT(String nOTICE_CONT) {
		this.NOTICE_CONT = nOTICE_CONT;
	}
	public String getNOTICE_FILE() {
		return NOTICE_FILE;
	}
	public void setNOTICE_FILE(String nOTICE_FILE) {
		this.NOTICE_FILE = nOTICE_FILE;
	}
	public Date getNOTICE_DATE() {
		return NOTICE_DATE;
	}
	public void setNOTICE_DATE(Date nOTICE_DATE) {
		this.NOTICE_DATE = nOTICE_DATE;
	}
	public int getNOTICE_READCOUNT() {
		return NOTICE_READCOUNT;
	}
	public void setNOTICE_READCOUNT(int nOTICE_READCOUNT) {
		NOTICE_READCOUNT = nOTICE_READCOUNT;
	}
	public int getNOTICE_PIN() {
		return NOTICE_PIN;
	}
	public void setNOTICE_PIN(int nOTICE_PIN) {
		NOTICE_PIN = nOTICE_PIN;
	}

	public String getNOTICE_REALFILE() {
		return NOTICE_REALFILE;
	}
	
	public void setNOTICE_REALFILE(String nOTICE_REALFILE) {
		NOTICE_REALFILE = nOTICE_REALFILE;
	}
	
}
