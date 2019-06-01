<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration form</title>
</head>
<body>
<!-- Registration Form -->
<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"
           modelAttribute="crmUser"
           class="form-horizontal">
    <!-- Check for registration error -->
    <c:if test="${registrationError != null}">
        <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                ${registrationError}
        </div>
    </c:if>

    <div>
        <!-- User name -->
        <form:input path="userName" placeholder="username" class="form-control"/>
    </div>
    <div>
        <!-- Password -->
        <form:password path="password" placeholder="password" class="form-control"/>
    </div>
    <div>
        <!-- Password -->
        <form:password path="matchingPassword" placeholder="repeat password" class="form-control"/>
    </div>
    <div>
        <!—- First Name -->
        <form:input path="firstName" placeholder="first name" class="form-control"/>
    </div>
    <div>
        <!—- Last Name -->
        <form:input path="lastName" placeholder="last name" class="form-control"/>
    </div>
    <div>
        <!—- email -->
        <form:input path="email" placeholder="email" class="form-control"/>
    </div>
    <button type="submit" class="btn btn-primary">Register</button>
</form:form>

</body>
</html>
