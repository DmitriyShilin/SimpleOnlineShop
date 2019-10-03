<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />			
		<a href="${pageContext.request.contextPath}/user/profileUpdate" class="profile">Profile update.</a>
		&nbsp;|&nbsp;
		<a href="${pageContext.request.contextPath}/welcome" class="profile">Click here to go to the main page.</a>
		<div class="profile-info">
			<table>
				<tr>
					<td><span class="profile-span">Surname:</span> <c:out value= "${currentUser.surname}"/></td>
				</tr> 
				<tr>
					<td><span class="profile-span">Name:</span> <c:out value= "${currentUser.name}"/></td>
				</tr> 
				<tr>
					<td><span class="profile-span">Patronymic:</span> <c:out value= "${currentUser.patronymic}"/></td>
				</tr> 
				<tr>
					<td><span class="profile-span">Email:</span> <c:out value= "${currentUser.email}"/></td>
				</tr> 
				<tr>
					<td><span class="profile-span">Phone:</span> <c:out value= "${currentUser.phone}"/></td>
				</tr>
			</table>				
		</div>
	</body>
</html>