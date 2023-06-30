package action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BookDAO;
import svc.BookSearchService;
import vo.ActionForward;
import vo.ISBNVO;
import vo.PageInfo;

public class BookSearchAction implements Action {
		public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
//			request.setCharacterEncoding("UTF-8");
			ArrayList<ISBNVO> bookIndex=new ArrayList<ISBNVO>();
			System.out.println("request.getParameter(keyword):" + request.getParameter("searchKeyword"));
			
			String keyword = request.getParameter("searchKeyword");
			int page = 1;
			int limit = 10;
			
			// 책 제목으로 검색하기 ----------------------------------------------------------------------------------------------
			if( request.getParameter("keyword_type")!=null && request.getParameter("keyword_type").equals("key_title")) {
		    	
		    	if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
					page = Integer.parseInt(request.getParameter("page"));
				}
		    	
		    	BookSearchService bookSearch = new BookSearchService();
		    	bookIndex = bookSearch.getTitleSearchBookList(keyword, page);
		    	int listCount = bookSearch.getBookListCount(keyword, request.getParameter("keyword_type"));
		    	
		    	System.out.println("action의 listCount" + listCount);
		    	
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
			}
			
			// 저자 이름으로 검색하기 ----------------------------------------------------------------------------------------------
			else if (request.getParameter("keyword_type")!=null && request.getParameter("keyword_type").equals("key_author")) {
		    	
		    	if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
					page = Integer.parseInt(request.getParameter("page"));
				}
		    	
		    	BookSearchService bookSearch = new BookSearchService();
		    	bookIndex = bookSearch.getAuthorSearchBookList(keyword, page);
		    	int listCount = bookSearch.getBookListCount(keyword, request.getParameter("keyword_type"));
		    	
		    	System.out.println("action의 listCount" + listCount);
		    	
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
			}
			
			// 출판사 이름으로 검색하기 -----------------------------------------------------------------------------------------------
			else if (request.getParameter("keyword_type")!=null && request.getParameter("keyword_type").equals("key_company")) {

		    	if( !(request.getParameter("page")==null || request.getParameter("page").equals(""))){
					page = Integer.parseInt(request.getParameter("page"));
				}
		    	
		    	BookSearchService bookSearch = new BookSearchService();
		    	bookIndex = bookSearch.getCompanySearchBookList(keyword, page);
		    	int listCount = bookSearch.getBookListCount(keyword, request.getParameter("keyword_type"));
		    	
		    	System.out.println("action의 listCount" + listCount);
		    	
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
			}
			
			ActionForward forward= new ActionForward();
			forward.setPath("/book/bookSearchPage.jsp");
//			forward.setPath("/user/bookSearchPage.jsp");
//			forward.setPath("/library/index.jsp");
			return forward;	
	}
}
