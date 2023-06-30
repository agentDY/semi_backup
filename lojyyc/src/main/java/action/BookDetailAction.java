package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import svc.BookCategorySearchService;
import svc.BookDetailService;
import svc.BookSearchService;
import svc.BookSearchTotalService;
import vo.ActionForward;
import vo.ISBNVO;
import vo.PageInfo;

public class BookDetailAction implements Action {

	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		 	request.setCharacterEncoding("UTF-8");
		 
			List<ISBNVO> bookIndex=new ArrayList<ISBNVO>();

	    	String keyword = request.getParameter("isbn_book");
	    	System.out.println("BookDetailAction :" + keyword);
	    	
	    	BookDetailService bookSearch = new BookDetailService();
	    	bookIndex = bookSearch.getBookList(keyword);

	    	request.setAttribute("bookIndex", bookIndex);

	        ActionForward forward= new ActionForward();
	        forward.setPath("../book/bookDetailPage.jsp");
	   		return forward;	
	}
}
