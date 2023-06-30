package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.NoticeDetailService;
import svc.NoticeModifyProService;
import svc.NoticeWriteProService;
import vo.ActionForward;
import vo.NoticeVO;
import vo.UserVO;

public class NoticeWriteFormAction implements Action {

	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	HttpSession session=request.getSession();
		 	ActionForward forward = new ActionForward();
		 	
			NoticeWriteProService noticeWriteProService = new NoticeWriteProService();
			boolean isRightUser = noticeWriteProService.isRightPassword(request.getParameter("WRITE_CONFIRM"));

			if(isRightUser) {
				forward.setPath("/notice/qna_notice_write.jsp");
		
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				}
	   		return forward;
	 }
	 
}