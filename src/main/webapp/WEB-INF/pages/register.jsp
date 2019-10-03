<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Register</h1>
		<h2>User Input From</h2>
		<h2 class="error"><c:out value= "${message}"/></h2>		
		<div class="form-input">
		<fieldset>
			<legend>Create account</legend>
					<form:form action="saveUser" method="post" modelAttribute="user">
			            <table>
			            	<tr>
			                    <th>Name</th>
			                    <td><form:input path="name" /></td>
			                </tr> 
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="name" cssClass="error" /></td>
			                </tr>           
			                <tr>
			                    <th>Email</th>
			                    <td><form:input path="email" /></td>
			                </tr>
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="email" cssClass="error" /></td>
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