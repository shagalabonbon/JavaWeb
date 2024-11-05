<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/hash/compare">
		<fieldset>
			<legend>Hash Compare</legend>
			<p>UserName:<input type="text" name="username" placeholder="請輸入帳號" required></p>
			<p>Password:<input type="password" name="password" placeholder="請輸入密碼" required> </p>
			
			<button type="reset">Reset</button>
			<button type="submit">Submit</button>
		</fieldset>
	</form>
</body>
</html>