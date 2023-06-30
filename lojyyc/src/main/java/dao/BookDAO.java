package dao;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.ArrayList;
import javax.sql.DataSource;
import vo.*;
import java.util.*;

public class BookDAO {
	DataSource ds;
	Connection con;
	private static BookDAO bookDAO;

	private BookDAO() {
		// TODO Auto-generated constructor stub
	}

	public static BookDAO getInstance(){
		if(bookDAO == null){
			bookDAO = new BookDAO();
		}
		return bookDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}

	// 제목으로 도서검색 ---------------------------------------------------------------------------------------------------------
	 public ArrayList<ISBNVO> searchBooksByTitle(String keyword, int page) {
		 System.out.println("searchBooksDAO > 도서 이름으로 검색");
		 ArrayList<ISBNVO> books = new ArrayList<ISBNVO>();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String book_search_sql = "select * from (select rownum rnum, A.* from (SELECT * FROM ISBN WHERE ISBN_TITLE LIKE ?) A ) where rnum between ? and ?";
		 
		 int startrow = (page-1) * 10+1;
		 int endrow = (page-1) * 10+10;  
		 
		 try {
	            // execute SQL query to search books
//	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ISBN WHERE ISBN_TITLE LIKE ?");
	        	
			 pstmt = con.prepareStatement(book_search_sql);
	        	
			 pstmt.setString(1, "%" + keyword + "%");
			 pstmt.setInt(2, startrow);
			 pstmt.setInt(3, endrow);
	         rs = pstmt.executeQuery();
	         System.out.println("query executed");

	            // iterate through the search results and add them to the list
	            while (rs.next()) {
	            	ISBNVO book = new ISBNVO();
	                book.setISBN_ID(rs.getString("ISBN_ID"));
	                book.setISBN_IMG(rs.getString("ISBN_IMG"));
	                book.setISBN_TITLE(rs.getString("ISBN_TITLE"));
	                book.setISBN_AUTHOR(rs.getString("ISBN_AUTHOR"));
	                book.setISBN_TRANS(rs.getString("ISBN_TRANS"));
	                book.setISBN_YEAR(rs.getInt("ISBN_YEAR"));
	                book.setISBN_COM(rs.getString("ISBN_COM"));
	                book.setISBN_CG(rs.getString("ISBN_CG"));
	                book.setISBN_INFO(rs.getString("ISBN_INFO"));
	                book.setISBN_BOOK(rs.getString("ISBN_BOOK"));
	                book.setISBN_STATUS(rs.getString("ISBN_STATUS"));
	                book.setISBN_DEL(rs.getString("ISBN_DEL"));
	                books.add(book);
	            }
	        } catch (Exception e) {
	            System.out.println("저자 검색 에러 : "+ e.getMessage());
	        } finally {
	        	close(rs);
	        	close(pstmt);
	        }
	        System.out.println(books);
	        return books;
	    }
	 
	 // 저자이름으로 도서검색 ---------------------------------------------------------------------------------------------------------
	 public ArrayList<ISBNVO> searchBooksByAuthor(String keyword, int page) {
		 System.out.println("searchBooksDAO > 저자 이름으로 검색");
		 ArrayList<ISBNVO> books = new ArrayList<ISBNVO>();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String book_search_sql = "select * from (select rownum rnum, A.* from (SELECT * FROM ISBN WHERE ISBN_AUTHOR LIKE ?) A ) where rnum between ? and ?";
		 
		 int startrow = (page-1) * 10+1;
		 int endrow = (page-1) * 10+10;  
		 
		 try {
			 // execute SQL query to search books
//	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ISBN WHERE ISBN_AUTHOR LIKE ?");
			 
			 pstmt = con.prepareStatement(book_search_sql);
			 
			 pstmt.setString(1, "%" + keyword + "%");
			 pstmt.setInt(2, startrow);
			 pstmt.setInt(3, endrow);
			 rs = pstmt.executeQuery();
			 System.out.println("query executed");
			 
			 // iterate through the search results and add them to the list
			 while (rs.next()) {
				 ISBNVO book = new ISBNVO();
				 book.setISBN_ID(rs.getString("ISBN_ID"));
				 book.setISBN_IMG(rs.getString("ISBN_IMG"));
				 book.setISBN_TITLE(rs.getString("ISBN_TITLE"));
				 book.setISBN_AUTHOR(rs.getString("ISBN_AUTHOR"));
				 book.setISBN_TRANS(rs.getString("ISBN_TRANS"));
				 book.setISBN_YEAR(rs.getInt("ISBN_YEAR"));
				 book.setISBN_COM(rs.getString("ISBN_COM"));
				 book.setISBN_CG(rs.getString("ISBN_CG"));
				 book.setISBN_INFO(rs.getString("ISBN_INFO"));
				 book.setISBN_BOOK(rs.getString("ISBN_BOOK"));
	             book.setISBN_STATUS(rs.getString("ISBN_STATUS"));
				 book.setISBN_DEL(rs.getString("ISBN_DEL"));
				 books.add(book);
			 }
		 } catch (Exception e) {
			 System.err.println("제목 검색 에러: " + e.getMessage());
		 } finally {
			 close(rs);
			 close(pstmt);
		 }
		 System.out.println(books);
		 return books;
	 }
	 
