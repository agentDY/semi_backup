// NoticeWriteAdminAction  ================================================================================== 공지사항 등록시 관리자 권한 확인(세션id)
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


public class NoticeWriteAdminAction implements Action {

	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	HttpSession session=request.getSession();
		 	ActionForward forward = new ActionForward();
			
	   		String sessionId = null;
	   		sessionId = (String) session.getAttribute("USER_ID");
	   	
	   		if(sessionId!=null) {
			sessionId = (String) session.getAttribute("USER_ID");
	   		} else {
	   			sessionId = "null";
	   		}
	   		
	   		
			NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
			boolean isSessionAdmin = false;
			
			//세션 확인 - 어드민 : 등록가능, 비로그인 or 일반계정 : 권한없음
			if(sessionId.equals("admin")) {
				isSessionAdmin = true;
				forward.setPath("/notice/qna_notice_writeAdmin.jsp");
			
	 		}else if(sessionId.equals("null")){
	 			response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('등록권한이 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
	 		}else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('등록권한이 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				}
	   		return forward;
	 }
	 
}