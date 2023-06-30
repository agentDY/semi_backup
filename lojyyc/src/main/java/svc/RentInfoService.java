package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.*;

import dao.BookDAO;
import dao.UserDAO;
import vo.RentInfoVO;
import vo.RentVO;
import vo.UserVO;

public class RentInfoService {
	
	public ArrayList<RentInfoVO> getRentInfo(String id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
//		rentBookUserId = rainys2s2
		ArrayList<RentInfoVO> rentInfo = null;
		
		try {
			rentInfo = userDAO.searchRentBook(id);
			System.out.println("RentInfoService 클래스의 getRentInfo 매개변수 id값은? : " + id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { close(con);}
		return rentInfo;
	}
	
	// 선택한 회원의 대출정보 가져오기
	public List<RentVO> getUserRentInfo(String id){
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		List<RentVO> rentInfo = null;
		try {
			rentInfo = bookDAO.getSelectedUserRentInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { close(con);}
		
		return rentInfo;
	}
	
//	public List<RentInfoVO> getRentTitle(String id){
//		Connection con = getConnection();
//		BookDAO bookDAO = BookDAO.getInstance();
//		List<RentInfoVO> rentTitle = bookDAO.getRentTitle(id);
//		
//		return rentTitle;
//	}
	
	public int getRentInfoCount(String id){
		Connection con = getConnection();
		BookDAO rentDAO = BookDAO.getInstance();
		rentDAO.setConnection(con);
		int rentBookListCount = 0;
		
		try {
			rentBookListCount = rentDAO.getRentCount(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { close(con);}

		return rentBookListCount;
	}
	
	// 페이징용
	public ArrayList<RentInfoVO> getUserRentInfo(String id, int page) throws Exception {
		System.out.println("service4444");
		Connection con = getConnection();
		BookDAO rentDAO = BookDAO.getInstance();
		rentDAO.setConnection(con);
		ArrayList<RentInfoVO> searchUserList = null;
		System.out.println("검색해야하는 유저으 아이디" + id);
		
		try {
			searchUserList = rentDAO.getUserRentInfo(id, page);
			System.out.println("객체가 무슨 리스트를 가지고있냐" + searchUserList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally { close(con);}
		return searchUserList;
	}

}
