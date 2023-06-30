package action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import svc.BookReturnService;
import vo.ActionForward;
import vo.RentInfoVO;
import vo.RentVO;

public class BookReturnAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String id = request.getParameter("rent_id");	// rent_id_seq
		System.out.println("리턴액션의 id: " + id); 	// rent_book 값
		
//		String idid = request.getParameter("rentBookUserId");	// 선택된 유저의 아이디값
		String idid = request.getParameter("u_id");	// 선택된 유저의 아이디값
		System.out.println("idid: " + idid);
		
		// 세션아이디 확인용 //
		HttpSession session=request.getSession();
		System.out.println("session 아이디값 : " + session.getAttribute("USER_ID"));
		//////////////////
		
		RentInfoVO rentInfoVo = new RentInfoVO();
		BookReturnService returnService = new BookReturnService();
		boolean Count = returnService.BookReturnService(id);
		ActionForward forward = new ActionForward();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		///////////////////////////////////////////////////
		JSONObject responseObject = new JSONObject();
   		responseObject.put("userid", session.getAttribute("USER_ID"));
   		response.setContentType("application/json");
   		response.setCharacterEncoding("UTF-8");
   		response.getWriter().write(responseObject.toString());
   		////////////////////////////////////////////////
		
		
		
//		out.println("<script>");
//		out.println("alert('반납이 완료되었습니다')");
//		out.println("history.back();");
//		out.println("location.reload();");
//		out.println("location.href = document.referrer;");
//		out.print("location.href ='getUserRentInfo.lo&rentBookUserId='"+ idid +";");
//		out.println("</script>");
		
//		return forward;
		return null;
	}
}
