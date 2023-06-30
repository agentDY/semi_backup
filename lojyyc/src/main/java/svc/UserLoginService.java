package svc;

import java.sql.Connection;
import static db.JdbcUtil.*;

import dao.UserDAO;
import vo.UserVO;

public class UserLoginService {
	public boolean login(UserVO user) {
		Connection con =  getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		boolean loginResult = false;
		UserVO result = userDAO.selectLoginId(user);
		String loginId = result.getUSER_ID();
		System.out.println("UserLoginService loginId 여부 : " + loginId);
		String loginDel = result.getUSER_DEL();
		System.out.println("UserLoginService del 여부 : " + loginDel);

//		loginDel.equals('N') > 정상회원
//		loginId == null > 비정상회원
		
		if( !(loginId == null) && !(loginDel.equals("Y"))) {
			System.out.println("delete 된 아이디 검증성공");
			loginResult = true;
		} 
		return loginResult;
	}
	
	public String  nm(UserVO user) {
		Connection con =  getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		String loginName = userDAO.selectLoginNm(user);
		
		close(con);
	return loginName;
		
	}
}
