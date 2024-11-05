<%@ page import="java.util.Random" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 這是 JSP 註解 --%>
<%-- 網址：http://localhost:8080/JavawebTest/lottery.jsp --%>

<%-- Model 0--%>
<%                                // < % % > 用於插入Java代碼  
    Random random = new Random();
	int n1 = random.nextInt(10);  // 0~9 隨機數 總共印出四位數 XXXX = n1n2n3n4
	int n2 = random.nextInt(10);
	int n3 = random.nextInt(10);  
	int n4 = random.nextInt(10);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lottery電腦選號</title>
	</head>
	<body>
	    <H1>
	    <%=n1 %>
		<%=n2 %>
		<%=n3 %>
		<%=n4 %>  
	    </H1>
	</body>
</html>