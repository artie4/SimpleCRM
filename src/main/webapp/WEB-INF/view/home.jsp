<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: arthur
  Date: 26.05.19
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

</head>
<body>

<nav class="navbar sticky-top navbar-dark bg-dark">
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <!-- Navbar content -->
                <p class="text-success">User:<security:authentication property="principal.username"/></p>
            </div>
            <div class="col">
                <p class="text-success">Role:<security:authentication property="principal.authorities"/></p>
            </div>

            <div class="col">
                <form:form action="${pageContext.request.contextPath}/customer/list"
                           method="get">

                    <button class="btn btn-outline-success" type="submit">Customers</button>
                </form:form>
            </div>

            <div class="col">
                <form:form action="${pageContext.request.contextPath}/logout"
                           method="post">

                    <button class="btn btn-outline-success" type="submit">Logout</button>
                </form:form>
            </div>
        </div>
    </div>

</nav>


    <h2>Home Page</h2>
    <hr>

    <p>
        <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
        (Only for Manager peeps)
    </p>

    <p>
        <a href="${pageContext.request.contextPath}/systems">Support Systems Meeting</a>
        (Only for Admin peeps)
    </p>


</body>
</html>
