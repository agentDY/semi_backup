package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import vo.RentInfoVO;
import vo.UserVO;

public class UserDAO {
	private DataSource ds;
	private Connection con;
//	private PreparedStatement pstmt = null;
//	private ResultSet rs;
	
	private static UserDAO userDAO;
	
//	 UserDAO 객체 만들기 -------------------------------------------------------------------
	public static UserDAO getInstance() {
		if(userDAO == null){
			userDAO = new UserDAO();
		}
		return userDAO;
	}
	
//	Connection 가져오기 -------------------------------------------------------------------------
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	   //////////////////
	//DataSource 초기화
	public UserDAO() {
	     try {
	          Context initCtx = new InitialContext();
	          Context envCtx = (Context)initCtx.lookup("java:comp/env");
	          ds = (DataSource)envCtx.lookup("jdbc/OracleDB");
	     } catch (NamingException e) {
	           e.printStackTrace();
	     }
	}   
	   
	// 아이디 체크
	public boolean idCheck(UserVO uv) {
		boolean idchk = false;
        if(uv.getUSER_ID() == null || uv.getUSER_ID().length() == 0) {
        	System.out.println("아이디가 없습니다.");
        	idchk = true;
        	return idchk;
        }else {
        String SQL = "select count(*) as result from users where USER_ID = ? ";
         
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
        	con = ds.getConnection();
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, uv.getUSER_ID());

            rs = pstmt.executeQuery();

            while(rs.next()) {
	            int result = rs.getInt(1);
	            if(result > 0) idchk = true;
            }
            return idchk;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {close(rs); close(pstmt);close(con);}
        }
        return idchk;
    }
	
	//로그인
		public UserVO selectLoginId(UserVO user) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql = "select user_id, user_del from users where user_id= ? and user_pw=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUSER_ID());
				pstmt.setString(2, user.getUSER_PW());
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					user.setUSER_ID(rs.getString("user_id"));
					user.setUSER_DEL(rs.getString("user_del"));
				}
				System.out.println("UserDAO : " + user.getUSER_ID());	// admin
				System.out.println("UserDAO : " + user.getUSER_DEL());	// N 
				
			}catch(Exception ex) {System.out.println("에러: "+ex);}
			finally {close(rs); close(pstmt);}
			return user;
		}
		
		//로그인한 유저이름 리턴
		public String selectLoginNm(UserVO user) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String loginId = null;
			String sql = "select user_name from users where user_id=? and user_pw=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUSER_ID());
				pstmt.setString(2, user.getUSER_PW());
				
				System.out.println(user.getUSER_ID());
				System.out.println(user.getUSER_PW());
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					user.setUSER_NAME(rs.getString("user_name"));
				}
			}catch(Exception ex) {System.out.println("에러: "+ex);}
			finally {close(rs); close(pstmt);}
			return loginId;
		}
	
	
	// 회원가입
//	public int insertUser(UserVO user) {
//		String sql = "insert into users values(?,?,?,?,?,?,?,?)";
//		int insertCount = 0;
 //
