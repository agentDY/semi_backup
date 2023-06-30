package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.UserDAO;
import vo.UserVO;

public class UserListService {
	
	// 전체목록 개수조회 --------------------------------------------------------------------
	public int getListCount() throws Exception{
		System.out.println("service1111");
		int listCount = 0;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		listCount = userDAO.selectListCount();
		close(con);
		return listCount;
	}
	
	// 검색시 목록개수조회 -------------------------------------------------------------------
	public int getSearchListCount(String name) throws Exception{
		System.out.println("service2222");
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int searchListCount = userDAO.getSearchListCount(name);
		close(con);
		return searchListCount;
	}
	
	// 아이디 검색시 목록개수조회 -------------------------------------------------------------------
	public int getSearchListCountById(String id) throws Exception{
		System.out.println("service2222");
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int searchListCount = userDAO.getSearchListCountById(id);
		close(con);
		return searchListCount;
	}

	// admin 거를때 쓰는거 -------------------------------------------------------------------
	public ArrayList<UserVO> getUserList(){
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<UserVO> userList = userDAO.selectUserList();
		close(con);
		return userList;
	}
	
	
	// 회원목록가져오기 (전체목록) -------------------------------------------------------------------
	public ArrayList<UserVO> getUserList(int page, int limit) throws Exception{
		System.out.println("service3333");
		ArrayList<UserVO> userList = null;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		userList = userDAO.selectAllUserList(page,limit);
		close(con);
		return userList;
	}
	
	// 이름으로 회원검색 service-----------------------------------------------------------------------------
	public ArrayList<UserVO> getSearchListByName(String name, int page) throws Exception {
		System.out.println("service4444");
		ArrayList<UserVO> searchUserList = null;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		searchUserList = userDAO.selectSearchListByName(name, page);
		close(con);
		return searchUserList;
	}
	
	// 아이디로 회원검색 service-----------------------------------------------------------------------------
	public ArrayList<UserVO> getSearchListById(String id, int page) throws Exception {
		System.out.println("service5555");
		ArrayList<UserVO> searchUserList = null;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		searchUserList = userDAO.selectSearchListById(id, page);
		close(con);
		return searchUserList;
	}
}