	 // 출판사 이름으로 도서검색 ---------------------------------------------------------------------------------------------------------
	 public ArrayList<ISBNVO> searchBooksByCompany(String keyword, int page) {
		 System.out.println("searchBooksDAO > 출판사 이름으로 검색");
		 ArrayList<ISBNVO> books = new ArrayList<ISBNVO>();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String book_search_sql = "select * from (select rownum rnum, A.* from (SELECT * FROM ISBN WHERE ISBN_COM LIKE ?) A ) where rnum between ? and ?";
		 
		 int startrow = (page-1) * 10+1;
		 int endrow = (page-1) * 10+10;  
		 
		 try {
			 // execute SQL query to search books
//	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ISBN WHERE ISBN_TITLE LIKE ?");
			 
			 pstmt = con.prepareStatement(book_search_sql);
			 
			 pstmt.setString(1, "%" + keyword + "%");
			 pstmt.setInt(2, startrow);
			 pstmt.setInt(3, endrow);
			 rs = pstmt.executeQuery();
			 System.out.println("query executed");
			 
			 // iterate through the search results and add them to the list
			 while (rs.next()) {
				 ISBNVO book = new ISBNVO();
				 book.setISBN_ID(rs.getString("ISBN_ID"));
				 book.setISBN_IMG(rs.getString("ISBN_IMG"));
				 book.setISBN_TITLE(rs.getString("ISBN_TITLE"));
				 book.setISBN_AUTHOR(rs.getString("ISBN_AUTHOR"));
				 book.setISBN_TRANS(rs.getString("ISBN_TRANS"));
				 book.setISBN_YEAR(rs.getInt("ISBN_YEAR"));
				 book.setISBN_COM(rs.getString("ISBN_COM"));
				 book.setISBN_CG(rs.getString("ISBN_CG"));
				 book.setISBN_INFO(rs.getString("ISBN_INFO"));
				 book.setISBN_BOOK(rs.getString("ISBN_BOOK"));
	             book.setISBN_STATUS(rs.getString("ISBN_STATUS"));				 
				 book.setISBN_DEL(rs.getString("ISBN_DEL"));
				 books.add(book);
			 }
		 } catch (Exception e) {
			 System.err.println("출판사 검색 에러: " + e.getMessage());
		 } finally {
			 close(rs);
			 close(pstmt);
		 }
		 System.out.println(books);
		 return books;
	 }
		
	//// 검색한 도서 개수 구하기 --------------------------------------------------------------------
	public int getSearchListCount(String keyword, String keyword_type) {
		int listCount = 0;
					
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 책 제목으로 검색시 --------------------------------------------------------------------
		if (keyword_type.equals("key_title")) {
			String searchBookTitle_sql= "SELECT COUNT(*) FROM ISBN WHERE ISBN_TITLE LIKE ?";
			
			try{
				pstmt=con.prepareStatement(searchBookTitle_sql);
				System.out.println("bookDAO_getSearchListCount2222222");
				pstmt.setString(1, "%" + keyword + "%");
				rs = pstmt.executeQuery();
				System.out.println(listCount);
				
				if(rs.next()){
					listCount = rs.getInt(1);
				}
				
			}catch(Exception ex){
				System.out.println("책제목 getListCount 에러: " + ex.getMessage());			
			}finally{
				close(rs);
				close(pstmt);
			}
		}
		
		// 저자 이름으로 검색시 --------------------------------------------------------------------
		if (keyword_type.equals("key_author")) {
			String searchBookAuthor_sql= "SELECT COUNT(*) FROM ISBN WHERE ISBN_AUTHOR LIKE ?";
			try{
				pstmt=con.prepareStatement(searchBookAuthor_sql);
				System.out.println("bookDAO_getSearchListCount3333");
				pstmt.setString(1, "%" + keyword + "%");
				rs = pstmt.executeQuery();
				System.out.println(listCount);
				
				if(rs.next()){
					listCount = rs.getInt(1);
				}
				
			}catch(Exception ex){
				System.out.println("저자 getListCount 에러: " + ex.getMessage());			
			}finally{
				close(rs);
				close(pstmt);
			}
		}
		
		// 출판사 이름으로 검색시 ----------------------------------------------------------------------
		if(keyword_type.equals("key_company")) {
			String searchBookCom_sql= "SELECT COUNT(*) FROM ISBN WHERE ISBN_COM LIKE ?";
			try{
				pstmt=con.prepareStatement(searchBookCom_sql);
				System.out.println("bookDAO_getSearchListCount444");
				pstmt.setString(1, "%" + keyword + "%");
				rs = pstmt.executeQuery();
				System.out.println(listCount);
				
				if(rs.next()){
					listCount = rs.getInt(1);
				}
				
			}catch(Exception ex){
				System.out.println("출판사 getListCount 에러: " + ex.getMessage());			
			}finally{
				close(rs);
				close(pstmt);
			}
		}
				return listCount;
	}
	
