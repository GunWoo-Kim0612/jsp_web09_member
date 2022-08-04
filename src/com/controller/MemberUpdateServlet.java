package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web09.dao.MemberDAO;
import com.web09.dto.MemberVO;


@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		MemberDAO mDao = MemberDAO.getInstance();
		
		//requst영역에 저장되어있는 userid정보를 MemberDAO 의 메소드의 매개변수로 사용해 해당 아이디의 전체정보를 request영역에 저장
		MemberVO mVo = mDao.getMemver(userid);
		request.setAttribute("mVo", mVo);
		
		
		
		//이동
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//폼에서 받아온 정보
		String userid = request.getParameter("userid");
		String pwd= request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		
		
		//객체생성
		MemberVO mVo = new MemberVO();
		mVo.setUserid(userid);
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setAdmin(Integer.parseInt(admin));
		
		//MemberDAO 의 UpdateMember()메소드를 통해 DB로 전달
		MemberDAO mDao = MemberDAO.getInstance();
		mDao.updateMember(mVo);
		
		//업데이트 submit 이후 login.do
		response.sendRedirect("login.do");
	}

}
