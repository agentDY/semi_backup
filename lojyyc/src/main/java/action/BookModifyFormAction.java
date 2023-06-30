package action;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.*;
import vo.*;

public class BookModifyFormAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	System.out.println("액션 : "+request.getParameter("USER_ID"));
		 	ActionForward forward = new ActionForward();
			String isbn_book=request.getParameter("isbn_book");
			System.out.println("isbn_book"+isbn_book);
//			String isbn_book="8907"; // /////////////테스트용 위에꺼로 바꿔야함
			HttpSession admin=request.getSession();
			String admin_id = (String) admin.getAttribute("USER_ID");
			System.out.println("액션세션:"+(String) admin.getAttribute("USER_ID"));
			BookDetailService bookDetailService = new BookDetailService();	
		   	List<ISBNVO> isbnvo=bookDetailService.getBookList(isbn_book);
		   	BookModifyProService bookModifyProService = new BookModifyProService();
		   	boolean isArticleWriter = bookModifyProService.isArticleWriter(admin_id);
		   	System.out.println("isArticleWriter: "+isArticleWriter);
		   	request.setAttribute("isbn_book", isbnvo);
		   	
		   	forward.setPath("/book/book_modify.jsp");
		   	
//		   	if(isArticleWriter) {
//		   		response.setContentType("text/html;charset=UTF-8");
//				PrintWriter out=response.getWriter();
//				out.println("<script>");
//				out.println("alert('성공');");
//				out.println("history.back();");
//				out.println("</script>");
//				forward.setPath("/board/qna_board_modify.jsp");
//		   	}else {
//		   		response.setContentType("text/html;charset=UTF-8");
//				PrintWriter out=response.getWriter();
//				out.println("<script>");
//				out.println("alert('수정할 권한이 없습니다.');");
//				out.println("history.back();");
//				out.println("</script>");
//		   	}
	   		return forward;
	   		
	 }
	 
}