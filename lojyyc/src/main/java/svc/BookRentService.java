package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.UserDAO;
import vo.ActionForward;
import vo.RentVO;
import vo.UserVO;

public class BookRentService {

	public List<RentVO> getRentInfo(String keyword, String id) throws Exception {

		UserVO user = new UserVO();
		user.setUSER_ID(id);
		List<RentVO> bookRent = new ArrayList<>();
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);

		System.out.println("BookRentService 1 keyword = ISBN_BOOK:" + keyword);
		System.out.println("BookRentService 1 id :" + id);

		String loginId = bookDAO.selectLoginId(user);

		if (loginId != null) { //회원검증
			
			String bookISBNBook = null;
			bookISBNBook = bookDAO.getRentID(keyword);
			
			if (bookISBNBook != null) { //대출가능도서 검증
				bookDAO.updateISBNBook(bookISBNBook);
				System.out.println("BookRentService 2 bookISBNbook :" + bookISBNBook);
				
				System.out.println("BookRentService 3 keyword :" + bookISBNBook);
				System.out.println("BookRentService 3 id :" + id);
	
				bookDAO.insertRentInfo(bookISBNBook, id);
				
				System.out.println("BookRentService 4 bookISBNBook:" + bookISBNBook);
				System.out.println("BookRentService 4 id:" + id);

				bookRent = bookDAO.getRentInfo(bookISBNBook, id);
			}
			
			close(con);

			return bookRent;

		} else {
			close(con);
			return bookRent;
		}
	}
	
	// 검색시 목록개수조회 -------------------------------------------------------------------
		public int getSearchListCount(String id) throws Exception{
			System.out.println("service2222");
			Connection con = getConnection();
			BookDAO bookDAO = BookDAO.getInstance();
			bookDAO.setConnection(con);
			int rentCount = bookDAO.getRentCount(id);
			close(con);
			return rentCount;
		}

}
