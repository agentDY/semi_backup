package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;
import java.sql.Connection;
import java.util.ArrayList;
import dao.UserDAO;
import vo.UserVO;


public class MyPageService {
	
	public ArrayList<UserVO> getUserInfo(String id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<UserVO> memberList = userDAO.selectUserInfo(id);
		close(con);
		return memberList;
	}
	
}
