<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>

    <title>List Customers</title>

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
    <!-- Navbar content -->
    <p class="text-success">User:<security:authentication property="principal.username"/></p>
    <p class="text-success">Role:<security:authentication property="principal.authorities"/></p>

    <form:form action="${pageContext.request.contextPath}/logout"
               method="post">

        <button class="btn btn-outline-success" type="submit">Logout</button>
    </form:form>
    </div>

</nav>

<div class="container">
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>

    <div class="container">

        <div class="container">
            <div class="row">
                <div class="col-sm">
            <!-- put new button: Add Customer -->
            <input type="button" value="Add Customer"
                   onclick="window.location.href='showFormForAdd'; return false;"
                   class="add-button"
            />
                </div>

                <div class="col-sm">
            <!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName"/>

                <input type="submit" value="Search" class="add-button"/>
            </form:form>

                </div>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Email</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="tempCustomer" items="${customers}">

                <!-- construct an "update" link with customer id -->
                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>

                <!-- construct an "delete" link with customer id -->
                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>

                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>

                    <td>
                        <!-- display the update link -->
                        <a href="${updateLink}">Update</a>

                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this customer?')))
                            return false">Delete</a>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

</body>
</html>