	// 카테고리 선택시 도서 개수 구하기 -----------------------------------------------------------------------------------
	// 카테고리 선택시 도서 개수 구하기 -----------------------------------------------------------------------------------
		public int bookDetailCount (String keyword) {
			int listCount = 1;
			PreparedStatement pstmt = null;
			Statement stmt = null;
			ResultSet rs = null;
			System.out.println("매개변수 key: " + keyword);
			
			if (keyword.equals("전체")) {
				String countTotal_sql = "SELECT COUNT(*) FROM ISBN";
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(countTotal_sql);
					
					if(rs.next()) {listCount = rs.getInt(1);}
					System.out.println("토탈 listCount: " + listCount);
				} catch(Exception ex) {
					System.out.println("total카운트 에러: " + ex.getMessage());
				} finally {close(rs); close(stmt);}
				
			} 
			else if(keyword.equals("미보유")){
				String countCategory_sql_del = "SELECT COUNT(*) FROM ISBN WHERE ISBN_DEL = 'Y'";
				
				try {
					pstmt = con.prepareStatement(countCategory_sql_del);
//					pstmt.setString(1, keyword);
					rs = pstmt.executeQuery();
					
					if(rs.next()) { listCount = rs.getInt(1);}
					System.out.println("detailcount: " + listCount); // 문학 , listCount: 30
				} catch(Exception ex) {
					System.out.println("bookDetailCount:에러 " + ex);
				} finally {close(rs); close(pstmt);} 
			}
			else {
				
				String countCategory_sql = "SELECT COUNT(*) FROM ISBN WHERE ISBN_CG = ?";
				
				try {
					pstmt = con.prepareStatement(countCategory_sql);
					pstmt.setString(1, keyword);
					rs = pstmt.executeQuery();
					
					if(rs.next()) { listCount = rs.getInt(1);}
					System.out.println("detailcount: " + listCount); // 문학 , listCount: 30
				} catch(Exception ex) {
					System.out.println("bookDetailCount:에러 " + ex);
				} finally {close(rs); close(pstmt);} 
			}
			return listCount;
		}
	
	// 도서검색: 전체 출력//////////////////////////////////////////////////////////////////
		public List<ISBNVO> searchBooksTotal(String keyword, int page) {
			List<ISBNVO> books = new ArrayList<>();
			
			ResultSet rs = null;
			String bookTotal_sql = 
					"select * from (select rownum rnum, A.* from (SELECT * FROM ISBN) A ) where rnum between ? and ?";
			int startrow = (page-1) * 10+1;//11
			int endrow = (page-1) * 10+10;//20
			
			try {
				// execute SQL query to search books
//				PreparedStatement stmt = con.prepareStatement("SELECT * FROM ISBN");
				PreparedStatement stmt = con.prepareStatement(bookTotal_sql);
				stmt.setInt(1, startrow);
				stmt.setInt(2, endrow);
//				ResultSet rs = stmt.executeQuery();
				rs = stmt.executeQuery();
				System.out.println("도서검색 BookDAO: 전체 출력");

				
				// iterate through the search results and add them to the list
				while (rs.next()) {
					ISBNVO book = new ISBNVO();
					book.setISBN_ID(rs.getString("ISBN_ID"));
					book.setISBN_IMG(rs.getString("ISBN_IMG"));
					book.setISBN_TITLE(rs.getString("ISBN_TITLE"));
					book.setISBN_AUTHOR(rs.getString("ISBN_AUTHOR"));
					book.setISBN_TRANS(rs.getString("ISBN_TRANS"));
					book.setISBN_YEAR(rs.getInt("ISBN_YEAR"));
					book.setISBN_COM(rs.getString("ISBN_COM"));
					book.setISBN_CG(rs.getString("ISBN_CG"));
					book.setISBN_INFO(rs.getString("ISBN_INFO"));
					book.setISBN_BOOK(rs.getString("ISBN_BOOK"));
		            book.setISBN_STATUS(rs.getString("ISBN_STATUS"));
					book.setISBN_DEL(rs.getString("ISBN_DEL"));
					books.add(book);
				}
				
				close(stmt);
			} catch (Exception e) {
				System.out.println("도서 전체출력 dao 에러: " + e.getMessage());
				e.printStackTrace();
			} finally { close(rs); }
			System.out.println(books);
			return books;
		}

		// 도서검색: 카테고리별
		// 출력//////////////////////////////////////////////////////////////////
	public List<ISBNVO> searchBooksByCategory(String keyword, int page) {
		List<ISBNVO> books = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String bookCate_sql = 
				"select * from (select rownum rnum, A.* from (SELECT * FROM ISBN WHERE ISBN_CG LIKE ?) A ) where rnum between ? and ?";
	
		String bookCate_sql2 = 
				"select * from (select rownum rnum, A.* from (SELECT * FROM ISBN WHERE ISBN_DEL ='Y') A ) where rnum between ? and ?";
	
		int startrow = (page-1) * 10+1;//11
		int endrow = (page-1) * 10+10;//20
				
		try {	
			if(keyword.equals("미보유")) {
			//	PreparedStatement stmt = con.prepareStatement("SELECT * FROM ISBN WHERE ISBN_DEL = 'Y'");
				stmt = con.prepareStatement(bookCate_sql2);
				stmt.setInt(1, startrow);
				stmt.setInt(2, endrow);
				rs = stmt.executeQuery();
			}else{
				// execute SQL query to search books
	
				stmt = con.prepareStatement(bookCate_sql);
				stmt.setString(1, "%" + keyword + "%");
				stmt.setInt(2, startrow);
				stmt.setInt(3, endrow);
				rs = stmt.executeQuery();		
			}
			System.out.println("도서검색: 카테고리별 출력");
	
			// iterate through the search results and add them to the list
			while (rs.next()) {
				ISBNVO book = new ISBNVO();
				book.setISBN_ID(rs.getString("ISBN_ID"));
				book.setISBN_IMG(rs.getString("ISBN_IMG"));
				book.setISBN_TITLE(rs.getString("ISBN_TITLE"));
				book.setISBN_AUTHOR(rs.getString("ISBN_AUTHOR"));
				book.setISBN_TRANS(rs.getString("ISBN_TRANS"));
				book.setISBN_YEAR(rs.getInt("ISBN_YEAR"));
				book.setISBN_COM(rs.getString("ISBN_COM"));
				book.setISBN_CG(rs.getString("ISBN_CG"));
				book.setISBN_INFO(rs.getString("ISBN_INFO"));
				book.setISBN_BOOK(rs.getString("ISBN_BOOK"));
				book.setISBN_STATUS(rs.getString("ISBN_STATUS"));
		        book.setISBN_DEL(rs.getString("ISBN_DEL"));
				books.add(book);
			}
					
			close(rs);
			close(stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(books);
		return books;
	}
		
		//////////***************************************************************** 확인요망
		
		
//		도서상세정보 출력
		public List<ISBNVO> searchBookDetails(String keyword) {
			List<ISBNVO> books = new ArrayList<>();

			try {
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ISBN WHERE ISBN_BOOK = ?");
				pstmt.setString(1, keyword);
//				pstmt.setString(1, "%" + keyword + "%");
				ResultSet rs = pstmt.executeQuery();
				System.out.println("도서검색: 상세정보 출력");

				while (rs.next()) {
					ISBNVO book = new ISBNVO();
					book.setISBN_ID(rs.getString("ISBN_ID"));
					book.setISBN_IMG(rs.getString("ISBN_IMG"));
					book.setISBN_TITLE(rs.getString("ISBN_TITLE"));
					book.setISBN_AUTHOR(rs.getString("ISBN_AUTHOR"));
					book.setISBN_TRANS(rs.getString("ISBN_TRANS"));
					book.setISBN_YEAR(rs.getInt("ISBN_YEAR"));
					book.setISBN_COM(rs.getString("ISBN_COM"));
					book.setISBN_CG(rs.getString("ISBN_CG"));
					book.setISBN_INFO(rs.getString("ISBN_INFO"));
	                book.setISBN_BOOK(rs.getString("ISBN_BOOK"));
		            book.setISBN_STATUS(rs.getString("ISBN_STATUS"));
	                book.setISBN_DEL(rs.getString("ISBN_DEL"));
					books.add(book);
				}
				close(rs);
				close(pstmt);
			} catch (Exception e) {
				System.out.println("도서상세정보 출력 error : " + e.getMessage());
			}
			return books;
		}
		
		
//		도서대출: 대출가능도서 체크
		public String getRentID(String keyword) throws Exception {
			
			PreparedStatement pstmt;
			ResultSet rs;
			String bookID = null;
			
			try {	
			pstmt = con.prepareStatement("SELECT ISBN_BOOK FROM ISBN WHERE ISBN_BOOK = ? AND ISBN_STATUS = "+"'N'"+" AND "+"ISBN_DEL = "+"'N'");
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			bookID = rs.getString(1);
			}
			System.out.println("도서대출: 대출가능 도서가져오기");
			System.out.println(bookID);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("도서대출: 대출가능 도서가져오기 error");
			} 
			return bookID;

		}	
	
		
//		도서대출: 도서대출내역 삽입
		public void insertRentInfo(String bookISBNBook, String id) throws Exception {
			
			PreparedStatement pstmtA;
			
			try {
				pstmtA = con.prepareStatement("INSERT INTO RENT(RENT_ID_SEQ,RENT_BOOK,RENT_USER,RENT_DATE,RENT_RETURN,RENT_STATUS)"
						+ "VALUES(TO_CHAR(SYSTIMESTAMP,'YYMMDD-HHMMSS')||'-'||RENT_ID_SEQ.NEXTVAL,?,?,sysdate,sysdate+14,"+"'Y'"+")");
				pstmtA.setString(1, bookISBNBook);
				pstmtA.setString(2, id);
				int cnt = pstmtA.executeUpdate();
				System.out.println("결과 cnt: "+cnt);
				
				if(cnt>0) commit(con);
				else rollback(con);
				System.out.println("도서대출: 도서대출내역 삽입");
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("도서대출: 도서대출내역 삽입 error");
			}
		}
		
//		도서대출: ISBN에 대출처리		
		public void updateISBNBook(String bookISBNBook) throws Exception {
			
			PreparedStatement pstmtA;
			
			try {
				pstmtA = con.prepareStatement("UPDATE ISBN SET ISBN_STATUS = "+"'Y'"+" WHERE ISBN_BOOK = ?");
				pstmtA.setString(1, bookISBNBook);
				pstmtA.executeUpdate();
				int cnt = pstmtA.executeUpdate();
				System.out.println("결과 cnt: "+cnt);
				
				if(cnt>0) commit(con);
				else rollback(con);
				System.out.println("도서대출: ISBN에 대출처리 삽입");

				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("도서대출: ISBN에 대출처리 error");
			}
		}
		
//		도서대출: 도서대출내역 가져오기
		public List<RentVO> getRentInfo(String bookISBNBook, String id) throws Exception {
			PreparedStatement pstmt;
			ResultSet rs;
			List<RentVO> bookID = new ArrayList<>();
			
			try {	
			pstmt = con.prepareStatement("select * from rent where rent_book = ? and rent_user = ? and rent_status = 'Y' order by rent_date");
			pstmt.setString(1, bookISBNBook);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();

			// RENT_BOOK : ISBN_TITLE?
			while (rs.next()) {
				RentVO book = new RentVO();
				book.setRENT_ID_SEQ(rs.getString("RENT_ID_SEQ"));
				book.setRENT_BOOK(rs.getString("RENT_BOOK"));
				book.setRENT_USER(rs.getString("RENT_USER"));
				book.setRENT_DATE(rs.getDate("RENT_DATE"));
				book.setRENT_RETURN(rs.getDate("RENT_RETURN"));
				book.setRENT_STATUS(rs.getString("RENT_STATUS"));
				
				bookID.add(book);
			}
			
			System.out.println("도서대출: 도서대출내역 가져오기");
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("도서대출: 도서대출내역 가져오기 error");

			} finally {
//				close(con);
			}
			return bookID;
		}
		
		// 선택한 유저의 대출내역 가져오기
		public List<RentVO> getSelectedUserRentInfo(String id) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<RentVO> bookID = new ArrayList<>();
			String sql = "SELECT * FROM RENT WHERE RENT_USER = ? ORDER BY RENT_DATE";
			System.out.println("SELECT * FROM RENT WHERE RENT_USER = ?" + id);
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				// RENT_BOOK : ISBN_TITLE?
				while (rs.next()) {
					RentVO book = new RentVO();
					book.setRENT_ID_SEQ(rs.getString("RENT_ID_SEQ"));
					book.setRENT_BOOK(rs.getString("RENT_BOOK"));
					book.setRENT_USER(rs.getString("RENT_USER"));
					book.setRENT_DATE(rs.getDate("RENT_DATE"));
					book.setRENT_RETURN(rs.getDate("RENT_RETURN"));
					book.setRENT_STATUS(rs.getString("RENT_STATUS"));
					bookID.add(book);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("도서대출: getSelectedUserRentInfo dao 가져오기 error");

			} finally {
				close(rs); close(pstmt);
			}
			return bookID;
		}
		
		// ******************************************
		// 선택한 유저의 도서대출 개수 가져오기
		public int getRentCount(String id) {
			System.out.println("선택한 유저의 대출개수 가져오기");
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT COUNT(*) FROM RENT WHERE RENT_USER = ? ";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				System.out.println("rs의 값: " + rs);
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			}catch(Exception e) {e.printStackTrace();}
			finally { close(rs); close(pstmt);}
			return listCount;
		}
		
		///////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////
		public ArrayList<RentInfoVO> getUserRentInfo(String id, int page){	
			System.out.println("bookDAO 페이징용");
			PreparedStatement pstmt;
			ResultSet rs;
			
			ArrayList<RentInfoVO> rentList = new ArrayList<>();
			
//			String user_detail_sql = "select * from (select rownum rnum, A.* from (SELECT * FROM USERS WHERE USER_NAME LIKE ?) A ) where rnum between ? and ?";
			String rentinfo_sql = "select * from (select rownum rnum, A.* from (select distinct m.*, i.isbn_title, i.isbn_author from (select r.rent_id_seq, r.rent_date, r.rent_return, r.rent_book, r.rent_return_date from rent r, users u where r.rent_user = ?) m , isbn i where m.rent_book = i.isbn_book ORDER BY RENT_DATE) A ) where rnum between ? and ?"; 
			
			int startrow = (page-1) * 10+1;
			int endrow = (page-1) * 10+10;
			try {
				pstmt = con.prepareStatement(rentinfo_sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					RentInfoVO rentInfoList = new RentInfoVO();
					rentInfoList.setISBN_AUTHOR(rs.getString("ISBN_AUTHOR"));
					rentInfoList.setISBN_TITLE(rs.getString("ISBN_TITLE"));
					rentInfoList.setRENT_BOOK(rs.getString("RENT_BOOK"));
					rentInfoList.setRENT_DATE(rs.getDate("RENT_DATE"));
					rentInfoList.setRENT_ID_SEQ(rs.getString("RENT_ID_SEQ"));
					rentInfoList.setRENT_RETURN(rs.getDate("RENT_RETURN"));
					rentInfoList.setRENT_RETURN_DATE(rs.getDate("RENT_RETURN_DATE"));

					System.out.println("rs의 저거" + rs.getString("ISBN_AUTHOR"));
					rentList.add(rentInfoList);
				}
				close(rs);
				close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
			return rentList;
		}
		///////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////
		
		// 도서 반납하기
		public int returnBooksISBN(String id) {
			// RENT_ID_SEQ
			PreparedStatement pstmt = null;
			int resultCount = 0;
			int resultCount1 = 0;
			int resultCount2 = 0;
			
//			String sql1 = "UPDATE ISBN SET ISBN_STATUS = 'N' WHERE ISBN_BOOK = ?";
			String sql1 = "UPDATE ISBN SET ISBN_STATUS = 'N' WHERE ISBN_BOOK = (SELECT ISBN_BOOK FROM ISBN WHERE ISBN_BOOK = (SELECT RENT_BOOK FROM RENT WHERE RENT_ID_SEQ = ?))";
			String sql2 = "UPDATE RENT SET RENT_STATUS = 'N', RENT_RETURN_DATE = SYSDATE, RENT_DELAY = TO_NUMBER(TRUNC(SYSDATE - TO_DATE(RENT_RETURN))) WHERE RENT_ID_SEQ = ? ";

			//			String sql1 = "update isbn set isbn_status = 'N' where isbn_book=(select isbn_book from isbn where isbn_book=(select rent_book from rent where rent_id_seq=?))";
//			String sql2 = "UPDATE RENT SET RENT_STATUS ='N', RENT_RETURN_DATE = SYSDATE WHERE RENT_ID_SEQ = ?";
			try {
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, id);
				resultCount1 = pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, id);
				resultCount2 = pstmt.executeUpdate();
				
				resultCount = resultCount1 + resultCount2;
				
				System.out.println("isbn 테이블 변경:"+resultCount1);
				System.out.println("rent 테이블 변경"+resultCount2);
				
				if(resultCount > 1) { 
					commit(con);
					System.out.println("반납완료");
				}
				else { 
					rollback(con);
					System.out.println("반납실패");
				}
			} catch (Exception e) {e.printStackTrace();}
			finally { close(pstmt); }
			
			return resultCount;
		}
		
