package action;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import vo.PageInfo;
import vo.RentInfoVO;
import vo.UserVO;
import vo.ActionForward;
import svc.RentInfoService;
import svc.UserListService;

public class SearchUserAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("execute_searchUser");
		ArrayList<UserVO> searchUser = new ArrayList<UserVO>();
		System.out.println("request.getParameter('user_type'): "+request.getParameter("user_type"));
		// name: 이름으로 검색시, id:아이디로 검색시

		String id = request.getParameter("USER_ID");
		
		int page = 1;
		int limit = 10;
		
		// 회원 이름으로 검색시(라디오박스 체크여부) --------------------------------------------------------------------
		if( request.getParameter("user_type")!=null && request.getParameter("user_type").equals("name")) {
//		if( request.getParameter("user_type").equals("name")) {
			String searchName = request.getParameter("search_value");
			System.out.println(searchName);
			
			if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			UserListService searchListService = new UserListService();
			searchUser = searchListService.getSearchListByName(searchName, page); 	// 리스트 받아오기
			int listCount = searchListService.getSearchListCount(searchName);		// 리스트 개수 받아오기
			System.out.println("action클래스의 listcount: " + listCount);
			
			RentInfoService rentInfoService = new RentInfoService();
			ArrayList<RentInfoVO> rentInfo = rentInfoService.getRentInfo(id);
//			String str = rentInfo.get(0).getISBN_TITLE();
//			System.out.println("str: " + str);
			
			int maxPage = (int)((double)listCount/limit+0.95);
			int startPage = (((int)((double)page/10+0.9))-1) * 10+1;
			int endPage = startPage + 10 -1;
			
			if(endPage > maxPage) endPage = maxPage;
			
			PageInfo pageInfo = new PageInfo();
			
			pageInfo.setEndPage(endPage);//10
			pageInfo.setListCount(listCount);//20
			pageInfo.setMaxPage(maxPage);//2
			pageInfo.setPage(page);//2
			pageInfo.setStartPage(startPage);//1	
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("rentInfo", rentInfo);
			request.setAttribute("userList", searchUser);
		}
		
		// 회원 아이디로 검색시(라디오박스 체크여부) --------------------------------------------------------------------
		else if ( request.getParameter("user_type")!=null && request.getParameter("user_type").equals("id")) {
			String searchId = request.getParameter("search_value");
			System.out.println(searchId);
			
			if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			UserListService searchListService = new UserListService();
			searchUser = searchListService.getSearchListById(searchId, page);	// 리스트 받아오기
			int listCount = searchListService.getSearchListCountById(searchId);	// 리스트 개수 받아오기
			System.out.println("action클래스의 listcount: " + listCount);
			
			int maxPage = (int)((double)listCount/limit+0.95);
			int startPage = (((int)((double)page/10+0.9))-1) * 10+1;
			int endPage = startPage + 10 -1;
			
			if(endPage > maxPage) endPage = maxPage;
			
			PageInfo pageInfo = new PageInfo();
			
			pageInfo.setEndPage(endPage);//10
			pageInfo.setListCount(listCount);//20
			pageInfo.setMaxPage(maxPage);//2
			pageInfo.setPage(page);//2
			pageInfo.setStartPage(startPage);//1	
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("userList", searchUser);
		}
		
		ActionForward forward= new ActionForward();
		forward.setPath("/user/seachUser.jsp");
		return forward;
	}
	
	
}
