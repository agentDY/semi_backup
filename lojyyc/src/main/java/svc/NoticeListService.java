// NoticeListService  ==================================================

package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.NoticeDAO;
import vo.NoticeVO;

public class NoticeListService {

	//총 게시글 개수
	public int getListCount() throws Exception{
		
		int listCount = 0;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		listCount = noticeDAO.selectListCount();
		close(con);
		return listCount;
		
	}
	
	//검색결과에 해당하는 총 게시글 수 
	public int getSearchedListCount(String searchWord, String searchField) throws Exception{ 
		int listCount = 0;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		listCount = noticeDAO.selectSearchedListCount(searchWord, searchField);
		close(con);
		return listCount;
		
	}
	
	//공지사항 목록 한 페이지 당 게시글
	public ArrayList<NoticeVO> getNoticeList(int page, int limit) throws Exception{
		
		ArrayList<NoticeVO> noticeList = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		noticeList = noticeDAO.selectNoticeList(page,limit);    
		close(con);
		return noticeList;
		
	}
	
	//공지사항 목록 검색 결과
	public ArrayList<NoticeVO> getSearchResult(int page, int limit, String searchWord, String searchField) throws Exception{
		
		ArrayList<NoticeVO> noticeList = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		noticeList = noticeDAO.selectSearchResult(page,limit,searchWord, searchField);
		close(con);
	return noticeList;
		
	}
	
	//메인페이지 공지사항 목록
	public ArrayList<NoticeVO> getNoticeListForMain(int page, int limit) throws Exception{
		
		ArrayList<NoticeVO> noticeList = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		noticeList = noticeDAO.selectNoticeListForMain(page,limit);    
		close(con);
		
		return noticeList;
	}

}