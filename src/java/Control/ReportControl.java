/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.Accounts;
import Model.Lessons;
import Model.Reporters;
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
public class ReportControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ss = request.getSession();
        DAO dao = new DAO();
        if (request.getParameter("method").equals("room")) {
            String idTest = request.getParameter("idTest");
            String idRoom = request.getParameter("idRoom");
            ArrayList<Reporters> listReporter = dao.gettAllReporterWithCodeDESC(idTest, idRoom);
            request.setAttribute("listReporter", listReporter);
            request.getRequestDispatcher("show.jsp").forward(request, response);

        } else if (request.getParameter("method").equals("show")) {
            String idTest = request.getParameter("idTest");      
            ArrayList<Reporters> listReporter = dao.gettAllReporterWithTestDESC(idTest);
            request.setAttribute("listReporter", listReporter);
            request.getRequestDispatcher("show.jsp").forward(request, response);
//            //go back to lessson                           
//            Topics tp = (Topics) ss.getAttribute("tp");
//            // get lessons of this topic
//            List<Lessons> ls = dao.getLessonsWithTopic(tp.getIdTopic());
//            List<Tests> lsTest = dao.getAllTest();
//            // put up session
//            ss.setAttribute("tp", tp);
//            ss.setAttribute("ls", ls);
//            request.setAttribute("lsTest", lsTest);
//            request.getRequestDispatcher("lesson.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
