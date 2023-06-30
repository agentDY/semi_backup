package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.NoticeVO;

public class NoticeDAO {

	DataSource ds;
	Connection con;
	private static NoticeDAO noticeDAO;

	private NoticeDAO() {
		// TODO Auto-generated constructor stub
	}

	public static NoticeDAO getInstance() {
		if (noticeDAO == null) {
			noticeDAO = new NoticeDAO();
		}
		return noticeDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 글의 개수 구하기.
		public int selectListCount() {

			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				System.out.println("getConnection");
				pstmt = con.prepareStatement("select count(*) from notice9");
				rs = pstmt.executeQuery();

				if (rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (Exception ex) {
				System.out.println("selectListCount 에러1: " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}
			return listCount;
		}
		
		// 검색결과에 해당되는 게시글의 개수 구하기.
		public int selectSearchedListCount(String searchWord, String searchField) { 

			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String noticeSQL = null;

			if(searchField.equals("title")) {
				noticeSQL = "select count(*) from NOTICE9 where NOTICE_TITLE LIKE '%' || ? || '%'";
			} else if(searchField.equals("content")) {
				noticeSQL = "select count(*) from NOTICE9 where NOTICE_CONT LIKE '%' || ? || '%' ";
			}
			
			try {
				System.out.println("getConnection");
				pstmt = con.prepareStatement(noticeSQL);
				pstmt.setString(1, searchWord);
				
				rs = pstmt.executeQuery();

				if (rs.next()) {
					listCount = rs.getInt(1);
					System.out.println("가져오는 공지사항 개수 : " + listCount);
				}
			} catch (Exception ex) {
				System.out.println("selectSearchedListCount 에러2: " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}
			return listCount;
		}

		// 글 목록 보기.  --수정 완 (한 페이지 당 나올 게시글 10개씩)
		public ArrayList<NoticeVO> selectNoticeList(int page, int limit) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String board_list_sql = "select * from (select rownum rnum, A.* from (select * from notice9 order by notice_pin desc, notice_NUM desc ) A ) where rnum between ? and ? order by notice_pin desc, rnum ";
			ArrayList<NoticeVO> noticeList = new ArrayList<NoticeVO>();
			int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호..
			int endrow = (page - 1) * 10 + 10;
			
			

			try {
				pstmt = con.prepareStatement(board_list_sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					NoticeVO noticeVO = new NoticeVO();
					noticeVO.setNOTICE_NUM(rs.getInt("NOTICE_NUM"));
					noticeVO.setNOTICE_ADMIN(rs.getString("NOTICE_ADMIN"));
					noticeVO.setNOTICE_TITLE(rs.getString("NOTICE_TITLE"));
					noticeVO.setNOTICE_CONT(rs.getString("NOTICE_CONT"));
					noticeVO.setNOTICE_FILE(rs.getString("NOTICE_FILE"));
					noticeVO.setNOTICE_DATE(rs.getDate("NOTICE_DATE"));
					noticeVO.setNOTICE_READCOUNT(rs.getInt("NOTICE_READCOUNT"));
					noticeVO.setNOTICE_PIN(rs.getInt("NOTICE_PIN"));
					noticeVO.setNOTICE_REALFILE(rs.getString("NOTICE_REALFILE"));
					noticeList.add(noticeVO);
				}

			} catch (Exception ex) {
				System.out.println("selectNoticeList 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}

			return noticeList;

		}
		
		
		// 메인페이지 최신글 5개 출력용--수정 완 (한 페이지 당 나올 게시글 10개씩)
			public ArrayList<NoticeVO> selectNoticeListForMain(int page, int limit) {

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String board_list_sql = "select * from (select rownum rnum, A.* from (select * from notice9 order by notice_NUM desc ) A ) where rnum between ? and ? order by rnum";
				ArrayList<NoticeVO> noticeList = new ArrayList<NoticeVO>();
				int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호..
				int endrow = (page - 1) * 10 + 5;
				
				

				try {
					pstmt = con.prepareStatement(board_list_sql);
					pstmt.setInt(1, startrow);
					pstmt.setInt(2, endrow);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						NoticeVO noticeVO = new NoticeVO();
						noticeVO.setNOTICE_NUM(rs.getInt("NOTICE_NUM"));
						noticeVO.setNOTICE_ADMIN(rs.getString("NOTICE_ADMIN"));
						noticeVO.setNOTICE_TITLE(rs.getString("NOTICE_TITLE"));
						noticeVO.setNOTICE_CONT(rs.getString("NOTICE_CONT"));
						noticeVO.setNOTICE_FILE(rs.getString("NOTICE_FILE"));
						noticeVO.setNOTICE_DATE(rs.getDate("NOTICE_DATE"));
						noticeVO.setNOTICE_READCOUNT(rs.getInt("NOTICE_READCOUNT"));
						noticeVO.setNOTICE_PIN(rs.getInt("NOTICE_PIN"));
						noticeVO.setNOTICE_REALFILE(rs.getString("NOTICE_REALFILE"));
						noticeList.add(noticeVO);
					}

				} catch (Exception ex) {
					System.out.println("selectNoticeListForMain 에러 : " + ex);
				} finally {
					close(rs);
					close(pstmt);
				}

				return noticeList;

			}
		

		// 글 내용 보기.  --수정 완
		public NoticeVO selectNotice(int notice_num) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			NoticeVO noticeVO = null;
			
			try {
				pstmt = con.prepareStatement("select * from NOTICE9 where NOTICE_NUM = ?");
				pstmt.setInt(1, notice_num);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					noticeVO = new NoticeVO();
					noticeVO.setNOTICE_NUM(rs.getInt("NOTICE_NUM"));
					noticeVO.setNOTICE_ADMIN(rs.getString("NOTICE_ADMIN"));
					noticeVO.setNOTICE_TITLE(rs.getString("NOTICE_TITLE"));
					noticeVO.setNOTICE_CONT(rs.getString("NOTICE_CONT"));
					noticeVO.setNOTICE_FILE(rs.getString("NOTICE_FILE"));
					noticeVO.setNOTICE_DATE(rs.getDate("NOTICE_DATE"));
					noticeVO.setNOTICE_READCOUNT(rs.getInt("NOTICE_READCOUNT"));
					noticeVO.setNOTICE_PIN(rs.getInt("NOTICE_PIN"));
					noticeVO.setNOTICE_REALFILE(rs.getString("NOTICE_REALFILE"));
				}
			} catch (Exception ex) {
				System.out.println("getDetail 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}

			return noticeVO;

		}
		
		// 게시글 검색 결과 보기
		public ArrayList<NoticeVO> selectSearchResult(int page, int limit, String searchWord, String searchField) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<NoticeVO> noticeList = new ArrayList<NoticeVO>();
			// int startrow = (page - 1) * 10 + 1; 읽기 시작할 row 번호..
			int startrow = (page - 1) * 10 + 1; // rs 1번행부터 10번행까지 
			int endrow = (page - 1) * 10 + 10;
			String board_list_sql = null;
					

			
			if(searchField.equals("title")) {
				board_list_sql = "select * from ( select rownum rnum, A.* from ( select * from notice9 order by notice_NUM desc ) A where NOTICE_TITLE LIKE '%' || ? || '%') where rnum between ? and ? order by notice_pin desc, rnum ";
			} else if(searchField.equals("content")) {
				board_list_sql = "select * from ( select rownum rnum, A.* from ( select * from notice9 order by notice_NUM desc ) A where NOTICE_CONT LIKE '%' || ? || '%') where rnum between ? and ? order by notice_pin desc, rnum ";
			}
			
				try {
				pstmt = con.prepareStatement(board_list_sql);
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					NoticeVO noticeVO = new NoticeVO();
					noticeVO.setNOTICE_NUM(rs.getInt("NOTICE_NUM"));
					noticeVO.setNOTICE_ADMIN(rs.getString("NOTICE_ADMIN"));
					noticeVO.setNOTICE_TITLE(rs.getString("NOTICE_TITLE"));
					noticeVO.setNOTICE_CONT(rs.getString("NOTICE_CONT"));
					noticeVO.setNOTICE_FILE(rs.getString("NOTICE_FILE"));
					noticeVO.setNOTICE_DATE(rs.getDate("NOTICE_DATE"));
					noticeVO.setNOTICE_READCOUNT(rs.getInt("NOTICE_READCOUNT"));
					noticeVO.setNOTICE_PIN(rs.getInt("NOTICE_PIN"));
					noticeVO.setNOTICE_REALFILE(rs.getString("NOTICE_REALFILE"));
					noticeList.add(noticeVO);
				}

			} catch (Exception ex) {
				System.out.println("selectSearchList 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return noticeList;

		}
		

		// 글 등록.	--수정 완
		public int insertNotice(NoticeVO notice) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int num = 0;
			String sql = "";
			int insertCount = 0;

			try {
				pstmt = con.prepareStatement("select max(notice_num) from notice9");
				rs = pstmt.executeQuery();

				if (rs.next())
					num = rs.getInt(1) + 1;
				else
					num = 1;

				sql = "insert into notice9 (NOTICE_NUM, NOTICE_ADMIN, NOTICE_TITLE,";
				sql += "NOTICE_CONT, NOTICE_FILE, NOTICE_DATE, NOTICE_READCOUNT, NOTICE_PIN, NOTICE_REALFILE) values(?,?,?,?,?,SYSDATE,?,?,?)";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, "관리자");
				pstmt.setString(3, notice.getNOTICE_TITLE());
				pstmt.setString(4, notice.getNOTICE_CONT());
				pstmt.setString(5, notice.getNOTICE_FILE());
				pstmt.setInt(6, 0);
				pstmt.setInt(7, notice.getNOTICE_PIN());
				pstmt.setString(8, notice.getNOTICE_REALFILE());

				insertCount = pstmt.executeUpdate();

			} catch (Exception ex) {
				System.out.println("noticeInsert 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}

			return insertCount;

		}

		// 글 수정.
		public int updateNotice(NoticeVO noticeVO) {

			int updateCount = 0;
			PreparedStatement pstmt = null;
			String sql = "update notice9 set NOTICE_TITLE=?,NOTICE_CONT=?, NOTICE_PIN=?, NOTICE_FILE=?, NOTICE_REALFILE=? where NOTICE_NUM=?";
			String sql_n = "update notice9 set NOTICE_TITLE=?,NOTICE_CONT=?, NOTICE_PIN=? where NOTICE_NUM=?";

			try {

				//File 변경 사항 있으면 File 변경사항 DB에 반영
				if(noticeVO.getNOTICE_FILE() != null) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, noticeVO.getNOTICE_TITLE());
				pstmt.setString(2, noticeVO.getNOTICE_CONT());
				pstmt.setInt(3, noticeVO.getNOTICE_PIN());
				pstmt.setString(4, noticeVO.getNOTICE_FILE());
				pstmt.setString(5, noticeVO.getNOTICE_REALFILE());
				pstmt.setInt(6, noticeVO.getNOTICE_NUM());
				updateCount = pstmt.executeUpdate();
				} 
				//File 변경사항 없으면 File명 제외하고 입력
				else {
				pstmt = con.prepareStatement(sql_n);
				pstmt.setString(1, noticeVO.getNOTICE_TITLE());
				pstmt.setString(2, noticeVO.getNOTICE_CONT());
				pstmt.setInt(3, noticeVO.getNOTICE_PIN());
				pstmt.setInt(4, noticeVO.getNOTICE_NUM());
				updateCount = pstmt.executeUpdate();
				}			
			} catch (Exception ex) {
				System.out.println("updateNotice 에러 : " + ex);
			} finally {
				close(pstmt);
			}

			return updateCount;
		}
		
		// 글 삭제. 
		public int deleteArticle(int notice_num) {
			String notice_file = "select NOTICE_FILE from NOTICE9 where notice_num = ?";

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String notice_delete_sql = "delete from NOTICE9 where NOTICE_num=?";
			int deleteCount = 0;
			String filenm = "";

			try {
				pstmt = con.prepareStatement(notice_delete_sql);
				pstmt.setInt(1, notice_num);
				deleteCount = pstmt.executeUpdate();

			} catch (Exception ex) {
				System.out.println("noticeDelete 에러 : " + ex);
			} finally {
				close(pstmt);
			}

			return deleteCount;

		}

	// 파일칼럼 데이터 삭제. , 실제파일 삭제		====================================================
		public int deleteFileData (int notice_num) {
			String notice_file = "update NOTICE9 set NOTICE_FILE = ? WHERE NOTICE_NUM = ?";

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String filename_sql = "select NOTICE_FILE from NOTICE9 where notice_num = ?";
			int deleteCount = 0;
			String filenm = "";

			try {
				pstmt = con.prepareStatement(filename_sql);
				pstmt.setInt(1, notice_num);
				rs = pstmt.executeQuery();
				rs.next();
				filenm = rs.getString("NOTICE_FILE");
				
				pstmt = con.prepareStatement(notice_file);
				pstmt.setString(1, "");
				pstmt.setInt(2, notice_num);
				deleteCount = pstmt.executeUpdate();

				File file = new File("C:\\jspwork\\lojyyc\\src\\main\\webapp\\noticeUpload\\" + filenm);
				if (file.exists()) {
					file.delete();
				}
				//수정 시 데이터 삭제되는지 확인
			} catch (Exception ex) {
				System.out.println("DeleteFileData 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}

			return deleteCount;

		}

		// 실제파일만 삭제		===============================================================
		public String removeFile (int notice_num) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String filename_sql = "select NOTICE_REALFILE from NOTICE9 where notice_num = ?";
			int deleteCount = 0;
			String realFileName = "";
			
			try {
				pstmt = con.prepareStatement(filename_sql);
				pstmt.setInt(1, notice_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				realFileName = rs.getString("NOTICE_REALFILE");
				}
				
				//수정 시 데이터 삭제되는지 확인
			} catch (Exception ex) {
				System.out.println("removeFile 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}

		
			return realFileName;

		}
		
		
		// 조회수 업데이트.
		public int updateReadCount(int notice_num) {
			
			PreparedStatement pstmt = null;
			int updateCount = 0;
			String sql = "update notice9 set NOTICE_READCOUNT = NOTICE_READCOUNT+1 where NOTICE_NUM = " + notice_num;

			try {
				pstmt = con.prepareStatement(sql);
				updateCount = pstmt.executeUpdate();
			} catch (SQLException ex) {
				System.out.println("setReadCountUpdate 에러 : " + ex);
			} finally {
				close(pstmt);
			}
			return updateCount;
		}

		// 글쓴이인지 확인. 입력된 비밀번호가 admin 비밀번호와 일치하는지 확인. (게시글 수정)
		public boolean isWriterAdmin(int notice_num, String pass) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String board_sql = "select USER_PW from USERS where USER_ID=?";
			boolean isWriter = false;

			try {
				pstmt = con.prepareStatement(board_sql);
				pstmt.setString(1, "admin");
				rs = pstmt.executeQuery();
				rs.next();

				if (pass.equals(rs.getString("USER_PW"))) {
					isWriter = true;
				}
			} catch (SQLException ex) {
				System.out.println("isWriterAdmin 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}

			return isWriter;

		}
		
		// 글쓴이인지 확인. 입력된 비밀번호가 admin 비밀번호와 일치하는지 확인. (게시글 작성)
		public boolean isAdminPassword(String pass) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String board_sql = "select USER_PW from USERS where USER_ID=?";
			boolean isWriter = false;

			try {
				pstmt = con.prepareStatement(board_sql);
				pstmt.setString(1, "admin");
				rs = pstmt.executeQuery();
				rs.next();

				if (pass.equals(rs.getString("USER_PW"))) {
					isWriter = true;
				}
			} catch (SQLException ex) {
				System.out.println("isAdminPassword 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}

			return isWriter;

		}
		
		// 글쓴이인지 확인. 세션id 가져와서 세션아이디가 admin일 경우 isWriter = true;
		public boolean isWriterAdmin(String sessionId) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String board_sql = "select USER_ID from USERS where USER_ID=?";
			boolean isWriter = false;

			try {
				pstmt = con.prepareStatement(board_sql);
				pstmt.setString(1, sessionId);
				rs = pstmt.executeQuery();
				rs.next();

				if (sessionId.equals(rs.getString("admin"))) {
					isWriter = true;
				}
			} catch (SQLException ex) {
				System.out.println("isWriterAdmin 에러 : " + ex);
			} finally {
				close(rs);
				close(pstmt);
			}

			return isWriter;
		}
}
