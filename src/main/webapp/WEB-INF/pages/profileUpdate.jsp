<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Update</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Profile Update</h1>
		<h2>User Input From</h2>
		<div class="form-input">
		<fieldset>
			<legend>Update account information</legend>
					<form:form action="updateUser" method="post" modelAttribute="userUpdate">
			            <table>
			            	<tr>
			                    <th>Surname</th>
			                    <td><form:input path="surname" value="${currentUser.surname}"/></td>
			                </tr>
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="surname" cssClass="error" /></td>
			                </tr>			            	
			            	<tr>
			                    <th>Name</th>
			                    <td><form:input path="name" value="${currentUser.name}"/></td>
			                </tr> 
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="name" cssClass="error" /></td>
			                </tr>
			                <tr>
			                    <th>Patronymic</th>
			                    <td><form:input path="patronymic" value="${currentUser.patronymic}"/></td>
			                </tr> 
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="patronymic" cssClass="error" /></td>
			                </tr>
			                <tr>
			                    <th>Phone</th>
			                    <td><form:input path="phone" placeholder="+380123456789" value="${currentUser.phone}"/></td>
			                </tr> 
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="phone" cssClass="error" /></td>
			                </tr>
			                <tr>
			                    <th>Password</th>
			                    <td><form:password path="password" /></td>
			                </tr>  
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="password" cssClass="error" /></td>
			                </tr>
			                <tr>
			                    <th>Re-enter password</th>
			                    <td><form:password path="matchingPassword" /></td>
			                </tr>
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="matchingPassword" cssClass="error" /></td>
			                </tr>
			                <tr>               
			                    <td><form:button>Submit</form:button></td>
			                </tr>
			            </table>            
			        </form:form>
	        </fieldset>
        </div>
	</body>
</html>