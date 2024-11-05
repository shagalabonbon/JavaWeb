<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Password</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
<link rel="stylesheet" href="/JavawebTest/css/buttons.css">	
</head>
<body style="padding:15px">
		<form method="post" action="/JavawebTest/user/update/password" class="pure-form">
			<fieldset>
				<legend>修改密碼</legend>
				帳號：${userCert.username}<p/>
				舊密碼：<input type="password" name="oldPassword" placeholder="請輸入舊密碼" required><p/>
				新密碼：<input type="text" name="newPassword" placeholder="請輸入新密碼" required><p/>
				
				<button type="reset" class="button-warning pure-button">Reset</button>
				<button type="submit" class="button-success pure-button">Submit</button>
				  
			</fieldset>
		</form>
</body>
</html>