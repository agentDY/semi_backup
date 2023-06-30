package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.File;
import java.sql.Connection;
import dao.NoticeDAO;

public class NoticeModifyFileProService {

	public boolean isWriterAdmin(int board_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isAdmin = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		isAdmin = noticeDAO.isWriterAdmin(board_num, pass);
		close(con);
		return isAdmin;
		
	}

	public boolean removeArticle(int notice_num, String notice_file) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int deleteCount = noticeDAO.deleteArticle(notice_num);
		
		try {
		     // 파일이 실제 업로드 되어있는(파일이 존재하는) 경로를 지정한다.
//            String filePath = "C:\\jwork\\library\\src\\main\\webapp\\noticeUpload\\" + notice_file;
            String filePath = "C:\\jspwork\\lojyyc\\src\\main\\webapp\\noticeUpload\\" + notice_file;

            // 경로와 파일명으로 파일 객체를 생성한다.
            File deleteFile = new File(filePath);

            // 파일 길이를 가져온다.
//            int fSize = (int) dFile.length();
			
			if(deleteFile.exists()) {
				deleteFile.delete();
				System.out.println("삭제완료");
			} 
		}catch(Exception io) {
				System.out.println("삭제오류");
			}
		
		
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}
	
	public boolean removeFileData(int notice_num, String notice_file) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int deleteCount = noticeDAO.deleteFileData(notice_num);
		
		try {
		     // 파일이 실제 업로드 되어있는(파일이 존재하는) 경로를 지정한다.
            String filePath = "C:\\jspwork\\lojyyc\\src\\main\\webapp\\noticeUpload\\" + notice_file;
//            String filePath = "C:\\jwork\\library\\src\\main\\webapp\\noticeUpload\\" + notice_file;

            // 경로와 파일명으로 파일 객체를 생성한다.
            File deleteFile = new File(filePath);

            // 파일 길이를 가져온다.
//            int fSize = (int) dFile.length();
			
			if(deleteFile.exists()) {
				deleteFile.delete();
				System.out.println("삭제완료");
			} 
		}catch(Exception io) {
				System.out.println("삭제오류");
			}
		
		
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}

}
