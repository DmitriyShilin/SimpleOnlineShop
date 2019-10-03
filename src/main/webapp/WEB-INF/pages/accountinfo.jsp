<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account info</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Account info.</h1>
		<h3><a href="${pageContext.request.contextPath}/user/profile">Profile.</a></h3>
		<h3><a href="${pageContext.request.contextPath}/user/orderList">Order list.</a></h3>	
		<c:if test="${orderNewSize > 0}"><h3><a href="${pageContext.request.contextPath}/user/choiceCart">Choice cart.</a></h3></c:if>	
		<sec:authorize access="hasRole('ADMIN')"><h3><a href="${pageContext.request.contextPath}/managementProduct">Management Product.</a></h3></sec:authorize>		
	</body>
</html>	