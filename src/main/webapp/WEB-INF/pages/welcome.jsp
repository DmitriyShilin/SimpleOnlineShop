<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SimpleOnlineShop</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<jsp:include page="_cart.jsp" />
		<jsp:include page="_menu.jsp" />
		<a href="${pageContext.request.contextPath}/setProduct">Set Products</a><br/>
		<a href="${pageContext.request.contextPath}/setAdmin">Set Admin email: 2718@i.ua  password: 12345678</a>
	</body>
</html>