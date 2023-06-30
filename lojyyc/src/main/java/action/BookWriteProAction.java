package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import svc.BookWriteProService;
import vo.*;

public class BookWriteProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
//		BoardBean boardBean = null;
		ISBNVO isbnvo = null;
		String realFolder = "";
		String saveFolder = "/imgs";
		int fileSize = 5 * 1024 * 1024;
//		realFolder = "C:\\jwork\\library\\src\\main\\webapp\\"+saveFolder; 
		realFolder = "C:\\jspwork\\lojyyc\\src\\main\\webapp\\"+saveFolder; //** 실제 경로인데, 다를거로 예상됩니다.
//		ServletContext context = request.getServletContext();
//		realFolder = context.getRealPath(saveFolder);
		System.out.println(realFolder+" Upload Success");
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		isbnvo = new ISBNVO();
		isbnvo.setISBN_TITLE(multi.getParameter("ISBN_TITLE"));
		isbnvo.setISBN_ID(multi.getParameter("ISBN_ID"));
		isbnvo.setISBN_BOOK(multi.getParameter("ISBN_BOOK"));
		isbnvo.setISBN_AUTHOR(multi.getParameter("ISBN_AUTHOR"));
		isbnvo.setISBN_TRANS(multi.getParameter("ISBN_TRANS"));
		isbnvo.setISBN_YEAR(Integer.parseInt(multi.getParameter("ISBN_YEAR").replace(" 년","")));
		isbnvo.setISBN_COM(multi.getParameter("ISBN_COM"));
		isbnvo.setISBN_CG(multi.getParameter("ISBN_CG"));
		String content=multi.getParameter("ISBN_INFO");
		content = content.replace("\r\n","<br>");
		isbnvo.setISBN_INFO(content);
		isbnvo.setISBN_IMG(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		BookWriteProService bookWriteProService = new BookWriteProService();
		boolean isWriteSuccess = bookWriteProService.registArticle(isbnvo);
		
		
		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패\\n입력한 정보를 다시 확인해주세요.\\n(이미 등록된 도서일 가능성이 있습니다.)')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('등록이 완료되었습니다.');");
			out.println("location.href='bookDetail.lo?isbn_book="+multi.getParameter("ISBN_BOOK")+"';");
			out.println("</script>");
//			forward = new ActionForward();
//			forward.setRedirect(true);
//			forward.setPath("booklist.lo");
		}
		return forward;
	}
}