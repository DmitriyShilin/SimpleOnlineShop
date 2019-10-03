<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SimpleOnlineShop</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<c:choose>
			<c:when test="${!empty order.lineItem}">
				<c:set var="dep"/>
				<table>
					<c:forEach items="${order.lineItem}" var="lineitem">
						<tr>
							<td><img src="${pageContext.request.contextPath}/resources/images/<c:out value="${lineitem.idProduct}"/>.jpg" class="cart-img"></td>
							<td><a href="${pageContext.request.contextPath}/buy/details/<c:out value="${lineitem.idProduct}"/>" class="cart-name"><c:out value="${lineitem.product.name}"/></a><br/><c:out value="${lineitem.product.price}"/><c:out value="${lineitem.product.currency}"/></td>							
							<td><a href="${pageContext.request.contextPath}/buy/quantity/minus?idProduct=<c:out value="${lineitem.idProduct}"/>" class="cart-plus-minus">-</a></td>
							<td class="cart-quantity">
								<c:out value="${lineitem.quantity}"/> 
								<c:choose>
									<c:when test="${lineitem.quantity > 1}">items</c:when>
									<c:otherwise>item</c:otherwise>
								</c:choose>
							</td>							
							<td><a href="${pageContext.request.contextPath}/buy/quantity/plus?idProduct=<c:out value="${lineitem.idProduct}"/>" class="cart-plus-minus">+</a></td>
							<td class="cart-quanity"><c:out value="${lineitem.amount}"/><c:out value="${lineitem.product.currency}"/></td>								
						</tr>
						<c:set var="dep" value="${lineitem.product.department}"/>
					</c:forEach>
				</table>	
				<a href="${pageContext.request.contextPath}/buy/<c:out value="${dep}"/>" class="cart-continue-shopping-full">Continue shopping</a>
				<div class="cart-continue-shopping">	
				<span class="cart-continue-shopping-total">Total: <c:out value="${order.totalprice}"/><c:out value="${order.lineItem[0].product.currency}"/></span><br/>
				<a href="${pageContext.request.contextPath}/buy/checkout" class="cart-continue-shopping-checkout">Checkout</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="cart-empty">Your cart is empty.</div>
				<a href="${pageContext.request.contextPath}/welcome" class="cart-continue-shopping-empty">Continue shopping</a>
			</c:otherwise>
		</c:choose>
	</body>
</html>