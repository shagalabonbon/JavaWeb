<%@page import="javaweb.model.dto.OrderDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	

<%@ taglib uri="jakarta.tags.core" prefix="c" %>        <%-- 核心庫 --%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>       <%-- 格式化庫 --%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>  <%-- 功能庫 --%>


<%
    List<OrderDto> orderDtos = (List<OrderDto>)request.getAttribute("orderDtos");    // 得到 CartServlet 所傳來的資料
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User.cart</title>
		<%-- 引入 PureCss --%>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
		<link rel="stylesheet" href="/JavawebTest/css/buttons.css">	
	</head>
	<body>
		<div>
			<%-- Menu 導覽列 --%>
			<%@include  file="/WEB-INF/view/menu.jspf"%>  
		
			<fieldset>
				<legend>${userCert.username} 的 Cart 購物車</legend>
				<table class="pure-table">
					<thead>
						<tr>
							<th>訂單ID</th>
							<th>使用者ID</th>
							<th>訂單日期</th>
							<th>商品ID</th>
							<th>商品名稱</th>
							<th>商品單價</th>
							<th>訂購數量</th>
							<th>小計</th>
							<th>訂單狀態</th>
						</tr>
					</thead>
	
					<tbody>
	
						<!-- 初始化總計變數  -->
	
						<c:set var="total" value="0"/>
	
						<c:forEach var="orderDto" items="${ orderDtos }">
							<tr>
								<td>${ orderDto.orderId }</td>
								<td>${ orderDto.userId }</td>
								<td>${ orderDto.orderDate }</td>
								<td>${ orderDto.productId }</td>
								<td>${ orderDto.productName }</td>
								<td align="right"><fmt:formatNumber value="${orderDto.unitPrice}" type="currency" /></td> <!-- formatNumber 地區屬性先前已設入 Session -->
								<td>${ orderDto.quantity }</td>
								<td align="right"><fmt:formatNumber value="${orderDto.subtotal}" type="currency" /></td>
								<td>${ orderDto.orderStatus }</td>
	
							</tr>
							
						    <c:set var="total" value="${ total + orderDto.subtotal }" />  <!-- 累計加總 -->
						   
						</c:forEach>
	
						<!-- 顯示總計 -->
						<tr>
							<td colspan="7" align="right">總計</td>
							<td align="right">
								<b>
									<fmt:formatNumber value="${total}" type="currency" />
								</b>
							</td>
							<td></td>
						</tr>
						
						<!-- 結帳 / 取消 -->
						<tr style="display: ${ fn:length(orderDtos)==0 ? 'none':'' }">   <!-- 使用函式庫功能：訂單無內容時不顯示按鈕 -->
							<td colspan="7" align="right">結帳</td>
							<td><a href="/JavawebTest/order/finish" class="button-success pure-button">結帳</a></td>
							<td><a href="/JavawebTest/order/cancel" class="button-warning pure-button">取消</a></td>
						</tr>
						
					</tbody>
				</table>
			</fieldset>
	    </div>
		
	</body>
</html>

