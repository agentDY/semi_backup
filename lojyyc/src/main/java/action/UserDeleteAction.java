package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserDeleteService;
import vo.ActionForward;


public class UserDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("회원탈퇴 경로찍기 Action");
		HttpSession session = request.getSession();
		String deleteId = (String)session.getAttribute("USER_ID");
		String deletePW = request.getParameter("delPW");
		ActionForward forward = null;
		
		if(deleteId == null) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/user/userLogin.lo");
			
		} else if(deleteId.equals("admin")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('관리자는 탈퇴할 수 없습니다.');");
			out.print("location.href = '/user/deleteForm.jsp'");
			out.print("</script>");
			
		}else {
			System.out.println("deleteId 가 " + deleteId + " 일 때");
			UserDeleteService userDelteService = new UserDeleteService();
			boolean deleteResult = userDelteService.deleteUserCommit(deleteId, deletePW);
			
			if(deleteResult) {
				// 세션 로그아웃 추가
				session.invalidate();
				 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('회원탈퇴 성공');");
				out.print("location.href='/index.jsp'");
				out.print("</script>");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('회원정보 삭제 실패')");
				out.print("location.href='/user/userDelete.lo'");
				out.print("</script>");
			}
			
		}
		return null;
	}

}
