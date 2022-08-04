<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.sql.*" %>
<%@page import="javax.sql.*" %>
<%@page import="javax.naming.*" %>
<%!
   Connection conn=null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   try{         
      
      Context init = new InitialContext();
       DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB2");
       conn = ds.getConnection();
       out.println("DBCP 연결성공");
      
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(conn != null)
            conn.close();   
      } catch (Exception e){
         e.printStackTrace();
      }
   }
%>

</body>
</html>