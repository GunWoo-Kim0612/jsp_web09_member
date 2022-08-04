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

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/login.jsp";

		
		//update 이후 세션을 통해 로그인유지 처리
		HttpSession session = request.getSession();
		//세션의 속성 loginUser가 존재한다면
		if(session.getAttribute("loginUser") != null) {
			url = "main.jsp"; //로그인페이지가 아닌 메인페이지로 이동
		}
		
		//세션 존재 -> main.jsp,  세선x -> lonin.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		
		String url = "member/login.jsp";
		
		
	
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		int result = mDAO.userCheck(userid, pwd);
		
		if(result == 1 ) {
			//로그인 성공시 페이지 이동  + 세션관리
			MemberVO mVo = mDAO.getMemver(userid);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mVo);
			session.setAttribute("userid", mVo.getUserid());
			request.setAttribute("message", "로그인성공.");
			url="main.jsp";

		}else if(result == 0 )	{
			//로그인 실패  패스워드 null이거나 틀림 login.jsp 의 ${message} 지정 (영역객체)
			request.setAttribute("message", "비밀번호가 틀립니다.");
			
		
		}else if (result == -1){
			request.setAttribute("message", "존재하지 않는 회원입니다.");
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

//login.jsp -> servlet (로그인 성공 실패에 대한 처리)
//성공시 -> main.jsp
//실패시 -> login.jsp

//페이지 이동 방식 1. response.Redirect
//			  2. foward 방식
