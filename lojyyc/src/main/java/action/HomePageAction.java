package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class HomePageAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HttpSession session=request.getSession();
		 	System.out.println("homePageAction : " + session.getAttribute("USER_ID"));

	   		ActionForward forward = null;
	   		forward = new ActionForward();
		   	forward.setPath("/index.jsp");
		   	//****** 경로확인필요
		  return forward;

	 }
}
