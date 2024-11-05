<%@page import="javaweb.model.dto.OrderDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	

<%@ taglib uri="jakarta.tags.core" prefix="c" %>        <%-- 核心庫 --%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>       <%-- 格式化庫 --%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>  <%-- 功能庫 --%>


<%
    List<OrderDto> orderFinishedDtos = (List<OrderDto>)request.getAttribute("orderFinishedDtos");    // 得到 OrderServlet 所傳來的資料
    List<OrderDto> orderCancelDtos   = (List<OrderDto>)request.getAttribute("orderCancelDtos");    
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>歷史訂單</title>
		<%-- 引入 PureCss --%>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
		<link rel="stylesheet" href="/JavawebTest/css/buttons.css">	
	</head>
	<body>
		<div>
			<%-- Menu 導覽列 --%>
			<%@include  file="/WEB-INF/view/menu.jspf"%>  
		</div>
		
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

						<c:forEach var="orderDto" items="${ orderFinishedDtos }">
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
						</c:forEach>
						
						<tr>
							<td colspan="9"></td>					
						</tr>
						
						<c:forEach var="orderDto" items="${ orderCancelDtos }">
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
						</c:forEach>
						
					</tbody>
				</table>
			</fieldset>
	    
	</body>
</html>

