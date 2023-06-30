// NoticeSearchResultAction  =========================================================================  리스트에서 공지사항 검색결과 가져옴.
package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.NoticeListService;
import vo.ActionForward;
import vo.NoticeVO;
import vo.PageInfo;

 public class NoticeSearchResultAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		
		String searchWord = request.getParameter("searchWord"); //개관
		String searchField = request.getParameter("searchField"); //title
		 
		ArrayList<NoticeVO> noticeList=new ArrayList<NoticeVO>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		NoticeListService noticeListService = new NoticeListService();
		int listCount=noticeListService.getSearchedListCount(searchWord, searchField); //총 리스트 수를 받아옴.
		noticeList = noticeListService.getSearchResult(page,limit,searchWord, searchField); //리스트를 받아옴. (검색결과에 해당하는 게시글 목록)
		
		//총 페이지 수.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	    int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("noticeList", noticeList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/notice/qna_notice_listSearchPage.jsp?page="+page+"&searchWord="+searchWord+"&searchField="+searchField);
   		return forward;
   		
	 }
	 
 }