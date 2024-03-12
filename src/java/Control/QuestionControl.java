/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.Accounts;
import Model.Lessons;
import Model.Questions;
import Model.Rooms;
import Model.Tests;
import Model.Topics;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class QuestionControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuestionControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ss = request.getSession();
        if (ss.getAttribute("acc") != null) {
            Accounts acc = (Accounts) ss.getAttribute("acc");
            if (request.getParameter("method").equals("create")) { // create,add question for test, only admin , in edit page

                if (acc.getIsAdmin().equals("true")) {
                    String idTest = request.getParameter("idTest");
                    request.setAttribute("idTest", idTest);
                    request.setAttribute("action", "1");
                }
            } else if (request.getParameter("method").equals("addQuestion2")) { //method add question for test in lesson page     
                request.setAttribute("idLesson", request.getParameter("idLesson"));
                request.setAttribute("action", "2");

            } else if (request.getParameter("method").equals("addQuestion3")) { //create test for room exam   
                request.setAttribute("action", "3");

            }
            request.setAttribute("a", true);
            request.setAttribute("b", false);
            request.setAttribute("c", false);
            request.setAttribute("d", false);

            request.getRequestDispatcher("createQuestion.jsp").forward(request, response);
        } else {
            request.setAttribute("notice", "You must login to create/edit question/test");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int numOfQuestion = -1;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ss = request.getSession();
        Accounts acc = (Accounts) ss.getAttribute("acc");
        DAO dao = new DAO();
        PrintWriter out = response.getWriter();
        if (request.getParameter("method").equals("create")) { // get number of question
            if (request.getParameter("inNum") != null) {
                String sizeSTR = request.getParameter("numOfQuestion");
                request.setAttribute("a", true);
                request.setAttribute("b", false);
                request.setAttribute("c", false);
                request.setAttribute("d", false);
                try {
                    int size = Integer.parseInt(sizeSTR);
                    if (size > 0) {
                        numOfQuestion = size;
                        // action 1 create,add question for idtest, only admin , in edit page
                        if (request.getParameter("action").equals("1")) {
                            if (!request.getParameter("idTest").isBlank() && acc.getIsAdmin().equals("true")) {
                                String idTest = request.getParameter("idTest");
                                request.setAttribute("idTest", idTest);
                                request.setAttribute("d", true);

                            }
                        } // action 2 method add question for test in idlesson page 
                        else if (request.getParameter("action").equals(2)) {
                            if (!request.getParameter("idLesson").isBlank() && acc.getIsAdmin().equals("true")) {
                                Lessons ls = dao.getLessonWithID(request.getParameter("idLesson"));
                                request.setAttribute("lesson", ls);
                                request.setAttribute("c", true);
                            }
                        } // action 3 create test for room exam
                        else if (request.getParameter("action").equals("3")) {
                            request.setAttribute("b", true);
                        } else {
                            request.setAttribute("notice", "Something error !!!");
                        }

                        request.setAttribute("a", false);
                        request.setAttribute("size", size);
                    } else {
                        request.setAttribute("notice", "Please enter number >0 !!!");
                    }
                } catch (Exception e) {
                    request.setAttribute("notice", e.getMessage());
                }
                request.getRequestDispatcher("createQuestion.jsp").forward(request, response);

            }

        } // create question for Room
        else if (request.getParameter("method").equals("addQuestionB")) {
            if (numOfQuestion > 0 && acc != null) {
                String idTest = "";
                String nameRoom = request.getParameter("nameRoom");
                String time = request.getParameter("time");
                try {
                    //add question
                    int t = Integer.parseInt(time);
                    if (t > 0) {
                        idTest = dao.createNewTest(nameRoom, null, t);
                        for (int i = 1; i <= numOfQuestion; i++) {
                            String title = request.getParameter("title" + i);
                            String question = request.getParameter("question" + i);
                            String asA = request.getParameter("answerA" + i);
                            String asB = request.getParameter("answerB" + i);
                            String asC = request.getParameter("answerC" + i);
                            String asD = request.getParameter("answerD" + i);
                            String correctAnswer = request.getParameter("correctAnswer" + i);

                            String description = "Câu" + i + "<br><div class=\"question-name\"> \n"
                                    + " <p><strong><em>" + title + "</em></strong></p>\n"
                                    + " <p>" + question + "</p> \n"
                                    + "</div>";
                            String answerA = "<p><span><strong>A. </strong>" + asA + "</span></p>";
                            String answerB = "<p><span><strong>A. </strong>" + asB + "</span></p>";
                            String answerC = "<p><span><strong>A. </strong>" + asC + "</span></p>";
                            String answerD = "<p><span><strong>A. </strong>" + asD + "</span></p>";

                            Questions q = new Questions(description, answerA, answerB, answerC, answerD, idTest, correctAnswer);
                            dao.addQuestion(q);

                        }
                        // create rooms    
                        Rooms r = new Rooms(nameRoom, acc.getUserName(), idTest, "false");
                        dao.addRoom(r);
                        ArrayList<Rooms> rr = new ArrayList<>();
                        rr = dao.getAllRoom(acc);
                        ss.setAttribute("rooms", rr);
                        request.getRequestDispatcher("exam.jsp").forward(request, response);
                    }//else go back to lesson page

                } catch (Exception e) {
                    System.out.println("addQuestion" + e.getMessage());
                }

            }
        } // create question for a lesson
        else if (request.getParameter("method").equals("addQuestionC") && acc.getIsAdmin().equals("true")) {

            if (numOfQuestion > 0) {
                String idTest = "";
                String time = request.getParameter("time");
                String descriptionTs = request.getParameter("descriptionTs");
                String idLesson = request.getParameter("idLesson");
                try {
                    //add question
                    int t = Integer.parseInt(time);
                    if (t > 0) {
                        // create new test 
                        idTest = dao.createNewTest(descriptionTs, idLesson, t);
                        // add question
                        for (int i = 1; i <= numOfQuestion; i++) {
                            String title = request.getParameter("title" + i);
                            String question = request.getParameter("question" + i);
                            String asA = request.getParameter("answerA" + i);
                            String asB = request.getParameter("answerB" + i);
                            String asC = request.getParameter("answerC" + i);
                            String asD = request.getParameter("answerD" + i);
                            String correctAnswer = request.getParameter("correctAnswer" + i);

                            String description = "Câu" + i + "<br><div class=\"question-name\"> \n"
                                    + " <p><strong><em>" + title + "</em></strong></p>\n"
                                    + " <p>" + question + "</p> \n"
                                    + "</div>";
                            String answerA = "<p><span><strong>A. </strong>" + asA + "</span></p>";
                            String answerB = "<p><span><strong>B. </strong>" + asB + "</span></p>";
                            String answerC = "<p><span><strong>C. </strong>" + asC + "</span></p>";
                            String answerD = "<p><span><strong>D. </strong>" + asD + "</span></p>";

                            Questions q = new Questions(description, answerA, answerB, answerC, answerD, idTest, correctAnswer);
                            dao.addQuestion(q);
                            //go back to lessson                           
                            Topics tp = (Topics) ss.getAttribute("tp");
                            // get lessons of this topic
                            List<Lessons> ls = dao.getLessonsWithTopic(tp.getIdTopic());
                            List<Tests> lsTest = dao.getAllTest();
                            // put up session
                            ss.setAttribute("tp", tp);
                            ss.setAttribute("ls", ls);
                            request.setAttribute("lsTest", lsTest);
                            request.getRequestDispatcher("lesson.jsp").forward(request, response);

//                            request.getRequestDispatcher("/home").forward(request, response);
                        }
                    } //else go back to leson page

                } catch (Exception e) {
                    System.out.println("addQuestion" + e.getMessage());
                    out.print(e.getMessage());
                }

            }
        } // create / add question for test
        else if (request.getParameter("method").equals("addQuestionD") && acc.getIsAdmin().equals("true")) {

            if (numOfQuestion > 0) {

                String idTest = request.getParameter("idTest");
                try {
                    // add question
                    for (int i = 1; i <= numOfQuestion; i++) {
                        String title = request.getParameter("title" + i);
                        String question = request.getParameter("question" + i);
                        String asA = request.getParameter("answerA" + i);
                        String asB = request.getParameter("answerB" + i);
                        String asC = request.getParameter("answerC" + i);
                        String asD = request.getParameter("answerD" + i);
                        String correctAnswer = request.getParameter("correctAnswer" + i);

                        String description = "Câu" + i + "<br><div class=\"question-name\"> \n"
                                + " <p><strong><em>" + title + "</em></strong></p>\n"
                                + " <p>" + question + "</p> \n"
                                + "</div>";
                        String answerA = "<p><span><strong>A. </strong>" + asA + "</span></p>";
                        String answerB = "<p><span><strong>B. </strong>" + asB + "</span></p>";
                        String answerC = "<p><span><strong>C. </strong>" + asC + "</span></p>";
                        String answerD = "<p><span><strong>D. </strong>" + asD + "</span></p>";

                        Questions q = new Questions(description, answerA, answerB, answerC, answerD, idTest, correctAnswer);
                        dao.addQuestion(q);
                        //go back to lessson                           
                        Topics tp = (Topics) ss.getAttribute("tp");
                        // get lessons of this topic
                        List<Lessons> ls = dao.getLessonsWithTopic(tp.getIdTopic());
                        List<Tests> lsTest = dao.getAllTest();
                        // put up session
                        ss.setAttribute("tp", tp);
                        ss.setAttribute("ls", ls);
                        request.setAttribute("lsTest", lsTest);
                        request.getRequestDispatcher("lesson.jsp").forward(request, response);

//                            request.getRequestDispatcher("/home").forward(request, response);
                    }

                } catch (Exception e) {
                    System.out.println("addQuestion" + e.getMessage());
                    out.print(e.getMessage());
                }

            }
        } else if (request.getParameter("method").equals("edit") && acc.getIsAdmin().equals("true")) {
            //test descriptionTs,time,idLesson,idTest
            String idTest = request.getParameter("idTest");
            //String idLesson = request.getParameter("idLesson");
            String timeSTR = request.getParameter("time");
            String descriptionTs = request.getParameter("descriptionTs");
            int time = Integer.parseInt(timeSTR);
            if (time < 0) {
                time = -time;
            }
            Tests test = dao.getTestWithID(idTest);
            test.setDescriptionTs(descriptionTs);
            test.setTime(timeSTR);
            if (!dao.updateTest(test)) {
                out.print("Error edit Test");
                return;
            }

            ArrayList<Questions> listQuestion = dao.getQuestionWithTest(idTest);

            for (Questions questions : listQuestion) {
                try {
                    String idQuestion = questions.getIdQuestion();
                    String descriptionQ = request.getParameter("descriptionQ" + idQuestion);
                    String delete = request.getParameter("delete" + idQuestion);
                    String answer1 = request.getParameter("answerA" + idQuestion);
                    String answer2 = request.getParameter("answerB" + idQuestion);
                    String answer3 = request.getParameter("answerC" + idQuestion);
                    String answer4 = request.getParameter("answerD" + idQuestion);
                    String correctAnswer = request.getParameter("correctAnswer" + idQuestion);

                    if (delete != null) {
                        dao.deleteQuestion(idQuestion);
                    } else {
                        Questions q = new Questions(questions.getIdQuestion(), descriptionQ, answer1, answer2, answer3, answer4, idTest, correctAnswer);
                        if (!dao.updateQuestion(q)) {
                            out.print("Error edit Test,Question" + q.toString());
                            return;

                        }
                    }

                } catch (Exception e) {
                    out.print("Error edit Test,Question:" + e.getMessage());
                    return;
                }

            }
            //go back to lessson                           
            Topics tp = (Topics) ss.getAttribute("tp");
            // get lessons of this topic
            List<Lessons> ls = dao.getLessonsWithTopic(tp.getIdTopic());
            List<Tests> lsTest = dao.getAllTest();
            // put up session
            ss.setAttribute("tp", tp);
            ss.setAttribute("ls", ls);
            request.setAttribute("lsTest", lsTest);
            request.getRequestDispatcher("lesson.jsp").forward(request, response);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
