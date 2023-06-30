package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import svc.UserListService;
import vo.ActionForward;
import vo.UserVO;

public class UserCheckAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	HttpSession session=request.getSession();
	   		String id= (String)session.getAttribute("id");
	   		// String id = (String)session.getAttribute("USER_ID");
	   		ActionForward forward = null;
	   		
	   		System.out.println("세션id: " + id);
	   		System.out.println("userID: " + request.getParameter("USER_ID"));
	   		
	   		if(id==null){
	   			forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("/user/userLogin.lo");
				
	   		}else if(id.equals("admin")){
		   	    forward = new ActionForward();
		   	    UserListService userListService = new UserListService();
		   		ArrayList<UserVO> userList=userListService.getUserList();
		   		request.setAttribute("userList", userList);
		   		
		   		String RequestURI = request.getRequestURI();
		   		System.out.println("액션에서 받는 uri: " + RequestURI);
		   		
//		   		forward.setPath("userList.jsp");
		   		forward.setPath("/user/user_info.jsp");
		   		
	   		}
	   		else {
	   	    forward = new ActionForward();
	   	    UserListService userListService = new UserListService();
	   		ArrayList<UserVO> userList=userListService.getUserList();
	   		request.setAttribute("userList", userList);
	   		forward.setPath("/index.jsp");
	   		}
	   		
	   		return forward;
	}
}