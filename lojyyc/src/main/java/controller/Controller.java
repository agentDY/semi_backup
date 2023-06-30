package controller;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import action.BookCategorySearchAction;
import action.BookDeleteProAction;
import action.BookDetailAction;
import action.BookModifyFormAction;
import action.BookModifyProAction;
import action.BookRentAction;
import action.BookReturnAction;
import action.BookSearchAction;
import action.BookSearchPageAction;
import action.BookSearchTotalAction;
import action.BookWriteProAction;
import action.HomePageAction;
import action.MyPageAction;
import action.NoticeDeleteAdminAction;
import action.NoticeDeleteProAction;
import action.NoticeDetailAction;
import action.NoticeListAction;
import action.NoticeModifyAdminAction;
import action.NoticeModifyFormAction;
import action.NoticeModifyProAction2;
import action.NoticeModifyResultAction;
import action.NoticeSearchResultAction;
import action.NoticeWriteAdminAction;
import action.NoticeWriteFormAction;
import action.NoticeWriteProAction;
import action.SearchUserAction;
import action.UserCheckAction;
import action.UserDeleteAction;
import action.UserDoubleCheckAction;
import action.UserEditAction;
import action.UserInfoAction;
import action.UserJoinAction;
import action.UserListAction;
import action.UserLoginAction;
import action.UserLogoutAction;
import action.UserRentInfoAction;
import vo.ActionForward;