//PreparedStatement pstmt = null;
 //
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, user.getUSER_ID());
//			pstmt.setString(2, user.getUSER_NAME());
//			pstmt.setString(3, user.getUSER_PW());
//			pstmt.setString(4, user.getUSER_ADDR1() + user.getUSER_ADDR2() + user.getUSER_ADDR3() + user.getUSER_ADDR4());
//			pstmt.setString(5, user.getUSER_TEL());
//			pstmt.setString(6, user.getUSER_EMAIL());
//			pstmt.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
//			pstmt.setString(8, user.getUSER_DEL());
//			insertCount = pstmt.executeUpdate();
//		} catch (Exception ex) {
//			System.out.println("joinUser 에러: " + ex);
//		} finally {
//			close(pstmt);
//		}
//		return insertCount;
//	}

		// 개인정보 변경
				public UserVO UserInfo(UserVO user) {
					 
					PreparedStatement pstmt = null;
					ResultSet rs = null;
		 
					String sql = "update users set user_addr = ?, user_tel = ?, user_email = ?, user_pw = ? where user_id = ?";

					try {
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, user.getUSER_ADDR());
						pstmt.setString(2, user.getUSER_TEL());
						pstmt.setString(3, user.getUSER_EMAIL());
						pstmt.setString(4, user.getUSER_PW());
						pstmt.setString(5, user.getUSER_ID());
						System.out.println("user의 USER_ADDR : " + user.getUSER_ADDR());
						System.out.println("user의 USER_TEL : " + user.getUSER_TEL());
						System.out.println("user의 USER_EMAIL : " + user.getUSER_EMAIL());
						System.out.println("user의 USER_PW : " + user.getUSER_PW());
						System.out.println("user의 USER_ID : " + user.getUSER_ID());

						int updateCount = pstmt.executeUpdate();
						
						if (updateCount > 0) {
							System.out.println("업데이트 성공");
						}else {
							user = null;
						}


					} catch (Exception e) {
						System.out.println("editUserInfo error : " + e);
					} finally {
						close(pstmt);
					}
					return user;
				}
		// 사용자 정보 가져오기
	public ArrayList<UserVO> selectUserInfo(String id) {
		String sql = "select * from users where user_id = ?";
		ArrayList<UserVO> userInfo = new ArrayList<UserVO>();
		UserVO user = null;
 
		PreparedStatement pstmt = null;
        ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					user = new UserVO();
					user.setUSER_ID(rs.getString("USER_ID"));
					user.setUSER_NAME(rs.getString("USER_NAME"));
					user.setUSER_PW(rs.getString("USER_PW"));
					user.setUSER_ADDR(rs.getString("USER_ADDR"));
					user.setUSER_TEL(rs.getString("USER_TEL"));
					user.setUSER_EMAIL(rs.getString("USER_EMAIL"));
					user.setUSER_DATE(rs.getDate("USER_DATE"));
					userInfo.add(user);
				} while (rs.next());
			}
		} catch (Exception ex) {
			System.out.println("getDeatilUser 에러1: " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return userInfo;
	}

		//설정아이디 검색
	public UserVO selectUser(String id) {
		String sql = "select * from users where user_id=?";
		UserVO user = null;
 
		PreparedStatement pstmt = null;
        ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new UserVO();
				user.setUSER_ID(rs.getString("USER_ID"));
				user.setUSER_NAME(rs.getString("USER_NAME"));
				user.setUSER_PW(rs.getString("USER_PW"));
				user.setUSER_ADDR(rs.getString("USER_ADDR"));
				user.setUSER_TEL(rs.getString("USER_TEL"));
				user.setUSER_EMAIL(rs.getString("USER_EMAIL"));
			}
		} catch (Exception ex) {
			System.out.println("getDeatilUser 에러2 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return user;
	}

				//회원탈퇴
	public int deleteUser(String deleteId, String deletePW) {
		String sql = "update (select user_id, user_pw, user_del from users where user_id = ? and user_pw = ?) set user_del = ?";
		int deleteCount = 0;
 
		PreparedStatement pstmt = null;
        ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, deleteId);
			pstmt.setString(2, deletePW);
			pstmt.setString(3, "Y");
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("deleteUser 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return deleteCount;

	}

		// 비밀번호 일치 확인.
		public boolean isPassword(String id, String pass) {
			String sql = "select user_pw from users where user_id = ?";
			boolean isPass = false;
 
			PreparedStatement pstmt = null;
			ResultSet rs = null;


			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					String pw = rs.getString("USER_PW");
					if (pw.equals(pass)) {
						isPass = true;
					} else {
						isPass = false;
					}
				}

			} catch (Exception e) {
				System.out.println("isPassword 에러 : " + e);
			} finally {
				close(rs);
				close(pstmt);
			}

			return isPass;

		}

		// 개인정보 변경
		public ArrayList<UserVO> UserInfo(String id, UserVO user) {
 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
 
			String sql = "update user_pass, user_tel, user_addr, user_email from users where user_id = ?";
			ArrayList<UserVO> userUpdate = null;

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUSER_PW());
				pstmt.setString(2, user.getUSER_TEL());
				pstmt.setString(3, user.getUSER_ADDR());
				pstmt.setString(4, user.getUSER_EMAIL());
				pstmt.setString(5, id);

				int updateCount = pstmt.executeUpdate();

				if (updateCount == 0) {
					System.out.println("개인정보 변경 안됨 ");
				} else {
					userUpdate.add(user);
					commit(con);
				}

			} catch (Exception e) {
				System.out.println("editUserInfo error : " + e);
			} finally {
				close(pstmt);
			}
			return userUpdate;
		}
	
		//전체조회
		public ArrayList<UserVO> selectUserList(){
			String sql = "select * from users";
			ArrayList<UserVO> userList = new ArrayList<UserVO>();
			UserVO mb = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
 
			
			try {
				pstmt = con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					do {
						mb=new UserVO();
						mb.setUSER_ID(rs.getString("USER_ID"));
						mb.setUSER_NAME(rs.getString("USER_NAME"));
						mb.setUSER_PW(rs.getString("USER_PW"));
						mb.setUSER_ADDR(rs.getString("USER_ADDR"));
						mb.setUSER_TEL(rs.getString("USER_TEL"));
						mb.setUSER_EMAIL(rs.getString("USER_EMAIL"));
						userList.add(mb);
					}while(rs.next());
				}
			}catch(Exception ex) {System.out.println("getDeatilUser 에러1: "+ex);}
			finally {close(rs); close(pstmt);}
			return userList;
		}
		
		
		// 마이페이지 회원정보 가져오기
		public ArrayList<RentInfoVO> searchRentBook(String id) {
			String sql = "select distinct m.*, i.isbn_title, i.isbn_author from (select r.rent_id_seq, r.rent_date, r.rent_return, r.rent_book, r.rent_return_date from rent r, users u where r.rent_user = ?) m , isbn i where m.rent_book = i.isbn_book ORDER BY RENT_DATE";
			
			PreparedStatement pstmt = null;
	        ResultSet rs = null;

			ArrayList<RentInfoVO> rentInfo = new ArrayList<RentInfoVO>();

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					RentInfoVO rent = new RentInfoVO();
					rent.setRENT_ID_SEQ(rs.getString("RENT_ID_SEQ"));
					rent.setRENT_DATE(rs.getDate("RENT_DATE"));
					rent.setRENT_RETURN(rs.getDate("RENT_RETURN"));
					rent.setISBN_TITLE(rs.getString("ISBN_TITLE"));
					rent.setISBN_AUTHOR(rs.getString("ISBN_AUTHOR"));
					rent.setRENT_BOOK(rs.getString("RENT_BOOK"));
					rent.setRENT_RETURN_DATE(rs.getDate("RENT_RETURN_DATE"));
					
					System.out.println(rs.getString("ISBN_TITLE"));
					System.out.println("반납일"+rs.getDate("RENT_RETURN_DATE"));
					
					rentInfo.add(rent);
				}

				} catch (Exception e) {
					System.out.println("SearchRentBook sql문 에러 : " + e);

				} finally {
					close(rs);
					close(pstmt);
				}
				return rentInfo;

		}

	//	회원목록전체조회 ------------------------------------------------------------------------------
	public ArrayList<UserVO> selectAllUserList(int page, int limit){
		System.out.println("userDAO_SELECT ALL USER LIST");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userList_sql="select * from (select rownum rnum, A.* from (select * from USERS) A ) where rnum between ? and ?";
		
		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		int startrow = (page-1) * 10+1;
		int endrow = (page-1) * 10+10;
		
		try {
			pstmt = con.prepareStatement(userList_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO user = new UserVO();
				user.setUSER_ID(rs.getString("USER_ID"));
				user.setUSER_NAME(rs.getString("USER_NAME"));
				user.setUSER_ADDR(rs.getString("USER_ADDR"));
				user.setUSER_TEL(rs.getString("USER_TEL"));
				user.setUSER_EMAIL(rs.getString("USER_EMAIL"));
				user.setUSER_DATE(rs.getDate("USER_DATE"));
				userList.add(user);
			}
		} catch(Exception e) {System.out.println("selectAllUserList에러"); e.getMessage();}
		finally {
			close(rs);
			close(pstmt);
		}
		return userList;
	}
	
	// 전체 회원 개수 구하기 --------------------------------------------------------------------
	public int selectListCount() {
		System.out.println("userDAO_selectListCount");
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt=con.prepareStatement("SELECT COUNT(*) FROM USERS");
			rs = pstmt.executeQuery();

			if(rs.next()){
				listCount = rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	// 이름으로 검색한 회원 개수 구하기 --------------------------------------------------------------------
	public int getSearchListCount(String name) {
		System.out.println("userDAO_getSearchListCount");
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String searchName_sql= "SELECT COUNT(*) FROM USERS WHERE USER_NAME LIKE ?";
		try{
			pstmt=con.prepareStatement(searchName_sql);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			if(rs.next()){
				listCount = rs.getInt(1);
				System.out.println(listCount);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	// 아이디로 검색한 회원 개수 구하기 --------------------------------------------------------------------
		public int getSearchListCountById(String id) {
			System.out.println("userDAO_getSearchListCount");
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String searchID_sql= "SELECT COUNT(*) FROM USERS WHERE USER_ID LIKE ?";
			try{
				pstmt=con.prepareStatement(searchID_sql);
				pstmt.setString(1, "%" + id + "%");
				rs = pstmt.executeQuery();
				if(rs.next()){
					listCount = rs.getInt(1);
					System.out.println(listCount);
				}
			}catch(Exception ex){
				System.out.println("getListCount 에러: " + ex);			
			}finally{
				close(rs);
				close(pstmt);
			}
			return listCount;
		}
	
	//	회원부분조회(이름) --------------------------------------------------------------------
	public ArrayList<UserVO> selectSearchListByName(String name, int page){	
		System.out.println("userDAO_부분조회(이름)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<UserVO> searchUserList = new ArrayList<UserVO>();
		
		String user_detail_sql = "select * from (select rownum rnum, A.* from (SELECT * FROM USERS WHERE USER_NAME LIKE ?) A ) where rnum between ? and ?";
		int startrow = (page-1) * 10+1;//11
		int endrow = (page-1) * 10+10;//20
		try {
			pstmt = con.prepareStatement(user_detail_sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO searchUser = new UserVO();
				searchUser.setUSER_ID(rs.getString("USER_ID"));
				searchUser.setUSER_NAME(rs.getString("USER_NAME"));
				searchUser.setUSER_ADDR(rs.getString("USER_ADDR"));
				searchUser.setUSER_TEL(rs.getString("USER_TEL"));
				searchUser.setUSER_EMAIL(rs.getString("USER_EMAIL"));
				searchUser.setUSER_DATE(rs.getDate("USER_DATE"));
				searchUserList.add(searchUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return searchUserList;
	}
	
	// 회원부분조회(아이디) --------------------------------------------------------------------
	public ArrayList<UserVO> selectSearchListById(String id, int page){	
		System.out.println("userDAO_부분조회(아이디)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String user_detail_sql = "select * from (select rownum rnum, A.* from (SELECT * FROM USERS WHERE USER_ID LIKE ?) A ) where rnum between ? and ?";
		int startrow = (page-1) * 10+1;//11
		int endrow = (page-1) * 10+10;//20
		
		ArrayList<UserVO> searchUserList = new ArrayList<UserVO>();
		try {
			pstmt = con.prepareStatement(user_detail_sql);
			pstmt.setString(1, "%" + id + "%");
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO searchUser = new UserVO();
				searchUser.setUSER_ID(rs.getString("USER_ID"));
				searchUser.setUSER_NAME(rs.getString("USER_NAME"));
				searchUser.setUSER_ADDR(rs.getString("USER_ADDR"));
				searchUser.setUSER_TEL(rs.getString("USER_TEL"));
				searchUser.setUSER_EMAIL(rs.getString("USER_EMAIL"));
				searchUser.setUSER_DATE(rs.getDate("USER_DATE"));
				searchUserList.add(searchUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return searchUserList;
	}
	
	// 회원가입
	public int insertUser(UserVO user) {
			System.out.println("user_join dao");
			PreparedStatement pstmt = null;
//			String sql = "insert into users values(?,?,?,?,?,?,?,?)";
			String sql = "insert into users(USER_ID, USER_NAME, USER_PW, USER_ADDR, USER_TEL, USER_EMAIL, USER_DATE) values(?,?,?,?,?,?,?)";
			int insertCount = 0;
			System.out.println("sql: " + sql);
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUSER_ID());
				pstmt.setString(2, user.getUSER_NAME());
				pstmt.setString(3,  user.getUSER_PW());
				pstmt.setString(4, user.getUSER_ADDR1() +user.getUSER_ADDR2()+user.getUSER_ADDR3()+user.getUSER_ADDR4());
				pstmt.setString(5,  user.getUSER_TEL());
				pstmt.setString(6, user.getUSER_EMAIL());
				pstmt.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
//				pstmt.setString(8, user.getUSER_DEL());
				
				insertCount=pstmt.executeUpdate();
			}catch(Exception ex) {System.out.println("joinUser 에러: "+ex);}
			finally {close(pstmt);}
			return insertCount;
		}
}
