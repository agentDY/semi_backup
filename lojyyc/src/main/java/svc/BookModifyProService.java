package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import vo.ISBNVO;
import dao.BookDAO;

public class BookModifyProService {

	public boolean isArticleWriter(String admin_id) throws Exception {
		boolean isArticleAdminWriter = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		System.out.println("서비스pass: "+admin_id);
		isArticleAdminWriter = bookDAO.isArticleAdmin(admin_id);
		System.out.println("트루받아야함: "+isArticleAdminWriter);
		close(con);
		return isArticleAdminWriter;
	}

	public boolean modifyArticle(ISBNVO isbnvo) throws Exception {
		boolean isModifySuccess = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int updateCount = bookDAO.updateArticle(isbnvo);
		
		if(updateCount > 0){
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
