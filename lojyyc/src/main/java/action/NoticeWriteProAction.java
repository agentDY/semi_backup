// NoticeWriteProAction ====================================================     공지사항 등록 DB에 등록
package action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.NoticeWriteProService;
import vo.ActionForward;
import vo.NoticeVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeWriteProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		String realFolder = "";
		String saveFolder = "/noticeUpload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = "C:\\jspwork\\lojyyc\\src\\main\\webapp\\"+saveFolder;		//**절대경로
//		realFolder = "C:\\jwork\\library\\src\\main\\webapp\\"+saveFolder;		//**절대경로
					//	C:\jspwork\library\src\main\webapp\noticeUpload 
		System.out.println("realFolder77777: "+realFolder);
		
		//파일업로드
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
				
		NoticeVO noticeVO= new NoticeVO();
		noticeVO.setNOTICE_TITLE(multi.getParameter("NOTICE_TITLE"));
		noticeVO.setNOTICE_CONT(multi.getParameter("NOTICE_CONT").replace("\r\n","<br>"));
		
		//상단고정여부
		if (multi.getParameter("NOTICE_PIN") != null ) {
		noticeVO.setNOTICE_PIN(Integer.parseInt(multi.getParameter("NOTICE_PIN")));
		} else {
			noticeVO.setNOTICE_PIN(0);	
		}
		
		
		noticeVO.setNOTICE_FILE(multi.getOriginalFileName((String) multi.getFileNames().nextElement())); //원래 파일명
		noticeVO.setNOTICE_REALFILE(multi.getFilesystemName("NOTICE_FILE"));		//실제파일명 (중복 시 파일명 달라지는 경우)
		
		NoticeWriteProService noticeWriteProService = new NoticeWriteProService();
		
		//게시물 등록
		boolean isWriteSuccess = noticeWriteProService.registArticle(noticeVO);
		
		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/notice/noticeList.lo");
		}

		return forward;

	}

}