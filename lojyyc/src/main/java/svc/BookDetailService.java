package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import dao.BookDAO;
import dao.UserDAO;
import vo.ISBNVO;

public class BookDetailService {

	public List<ISBNVO> getBookList(String keyword) throws Exception{
		
        List<ISBNVO> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookList = bookDAO.searchBookDetails(keyword);
    	System.out.println("BookDetailService :" + bookList);

		close(con);
		return bookList;
	}


	
	
		
}
