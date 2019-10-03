<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choice cart</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">        
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Choice cart.</h1>
		<h2>Please select carts for to mix with a current cart.</h2>
		<div class="choice-cart-user">
		<form:form action="mixCart" method="post" modelAttribute="choiceCartList">
			<c:forEach items="${orderUserNew}" var="orderNew">	
				<table border="1">
					<thead>
						<tr>							
							<th>Order date</th>
							<th width="60px">Status</th>
							<th width="250px">Line item</th>
							<th>Total price</th>
							<th><form:checkbox path="orderedDate" value="${orderNew.orderedDate}"/></th>
						</tr>
					</thead>
					<tbody>					
						<tr>				
							<td><c:out value= "${orderNew.orderedDate}"/></td>							
							<td><c:out value= "${orderNew.status}"/></td>
							<td><c:forEach items="${orderNew.lineItem}" var="lineItemList">
									<ul><li class="specification"><a href="${pageContext.request.contextPath}/buy/details/<c:out value="${lineItemList.product.idProduct}"/>"><c:out value= "${lineItemList.product.name}"/></a> <c:out value= "${lineItemList.quantity}"/> 
									<c:choose>
										<c:when test="${lineItemList.quantity == 1}">item </c:when>
										<c:otherwise>items</c:otherwise>
									</c:choose>
									</li></ul>
								</c:forEach>
							</td>
							<td><c:out value= "${orderNew.totalprice}"/></td>							
						</tr>					
					</tbody>
				</table>				
				<h1></h1>			
			</c:forEach>			
			<br/>	
			<form:button>Mix carts</form:button>
		</form:form>
		</div>
		<c:choose>
			<c:when test="${empty order.lineItem}"><h2>Current cart is empty.</h2></c:when>
			<c:otherwise>
				<h2>Current cart.</h2>
				<table border="1">
					<thead>
						<tr>							
							<th>Order date</th>
							<th width="60px">Status</th>
							<th width="250px">Line item</th>
							<th>Total price</th>
						</tr>
					</thead>
					<tbody>					
						<tr>				
							<td><c:out value= "${order.orderedDate}"/></td>							
							<td><c:out value= "${order.status}"/></td>
							<td><c:forEach items="${order.lineItem}" var="lineItemList">
								<ul><li class="specification"><a href="${pageContext.request.contextPath}/buy/details/<c:out value="${lineItemList.product.idProduct}"/>"><c:out value= "${lineItemList.product.name}"/></a> <c:out value= "${lineItemList.quantity}"/> 
								<c:choose>
								<c:when test="${lineItemList.quantity == 1}">item </c:when>
								<c:otherwise>items</c:otherwise>
								</c:choose>
								</li></ul>
								</c:forEach>
							</td>
							<td><c:out value= "${order.totalprice}"/></td>
						</tr>				
					</tbody>
				</table>
			</c:otherwise>		
		</c:choose>
		<h2><a href="${pageContext.request.contextPath}/welcome" class="profile">Continue with the current cart</a></h2>
	</body>
</html>