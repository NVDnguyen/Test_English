/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.Accounts;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class ManageControl extends HttpServlet {

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
            out.println("<title>Servlet ManageControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageControl at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        DAO dao = new DAO();
        HttpSession ss = request.getSession();
        if (ss.getAttribute("acc") != null) {
            Accounts acc = (Accounts) ss.getAttribute("acc");
            if (acc.getIsAdmin().equals("true")) {
                if (request.getParameter("method") == null) {

                } else if (request.getParameter("method").equals("band")) {
                    if (dao.bandAccount(request.getParameter("userName"))) {

                    } else {

                        out.println("alert('Error occurred while banning this account');");

                    }

                } else if (request.getParameter("method").equals("unband")) {
                    if (dao.unbandAccount(request.getParameter("userName"))) {

                    } else {

                        out.println("alert('Error occurred while unbanning this account');");

                    }
                }
            } else {
                out.println("alert('Don't hack me);");

                return;
            }
            ArrayList<Accounts> listAccount = dao.getAllAccount();
            request.setAttribute("listAccount", listAccount);
            request.getRequestDispatcher("Manage.jsp").forward(request, response);

        } else {
            out.print("?????????????????????");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
