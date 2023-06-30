package action;

import java.util.*; 
import javax.servlet.http.*;
import svc.UserListService;
import vo.PageInfo;
import vo.UserVO;
import vo.ActionForward;

public class UserListAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("executeUserList");
		ArrayList<UserVO> userList=new ArrayList<UserVO>();
		int page = 1;
		int limit = 10;
		
		if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
			page = Integer.parseInt(request.getParameter("page"));
		} 
//		
		UserListService userListService = new UserListService();
		int listCount = userListService.getListCount();
		userList = userListService.getUserList(page, limit);
		
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int)((double)page/10+0.9))-1) * 10+1;
		int endPage = startPage + 10 -1;
		
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("userList", userList);
		
		ActionForward forward= new ActionForward();
		forward.setPath("/user/userList.jsp");
		return forward;
	} 
}
