<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
	<title>Registration Confirmation</title>
</head>

<body>

	<h2>User registered successfully!</h2>

	<hr>
	
	<a href="${pageContext.request.contextPath}/showLoginPage">Login with new user</a>
	
</body>

</html>