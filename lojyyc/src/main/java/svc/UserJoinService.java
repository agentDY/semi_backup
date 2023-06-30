package svc;

import vo.UserVO;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.UserDAO;

public class UserJoinService {
	public boolean joinUser(UserVO user) {
		boolean joinSuccess = false;
		UserDAO userDAO = UserDAO.getInstance();
		Connection con = getConnection();
		userDAO.setConnection(con);
		int insertCount = userDAO.insertUser(user);
		if(insertCount > 0 ) {
			joinSuccess = true;
			commit(con);
		}
		else {rollback(con);}
		close(con);
		return joinSuccess;
	}
}
