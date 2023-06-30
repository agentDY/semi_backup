package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MyPageService;
import svc.RentInfoService;
import vo.ActionForward;
import vo.UserVO;

public class UserInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
   		String id = (String)session.getAttribute("USER_ID");
   		ActionForward forward = null;
   		
   		System.out.println("request myPageAction: " + id);
		
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
	   			
	   			request.setAttribute("id", user.getUSER_ID());
	   			request.setAttribute("name", (user.getUSER_NAME()));
	   			request.setAttribute("addr", (user.getUSER_ADDR()));
	   			request.setAttribute("tel", (user.getUSER_TEL()));
	   			request.setAttribute("email", (user.getUSER_EMAIL()));
	   			request.setAttribute("UserVO", UserInfo);
	   			
	   			forward.setPath("/user/updateForm.jsp");
	   			
	}
		return forward;

}
}
