package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import svc.BookModifyProService;
import vo.*;

public class BookModifyProAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		System.out.println("모디파이액션 파일이름1111111: "+request.getParameter("ISBN_IMG"));

		String realFolder = "";
		System.out.println("1111");
		String saveFolder = "/imgs";
		System.out.println("22222");
		int fileSize = 5 * 1024 * 1024;
		System.out.println("3333");
//		realFolder = "C:\\jwork\\library\\src\\main\\webapp\\"+saveFolder;
		realFolder = "C:\\jspwork\\lojyyc\\src\\main\\webapp\\"+saveFolder;
		System.out.println("4444");
		
		
	 	MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
	 	System.out.println("112333123123:"+multi.getParameter("ISBN_BOOK"));
	 	
		HttpSession admin=request.getSession();
		String admin_id = (String) admin.getAttribute("USER_ID");
		ActionForward forward = null;
		boolean isModifySuccess = false;
//		int board_num=Integer.parseInt(request.getParameter("BOARD_NUM"));
		ISBNVO isbnvo=new ISBNVO();
		BookModifyProService bookModifyProService = new BookModifyProService();
		isModifySuccess=bookModifyProService.isArticleWriter(admin_id);
		
		if(!isModifySuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			isbnvo.setISBN_BOOK(multi.getParameter("ISBN_BOOK"));
			isbnvo.setISBN_ID(multi.getParameter("ISBN_ID"));
			isbnvo.setISBN_IMG(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
			isbnvo.setISBN_TITLE(multi.getParameter("ISBN_TITLE"));
			isbnvo.setISBN_AUTHOR(multi.getParameter("ISBN_AUTHOR"));
			isbnvo.setISBN_TRANS(multi.getParameter("ISBN_TRANS"));
			isbnvo.setISBN_YEAR(Integer.parseInt(multi.getParameter("ISBN_YEAR").replace(" 년","")));
			isbnvo.setISBN_COM(multi.getParameter("ISBN_COM"));
			isbnvo.setISBN_CG(multi.getParameter("ISBN_CG"));
			isbnvo.setISBN_DEL(multi.getParameter("ISBN_DEL"));
			
			String content=multi.getParameter("ISBN_INFO");
			content = content.replace("\r\n","<br>");
			isbnvo.setISBN_INFO(content);
//			isbnvo.setISBN_INFO(multi.getParameter("ISBN_INFO"));

		}			
			isModifySuccess = bookModifyProService.modifyArticle(isbnvo);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패\\n입력한 정보를 다시 확인해주세요.\\n(이미 등록된 도서일 가능성이 있습니다.)')");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정이 완료되었습니다.');");
				out.println("location.href='bookDetail.lo?isbn_book="+multi.getParameter("ISBN_BOOK")+"';");
				out.println("</script>");
//				forward = new ActionForward();
//				forward.setRedirect(true);
//				forward.setPath("booklist.lo"); 
			}
		return forward;
	}

}