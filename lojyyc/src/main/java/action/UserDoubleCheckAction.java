package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.UserDAO;
import vo.ActionForward;
import vo.UserVO;

public class UserDoubleCheckAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
		 	throws Exception{
//		String id = request.getParameter("id");
		String id = request.getParameter("USER_ID");	// joinForm 에서 id에 입력된 값 [#uid] 
		System.out.println("USER_ID  : " + id);

		UserVO uVO = new UserVO();
		uVO.setUSER_ID(id);
		UserDAO uDAO = new UserDAO();
		boolean result = uDAO.idCheck(uVO);
		String str ;
		
		if(result) {
			if(id=="") {
				str = "아이디를 입력해주세요.";
				}else {
				str = "은(는) 사용하실 수 없는 아이디입니다.";	
				}
			}
		else{str = "은(는) 사용 가능한 아이디입니다.";}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("str", str);
		map.put("id", id);

		JSONObject jObject = new JSONObject();
		jObject.put("map", map);

		response.setContentType("application/x-json; charset=utf-8");
		response.getWriter().print(jObject);
		
		return null;
	}
}