@WebServlet("*.lo")
public class Controller extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doProcess(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      String RequestURI = request.getRequestURI();
      String contextPath = request.getContextPath();
      String command = RequestURI.substring(contextPath.length());
      ActionForward forward = null;
      Action action = null;
      System.out.println("경로 :" + command);
//      HttpSession session = request.getSession(false);
//      if (session != null)
//         session.setAttribute("id", request.getParameter("USER_ID"));

      if (command.equals("/user/callProc.lo")) {
         Connection conn = getConnection();
         try {
            CallableStatement cstmt = conn.prepareCall("{call RENT_DELAY_PROC()}");
            cstmt.executeQuery();
            commit(conn);
            cstmt.close();

            close(conn);
            System.out.println("프로시저 호출 완료");
         } catch (Exception e) {
            e.getMessage();
         }
      } 
      else if (command.equals("/user/userJoin.lo")) {
         forward = new ActionForward();
         forward.setRedirect(false);
         forward.setPath("joinForm.jsp");
      } 
      else if (command.equals("/user/userJoinAction.lo")) {
         action = new UserJoinAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      else if (command.equals("/user/userDoubleCheckAction.lo")) {
         action = new UserDoubleCheckAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      else if (command.equals("/user/userLogin.lo")) {
         forward = new ActionForward();
//         forward.setRedirect(true);
//         forward.setPath("/index.jsp");
         	response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out=response.getWriter();
	        out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='/index.jsp';");
			out.println("</script>");
      } 
      else if (command.equals("/user/userLoginAction.lo")) {
         System.out.println("로그인액션 컨트롤러");
         action = new UserLoginAction();
         try {
        	 HttpSession session = request.getSession(false);
             if (session != null)
                session.setAttribute("id", request.getParameter("USER_ID"));
            forward = action.execute(request, response);
            System.out.println("로그인액션 컨트롤러 아래출력");
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      else if (command.equals("/NewFile.lo")) {
         System.out.println("고우투홈 액션 컨트롤러");
         action = new HomePageAction();

         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      else if (command.equals("/user/userLogoutAction.lo")) {
         System.out.println("로그아웃 액션 컨트롤러");
         action = new UserLogoutAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      else if (command.equals("/user/userCheckAction.lo")) {
         System.out.println("유저체크액션 컨트롤러");
         action = new UserCheckAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      else if (command.equals("/user/updateForm.lo")) {
         action = new UserInfoAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/user/EditInfo.lo")) {
         System.out.println("정보수정 2단계 컨트롤러");
         action = new UserEditAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/user/userDelete.lo")) {
         System.out.println("회원탈퇴 경로찍기 Controller");
         action = new UserDeleteAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      else if (command.equals("/user/MyPageAction.lo")) {
         action = new MyPageAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      else if (command.equals("/user/getUserRentInfo.lo")) {
         System.out.println("/getUserRentInfo.lo 컨트롤러");
         action = new UserRentInfoAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/user/userList.lo")) {
         System.out.println("controller1_userList");
         action = new UserListAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            System.out.println("userList_controller 에러");
         }
      }
      else if (command.equals("/user/searchUser.lo")) {
         System.out.println("controller2_searchUser");
         action = new SearchUserAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            System.out.println("searchUser_controller 에러");
            e.printStackTrace();
         }
      }
      else if (command.equals("/user/returnBook.lo")) {
         System.out.println("returnBook 컨트롤러");
         action = new BookReturnAction();
         try {
            forward = action.execute(request, response);

         } catch (Exception e) {
            System.out.println("returnBook controller 에러");
            e.printStackTrace();
         }
      } 
      else if (command.equals("/book/bookSearch.lo")) {
         action = new BookSearchAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
     
      else if (command.equals("/book/bookCategorySearch.lo")) {
         System.out.println("키워드카테고리액션: " + request.getParameter("keyword"));

         if (request.getParameter("keyword").equals("전체")) {
            action = new BookSearchTotalAction();
         } else {
            System.out.println("category 검색");
            action = new BookCategorySearchAction();
         }

         try {
            forward = action.execute(request, response);
            forward.setPath("/book/bookSearchPage.jsp");
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/book/bookDetail.lo")) {
         action = new BookDetailAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/book/bookRent.lo")) {
         action = new BookRentAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/book/bookDeleteForm.lo")) {
         String nowIsbn = request.getParameter("isbn_book");
         System.out.println("컨트롤러isbn: " + nowIsbn);
         request.setAttribute("isbn_id", nowIsbn);
//                     int board_num=Integer.parseInt(request.getParameter("board_num"));
//                     request.setAttribute("board_num",board_num);
         forward = new ActionForward();
         forward.setPath("/book/book_delete.jsp");
      }
      else if (command.equals("/book/bookDeletePro.lo")) {
         action = new BookDeleteProAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      else if (command.equals("/book/bookWriteForm.lo")) {
         forward = new ActionForward();
         forward.setPath("/book/book_write.jsp");
         System.out.println("111");
      }
      else if (command.equals("/book/bookWritePro.lo")) {
         action = new BookWriteProAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/book/bookModifyForm.lo")) {
         System.out.println("컨트롤러");
         action = new BookModifyFormAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/book/bookModifyPro.lo")) {
         action = new BookModifyProAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeWriteAdmin.lo")) {
         action = new NoticeWriteAdminAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeWriteForm.lo")) {
         action = new NoticeWriteFormAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
      else if (command.equals("/notice/noticeWritePro.lo")) {
         action = new NoticeWriteProAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeList.lo")) {
         action = new NoticeListAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeList.lo")) {
         action = new NoticeListAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeSearchResult.lo")) {
         action = new NoticeSearchResultAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeDetail.lo")) {
         action = new NoticeDetailAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeModifyAdmin.lo")) {
         String notice_num = request.getParameter("notice_num");
         request.setAttribute("notice_num", notice_num);
         action = new NoticeModifyAdminAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeModifyResult.lo")) {
         action = new NoticeModifyResultAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeModifyForm.lo")) {
         String notice_num = request.getParameter("notice_num");
         request.setAttribute("notice_num", notice_num);
         action = new NoticeModifyFormAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeModifyFile.lo")) {
         String notice_num = request.getParameter("notice_num");
         request.setAttribute("notice_num", notice_num);

         action = new NoticeModifyProAction2();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeModifyPro.lo")) {
         action = new NoticeModifyProAction2();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if (command.equals("/notice/noticeModifyAdminForm.lo")) {
         int notice_num = Integer.parseInt(request.getParameter("notice_num"));
         request.setAttribute("notice_num", notice_num);
         forward = new ActionForward();
         forward.setPath("/notice/qna_notice_modifyAdmin.jsp");

      }
      else if (command.equals("/notice/noticeDeleteAdmin.lo")) {
         int notice_num = Integer.parseInt(request.getParameter("notice_num"));
         request.setAttribute("notice_num", notice_num);
         String notice_file = request.getParameter("notice_file");
         request.setAttribute("notice_file", notice_file);
         String notice_realfile = request.getParameter("notice_realfile");
         request.setAttribute("notice_realfile", notice_realfile);

         action = new NoticeDeleteAdminAction();

         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
      else if (command.equals("/notice/noticeDeletePro.lo")) {

         int notice_num = Integer.parseInt(request.getParameter("notice_num"));
         request.setAttribute("notice_num", notice_num);

         String notice_file = request.getParameter("notice_file");
         request.setAttribute("notice_file", notice_file);

         String notice_realfile = request.getParameter("notice_realfile");
         request.setAttribute("notice_realfile", notice_realfile);

         action = new NoticeDeleteProAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
      else {
         try {
            // fileName 파라미터로 파일명을 가져온다.
            String fileName = request.getParameter("NOTICE_FILE");

            // 파일이 실제 업로드 되어있는(파일이 존재하는) 경로를 지정한다.
            String filePath = "C:\\jspwork\\lojyyc\\src\\main\\webapp\\noticeUpload\\";
//            String filePath = "C:\\jwork\\lojyyc\\src\\main\\webapp\\noticeUpload\\";

            // 경로와 파일명으로 파일 객체를 생성한다.
            File dFile = new File(filePath + "/" + fileName); // 슬래시 삽입

            // 마입타입 추가
            String mimeType = getServletContext().getMimeType(dFile.toString());
            if (mimeType == null) {
               response.setContentType("application/octet-stream");
            }

            // 파일 길이를 가져온다.
            int fSize = (int) dFile.length();

            // 파일이 존재
            if (fSize > 0) {

               // 파일명을 URLEncoder 하여 attachment, Content-Disposition Header로 설정
               String encodedFilename = "attachment; filename*=" + "UTF-8" + "''"
                     + URLEncoder.encode(fileName, "UTF-8");

               // ContentType 설정
               response.setContentType("application/octet-stream; charset=utf-8");

               // Header 설정
               response.setHeader("Content-Disposition", encodedFilename);

               // ContentLength 설정
               response.setContentLengthLong(fSize);

               BufferedInputStream in = null;
               BufferedOutputStream out = null;

               in = new BufferedInputStream(new FileInputStream(dFile));
               out = new BufferedOutputStream(response.getOutputStream());

               byte[] buffer = new byte[409600];
               int bytesRead = 0;

               /*
                * 모두 현재 파일 포인터 위치를 기준으로 함 (파일 포인터 앞의 내용은 없는 것처럼 작동) int read() : 1byte씩 내용을 읽어
                * 정수로 반환 int read(byte[] b) : 파일 내용을 한번에 모두 읽어서 배열에 저장 int read(byte[] b. int
                * off, int len) : 'len'길이만큼만 읽어서 배열의 'off'번째 위치부터 저장
                */
               while ((bytesRead = in.read(buffer)) != -1) {
                  out.write(buffer, 0, bytesRead);
               }

               // 버퍼에 남은 내용이 있다면, 모두 파일에 출력
               out.flush();

               System.out.println("completed");
               in.close();
               out.close();
            } else {
               throw new FileNotFoundException("파일이 없습니다.");
            }
         } catch (Exception e) {
            e.getMessage();
         }
      }

      
      
      
      if (forward != null) {
         if (forward.isRedirect()) {
            System.out.println("리다이렉트로 이동: " + forward.getPath());
            response.sendRedirect(forward.getPath());
         } else {
            System.out.println("포워딩으로 이동: " + forward.getPath());
            RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
            dispatcher.forward(request, response);
         }
      } else {
         System.out.println("forward가 null인 경우");
      }

   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doProcess(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doProcess(request, response);
   }

}