package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.*;
import dao.BookDAO;
import dao.UserDAO;
import vo.ISBNVO;

public class BookSearchService {
	// 책 제목으로 검색한 리스트를 가져오게 ----------------------------------------------------------------------
	public ArrayList<ISBNVO> getTitleSearchBookList(String keyword, int page) throws Exception{
		System.out.println("searchBooksService");
		ArrayList<ISBNVO> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookList = bookDAO.searchBooksByTitle(keyword, page);
		close(con);
		return bookList;
	}

	// 저자이름으로 검색한 리스트를 가져오게 ----------------------------------------------------------------------
	public ArrayList<ISBNVO> getAuthorSearchBookList(String keyword, int page) throws Exception{
		System.out.println("searchBooksService");
		ArrayList<ISBNVO> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookList = bookDAO.searchBooksByAuthor(keyword, page);
		close(con);
		return bookList;
	}
	
	// 출판사 이름으로 검색한 리스트를 가져오게 ----------------------------------------------------------------------
	public ArrayList<ISBNVO> getCompanySearchBookList(String keyword, int page) throws Exception{
		System.out.println("searchBooksService");
		ArrayList<ISBNVO> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookList = bookDAO.searchBooksByCompany(keyword, page);
		close(con);
		return bookList;
	}
	
	// 검색한 책 개수 구하기 ---------------------------------------------------------------------------------
	public int getBookListCount(String keyword, String keyword_type) throws Exception {
		System.out.println("service2222");
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int searchListCount = bookDAO.getSearchListCount(keyword, keyword_type);
		close(con);
		return searchListCount;
	}
}
