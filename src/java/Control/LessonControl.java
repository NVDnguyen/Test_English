/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.Lessons;
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
import java.util.List;

/**
 *
 * @author nguye
 */
public class LessonControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DAO dao = new DAO();
        HttpSession ss = request.getSession();

        if (request.getParameter("method") == null) {
            String idTp = request.getParameter("topic");
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
        } else if (request.getParameter("method").equals("add")) {
            // add lesson       
            Topics tp = (Topics) ss.getAttribute("tp"); // add this lesson into this topic
            out.print(
                    "<form method=\"post\" action=\"lesson\">\n"
                    + "                            <table>\n"
                    + "                                <tr>\n"
                    + "                                    <td>[Name of new Lesson :]</td> <td> <input class=\"form-text\" required name=\"descriptionL\"></td>\n"
                    + "                                </tr>                                \n"
                    + "                            </table>\n"
                    + "\n"
                    + "                            \n"
                    + "\n"
                    + "                            <input hidden name=\"idTopic\" value=\"" + tp.getIdTopic() + "\" >\n"
                    + "                            <input type=\"submit\" name=\"add\" value=\"Submit\">\n"
                    + "                        </form>"
            );

        } else if (request.getParameter("method").equals("delete")) {
            //delete
            String idLesson = request.getParameter("idLesson");
            if (dao.deleteLesson(idLesson)) {
                // get sellected topic 
                Topics tp = (Topics) ss.getAttribute("tp");
                // get lessons of this topic
                List<Lessons> ls = dao.getLessonsWithTopic(tp.getIdTopic());
                List<Tests> lsTest = dao.getAllTest();
                // put up session
                ss.setAttribute("tp", tp);
                ss.setAttribute("ls", ls);
                request.setAttribute("lsTest", lsTest);
                request.getRequestDispatcher("lesson.jsp").forward(request, response);
            } else {
                out.print("ERROR: Delete this Lesson fail");
            }

        } else if (request.getParameter("method").equals("edit")) {
            // edit lesson
            String idLesson = request.getParameter("idLesson");
            Lessons lesson = dao.getLessonWithID(idLesson);
            Topics tp = (Topics) ss.getAttribute("tp");
            out.print("   <form action=\"lesson\" method=\"post\">\n"
                    + "            <input hidden name=\"idTopic\" value=\"" + tp.getIdTopic() + "\" >\n"
                    + "            <input hidden name=\"idLesson\" value=\"" + lesson.getIdLesson() + "\" >\n"
                    + "            <div>Change description for this lesson </div><br>\n"
                    + "            <input required name=\"descriptionL\" value=\"" + lesson.getDescription() + "\"><br>\n"
                    + "            <input type=\"submit\" name=\"edit\">  \n"
                    + "        </form>");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO dao = new DAO();
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("add") != null) {

                String idTopic = request.getParameter("idTopic");
                String descriptionL = request.getParameter("descriptionL");
                if (!dao.addLesson(new Lessons(descriptionL, idTopic))) {
                    out.print("Error: Add fail ");
                    return;
                }
            } else if (request.getParameter("edit") != null) {
                String idLesson = request.getParameter("idLesson");
                String descriptionL = request.getParameter("descriptionL");
                Lessons ls = dao.getLessonWithID(idLesson);
                ls.setDescription(descriptionL);
                if (!dao.updateLesson(ls)) {
                    out.print("Error: Update fail ");
                    return;
                }
            }

            out.print("here");

            // get sellected topic 
            HttpSession ss = request.getSession();
            String idTopic = request.getParameter("idTopic");
            // get lessons of this topic
            List<Lessons> ls = dao.getLessonsWithTopic(idTopic);
            List<Tests> lsTest = dao.getAllTest();
            // put up session
            ss.setAttribute("tp", dao.getTopicWithID(idTopic));
            ss.setAttribute("ls", ls);

            out.print("here2");
            request.setAttribute("lsTest", lsTest);
            request.getRequestDispatcher("lesson.jsp").forward(request, response);

        }

    }

}
