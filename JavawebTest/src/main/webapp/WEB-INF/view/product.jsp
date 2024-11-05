<%@page import="javaweb.model.dto.ProductDto"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    //得到 ProductServlet 所傳來的資料
    List<ProductDto> productDtos = (List<ProductDto>)request.getAttribute("productDtos");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<link rel="stylesheet"
	  href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css"
	  integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls"
	  crossorigin="anonymous">
<link rel="stylesheet" href="/JavawebTest/CSS/Buttons.css">	

</head>
<body>
	<%-- Menu 導覽列 --%>
	<%@include  file="/WEB-INF/view/menu.jspf"%>
	 
	<form method="post" action="/JavawebTest/product/add" class="pure-form">
		<fieldset>
			<legend>Product新增</legend>
			Product ：<input type="text" name="productName" placeholder="請輸入商品名稱" required><p />
			Price ：<input type="text" name="price" placeholder="請輸入價格" required><p />
			StockQuantity：<input type="text" name="stockQuantity" placeholder="請輸入庫存" required><p />

			<button type="reset" class="button-success pure-button">Reset</button>
			<button type="submit" class="button-success pure-button">Submit</button>
		</fieldset>
	</form>

	<div>
		<fieldset>
			<legend>Product 列表</legend>
			<table class="pure-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>product_name</th>
						<th>price</th>
						<th>stock_quantity</th>
						<th>刪除</th>
					</tr>
				</thead>
				<%
				for (ProductDto productDto : productDtos) {
				%>
				<tr>
					<td><%=productDto.getProductId()%></td>
					<td><%=productDto.getProductName()%></td>
					<td><%=productDto.getPrice()%></td>
					<td><%=productDto.getStockQuantity()%></td>
					<td><a href="/JavawebTest/product/delete?productId=<%=productDto.getProductId()%>" class="button-error pure-button">刪除</td>
						
				</tr>
				<%
				}
				%>
			</table>
		</fieldset>
	</div>
</body>
</html>