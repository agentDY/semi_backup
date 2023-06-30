package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BookRentService;
import vo.ActionForward;
import vo.RentVO;

public class BookRentAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<RentVO> bookRentInfo = new ArrayList<RentVO>();

		String keyword = request.getParameter("keyword");
		System.out.println("BookRentAction keyword = isbn_book :" + keyword);

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("USER_ID");
		System.out.println("BookRentAction id : " + id);

		BookRentService bookRent = new BookRentService();

		if (bookRent != null) {
			bookRentInfo = bookRent.getRentInfo(keyword, id);
			request.setAttribute("bookRentInfo", bookRentInfo);
			System.out.println("대출처리 끝");
		}
		
//		/////////////////////////////////
//		getRentTitle(id);
//		/////////////////////////////////
		
		ActionForward forward = new ActionForward();
		forward.setPath("/book/bookRentPage.jsp");
		return forward;
	}
}
