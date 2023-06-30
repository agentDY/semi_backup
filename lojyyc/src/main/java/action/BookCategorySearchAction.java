package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookCategorySearchService;
import svc.BookSearchService;
import vo.ActionForward;
import vo.ISBNVO;
import vo.PageInfo;

public class BookCategorySearchAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("UTF-8");

		List<ISBNVO> bookIndex = new ArrayList<ISBNVO>();

		String keyword = request.getParameter("keyword");
		System.out.println("카테고리searchAction " + keyword);

		int page = 1;
    	int limit = 10;
    	
    	if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
			page = Integer.parseInt(request.getParameter("page"));
		}
    	
		BookCategorySearchService bookSearch = new BookCategorySearchService();
		bookIndex = bookSearch.getBookList(keyword, page);
		
		int listCount = bookSearch.getBookListCount(keyword); 
    	
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
		request.setAttribute("bookIndex", bookIndex);

		ActionForward forward = new ActionForward();
		forward.setPath("/book/bookSearchPage.jsp");
		return forward;
		
	}
}
