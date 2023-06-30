//  NoticeDetailAction =============================================== 상세페이지에 가져올 공지사항 정보 가져옴.

package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeVO;

 public class NoticeDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int notice_num=Integer.parseInt(request.getParameter("notice_num"));
		String page = request.getParameter("page");
		NoticeDetailService noticeDetailService = new NoticeDetailService();
		NoticeVO noticeVO = noticeDetailService.getNotice(notice_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("noticeVO", noticeVO);
   		forward.setPath("/notice/qna_notice_view.jsp");
   		return forward;

	 }
	 
}