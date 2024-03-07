<%-- 
    Document   : lesson
    Created on : Jan 21, 2024, 10:36:29 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="TemplateMo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>Test</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-finance-business.css">
        <link rel="stylesheet" href="assets/css/owl.css">

    </head>

    <body >

        <!-- ***** Preloader Start ***** -->
        <div id="preloader">
            <div class="jumper">
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>  
        <!-- ***** Preloader End ***** -->

        <!-- Header -->
        <div class="sub-header">
            <div class="container">
                <div >
                    <div class=" d-flex justify-content-center align-items-center">
                        <ul class="left-info">
                            <li><a href="#"><i class="fa fa-clock-o text-white"></i>Mon-Fri 09:00-17:00</a></li>
                            <li><a href="#"><i class="fa fa-phone text-white"></i>090-080-0760</a></li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <header class="">
            <nav class="navbar navbar-expand-lg">
                <div class="container">
                    <a class="navbar-brand" href="home"><h2>Test English</h2></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                            aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="home">Home</a>
                            </li>
                            <c:forEach items="${sessionScope.listTopic}" var="o">
                                <c:if test="${o.idTopic ne sessionScope.tp.idTopic}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="lesson?topic=${o.idTopic}">${o.nameTopic}</a>
                                    </li>
                                </c:if>
                                <c:if test="${o.idTopic eq sessionScope.tp.idTopic}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="lesson?topic=${o.idTopic}">${o.nameTopic}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <!-- feature -->
                            <li class="nav-item active">
                                <a class="nav-link" href="exam">EXAMS</a>
                            </li>                       


                            <!-- login / log out -->
                            <c:if test="${empty sessionScope.acc}">
                                <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
                                </c:if>

                            <c:if test="${not empty sessionScope.acc}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${sessionScope.acc.userName}
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">                                                                            
                                        <a class="dropdown-item" href="logout">Logout</a>
                                    </div>
                                </li>
                            </c:if>

                        </ul>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav ml-auto">
                            <!--                            <li>
                                                            <a href="login.jsp">
                                                                                                    <i class="fa fa-user fa-lg text-white"></i> 
                                                                Login
                                                            </a>
                                                        </li>-->
                        </ul>
                    </div>

                </div>
            </nav>
        </header>



        <div style="color: #66ffff; margin-bottom: 10cm;" >
            <!-- Wellcome Page Content -->
            <div class="page-heading header-text">
                <div class="container">               
                    <div class="row">
                        <div class="col-md-12">
                            <h1>Exams</h1>
                            <span>Language opens hearts and minds.</span>
                        </div>
                    </div>
                </div>
            </div>


            <div class="container mt-1">
                <div class="row">
                    <div class="container">
                        <!-- Input -->
                        <!-- feature only for user -->
                        <c:if test="${sessionScope.acc.getIsAdmin() eq 'false'}">
                            <div class="row justify-content-center">
                                <div class="col-md-6 mt-5">
                                    <div class="card">
                                        <div class="card-header text-center text-black-50">Enter Room Code</div>
                                        <div class="card-body">
                                            <form action="exam" method="POST">
                                                <div class="form-group">
                                                    <input type="text" class="form-control" name="roomCode" placeholder="Room Code" required>
                                                </div>
                                                <div class="form-group">
                                                    <button type="submit" name="join" class="btn btn-primary btn-block">Join Room</button>
                                                </div>
                                            </form>
                                            <form action="exam" method="POST">
                                                <div class="form-group">
                                                    <button type="submit" name="create" class="btn btn-success btn-block">Create Room</button>
                                                </div>
                                            </form>
                                            <div class="text-danger text-center">${notice}</div>

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:if>



                        <!-- display rooms available -->

                        <div class="row justify-content-center mt-4">

                            <c:if test="${not empty sessionScope.acc}">
                                <div >
                                    <table class="table-bordered table-dark text-center">
                                        <thead >
                                            <tr>
                                                <th>#</th>
                                                <th>Name Room</th>
                                                <th>Creater</th>
                                                <th>Code Room</th>
                                                <th>Active</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${sessionScope.rooms}" var="room" varStatus="i">
                                                <tr>

                                                    <td>${i.index + 1}</td>
                                                    <td>${room.nameRoom}</td>
                                                    <td>${room.creater}</td>
                                                    <td>${room.codeRoom}</td>
                                                    <td>${room.active}</td>
                                                    <td><a href="report?method=room&idTest=${room.idTest}&idRoom=${room.idRoom}" class="text-success">Show</a></td>    
                                                    <c:if test="${room.active eq 'true' }">
                                                        <td><a href="room?action=stop&idRoom=${room.idRoom}" class="text-danger">Stop</a></td>
                                                    </c:if>
                                                    <c:if test="${room.active eq 'false' }">
                                                        <td><a href="room?action=start&idRoom=${room.idRoom}" class="text-success">Start</a></td>
                                                    </c:if>



                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>






                        </div>


                    </div>
                </div>

            </div>



        </div>







        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Additional Scripts -->
        <script src="assets/js/custom.js"></script>
        <script src="assets/js/owl.js"></script>
        <script src="assets/js/slick.js"></script>
        <script src="assets/js/accordions.js"></script>

        <script language = "text/Javascript">
            cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
            function clearField(t) {                   //declaring the array outside of the
                if (!cleared[t.id]) {                      // function makes it static and global
                    cleared[t.id] = 1; // you could use true and false, but that's more typing
                    t.value = ''; // with more chance of typos
                    t.style.color = '#fff';
                }
            }
        </script>
        <script>
            function tryAgain(idTest) {
                window.location.assign("test?idT=" + idTest);
            }
        </script>

    </body>
</html>