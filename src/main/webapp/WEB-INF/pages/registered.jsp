<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registered</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Successfully registered.</h1>
		<h2><a href="${pageContext.request.contextPath}/login">Now you can log in.</a></h2>
		<h2><a href="${pageContext.request.contextPath}/welcome">Click here to go to the main page.</a></h2>		
	</body>
</html>