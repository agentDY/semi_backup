package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.UserDAO;

public class UserDeleteService {
	
	public boolean deleteUserCommit(String deleteId, String deletePW) {
		System.out.println("회원탈퇴 경로찍기 Service");
		boolean deleteResult = false;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int deleteCount = userDAO.deleteUser(deleteId, deletePW);
		
		if(deleteCount != 0) {
			commit(con);
			deleteResult = true;
		} else {
			rollback(con);
		} close(con);
		return deleteResult;
	} 
}
