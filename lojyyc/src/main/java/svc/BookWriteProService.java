package svc;

import java.sql.Connection;
import dao.BookDAO;
import vo.ISBNVO;
import static db.JdbcUtil.*;

public class BookWriteProService {
	public boolean registArticle(ISBNVO isbnvo) throws Exception{
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int insertCount = bookDAO.insertArticle(isbnvo);
		
		if(insertCount > 0){
			commit(con);
			isWriteSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isWriteSuccess;
	}
}
