package vo;

import java.util.Date;

public class RentVO {
	private String RENT_ID_SEQ; // 대여번호
	private String RENT_BOOK;	// 도서관리코드
	private String RENT_USER;
	private Date RENT_DATE;
	private Date RENT_RETURN;
	private String RENT_STATUS;
	
	
	public String getRENT_ID_SEQ() {
		return RENT_ID_SEQ;
	}
	public void setRENT_ID_SEQ(String rENT_ID_SEQ) {
		this.RENT_ID_SEQ = rENT_ID_SEQ;
	}
	public String getRENT_BOOK() {
		return RENT_BOOK;
	}
	public void setRENT_BOOK(String rENT_BOOK) {
		this.RENT_BOOK = rENT_BOOK;
	}
	public String getRENT_USER() {
		return RENT_USER;
	}
	public void setRENT_USER(String rENT_USER) {
		this.RENT_USER = rENT_USER;
	}
	public Date getRENT_DATE() {
		return RENT_DATE;
	}
	public void setRENT_DATE(Date rENT_DATE) {
		this.RENT_DATE = rENT_DATE;
	}
	public Date getRENT_RETURN() {
		return RENT_RETURN;
	}
	public void setRENT_RETURN(Date rENT_RETURN) {
		this.RENT_RETURN = rENT_RETURN;
	}
	public String getRENT_STATUS() {
		return RENT_STATUS;
	}
	public void setRENT_STATUS(String rENT_STATUS) {
		this.RENT_STATUS = rENT_STATUS;
	}
	
	
	
}
