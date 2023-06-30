package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;
import dao.BookDAO;
import vo.ISBNVO;

public class BookDeleteProService {

	public boolean isArticleWriter(String pass) throws Exception {
		System.out.println(pass);
		boolean isArticleAdminWriter = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		isArticleAdminWriter = bookDAO.isArticleAdmin(pass);
		close(con);
		return isArticleAdminWriter;
	}

	public boolean removeArticle(String isbn_book) throws Exception{
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int deleteCount = bookDAO.deleteArticle(isbn_book);
		System.out.println("딜리트서비스: "+isbn_book);
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}
}
