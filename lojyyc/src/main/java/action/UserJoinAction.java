package action;

import java.util.Date;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import svc.UserJoinService;
import vo.ActionForward;
import vo.UserVO;

public class UserJoinAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	UserVO user=new UserVO();
	   		boolean joinResult=false;
	   		
	   		user.setUSER_ID(request.getParameter("uid"));
	   		System.out.println("uid ::::::" + request.getParameter("uid"));
//	   		user.setUSER_ID(request.getParameter("USER_ID"));
	   		user.setUSER_NAME(request.getParameter("USER_NAME"));
	   		user.setUSER_PW(request.getParameter("upw"));
//	   		user.setUSER_PW(request.getParameter("USER_PW"));
	   		user.setUSER_ADDR1(request.getParameter("USER_ADDR1"));
	   		user.setUSER_ADDR2(request.getParameter("USER_ADDR2"));
	   		user.setUSER_ADDR3(request.getParameter("USER_ADDR3"));
	   		user.setUSER_ADDR4(request.getParameter("USER_ADDR4"));
	   		user.setUSER_TEL(request.getParameter("USER_TEL"));
	   		user.setUSER_EMAIL(request.getParameter("USER_EMAIL"));
//	   		user.setUSER_DEL(request.getParameter("USER_DEL"));
	   		System.out.println("유저DEL:" + request.getParameter("USER_DEL"));
//	   		
	   		UserJoinService userJoinService = new UserJoinService();
	   		joinResult=userJoinService.joinUser(user);
	   		
	   		ActionForward forward = null;
	   		if(joinResult==false){
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('회원등록실패')");
//	   			out.println("history.back()");
	   			out.println("</script>");
		   	}
	   		else{
		   	    forward = new ActionForward();
		   		forward.setRedirect(true);
		   		forward.setPath("/index.jsp");
	   		}
	   		return forward;
	}
}