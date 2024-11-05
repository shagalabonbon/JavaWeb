<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	

<%@ taglib uri="jakarta.tags.core" prefix="c" %> 


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Order</title>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
		<link rel="stylesheet" href="/JavawebTest/css/buttons.css">	
	</head>
	<body>
		<!-- Menu 導覽列 -->
		<%@include  file="/WEB-INF/view/menu.jspf"%>
		
		<div style="padding:15px">
		<form method="post" action="/JavawebTest/order" class="pure-form">
			<fieldset>
				<legend>Order 新增</legend>
				<table class="pure-table">
					<thead>
						<th>商品id</th><th>商品名稱</th><th>商品單價</th><th>商品庫存</th><th>下單數量</th>
					</thead>
					<tbody>
					    
						<tr>
							<td>
							    1
							    <input type="hidden" name="productId" value="1">     <!-- 隱藏欄：方便於後台判讀 -->
							</td>    
							<td>PC</td>
							<td>
								30000
								<input type="hidden" name="unitPrice" value="30000">
							</td>
							<td>50</td>
							<td><input type="number" name="amount" style="width:70px" value="0" min="0" max="50"></td>
						</tr>
						<tr>
							<td>
							    2
							    <input type="hidden" name="productId" value="2">     
							</td>
							<td>Mobile</td>
							<td>
								15000
								<input type="hidden" name="unitPrice" value="15000">
							</td>
							<td>100</td>
							<td><input type="number" name="amount" style="width:70px" value="0" min="0" max="100"></td>
						</tr>
						<tr>
							<td>
								3
								<input type="hidden" name="productId" value="3">
							</td>
							<td>MusicBox</td>
							<td>
								3000
								<input type="hidden" name="unitPrice" value="3000">
							</td>
							<td>200</td>
							<td><input type="number" name="amount" style="width:70px" value="0" min="0" max="200"></td>
						</tr>
						<tr>
							<td>
								4
								<input type="hidden" name="productId" value="4">
							</td>
							<td>Pad</td>
							<td>
								20000
								<input type="hidden" name="unitPrice" value="20000">
							</td>
							<td>75</td>
							<td><input type="number" name="amount" style="width:70px" value="0" min="0" max="75"></td>
						</tr>
						<tr>
							<td>
								5
								<input type="hidden" name="productId" value="5">
							</td>
							<td>Watch</td>
							<td>
								8000
								<input type="hidden" name="unitPrice" value="8000">
							</td>
							<td>150</td>
							<td><input type="number" name="amount" style="width:70px" value="0" min="0" max="150"></td>
						</tr>
					</tbody>
				</table> 
				<button type="reset" class="button-warning pure-button">Reset</button>
				<button type="submit" class="button-success pure-button">Submit</button>
			</fieldset>
		</form>
		</div>
	</body>
</html>
						
