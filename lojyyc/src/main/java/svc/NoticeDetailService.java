package svc;

import java.sql.Connection;
import dao.NoticeDAO;
import vo.NoticeVO;
import static db.JdbcUtil.*;

public class NoticeDetailService {

	public NoticeVO getNotice(int notice_num) throws Exception{
		// TODO Auto-generated method stub
		
		NoticeVO noticeVO = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int updateCount = noticeDAO.updateReadCount(notice_num);
	
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		noticeVO = noticeDAO.selectNotice(notice_num);
		System.out.println("노티스디테일서비스에서?? "+noticeVO.getNOTICE_CONT());
		close(con);
		
		return noticeVO;
		
	}

}
