// NoticeListAction ================================================= 공지사항 리스트 가져옴, 페이징처리


package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.NoticeListService;
import vo.ActionForward;
import vo.NoticeVO;
import vo.PageInfo;

 public class NoticeListAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 System.out.println("dortus");
		 
		ArrayList<NoticeVO> noticeList=new ArrayList<NoticeVO>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		NoticeListService noticeListService = new NoticeListService();
		int listCount=noticeListService.getListCount(); //총 리스트 수를 받아옴.
		noticeList = noticeListService.getNoticeList(page,limit); //리스트를 받아옴.
		//총 페이지 수.
   		int maxPage=(int) Math.floor((double)listCount/limit+0.95); //0.95를 더해서 올림 처리.
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
   		forward.setPath("/notice/qna_notice_list.jsp");
   		return forward;
   		
	 }
	 
 }