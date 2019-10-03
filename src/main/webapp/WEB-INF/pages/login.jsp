<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">        
    </head>
	<body>		
		<jsp:include page="_header.jsp" />
		<h1>Login</h1>
		<h2>User Input From</h2>
		<div class="form-input">				
			<form name="f" action="dologin" method="post" onsubmit="return validate();">
				<fieldset>
					<legend>Please sign in</legend>
					<c:if test="${not empty error}">
		                <div class="alert alert-danger">
		                    <spring:message code="loginFailed"/>
		                    <br/>
		                </div>
		            </c:if>
		            <table>                
		                <tr>
		                    <th>Email</th>
		                    <td>
		                        <input name="email" type="text"/>	                       
		                    </td>
		                </tr>
		                <tr>
		                    <th>Password</th>
		                    <td>
		                        <input name="password" type="password"/>	                        
		                    </td>
		                </tr>      
		                <tr>                    
		                    <td>
		                    	<input type="submit" value="sign in">
		                    </td>
		                </tr>
		            </table>  
		        </fieldset>          
		    </form>
        </div> 
        ADMIN email: 2718@i.ua password: 12345678
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>       
	</body>
</html>