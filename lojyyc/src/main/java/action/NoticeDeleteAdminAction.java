package action;

//  NoticeDeleteAdminAction ===================================================== 삭제 : 관리자 권한 확인
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

public class NoticeDeleteAdminAction implements Action {

	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	HttpSession session=request.getSession();
		 	ActionForward forward = new ActionForward();
		 	
		 	int notice_num = (Integer) request.getAttribute("notice_num");
		 	String notice_file = (String) request.getAttribute("notice_file");
		 	String notice_realfile = (String) request.getAttribute("notice_realfile");
//		 	int noticeNum = Integer.parseInt(notice_num); 
		 	
			String sessionId = null;
			sessionId = (String) session.getAttribute("USER_ID");
			
	   		if(sessionId!=null) {
			sessionId = (String) session.getAttribute("USER_ID");
	   		} else {
	   			sessionId = "null";
	   		}
	   		
			   		
			NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
			boolean isSessionAdmin = false;
			
			if(sessionId.equals("admin")) {
				isSessionAdmin = true;
				forward.setPath("/notice/qna_notice_deleteAdmin.jsp?notice_num="+notice_num+"&notice_file"+notice_file+"&notice_realfile"+notice_realfile);
			}else if(sessionId.equals("null")){
	 			response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('등록권한이 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정권한이 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				}
	   		return forward;
	 }
	 
}