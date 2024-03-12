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
                    <!-- enter number of question -->
                    <c:if test="${a}">

                        <form method="post" action="question?method=create">
                            <input hidden name="action" value="${action}">
                            <input hidden name="idLesson" value="${idLesson}">
                            <input hidden name="idTest" value="${idTest}">                            


                            <div class="form-group">
                                <label for="numOfQuestion">Enter number of Questions:</label>
                                <input type="number" class="form-control" id="numOfQuestion" name="numOfQuestion">
                            </div>
                            <button type="submit" class="btn btn-primary" name="inNum">Next</button>
                        </form>
                    </c:if>
                    <!-- when user is not admin and create test for Rooms -->       
                    <c:if test="${b}">
                        <form method="post" action="question?method=addQuestionB">

                            <table class="table table-bordered" >
                                <thead>
                                    <tr><td  class="text-center" colspan="2"> Create test for Room</td></tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Name Room</td><td><input name="nameRoom"type="text" ></td>
                                    </tr>
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
                    <!-- when is admin and create test and question for a lesson -->
                    <c:if test="${c}">
                        <form method="post" action="question?method=addQuestionC">

                            <table class="table table-bordered" >
                                <thead>
                                    <tr><td  class="text-center" colspan="2"> Create test for lesson [only admin]</td></tr>
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
                    <!-- when is admin and create question for 1 test existed -->
                    <c:if test="${d}">
                        <form method="post" action="question?method=addQuestionD&idTest=${idTest}">

                            <table class="table table-bordered" >
                                <thead>
                                    <tr><td  class="text-center" colspan="2"> Create question for this test [only admin]</td></tr>                                       

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
