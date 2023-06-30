package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;
import vo.UserVO;

public class BookSearchPageAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HttpSession session=request.getSession();
		 	System.out.println("bookSearchPageAction : " + session.getAttribute("USER_ID"));

	   		ActionForward forward = null;
	   		forward = new ActionForward();
		   	forward.setPath("/book/bookSearchPage.jsp");
		   	
		  return forward;

	 }
}
