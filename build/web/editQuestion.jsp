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

        <div class="container" >
            <div class="row justify-content-center ">
                <div class="col-lg-20 mx-auto ">                
                    <c:if test="${(sessionScope.acc.getIsAdmin() eq 'true')}">
                        <form method="post" action="question?method=edit">

                            <table class="table table-bordered"  >
                                <thead>
                                    <tr><td  class="text-center" colspan="2"> Edit your test</td></tr>
                                </thead>
                                <tbody>
                                <input name="idLesson" hidden value="${test.idLesson}">
                                <input name="idTest" hidden value="${test.idTest}">
                                <tr><td>For Test <input name="descriptionTs" required type="text" value="${test.descriptionTs}"></td> </tr>
                                <tr><td>Time(minutes)<input name="time" required type="number" value="<c:out value='${test.getTime()}'/>"></td> </tr>                                  
                                        <c:forEach items="${listQuestion}" var="o">
                                    <tr>
                                        <td>
                                            <table class="table" border="2">
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <div class="text-danger"> <input name="delete${o.getIdQuestion()}" type="checkbox" value="1">  Delete</div>
                                                        </td>
                                                    </tr>
                                                    <tr>                                                       
                                                        <td>
                                                            <div>${o.descriptionQ}</div><br><!-- comment -->
                                                            <textarea class="form-control" name="descriptionQ${o.getIdQuestion()}" rows="6" style="height: 3cm;" required>${o.descriptionQ}</textarea>
                                                        </td>
                                                    </tr>
                                                    <tr>                                                       
                                                        <td>
                                                            <div>${o.answer1}</div><br><!-- comment -->
                                                            <textarea class="form-control" name="answerA${o.getIdQuestion()}" rows="6" style="height: 1cm;" required>${o.answer1}</textarea>
                                                        </td><!-- answer A -->
                                                    </tr>
                                                    <tr>  
                                                        <td>                                                         
                                                            <div>${o.answer2}</div><br>
                                                            <textarea class="form-control" name="answerB${o.getIdQuestion()}" rows="6" style="height: 1cm;" required>${o.answer2}</textarea>
                                                        </td><!-- answer B -->
                                                    </tr>
                                                    <tr>                                                            <td>
                                                            <div>${o.answer3}</div><br>
                                                            <textarea class="form-control" name="answerC${o.getIdQuestion()}" rows="6" style="height: 1cm;" required>${o.answer3}</textarea>
                                                        </td><!-- answer C -->
                                                    </tr>
                                                    <tr>                                                            <td>
                                                            <div>${o.answer4}</div><br>
                                                            <textarea class="form-control" name="answerD${o.getIdQuestion()}" rows="6" style="height: 1cm;" required>${o.answer4}</textarea>
                                                        </td><!-- answer D -->
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <div class="text-success">Correct Answer</div> 
                                                            <select name="correctAnswer${o.getIdQuestion()}">
                                                                <option value="A" <c:if test="${o.getCorrectAnswer() eq 'A'}">selected</c:if>>A</option>
                                                                <option value="B" <c:if test="${o.getCorrectAnswer() eq 'B'}">selected</c:if>>B</option>
                                                                <option value="C" <c:if test="${o.getCorrectAnswer() eq 'C'}">selected</c:if>>C</option>
                                                                <option value="D" <c:if test="${o.getCorrectAnswer() eq 'D'}">selected</c:if>>D</option>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                    </tbody>

                                                </table>
                                            </td>
                                        </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                                        <a href="question?method=create&idTest=${test.idTest}">Add Question</a>               
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
