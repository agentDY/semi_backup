// NoticeModifyFormAction =========================================================  관리자 비밀번호 확인 후 수정창으로 이동
package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.NoticeDetailService;
import svc.NoticeModifyProService;
import vo.ActionForward;
import vo.NoticeVO;

public class NoticeModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
		 	int notice_num=Integer.parseInt(request.getParameter("notice_num"));
			
			NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
			boolean isRightUser=noticeModifyProService.isNoticeWriter(notice_num, request.getParameter("MODIFY_CONFIRM"));
			System.out.println("modify: " + request.getParameter("MODIFY_CONFIRM")); 

			if(!isRightUser){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 올바르지 않습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}

			else{
				
				NoticeDetailService noticeDetailService = new NoticeDetailService();	
			   	NoticeVO notice = noticeDetailService.getNotice(notice_num);
			   	request.setAttribute("notice", notice);
		   		forward.setPath("/notice/qna_notice_modify.jsp");
				
			}
		
	   		return forward;
	   		
	 }
	 
}