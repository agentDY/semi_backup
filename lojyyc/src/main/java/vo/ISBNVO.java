package vo;

public class ISBNVO {
		private String ISBN_ID;
		private String ISBN_IMG;
		private String ISBN_TITLE;	// 책제목
		private String ISBN_AUTHOR;	// 저자
		private String ISBN_TRANS;	// 번역
		private int ISBN_YEAR;
		private String ISBN_COM;
		private String ISBN_CG;
		private String ISBN_INFO;
		private String ISBN_BOOK;	// 도서코드
		private String ISBN_STATUS;	// 대출상태
		private String ISBN_DEL;	// 삭제여부
		
		
		public String getISBN_BOOK() {
			return ISBN_BOOK;
		}
		public void setISBN_BOOK(String ISBN_BOOK) {
			this.ISBN_BOOK = ISBN_BOOK;
		}
		
		public String getISBN_STATUS() {
			return ISBN_STATUS;
		}
		public void setISBN_STATUS(String ISBN_STATUS) {
			this.ISBN_STATUS = ISBN_STATUS;
		}
		
		public String getISBN_DEL() {
			return ISBN_DEL;
		}
		public void setISBN_DEL(String ISBN_DEL) {
			this.ISBN_DEL = ISBN_DEL;
		}
		
		public String getISBN_ID() {
			return ISBN_ID;
		}
		public void setISBN_ID(String iSBN_ID) {
			this.ISBN_ID = iSBN_ID;
		}
		public String getISBN_IMG() {
			return ISBN_IMG;	
		}
		public void setISBN_IMG(String iSBN_IMG) {
			this.ISBN_IMG = iSBN_IMG;
		}
		public String getISBN_TITLE() {
			return ISBN_TITLE;
		}
		public void setISBN_TITLE(String iSBN_TITLE) {
			this.ISBN_TITLE = iSBN_TITLE;
		}
		public String getISBN_AUTHOR() {
			return ISBN_AUTHOR;
		}
		public void setISBN_AUTHOR(String iSBN_AUTHOR) {
			this.ISBN_AUTHOR = iSBN_AUTHOR;
		}
		public String getISBN_TRANS() {
			return ISBN_TRANS;
		}
		public void setISBN_TRANS(String iSBN_TRANS) {
			this.ISBN_TRANS = iSBN_TRANS;
		}
		public int getISBN_YEAR() {
			return ISBN_YEAR;
		}
		public void setISBN_YEAR(int iSBN_YEAR) {
			this.ISBN_YEAR = iSBN_YEAR;
		}
		public String getISBN_COM() {
			return ISBN_COM;
		}
		public void setISBN_COM(String iSBN_COM) {
			this.ISBN_COM = iSBN_COM;
		}
		public String getISBN_CG() {
			return ISBN_CG;
		}
		public void setISBN_CG(String iSBN_CG) {
			this.ISBN_CG = iSBN_CG;
		}
		public String getISBN_INFO() {
			return ISBN_INFO;
		}
		public void setISBN_INFO(String iSBN_INFO) {
			this.ISBN_INFO = iSBN_INFO;
		}
		
	}