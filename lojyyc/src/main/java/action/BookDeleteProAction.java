package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import svc.BookDeleteProService;
import vo.ActionForward;

public class BookDeleteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 
		HttpSession admin=request.getSession();
		String admin_id = (String) admin.getAttribute("USER_ID");
		
		String isbn_book = request.getParameter("isbn_book");
//		String isbn_book = "8908";
		
		System.out.println("딜리트액션 아이디값: "+admin_id);
		System.out.println("딜리트액션 isbn값: "+isbn_book);
		ActionForward forward = null;
//		int board_num=Integer.parseInt(request.getParameter("board_num"));
		String nowPage = request.getParameter("page");
		
		BookDeleteProService bookDeleteProService = new BookDeleteProService();
		boolean isArticleWriter = bookDeleteProService.isArticleWriter(admin_id);
//		ISBNVO isbnvo=new ISBNVO();
	

		if(!isArticleWriter){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			System.out.println(request.getParameter("USER_PW"));
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		else{
			boolean isDeleteSuccess = bookDeleteProService.removeArticle(isbn_book);
			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패\\n이미 삭제처리된 도서일 가능성이 있습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제처리가 완료되었습니다.');");
				out.println("location.href='bookSearch.lo';"); //**전체리스트로 이동하는 실제 url패턴으로 변경해야합니다.
				out.println("</script>");
//				forward = new ActionForward();
//				forward.setRedirect(true);
//				forward.setPath("booklist.lo");
			}
			
		}


		return forward;
	}

}