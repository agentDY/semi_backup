//    NoticeDeleteProAction==================================================================== 공지사항 삭제진행

package action;

import java.io.File;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.NoticeDeleteProService;
import vo.ActionForward;

public class NoticeDeleteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		ActionForward forward = null;
		int notice_num= (Integer) request.getAttribute("notice_num");
		String notice_file = (String) request.getAttribute("notice_file");
		String notice_realfile = (String) request.getAttribute("notice_realfile");
		
		NoticeDeleteProService noticeDeleteProService = new NoticeDeleteProService();
		boolean isAdmin =noticeDeleteProService.isWriterAdmin(notice_num, request.getParameter("DELETE_CONFIRM"));
		
		//삭제 시 첨부파일 같이 삭제

		if(!isAdmin){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 올바르지 않습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else{
			boolean isDeleteSuccess = noticeDeleteProService.removeArticle(notice_num, notice_realfile);

			if(!isDeleteSuccess){
				System.out.println("isdel" + isDeleteSuccess);
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("/notice/noticeList.lo?page=1");
			}
		}
		
		return forward;
	}

}