package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MyPageService;
import svc.RentInfoService;
import svc.UserListService;
import vo.ActionForward;
import vo.PageInfo;

import java.io.PrintWriter;
import java.util.*;
import vo.RentInfoVO;
import vo.RentVO;
import vo.UserVO;

public class UserRentInfoAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("유저렌트인포액션이다");
		String idid = request.getParameter("rentBookUserId");	// name으로 보내는 값(user_id)
		System.out.println("idid값: " + idid);
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("USER_ID");	// 세션에 'admin'으로 저장이 되어있다. 
		System.out.println("유저렌트인포액션 session의 id값?: " + id);
		
		int page = 1;
    	int limit = 10;
    	
    	if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 로그인된 아이디가 없을 경우 
    	if (id.equals(null)){
    		PrintWriter out=response.getWriter();
    		forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./index.jsp");
    	} 
    	
    	// 로그인된 아이디가 admin이 아닐경우
    	else if(!id.equals("admin")){
   			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("MyPageAction.lo?id=" + id);
   		}
    	
//    	// 로그인된 아이디가 admin일 경우
    	else if (id.equals("admin")){
   			forward = new ActionForward();
   			forward.setRedirect(false);
   			
//   			회원 정보를 가져오기
   			MyPageService myPageService = new MyPageService();
   			ArrayList<UserVO> UserInfo = myPageService.getUserInfo(idid);

//   			대출 정보를 가져오기
//   			List<RentVO> UserRentInfo = rentInfoService.getUserRentInfo(id); //admin 값을 보내서 rentVO 객체를 받아왔어
   			RentInfoService rentInfoService = new RentInfoService();
   			ArrayList<RentInfoVO> rentInfo = new ArrayList<RentInfoVO>();
   			rentInfo = rentInfoService.getUserRentInfo(idid, page); // 로그인 된 유저의 값들이 저장되어있다
   			
// 개수를 가져오자
   			int listCount = rentInfoService.getRentInfoCount(idid); // 
   			
   			for(int i = 0; i < rentInfo.size(); i++) {
   				request.setAttribute("RENT_NO", rentInfo.get(i).getRENT_ID_SEQ());
	   			request.setAttribute("RENT_TITLE", rentInfo.get(i).getISBN_TITLE());
	   			request.setAttribute("RENT_AUTHOR", rentInfo.get(i).getISBN_AUTHOR());
	   			request.setAttribute("RENT_DATE", rentInfo.get(i).getRENT_DATE());
	   			request.setAttribute("RENT_RETURN", rentInfo.get(i).getRENT_RETURN());
	   			request.setAttribute("RENT_RETURN_DATE", rentInfo.get(i).getRENT_RETURN_DATE());
   			}
   			
//   			System.out.println("책즈에목"+(String)rentInfo.get(0).getISBN_TITLE());
   			
   			int maxPage = (int)((double)listCount/limit + 0.95); 
			int startPage = (((int)((double)page/10 + 0.9)) - 1) * 10 + 1;
			int endPage = startPage + 10 -1;
			
			if(endPage > maxPage) endPage = maxPage;
			
			PageInfo pageInfo = new PageInfo();
			
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);	
			request.setAttribute("pageInfo", pageInfo);
   			request.setAttribute("userList", UserInfo);
   			request.setAttribute("rent", rentInfo);
   			
   			System.out.println("rent 객체 : " + rentInfo);
   			
   			forward.setPath("/user/userRentList.jsp");
   		}
		return forward;
	}
}

