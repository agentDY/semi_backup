package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import dao.BookDAO;
import vo.ISBNVO;

public class BookSearchTotalService {


	public List<ISBNVO> getBookList(String keyword, int page) throws Exception{
		
        List<ISBNVO> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookList = bookDAO.searchBooksTotal(keyword, page);
		close(con);
		return bookList;
	}
	
	public int getBookListCount(String keyword) throws Exception {
		System.out.println("service2222");
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int bookListCount = bookDAO.bookDetailCount(keyword);
		close(con);
		return bookListCount;
	}
}
