package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.UserDAO;
import vo.UserVO;

public class EditInfoService {
	public UserVO UserInfo(UserVO user) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		UserVO userUpdate = userDAO.UserInfo(user);
		
		if(user != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return userUpdate;
	}
	
}
