<%-- 
    Document   : home
    Created on : Jan 22, 2024, 1:17:46 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="TemplateMo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>Test Your English</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-finance-business.css">
        <link rel="stylesheet" href="assets/css/owl.css">

    </head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <div class=" p-5 text-center container">
        <table class="table-bordered table-dark ">
            <thead>
                <tr>
                    <td>User Name</td>
                    <td>Status</td>
                    <td>Action</td>                                 
                </tr>
            </thead>
            <!-- userName,password, isAdmin, band; -->
            <c:forEach items="${listAccount}" var="o">
                <tr>
                    <td>${o.userName}</td>
                    <c:if test="${ o.band eq 'false' }">
                        <td class="text-success">Active</td>
                        <td><a href="manage?method=band&userName=${o.userName}">Band</a></td>
                        </c:if>
                        <c:if test="${ o.band eq 'true' }">
                        <td class="text-danger">Banded</td>
                        <td><a href="manage?method=unband&userName=${o.userName}">unBand</a></td>

                    </c:if>


                </tr>

            </c:forEach>
        </table>
    </div>
</body>
</html>
