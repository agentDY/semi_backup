// NoticeModifyProService =============================================================수정관련

package svc;

import static db.JdbcUtil.*;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpSession;

import vo.NoticeVO;
import dao.NoticeDAO;

public class NoticeModifyProService {

	
	public boolean isNoticeWriter(int notice_num, String pass) throws Exception {
		// 입력한 비밀번호가 admin비밀번호와 일치하는지
		
		boolean isNoticeWriter = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		isNoticeWriter = noticeDAO.isWriterAdmin(notice_num, pass);
		close(con);
		return isNoticeWriter;
		
	}
	
	public boolean isNoticeWriter(String sessionId) throws Exception {
		// 세션id가 admin인지
		
		boolean isNoticeWriter = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		isNoticeWriter = noticeDAO.isWriterAdmin(sessionId);
		close(con);
		return isNoticeWriter;
	}
	
 public boolean modifyNotice(NoticeVO noticeVO) throws Exception {
		//공지사항 수정

		boolean isModifySuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int updateCount = noticeDAO.updateNotice(noticeVO);
		
		if(updateCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
		
	}
	 
	 
	 public boolean removeFile (int notice_num)  throws Exception {
	//num에 해당하는 행의 realfile 값 가져와서 파일 삭제.		
			
Connection con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			int deleteCount = 0;
			
			boolean isRemoveSuccess = false;
			String realFileName = noticeDAO.removeFile(notice_num); //파일명을 select로 > 삭제 
			
			System.out.println(realFileName);
			try{
				File file = new File("C:\\jspwork\\lojyyc\\src\\main\\webapp\\noticeUpload\\" + realFileName);		//**절대경로
//				File file = new File("C:\\jwork\\library\\src\\main\\webapp\\noticeUpload" + realFileName);		//**절대경로
				
				if (file.exists()) {
					file.delete();
					deleteCount = 1;
					System.out.println("removeFileSVC 성공");
				}
			} catch (Exception ex) {
				System.out.println("removeFile 오류 : 파일 삭제 실패");
			}
			
			if(deleteCount > 0) {
				commit(con);
				
				isRemoveSuccess = true;
			} else {
				rollback(con);
			}
			close(con);
			return isRemoveSuccess;
	 }

}