/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.Accounts;
import Model.Questions;
import Model.Rooms;
import Model.Tests;
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
public class ExamControl extends HttpServlet {

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
            out.println("<title>Servlet ExamControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExamControl at " + request.getContextPath() + "</h1>");
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

        DAO dao = new DAO();
        ArrayList<Rooms> rr = new ArrayList<>();
        // feature for admin
        if (ss.getAttribute("acc") != null) {
            Accounts creater = (Accounts) ss.getAttribute("acc");
            if (creater.getIsAdmin().equals("true")) {
                rr = dao.getAllRoom();
            } else {
                rr = dao.getAllRoom(creater);
            }
        }
        ss.setAttribute("rooms", rr);
        request.getRequestDispatcher("exam.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ss = request.getSession();

        if (ss.getAttribute("acc") != null) {
            if (request.getParameter("join") != null) {
                String code = request.getParameter("roomCode");
                String notice = "";
                DAO dao = new DAO();
                if (dao.getTestRoomIsActive(code) != null) {
                    String idTest = dao.getTestRoomIsActive(code);
                    Tests test = dao.getTestWithID(idTest);
                    List<Questions> q = dao.getQuestionWithTest(idTest);
                    Rooms r = dao.getRoom(code);

                    ss.setAttribute("test", test);

                    request.setAttribute("listQuestions", q);
                    request.setAttribute("idRoom", r.getIdRoom());
                    request.getRequestDispatcher("test.jsp").forward(request, response);
                } else {
                    notice = "Code error";
                    request.setAttribute("notice", notice);
                    request.getRequestDispatcher("exam.jsp").forward(request, response);

                }
            } else if (request.getParameter("create") != null) {
                response.sendRedirect("question?method=addQuestionD");
            }
        } else {
            request.setAttribute("notice", "Please login");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
