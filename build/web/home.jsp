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

    <body>

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
                            <li class="nav-item active">
                                <a class="nav-link" href="home">Home</a>
                            </li>
                            <!-- lessons -->
                            <c:forEach items="${sessionScope.listTopic}" var="o">
                                <li class="nav-item">
                                    <a class="nav-link" href="lesson?topic=${o.idTopic}">${o.nameTopic}</a>
                                </li>   
                            </c:forEach>
                            <!-- feature -->
                            <li class="nav-item">
                                <a class="nav-link" href="exam">EXAMS</a>
                            </li>

                            <c:if test="${sessionScope.acc.isAdmin eq 'true'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="manage">Manager</a>
                                </li>
                            </c:if>


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

        <!-- Page Content -->
        <!-- Banner Starts Here -->
        <div class="main-banner header-text" id="top">
            <div class="Modern-Slider">
                <!-- Item -->
                <div class="item item-1">
                    <div class="img-fill">
                        <div class="text-content">
                            <h6>Test-English</h6>
                            <h4>Take your learning with you!</h4>
                            <p>Grammar lessons with exercises and clear explanations, grammar charts, reading and listening tests with transcriptions, writing lessons, instant marking, answer feedback, and much more! </p>
                            <a href="#home" class="filled-button" style="width: 4cm"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <div class="services" id="grammar">
            <div class="container">
                <div class="col-md-12">
                    <div class="section-heading">
                        <h2>What would we  <em>LEARN</em> today ?</h2>            
                    </div>
                </div>
            </div>
        </div>
        <!-- Menu Items -->
        <div class="services" >
            <div class="container">
                <div  class="row ">

                    <c:forEach items="${listTopic}" var="o">
                        <!-- Item Lession -->
                        <div class="col-md-4 ">
                            <div class="service-item" >
                                <img src="${o.imageTopic}" alt="">
                                <div class="down-content">
                                    <h4>${o.nameTopic}</h4>
                                    <p  style="height:5cm" >${o.describeTopic}</p>
                                    <a href="lesson?topic=${o.idTopic}" class="filled-button">Go to the Test</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>







        <div class="partners">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="owl-partners owl-carousel">



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



    </body>




</html>
