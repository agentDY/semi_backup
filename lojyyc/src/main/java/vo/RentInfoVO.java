package vo;

import java.util.Date;

public class RentInfoVO {
	
	private String RENT_ID_SEQ;
	private Date RENT_DATE;
	private Date RENT_RETURN;
	private String ISBN_TITLE;
	private String ISBN_AUTHOR;
	private String RENT_BOOK;
	private Date RENT_RETURN_DATE;
	
	
	public Date getRENT_RETURN_DATE() {
		return RENT_RETURN_DATE;
	}
	public void setRENT_RETURN_DATE(Date rENT_RETURN_DATE) {
		this.RENT_RETURN_DATE = rENT_RETURN_DATE;
	}
	public String getRENT_BOOK() {
		return RENT_BOOK;
	}
	public void setRENT_BOOK(String rENT_BOOK) {
		this.RENT_BOOK = rENT_BOOK;
	}
	
	public String getRENT_ID_SEQ() {
		return RENT_ID_SEQ;
	}
	public void setRENT_ID_SEQ(String rENT_ID_SEQ) {
		RENT_ID_SEQ = rENT_ID_SEQ;
	}
	public Date getRENT_DATE() {
		return RENT_DATE;
	}
	public void setRENT_DATE(Date rENT_DATE) {
		RENT_DATE = rENT_DATE;
	}
	public Date getRENT_RETURN() {
		return RENT_RETURN;
	}
	public void setRENT_RETURN(Date rENT_RETURN) {
		RENT_RETURN = rENT_RETURN;
	}
	public String getISBN_TITLE() {
		return ISBN_TITLE;
	}
	public void setISBN_TITLE(String iSBN_TITLE) {
		ISBN_TITLE = iSBN_TITLE;
	}
	public String getISBN_AUTHOR() {
		return ISBN_AUTHOR;
	}
	public void setISBN_AUTHOR(String iSBN_AUTHOR) {
		ISBN_AUTHOR = iSBN_AUTHOR;
	}
	
}
