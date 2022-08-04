package com.web09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.web09.dto.MemberVO;


public class MemberDAO {
	private MemberDAO() {

	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	//매번 커넥션 호출할필요가 있으므로 메소드생성
	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB2");
		conn = ds.getConnection();
		return conn;
	}

	// 사용자 인증시 메소드
	public int userCheck(String userid, String pwd) {
		int result = -1;
		String sql = "SELECT PWD FROM MEMBER WHERE USERID = ?" ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//메소드를 통한 커넥션 객체호출
			conn = getConnection();
			//커넥션객체를 통해 PrepareStatment 객체생성 메소드로 DB에 sql문 전달 
			pstmt = conn.prepareStatement(sql);
			// sql 의 ? 처리 (1번? 에 해당 매개변수값 넣는다)
			pstmt.setString(1, userid);
			
			//executeQuery의 반환타입 : ResultSet Object (pstmt의 sql 실행으로 생성된 데이터(ResultSet Object)형태로 반환 
			rs = pstmt.executeQuery();
			
			
			//ResultSet rows 형태의 데이터에 커서가 이동하면서 현재값이 유효하면 참 유효하지않으면 거짓(값이 있는지없는지로 간단하게 이해하자)
			if(rs.next()) {
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {		
					result = 1;
				}else {
					result = 0;
				}
			}else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if( pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}
	
	//아이디로 회원정보 가져오는 메소드
	public MemberVO getMemver(String userid) {
		MemberVO mVo = null;
		String sql = "SELECT * FROM MEMBER WHERE USERID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mVo  = new MemberVO();
				//MemberVO객체의 맴버변수에 sql을 실행후 반환된 ResultSet (rs)객체를 통해 값 read rs.getString("DB Column")
				mVo.setName(rs.getString("name"));
				mVo.setUserid(rs.getString("userid"));
				mVo.setPwd(rs.getString("pwd"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setAdmin(Integer.parseInt(rs.getString("admin")));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if( pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return mVo;
	}
	
	public int confirmID(String userid) {
		int result = -1;
		String sql = "SELECT USERID FROM MEMBER WHERE USERID=?";
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			//아이디로 조회했을때 값이 있으면 1, 없으면-1 중복체크가된다
			if(rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if( pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
				
		
		return result;
		
	}
	
	
	public int insertMember(MemberVO mVo) {
		//조건문 오류나면 여기가 1일것임
		int result = -1;
		
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//결과를 반환하지 않기 때문에 필요없다
		//ResultSet rs = null;
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getPhone());
			pstmt.setInt(6, mVo.getAdmin());
			
			//결과 1반환
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	public int updateMember(MemberVO mVo) {
		int result = -1;
		
		String sql = "UPDATE MEMBER SET PWD = ?, EMAIL = ?, PHONE = ?, ADMIN = ? WHERE USERID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			//이때 가져오는 값은 Update폼에 입력한 정보 mVo
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getUserid());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public int withdrwalMember(MemberVO mVo) {
		int result = -1;
		
		
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			System.out.println(mVo.getUserid());
			pstmt.setString(1, mVo.getUserid());
			System.out.println(result);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	
	
}