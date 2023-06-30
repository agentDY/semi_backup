package svc;

import java.sql.Connection;
import dao.NoticeDAO;
import vo.NoticeVO;
import static db.JdbcUtil.*;
public class NoticeWriteProService {

	public boolean registArticle(NoticeVO noticeVO) throws Exception{
		// 공지사항 등록
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int insertCount = noticeDAO.insertNotice(noticeVO);
		
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
	
	public boolean isRightPassword(String pass) throws Exception {
		// 관리자 확인 비밀번호
		
		boolean isRightPassword = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		isRightPassword = noticeDAO.isAdminPassword(pass);
		close(con);
		return isRightPassword;
	}
}
