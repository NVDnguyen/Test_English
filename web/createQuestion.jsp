<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Test Your English</title>

        <!-- Bootstrap core CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <c:if test="${first}">
                        <c:if test="${sessionScope.acc.getIsAdmin() eq 'true'}">
                            <form method="post" action="question?method=create&idLesson=${idLesson}&idTest=${idTest}">
                            </c:if>
                            <c:if test="${sessionScope.acc.getIsAdmin() eq 'false'}">
                                <form method="post" action="question?method=create">
                                </c:if>

                                <div class="form-group">
                                    <label for="numOfQuestion">Enter number of Questions:</label>
                                    <input type="number" class="form-control" id="numOfQuestion" name="numOfQuestion">
                                </div>
                                <button type="submit" class="btn btn-primary" name="inNum">Next</button>
                            </form>
                        </c:if>
                        <c:if test="${second && (sessionScope.acc.getIsAdmin() eq 'false')}">
                            <form method="post" action="question?method=addQuestion1">

                                <table class="table table-bordered" >
                                    <thead>
                                        <tr><td  class="text-center" colspan="2"> Create your test</td></tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Time(minutes)</td><td><input name="time" type="number"></td>
                                        </tr>
                                        <c:forEach var="i" begin="1" end="${size}">
                                            <tr>
                                                <td>Câu &NegativeMediumSpace; ${i}</td>
                                                <td>
                                                    <table class="table table-striped">
                                                        <tbody>
                                                            <tr>
                                                                <td>Title:</td>
                                                                <td><input type="text" class="form-control" name="title${i}"></td><!-- title -->
                                                            </tr>
                                                            <tr>
                                                                <td>Question:</td>
                                                                <td><input type="text" class="form-control" name="question${i}" required></td><!-- question -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" checked="1" name="correctAnswer${i}" value="A" required> A:</td>                                                            <td>
                                                                    <input type="text" class="form-control" name="answerA${i}">                                                               
                                                                </td><!-- answer A -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="B" required> B:</td>                                                            <td>
                                                                    <input type="text" class="form-control" name="answerB${i}">

                                                                </td><!-- answer B -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="C" required> C:</td>
                                                                <td>
                                                                    <input type="text" class="form-control" name="answerC${i}">
                                                                </td><!-- answer C -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="D" required>  D:</td>
                                                                <td>
                                                                    <input type="text" class="form-control" name="answerD${i}">
                                                                </td><!-- answer D -->
                                                            </tr>
                                                        </tbody>
                                                    </table>


                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                                <input type="submit" value="Submit">

                            </form>
                        </c:if>
                        <c:if test="${second && (sessionScope.acc.getIsAdmin() eq 'true')}">
                            <form method="post" action="question?method=addQuestion2">

                                <table class="table table-bordered" >
                                    <thead>
                                        <tr><td  class="text-center" colspan="2"> Create question for test 11</td></tr>
                                    </thead>
                                    <tbody>
                                        <tr><td>For Lesson: </td><td><input name="idLesson" hidden value="${lesson.idLesson}">${lesson.description} </td> </tr>
                                        <tr><td>Time(minutes)</td><td><input name="time" required type="number"></td> </tr>
                                        <tr><td>Name of Test:</td><td><input name="descriptionTs" required type="text"></td> </tr>
                                                <c:forEach var="i" begin="1" end="${size}">
                                            <tr>
                                                <td>Câu &NegativeMediumSpace; ${i}</td>
                                                <td>
                                                    <table class="table table-striped">
                                                        <tbody>
                                                            <tr>
                                                                <td>Title:</td>
                                                                <td><input type="text" class="form-control" name="title${i}"></td><!-- title -->
                                                            </tr>
                                                            <tr>
                                                                <td>Question:</td>
                                                                <td><input type="text" class="form-control" name="question${i}" required></td><!-- question -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" checked="1" name="correctAnswer${i}" value="A" required> A:</td>                                                            <td>
                                                                    <input type="text" class="form-control" name="answerA${i}">                                                               
                                                                </td><!-- answer A -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="B" required> B:</td>                                                            <td>
                                                                    <input type="text" class="form-control" name="answerB${i}">

                                                                </td><!-- answer B -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="C" required> C:</td>
                                                                <td>
                                                                    <input type="text" class="form-control" name="answerC${i}">
                                                                </td><!-- answer C -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="D" required>  D:</td>
                                                                <td>
                                                                    <input type="text" class="form-control" name="answerD${i}">
                                                                </td><!-- answer D -->
                                                            </tr>
                                                        </tbody>
                                                    </table>


                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                                <input type="submit" value="Submit">

                            </form>
                        </c:if>
                        <c:if test="${third && (sessionScope.acc.getIsAdmin() eq 'true')}">
                            <form method="post" action="question?method=addQuestion3&idTest=${idTest}">

                                <table class="table table-bordered" >
                                    <thead>
                                        <tr><td  class="text-center" colspan="2"> Create question for test 22</td></tr>                                       
                                        
                                    </thead>
                                    <tbody>                                      
                                        <c:forEach var="i" begin="1" end="${size}">
                                            <tr>
                                                <td>Câu &NegativeMediumSpace; ${i}</td>
                                                <td>
                                                    <table class="table table-striped">
                                                        <tbody>
                                                            <tr>
                                                                <td>Title:</td>
                                                                <td><input type="text" class="form-control" name="title${i}"></td><!-- title -->
                                                            </tr>
                                                            <tr>
                                                                <td>Question:</td>
                                                                <td><input type="text" class="form-control" name="question${i}" required></td><!-- question -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" checked="1" name="correctAnswer${i}" value="A" required> A:</td>                                                            <td>
                                                                    <input type="text" class="form-control" name="answerA${i}">                                                               
                                                                </td><!-- answer A -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="B" required> B:</td>                                                            <td>
                                                                    <input type="text" class="form-control" name="answerB${i}">

                                                                </td><!-- answer B -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="C" required> C:</td>
                                                                <td>
                                                                    <input type="text" class="form-control" name="answerC${i}">
                                                                </td><!-- answer C -->
                                                            </tr>
                                                            <tr>
                                                                <td><input type="radio" name="correctAnswer${i}" value="D" required>  D:</td>
                                                                <td>
                                                                    <input type="text" class="form-control" name="answerD${i}">
                                                                </td><!-- answer D -->
                                                            </tr>
                                                        </tbody>
                                                    </table>


                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                                <input type="submit" value="Submit">

                            </form>
                        </c:if>



                        <div class="text-danger">${notice}</div>

                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

</html>
