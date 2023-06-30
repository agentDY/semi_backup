package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeVO;

 public class NoticeModifyResultAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int notice_num=Integer.parseInt(request.getParameter("notice_num"));
		NoticeDetailService noticeDetailService = new NoticeDetailService();
		NoticeVO noticeVO = noticeDetailService.getNotice(notice_num);
		ActionForward forward = new ActionForward();
	   	request.setAttribute("noticeVO", noticeVO);
   		forward.setPath("/notice/qna_notice_view.jsp");
   		return forward;

	 }
	 
}