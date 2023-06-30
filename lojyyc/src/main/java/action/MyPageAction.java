package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MyPageService;
import svc.RentInfoService;
import vo.ActionForward;
import vo.ISBNVO;
import vo.RentInfoVO;
import vo.RentVO;
import vo.UserVO;

public class MyPageAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession();
   		String id = (String)session.getAttribute("USER_ID"); // session에 저장한 아이디(로그인한 아이디)
   		ActionForward forward = null;
   		
   		System.out.println("request myPageAction의 session id값: " + id);
		
   		if(id==null){
   			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/user/userLogin.lo");
   		}else{
   			forward = new ActionForward();
   			forward.setRedirect(false);
   			MyPageService myPageService = new MyPageService();
   			ArrayList<UserVO> UserInfo = myPageService.getUserInfo(id);
   			UserVO user = new UserVO();
   			user = UserInfo.get(0);
   			RentInfoService rentInfoService = new RentInfoService();
   			
   			request.setAttribute("id", user.getUSER_ID());
   			request.setAttribute("name", (user.getUSER_NAME()));
   			request.setAttribute("addr", (user.getUSER_ADDR()));
   			request.setAttribute("tel", (user.getUSER_TEL()));
   			request.setAttribute("email", (user.getUSER_EMAIL()));
   			request.setAttribute("UserVO", UserInfo);
   			session.setAttribute("id", request.getAttribute("id"));
   			
   			ArrayList<RentInfoVO> rentInfo = rentInfoService.getRentInfo(id);
   			System.out.println("rent 객체 : " + rentInfo);
   			request.setAttribute("rent", rentInfo);
   			
//   			ArrayList<ISBNVO> isbn = rentInfoService.getIsbnInfo(id);
//   			System.out.println("isbn 객체 : " + isbn);
//   			request.setAttribute("isbn", isbn);
   					
//   			RentVO rentVO = new RentVO();
   			
   			
//   			request.setAttribute("RENT_BOOK", rentVO.getRENT_BOOK());
//   			request.setAttribute("RENT_RETURN", rentVO.getRENT_RETURN());
//   			request.setAttribute("RENT_STATUS", rentVO.getRENT_STATUS());
//   			
   			forward.setPath("/user/myPage.jsp");
   			
//   		response.setContentType("text/html;charset=UTF-8");
//   		PrintWriter out=response.getWriter();
//   		out.println("<script>");
//   		out.println("alert('마이페이지입니다.');");
//   		out.println("location.href='./memberLogin.me';");
//   		out.println("</script>");
   		}
   		return forward;
		
	}
}
