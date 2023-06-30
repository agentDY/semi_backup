// NoticeModifyProAction2 ================================================================= 수정 화면 - 수정 완료

package action;

import java.io.File;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.NoticeDAO;
import svc.NoticeModifyProService;
import vo.ActionForward;
import vo.NoticeVO;

public class NoticeModifyProAction2 implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		String realFolder = "";
		String saveFolder = "noticeUpload";
		int fileSize = 5 * 1024 * 1024;
//		realFolder = "C:\\jwork\\library\\src\\main\\webapp\\"+saveFolder;
		realFolder = "C:\\jspwork\\lojyyc\\src\\main\\webapp\\"+saveFolder;
		
		//업로드 시점.
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
				
		int notice_num=Integer.parseInt(multi.getParameter("NOTICE_NUM"));
		
		NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
		
		
		
		boolean isModifySuccess = false;
		
		NoticeVO noticeVO=new NoticeVO();
		noticeVO.setNOTICE_NUM(notice_num);
		noticeVO.setNOTICE_TITLE(multi.getParameter("NOTICE_TITLE"));
		noticeVO.setNOTICE_CONT(multi.getParameter("NOTICE_CONT").replace("\r\n","<br>")); 
		
		if (multi.getParameter("NOTICE_PIN") != null ) {
			noticeVO.setNOTICE_PIN(Integer.parseInt(multi.getParameter("NOTICE_PIN")));
			} else {
				noticeVO.setNOTICE_PIN(0);	
			}
		
		noticeVO.setNOTICE_FILE(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		noticeVO.setNOTICE_REALFILE(multi.getFilesystemName("NOTICE_FILE"));
		
		if(multi.getOriginalFileName((String) multi.getFileNames().nextElement()) != null) {
			noticeModifyProService.removeFile(notice_num);
		}
		

		isModifySuccess = noticeModifyProService.modifyNotice(noticeVO);
		

		if(!isModifySuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패');");
			out.println("history.back()");
			out.println("</script>");
			
		} else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/notice/noticeModifyResult.lo?notice_num="+noticeVO.getNOTICE_NUM()); 
			
			}

		return forward;
	}

}