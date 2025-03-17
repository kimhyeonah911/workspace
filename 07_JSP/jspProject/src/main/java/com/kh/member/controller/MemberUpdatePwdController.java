package com.kh.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자가 보낸 데이터 추출
		//pwd == 세션에 로그인된 pwd -> 실패(errorMsg)
		//비밀번호 변경 -> 성공(myPage), 실패(errorMsg)
	
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser==null || !loginUser.getUserPwd().equals(userPwd)) {
			request.setAttribute("errorMsg", "현재 비밀번호가 일치하지 않습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			return;
		}
				
		Member m = new MemberService().updatePwdMember(loginUser.getUserId(), updatePwd);
				
		if(m != null) {
		session.setAttribute("loginUser", m);
		request.getSession().setAttribute("alertMsg", "성공적으로 변경이 완료되었습니다.");
					
		response.sendRedirect(request.getContextPath() + "/myPage.me");
		}else {
		//에러문구가 있는 에러페이지
		request.setAttribute("errorMsg", "비밀번호 변경에 실패하였습니다.");
					
		request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
