<%@page import="javaweb.model.dto.UserDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	

<%@ taglib uri="jakarta.tags.core" prefix="c" %> 

<%
    //得到 UserServlet 所傳來的資料
    List<UserDto> userDtos = (List<UserDto>)request.getAttribute("userDtos");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User</title>
		
		<%-- 引入 pure css --%>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
		<link rel="stylesheet" href="/JavawebTest/css/buttons.css">	
	</head>
	<body>
			<!-- Menu bar -->
			<%@include  file="/WEB-INF/view/menu.jspf"%>  
		
		<div style="padding:15px">
			<form method="post" action="/JavawebTest/user/add" class="pure-form">
				<fieldset>
					<legend>User新增</legend>
					Username：<input type="text" name="username" placeholder="請輸入用戶名稱" required><p/>
					Password：<input type="password" name="password" placeholder="請輸入密碼" required><p/>
					Email：<input type="email" name="email" placeholder="請輸入email" required><p/>
					Role：<select name="role">
							<option value="ROLE_ADMIN">ADMIN</option>
							<option value="ROLE_USER">USER</option>
					      </select> <p/>
					
					<button type="reset" class="button-warning pure-button">Reset</button>
					<button type="submit" class="button-success pure-button">Submit</button>
					  
				</fieldset>
			</form>
		
		
			<div>
				<fieldset>
					<legend>User 列表</legend>
					<table class="pure-table">
						<thead>
							<tr>
							<th>ID</th>
							<th>帳號</th>
							<th>郵件</th>
							<th>action</th>
							<th>角色(權限)</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
						</thead>
						
						<!-- JSTL + EL 寫法 (新)  -->                         <!-- c:function 呼叫函式庫標籤 -->
						
						<c:forEach var="userDto" items="${ userDtos }">       <!-- var：取出的變數名稱 , items：傳入的物件 -->
							<tr>
								<td>${ userDto.userId }</td>    <!-- EL 表達式 -->
								<td>${ userDto.username }</td>
								<td>${ userDto.email } </td>
								<td>${ userDto.active }</td>
								<td>${ userDto.role }</td>
								<td><a href="/JavawebTest/user/get?username=${ userDto.username }" class="button-secondary pure-button">修改</a></td>
								<td><a href="/JavawebTest/user/delete?userId=${ userDto.userId }" class="button-error pure-button">刪除</a></td>
							</tr>
						</c:forEach>
						
						<!-- Script let 寫法 (舊) -->
						  
						<!--% for(UserDto userDto : userDtos) { %>
							<tr>
								<td>< %=userDto.getUserId() %></td>    
								<td>< %=userDto.getUsername()%></td>
								<td>< %=userDto.getEmail()%></td>
								<td>< %=userDto.getActive() %></td>
								<td>< %=userDto.getRole() %></td>
								<td><a href="/JavawebTest/user/delete?userId= < %=userDto.getUserId() %>" class="button-error pure-button">刪除</td>
								<td><a href="/JavawebTest/user/get?username= < %=userDto.getUsername() %>" class="button-secondary pure-button">修改</td>							
							</tr>
						< % } % > -->
						
					</table>
				</fieldset>
			</div>
		</div>
	</body>
</html>

