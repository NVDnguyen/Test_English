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
         if (request.getParameter("action").equals("stop")) { // active => false
            String idRoom = request.getParameter("idRoom");
            dao.updateActiveRoom(idRoom, "false");

        } else if (request.getParameter("action").equals("start")) { // active => true
            String idRoom = request.getParameter("idRoom");
            dao.updateActiveRoom(idRoom, "true");

        }else if (request.getParameter("action").equals("delete")) { // active => true
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


}
