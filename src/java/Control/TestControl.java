/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.Accounts;
import Model.Lessons;
import Model.Questions;
import Model.Reporters;
import Model.Results;
import Model.Tests;
import Model.Topics;
import dao.DAO;
import java.io.IOException;
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
public class TestControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        HttpSession ss = request.getSession();
        Accounts acc = (Accounts)ss.getAttribute("acc");
        if (request.getParameter("method") == null) {
            String idTest = request.getParameter("idT");
            String idLesson = request.getParameter("idLs");

            // get db
            Tests test = dao.getTestWithID(idTest);
            List<Questions> q = dao.getQuestionWithTest(idTest);
            String idTp = dao.getIdTopicWithIDLeson(idLesson);
            Topics tp = dao.getTopicWithID(idTp);
            Lessons lesson = dao.getLessonWithID(idLesson);
            // publish in session
            
            ss.setAttribute("tp", tp);
            ss.setAttribute("lesson", lesson);
            ss.setAttribute("test", test);
            ss.setAttribute("idRoom", null);
            request.setAttribute("listQuestions", q);
            request.getRequestDispatcher("test.jsp").forward(request, response);
        } else if (request.getParameter("method").equals("deleteTest")) {
            String idTest = request.getParameter("idTest");
            dao.deleteTest(idTest);

            Topics t = (Topics) ss.getAttribute("tp");
            String idTp = t.getIdTopic();
            //get data from dao      

            // get sellected topic 
            Topics tp = dao.getTopicWithID(idTp);
            // get lessons of this topic
            List<Lessons> ls = dao.getLessonsWithTopic(idTp);
            List<Tests> lsTest = dao.getAllTest();

            // put up session         
            ss.setAttribute("tp", tp);
            ss.setAttribute("ls", ls);

            request.setAttribute("lsTest", lsTest);

            request.getRequestDispatcher("lesson.jsp").forward(request, response);
        } else if (request.getParameter("method").equals("editTest") && acc.getIsAdmin().equals("true")) {
            String idTest = request.getParameter("idTest");
            ArrayList<Questions> listQuestion = dao.getQuestionWithTest(idTest);
            
            
            
            
            Tests test = dao.getTestWithID(idTest);
            request.setAttribute("test", test);
            request.setAttribute("listQuestion", listQuestion);
            request.getRequestDispatcher("editQuestion.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ss = request.getSession();
        ArrayList<Results> resultList = new ArrayList<>();
        int grade = 0;
        DAO dao = new DAO();
        Tests test = (Tests) ss.getAttribute("test");
        ArrayList<Questions> questions = dao.getQuestionWithTest(test.getIdTest());

        //caculate grade
        for (Questions question : questions) {
            String getIdASQuestion = question.getIdQuestion() + "AS";
            String as = question.getCorrectAnswer();
            try {
                if (!request.getParameter(getIdASQuestion).isBlank() && request.getParameter(getIdASQuestion).equals(as)) {
                    grade += 1;
                }
                resultList.add(new Results(question, as, request.getParameter(getIdASQuestion)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // when user had acccount

        if (ss.getAttribute("acc") != null) {
            String thisUser = ((Accounts) ss.getAttribute("acc")).getUserName();

            if (request.getParameter("idRoom") != null) { // in room
                Reporters rp = new Reporters(thisUser, test.getIdTest(), String.valueOf(grade), request.getParameter("idRoom"));
                dao.addReporter(rp);
            } else {
                //make a reporter
                Reporters rp = new Reporters(thisUser, test.getIdTest(), String.valueOf(grade));
                dao.addReporter(rp);
            }

        }

        request.setAttribute("resultList", resultList);
        request.setAttribute("grade", grade);
        request.setAttribute("numOfQuestion", questions.size());
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

}
