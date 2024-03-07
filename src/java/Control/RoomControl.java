/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.Accounts;
import Model.Rooms;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nguye
 */
public class RoomControl extends HttpServlet {

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
            out.println("<title>Servlet RoomControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RoomControl at " + request.getContextPath() + "</h1>");
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
        DAO dao = new DAO();
        HttpSession ss = request.getSession();
        Accounts creater = (Accounts) ss.getAttribute("acc");
        if (request.getParameter("action") == null) { // default 
            String idTest = request.getParameter("idTest");
            String codeRoom = randomCode(5);
            String nameRoom = dao.getTestWithID(idTest).getDescriptionTs();
            Rooms room = new Rooms(nameRoom, codeRoom, creater.getUserName(), idTest, "false");
            dao.addRoom(room);

        } else if (request.getParameter("action").equals("stop")) { // active => false
            String idRoom = request.getParameter("idRoom");
            dao.updateActiveRoom(idRoom, "false");

        } else if (request.getParameter("action").equals("start")) { // active => true
            String idRoom = request.getParameter("idRoom");
            dao.updateActiveRoom(idRoom, "true");

        }
        
        ArrayList<Rooms> rr = new ArrayList<>();
        // feature for admin
        if (creater.getIsAdmin().equals("true")) {        
            rr = dao.getAllRoom();
        } else {
            rr = dao.getAllRoom(creater);
        }

        ss.setAttribute("rooms", rr);
        request.getRequestDispatcher("exam.jsp").forward(request, response);
    }

    private static String randomCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String charr = "QƯERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm1234567890";
        for (int i = 0; i < length; i++) {
            int randomInt = random.nextInt(charr.length());
            String c = charr.indent(randomInt);
            sb.append(c);
        }

        return sb.toString();
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
