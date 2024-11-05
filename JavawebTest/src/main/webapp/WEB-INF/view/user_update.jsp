<%@page import="javaweb.model.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	

<% 
	UserDto userDto = (UserDto)request.getAttribute("userDto");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User修改</title>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
		<link rel="stylesheet" href="/JavawebTest/css/buttons.css">	
	</head>
	<body style="padding:15px">
		<form method="post" action="/JavawebTest/user/update?userId=" class="pure-form">
			<fieldset>
				<legend>User修改</legend>
				序號：<input type="text" name="userId" value="<%=userDto.getUserId() %>" readonly><p/>
				帳號：<input type="text" name="username" value="<%=userDto.getUsername() %>" readonly><p/>
				電郵：<input type="email" name="email" value="<%=userDto.getEmail() %>" readonly><p/>
				狀態：<input type="radio" name="active" value="true" <%=userDto.getActive()?"checked":"" %> >True
					  <input type="radio" name="active" value="false" <%=userDto.getActive()?"":"checked" %> >False<p/>        <%-- checked 進入頁面時的默認選項--%>
				權限：<select name="role">
						<option value="ROLE_ADMIN" <%=userDto.getRole().equals("ROLE_ADMIN")?"selected":"" %> >ADMIN</option>  <%-- selected 進入頁面時的默認選項--%> 
						<option value="ROLE_USER" <%=userDto.getRole().equals("ROLE_USER")?"selected":"" %> >USER</option>
				      </select> <p/>
				<button type="submit" class="button-success pure-button">Update</button>
			</fieldset>
		</form>
		
		<form method="post" action="/JavawebTest/user/update/password" class="pure-form">
			<fieldset>
				<legend>修改密碼</legend>
				<input type="hidden" name="userId" value="<%=userDto.getUserId() %>" readonly><p/>
				<input type="hidden" name="username" value="<%=userDto.getUsername() %>" readonly><p/>
				舊密碼：<input type="password" name="password"><p/>
				新密碼：<input type="password" name="newPassword"><p/>
				
				<button type="submit" class="button-success pure-button">Update</button>
			</fieldset>
		</form>
	</body>
</html>