//		회원여부 검증		
		public String selectLoginId(UserVO user) {
			String loginId = null;
			PreparedStatement pstmt;
			ResultSet rs;
			String sql = "SELECT user_id FROM users left outer join rent on rent.rent_user = users.user_id AND (rent_delay IS NULL OR rent_delay <= 0 OR rent_delay = '') where user_id = ?";
//			String sql = "select user_id from users where user_id=?";
			System.out.println("userDao의 user_id: " + user.getUSER_ID());

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUSER_ID());

				System.out.println("selectLoginId : " + user.getUSER_ID());
				rs = pstmt.executeQuery();

				if (rs.next()) {
					loginId = rs.getString("USER_ID");
				}
			} catch (Exception ex) {
				System.out.println("에러: " + ex);
			} 
			return loginId;
		}
		
		//대출된 도서명 호출
				public String getRentBookName(String keyword) {
					Statement st = null;
					ResultSet rs = null;
					String getBook = null;
					System.out.println("getRentBookName: " + keyword);
					String query = "select ISBN_TITLE from ISBN where ISBN_BOOK = " + keyword;
					
					try {
						st = con.createStatement();
						rs = st.executeQuery(query);
					
						if(rs.next()) {
							getBook = rs.getString("ISBN_TITLE");
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						close(rs); close(st);
					}
					return getBook;
				}
		
				
//////////////////////////////////////////////
		
//	도서 등록
	public int insertArticle(ISBNVO isbnvo){
		PreparedStatement pstmt = null;
		String sql="";
		String sql_d="";
		int insertCount=0;
		
		try{
			sql="insert"
			+" into isbn(ISBN_ID, ISBN_IMG, ISBN_TITLE, ISBN_AUTHOR, ISBN_TRANS, ISBN_YEAR, ISBN_COM, ISBN_CG, ISBN_INFO, ISBN_BOOK)"
			+" values(?,?,?,?,?,?,?,?,?,?)";
			
			sql_d="insert"
			+" into isbn(ISBN_ID, ISBN_TITLE, ISBN_AUTHOR, ISBN_TRANS, ISBN_YEAR, ISBN_COM, ISBN_CG, ISBN_INFO, ISBN_BOOK)"
			+" values(?,?,?,?,?,?,?,?,?)";
			
			//sql="insert all"
			//+" into isbn(ISBN_ID, ISBN_IMG, ISBN_TITLE, ISBN_AUTHOR, ISBN_TRANS, ISBN_YEAR, ISBN_COM, ISBN_CG, ISBN_INFO)"
			//+" values(?,?,?,?,?,?,?,?,?)"
			//+" into book(book_id, book_isbn) values(?,?) select * from dual";
			
			//sql_d="insert all"
			//+" into isbn(ISBN_ID, ISBN_TITLE, ISBN_AUTHOR, ISBN_TRANS, ISBN_YEAR, ISBN_COM, ISBN_CG, ISBN_INFO)"
			//+" values(?,?,?,?,?,?,?,?)"
			//+" into book(book_id, book_isbn) values(?,?) select * from dual";
			
		
		if(isbnvo.getISBN_IMG()!=null) {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbnvo.getISBN_ID());
			pstmt.setString(2, isbnvo.getISBN_IMG());
			pstmt.setString(3, isbnvo.getISBN_TITLE());
			pstmt.setString(4, isbnvo.getISBN_AUTHOR());
			pstmt.setString(5, isbnvo.getISBN_TRANS());
			pstmt.setInt(6, isbnvo.getISBN_YEAR());
			pstmt.setString(7, isbnvo.getISBN_COM());
			pstmt.setString(8, isbnvo.getISBN_CG());
			pstmt.setString(9, isbnvo.getISBN_INFO());
			pstmt.setString(10, isbnvo.getISBN_BOOK());
			insertCount=pstmt.executeUpdate();
		}else {
			pstmt = con.prepareStatement(sql_d);
			pstmt.setString(1, isbnvo.getISBN_ID());
			pstmt.setString(2, isbnvo.getISBN_TITLE());
			pstmt.setString(3, isbnvo.getISBN_AUTHOR());
			pstmt.setString(4, isbnvo.getISBN_TRANS());
			pstmt.setInt(5, isbnvo.getISBN_YEAR());
			pstmt.setString(6, isbnvo.getISBN_COM());
			pstmt.setString(7, isbnvo.getISBN_CG());
			pstmt.setString(8, isbnvo.getISBN_INFO());
			pstmt.setString(9, isbnvo.getISBN_BOOK());
			insertCount=pstmt.executeUpdate();
		}
		
		System.out.println(isbnvo.getISBN_ID());
		System.out.println(isbnvo.getISBN_IMG());
		System.out.println(isbnvo.getISBN_TITLE());
		System.out.println(isbnvo.getISBN_AUTHOR());
		System.out.println(isbnvo.getISBN_TRANS());
		System.out.println(isbnvo.getISBN_YEAR());
		System.out.println(isbnvo.getISBN_COM());
		System.out.println(isbnvo.getISBN_CG());
		System.out.println(isbnvo.getISBN_INFO());
		System.out.println(isbnvo.getISBN_BOOK());
	
		}catch(Exception ex){
			System.out.println("bookInsert 에러 : "+ex);
		}finally{
			close(pstmt);
		}
		return insertCount;
	}
	
	
	//도서정보 수정.
			public int updateArticle(ISBNVO isbnvo){
				int updateCount = 0;
				PreparedStatement pstmt = null;
//				PreparedStatement pstmt2 = null;
				
				String i_sql="update ISBN set ISBN_IMG=?, ISBN_TITLE=?, ISBN_AUTHOR=?,"
						+ " ISBN_TRANS=?, ISBN_YEAR=?, ISBN_COM=?, ISBN_CG=?, ISBN_INFO=?, ISBN_BOOK=?, ISBN_DEL=?"
						+ " where ISBN_ID=? and ISBN_STATUS='N'";
				
				String i_sql_d="update ISBN set ISBN_TITLE=?, ISBN_AUTHOR=?,"
						+ " ISBN_TRANS=?, ISBN_YEAR=?, ISBN_COM=?, ISBN_CG=?, ISBN_INFO=?, ISBN_BOOK=?, ISBN_DEL=?"
						+ " where ISBN_ID=? and ISBN_STATUS='N'";
				
//				String b_sql="update BOOK set BOOK_ID=? where BOOK_ISBN=?";
//				String i_sql="update ISBN set ISBN_IMG=?, ISBN_TITLE=?, ISBN_AUTHOR=?,"
//						+ " ISBN_TRANS=?, ISBN_YEAR=?, ISBN_COM=?, ISBN_CG=?, ISBN_INFO=?"
//						+ " where ISBN_ID=(select BOOK_ISBN from BOOK where BOOK_ISBN=?)";
//				String i_sql_d="update ISBN set ISBN_TITLE=?, ISBN_AUTHOR=?,"
//						+ " ISBN_TRANS=?, ISBN_YEAR=?, ISBN_COM=?, ISBN_CG=?, ISBN_INFO=?"
//						+ " where ISBN_ID=(select BOOK_ISBN from BOOK where BOOK_ISBN=?)";

				try{
					if(isbnvo.getISBN_IMG()!=null) {
						pstmt = con.prepareStatement(i_sql);
						pstmt.setString(1, isbnvo.getISBN_IMG());
						pstmt.setString(2, isbnvo.getISBN_TITLE());
						pstmt.setString(3, isbnvo.getISBN_AUTHOR());
						pstmt.setString(4, isbnvo.getISBN_TRANS());
						pstmt.setInt(5, isbnvo.getISBN_YEAR());
						pstmt.setString(6, isbnvo.getISBN_COM());
						pstmt.setString(7, isbnvo.getISBN_CG());
						pstmt.setString(8, isbnvo.getISBN_INFO());
						pstmt.setString(9, isbnvo.getISBN_BOOK());
						pstmt.setString(10, isbnvo.getISBN_DEL());
						pstmt.setString(11, isbnvo.getISBN_ID());
						updateCount = pstmt.executeUpdate();
					
					}else{
						pstmt = con.prepareStatement(i_sql_d);
						pstmt.setString(1, isbnvo.getISBN_TITLE());
						pstmt.setString(2, isbnvo.getISBN_AUTHOR());
						pstmt.setString(3, isbnvo.getISBN_TRANS());
						pstmt.setInt(4, isbnvo.getISBN_YEAR());
						pstmt.setString(5, isbnvo.getISBN_COM());
						pstmt.setString(6, isbnvo.getISBN_CG());
						pstmt.setString(7, isbnvo.getISBN_INFO());
						pstmt.setString(8, isbnvo.getISBN_BOOK());
						pstmt.setString(9, isbnvo.getISBN_DEL());
						pstmt.setString(10, isbnvo.getISBN_ID());
						updateCount = pstmt.executeUpdate();
					}
				}catch(Exception ex){
					System.out.println("bookModify 에러 : " + ex);
				}finally{
					close(pstmt);
				}
				return updateCount;
			}
	
			//도서정보 삭제.
			public int deleteArticle(String isbn_book){
				PreparedStatement pstmt = null;
				String sql="update ISBN set ISBN_DEL='Y' where ISBN_BOOK=? and ISBN_DEL='N' and ISBN_STATUS='N'";
				int deleteCount=0;

				try{
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, isbn_book);
					deleteCount=pstmt.executeUpdate();
					
					System.out.println("삭제sql: "+sql);
					System.out.println("삭제sql: "+pstmt);
					System.out.println("삭제sql: "+deleteCount);
					System.out.println("삭제sql: "+isbn_book);
				}catch(Exception ex){
					System.out.println("bookDelete 에러 : "+ex);
				}	finally{
					close(pstmt);
				}
				return deleteCount;
			}
			
			
			
	
	
	
			//글쓴이인지 확인.
			public boolean isArticleAdmin(String admin_id){
				System.out.println("수정DAO: "+admin_id);
				Statement stmt=null;
//				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String admin_sql="select USER_ID from USERS where USER_ID='admin'";
				boolean isWriter = false;
				System.out.println("다오에서 어드민확인: "+admin_id);
				try{
					stmt=con.createStatement();
//					pstmt=con.prepareStatement(admin_sql);
//					pstmt.setString(1, admin);
					rs=stmt.executeQuery(admin_sql);
					rs.next();
					if(admin_id.equals(rs.getString("USER_ID"))){
						isWriter = true;
					}
				}catch(SQLException ex){
					System.out.println("isWriter 에러 : "+ex);
				}
				finally{
					close(stmt);
				}
				return isWriter;
			}
		
}
