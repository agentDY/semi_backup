package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.filters.SetCharacterEncodingFilter;
import svc.EditInfoService;
import svc.MyPageService;
import vo.ActionForward;
import vo.UserVO;

public class UserEditAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
	   	String id = (String)session.getAttribute("USER_ID");
	   	ActionForward forward = null;
	   	request.setCharacterEncoding("UTF-8");
	   	
	   	System.out.println("EditInfo Action");
	   	System.out.println("id : " + id);
	   	
	   	if(id==null){
	   		System.out.println("Action null 값 받음");
   			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/user/userLogin.lo");
	   	} else {
	   		System.out.println("EditInfo id 받음");
	   		forward = new ActionForward();
   			forward.setRedirect(false);
   			
   			EditInfoService editInfoService = new EditInfoService();
   			MyPageService myPageService = new MyPageService();
   			
   			ArrayList<UserVO> userInfo = myPageService.getUserInfo(id);
   			
   			UserVO Info =  new UserVO();
   			Info = userInfo.get(0); 
   			
   			UserVO user = new UserVO();
   			System.out.println("request USER_ADDR : " + request.getParameter("USER_ADDR4"));
   			user.setUSER_ID(id);
   			if (user.getUSER_ADDR4() != null) {
   			user.setUSER_ADDR(request.getParameter("USER_ADDR1")+" "+request.getParameter("USER_ADDR2")+request.getParameter("USER_ADDR3")+request.getParameter("USER_ADDR4"));
   			} else {
   				user.setUSER_ADDR(Info.getUSER_ADDR());
   				System.out.println("주소지 출력 : " + Info.getUSER_ADDR());
   			}
   			user.setUSER_TEL(request.getParameter("USER_TEL"));
   			user.setUSER_PW(request.getParameter("upw"));
   			user.setUSER_EMAIL(request.getParameter("USER_EMAIL"));
   			
   			UserVO UserInfo = editInfoService.UserInfo(user);
   			
   			if(UserInfo == null) {
   				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				System.out.println("Upadate 실패");
				out.print("<script>");
				out.print("alert('회원정보 업데이트에 실패했습니다.');");
				out.print("location.href='/user/MyPageAction.lo';");
				out.print("</script>");
   			} else {
   				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				System.out.println("Update 성공");
				out.print("<script>");
				out.print("alert('회원정보 업데이트 성공');");
				out.print("location.href='/user/MyPageAction.lo';");
				out.print("</script>");
   			}
   			
   			session.setAttribute("id", user.getUSER_ID());
   			
	   	}
			return forward;
	 }
}