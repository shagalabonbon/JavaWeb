<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
<link rel="stylesheet" href="/JavawebTest/css/buttons.css">	
<link rel="stylesheet" href="/JavawebTest/css/center.css">	
</head>
<body style="padding:15px">
	<form method="post" action="/JavawebTest/login" class="pure-form">  <!-- 登入頁面 -->
			<fieldset>
				<legend>🌐Login</legend>
				👨‍💻：<input type="text" name="username" placeholder="請輸入帳號" required><p/>
				🔒：<input type="password" name="password" placeholder="請輸入密碼" required><p/>
				
				<button type="reset" class="button-success pure-button">重置</button>
				<button type="submit" class="button-success pure-button">登入</button>
				  
			</fieldset>
	</form>

</body>
</html>