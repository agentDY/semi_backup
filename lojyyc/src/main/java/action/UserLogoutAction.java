package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.ActionForward;

public class UserLogoutAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HttpSession session=request.getSession();
		 session.invalidate();
		 
		 if( (String)session.getAttribute("USER_ID") == null) {
			 //java.lang.IllegalStateException: getAttribute: 세션이 이미 무효화되었습니다. 오류 뜬다
		   	 System.out.println("로그아웃 성공");
		 } else {
		   	 System.out.println("로그아웃 실패");
		 }
		 return null;
	 }
}
