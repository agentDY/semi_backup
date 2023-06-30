package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BookDAO;
import dao.NoticeDAO;
import vo.RentVO;

public class BookReturnService {

	 public boolean BookReturnService(String id) {
		boolean isModifySuccess = false;
		System.out.println("book return service1");
		System.out.println("book return service1의 매개변수" + id);
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int updateCount = bookDAO.returnBooksISBN(id);
			
		if(updateCount > 1){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	 }
}
