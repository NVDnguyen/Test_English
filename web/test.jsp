<%-- 
    Document   : lesson
    Created on : Jan 21, 2024, 10:36:29 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

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
        <style>
            #page-content {
                display: none;
            }
        </style>

        <script>
            window.onload = function () {
                // Hiển thị cửa sổ xác nhận khi trang được tải
                var confirmation = confirm("Bạn có muốn bắt đầu bài kiểm tra ngay bây giờ ?");
                // Nếu người dùng nhấn "OK"
                if (confirmation) {
                    // Hiển thị nội dung của trang
                    document.getElementById("page-content").style.display = "block";
                    startCountdown();
                    updateTOCPosition();
                } else {
                    // Nếu người dùng không nhấn "OK", chuyển hướng quay lại trang trước đó
                    history.back();
                }
            };



            function startCountdown() {
                var durationInMinutes = parseInt("${sessionScope.test.getTime()}"); // Chuyển đổi giá trị string thành số nguyên
                var durationInSeconds = durationInMinutes * 60; // Chuyển đổi sang số giây

                var clockElement = document.getElementById('clock');
                var timer = durationInSeconds;

                // Display the initial countdown
                updateClockDisplay(clockElement, timer);

                // Update the clock every second
                var countdown = setInterval(function () {
                    // Decrease the timer
                    timer--;

                    // If the timer reaches zero, stop the countdown
                    if (timer < 0) {
                        clearInterval(countdown);
                        submitFormAuto();


                        // You can add any additional actions when the countdown ends
                    } else {
                        // Update the clock display
                        updateClockDisplay(clockElement, timer);
                    }
                }, 1000);
            }
            ;

            function updateClockDisplay(clockElement, seconds) {
                var minutes = Math.floor(seconds / 60);
                var remainingSeconds = seconds % 60;

                // Display the remaining time in the clock div
                clockElement.textContent = minutes + ":" + (remainingSeconds < 10 ? "0" : "") + remainingSeconds;
            }
            ;

        </script>


    </head>

    <body>
        <div id="page-content" class="container">
            <div class="row">
                <div class="col-md-9">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th  class="text-center" ><b style="color: black">Test Description</b></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listQuestions}" var="q">
                                <tr>
                                    <td>
                                        <a id="${q.getIdQuestion()}"></a>
                                        <p>${q.getDescriptionQ()}</p>
                                        <div class="form-check">
                                            <input id="A" type="radio" name="${q.getIdQuestion()}" onclick="selected('${q.getIdQuestion()}A')" class="form-check-input">
                                            <label id="${q.getIdQuestion()}" style="color: black;">${q.getAnswer1()}</label>
                                        </div>
                                        <div class="form-check">
                                            <input id="B" type="radio" name="${q.getIdQuestion()}" onclick="selected('${q.getIdQuestion()}B')" class="form-check-input">
                                            <label id="${q.getIdQuestion()}" style="color: black;">${q.getAnswer2()}</label>
                                        </div>
                                        <div class="form-check">
                                            <input id="C" type="radio" name="${q.getIdQuestion()}" onclick="selected('${q.getIdQuestion()}C')" class="form-check-input">
                                            <label id="${q.getIdQuestion()}" style="color: black;">${q.getAnswer3()}</label>
                                        </div>
                                        <div class="form-check">
                                            <input id="D" type="radio" name="${q.getIdQuestion()}" onclick="selected('${q.getIdQuestion()}D')" class="form-check-input">
                                            <label id="${q.getIdQuestion()}" style="color: black;" >${q.getAnswer4()}</label>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>



                        </tbody>
                    </table> 
                    <div class="text-center ml-5">
                        <button type="button" class="btn btn-success" onclick="submitForm()">Submit</button>
                    </div>

                </div>
                <!--check Point-->
                <div  class="col-md-3 position-fixed " style="top: 3cm;right: 6cm; text-align: right" >
                    <div id='fm-container' class="container" style="width: 10cm; margin-left: 4cm">
                        <!-- result -->
                        <form id='result' action="test" method="post">  
                            <input hidden name="idRoom" value="${idRoom}">
                            <table class="table table-bordered">
                                <thead><tr><td colspan="10"><div id='clock'class="text-center h2 text-danger" ></div></td></tr></thead>
                                <tbody>
                                    <tr>
                                        <c:forEach items="${listQuestions}" var="q" varStatus="index">
                                            <td  id="${q.getIdQuestion()}YES">
                                                <a href="#${q.getIdQuestion()}">${index.index + 1}</a>
                                                <input type="hidden" id="${q.getIdQuestion()}AS" name="${q.getIdQuestion()}AS">                                        </td>
                                                <%-- check to down the line --%>
                                                <c:if test="${index.index % 10 == 9}">
                                            </tr><tr>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colspan="10">
                                            <div class="text-center">
                                                <button type="button" class="btn btn-success" onclick="submitForm()">Submit</button>
                                            </div>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <!-- end result -->

                        </form>



                    </div>
                </div>      

            </div>
        </div>

    </body>
    <script>
        function submitFormAuto() {

            document.getElementById('result').submit();


        }
        function submitForm() {
            var confirmation = confirm("Bạn có muốn nộp bài bây giờ không ?");
            // Nếu người dùng nhấn "OK"
            if (confirmation) {
                document.getElementById('result').submit();
            }

        }
        function selected(idQuestion) {
            var result = separateString(idQuestion);
            console.log("Numbers:", result.numbers); // In số
            console.log("Letters:", result.letters); // In chuỗi chữ cái A, B, C, D

            var idQ = result.numbers + "YES";
            var element = document.getElementById(idQ);
            if (element) {
                element.style.backgroundColor = "yellow";
            }
            var ans = result.numbers + "AS";
            var element1 = document.getElementById(ans);
            if (element1) {
                element1.value = result.letters; // Sử dụng innerHTML để thiết lập giá trị trong div
            }
        }
        function separateString(input) {
            var numbers = input.match(/\d+/); // Lấy tất cả các chữ số
            var letters = input.match(/[ABCD]+/g); // Lấy tất cả các chữ cái A, B, C, D

            // Kiểm tra nếu có chữ số và chữ cái
            var result = {
                numbers: numbers ? parseInt(numbers[0]) : null,
                letters: letters ? letters.join('') : null
            };
            return result;
        }


    </script>
</html>