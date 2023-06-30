package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import svc.UserLoginService;
import vo.ActionForward;
import vo.UserVO;

public class UserLoginAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	HttpSession session=request.getSession();
	   		UserVO user=new UserVO();
//	   		System.out.println("USER_ID ::::::" + request.getParameter("USER_ID"));
	   		user.setUSER_ID(request.getParameter("USER_ID"));
	   		user.setUSER_PW(request.getParameter("USER_PW"));
	   		
	   		System.out.println("UserLoginAction : " + user.getUSER_ID());	// 로그인된 아이디
	   		
	   		UserLoginService userLoginService = new UserLoginService();
	   		boolean loginResult = userLoginService.login(user);	// true
	   		String username = userLoginService.nm(user);	// 유저이름 어드민
	   		
	   	    session.setAttribute("username", user.getUSER_NAME());
	   	    session.setAttribute("USER_ID", user.getUSER_ID());
//	   	    session.setAttribute("USER_ID", user.getUSER_ID());
//	   	    session.setAttribute("id", request.getParameter("USER_ID"));	//	세션에 id로 로그인한 아이디를 저장한다.
	   	    
	   	    System.out.println("세션에 저장된 id: " + session.getAttribute("USER_ID"));
	   	    System.out.println("세션에 저장된 유저 username : " + session.getAttribute("username"));
	   	    System.out.println("loginResult : " + loginResult);
	   	    

	   		JSONObject responseObject = new JSONObject();
	   		responseObject.put("username", session.getAttribute("username"));
	   		responseObject.put("USER_ID", session.getAttribute("USER_ID"));
	   		response.setContentType("application/json");
	   		response.setCharacterEncoding("UTF-8");
	   		response.getWriter().write(responseObject.toString());
	   		
	   		ActionForward forward = null;
	 		
	   		if(loginResult){
		   	    System.out.println("login성공");
//	   			forward = new ActionForward();
//		   		forward.setPath("index.jsp");
	   		} else{
		   	    System.out.println("login실패");
		   	    session.invalidate();
		   	    PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('아이디나 비밀번호가 존재하지 않습니다.');");
				out.print("location.href='/index.jsp';");
				out.print("</script>");
	   		}
//	   		System.out.println("forward : " + forward);
	   		return null;
	}
}