package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import svc.BookCategorySearchService;
import svc.BookSearchService;
import svc.BookSearchTotalService;
import vo.ActionForward;
import vo.ISBNVO;
import vo.PageInfo;

public class BookSearchTotalAction implements Action {

	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
//			 request.setCharacterEncoding("UTF-8");
		 
			List<ISBNVO> bookIndex=new ArrayList<ISBNVO>();
			
			// 전체 
	    	String keyword = request.getParameter("keyword");
	    	System.out.println("키워드:"+ keyword);
	    	
	    	int page = 1;
	    	int limit = 10;
	    	
	    	if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
				page = Integer.parseInt(request.getParameter("page"));
			}
	    	
	    	BookSearchTotalService bookSearch = new BookSearchTotalService();
	    	bookIndex = bookSearch.getBookList(keyword, page);			// 리스트 받아오기
	    	int listCount = bookSearch.getBookListCount(keyword); 	// 리스트 개수 받아오기 30
	    	
	    	System.out.println("listCount키워드 : "+ listCount);
	    	
	    	int maxPage = (int)((double)listCount/limit + 0.95); // 3
			int startPage = (((int)((double)page/10+0.9))-1) * 10+1; // 1
			int endPage = startPage + 10 -1; // 10
			
			System.out.println("startPage 키워드 : "+ startPage);
			System.out.println("endPage 키워드 : "+ endPage);
			
			if(endPage > maxPage) endPage = maxPage; // 3
			
			PageInfo pageInfo = new PageInfo();
			
			pageInfo.setEndPage(endPage); //3
			pageInfo.setListCount(listCount); //30
			pageInfo.setMaxPage(maxPage); //3
			pageInfo.setPage(page); //2
			pageInfo.setStartPage(startPage); //1
			request.setAttribute("pageInfo", pageInfo);
	        request.setAttribute("bookIndex", bookIndex);

	        ActionForward forward= new ActionForward();
	   		forward.setPath("/book/bookSearchPage.jsp");
	   		return forward;	
	}
		 
	
}
