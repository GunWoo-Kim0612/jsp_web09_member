package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web09.dao.MemberDAO;
import com.web09.dto.MemberVO;

/**
 * Servlet implementation class WithdrwalServlet
 */
@WebServlet("/withdrwal.do")
public class WithdrwalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WithdrwalServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String url = "login.do";

		MemberDAO mDao = MemberDAO.getInstance();
		
		HttpSession session = request.getSession();
		
		
		
		MemberVO mVo = new MemberVO();
		mVo.setUserid((String)session.getAttribute("userid"));
		
		System.out.println(mVo.getUserid());
		int result = mDao.withdrwalMember(mVo);
		if (result == 1) {
			// 탈퇴성공, 세션무효화, 로그인페이지로 이동

			request.setAttribute("message", "회원탈퇴 성공");
			session.invalidate();
//			commit된 이후에는 forward 할 수 없습니다.
//			RequestDispatcher dispatcher = request.getRequestDispatcher("member/withdrwalMember.jsp");
//			dispatcher.forward(request, response);

		} else if(result == -1) {
			request.setAttribute("message", "회원탈퇴 실패");
		}

		response.sendRedirect(url);
//		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//		dispatcher.forward(request, response);
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
