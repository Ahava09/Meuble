/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Niveau;
import util.connection.ConnectBD;

/**
 *
 * @author itu
 */
@WebServlet(name = "NiveauServlet", urlPatterns = {"/NiveauServlet"})
public class NiveauServlet extends HttpServlet {
    ConnectBD connection = new ConnectBD();
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
            out.println("<title>Servlet NiveauServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NiveauServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        String nom = request.getParameter("nom");
        String dureemin = request.getParameter("dureemin");
        String dureemax = request.getParameter("dureemax");
        Niveau niveau = new Niveau(id,nom, dureemin, dureemax);
        try {
            niveau.update(connection.getOnConnection());
            new MatiereServlet().doGet(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NiveauServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        String nom = request.getParameter("nom");
        String dureemin = request.getParameter("dureemin");
        String dureemax = request.getParameter("dureemax");
        Niveau niveau = new Niveau(nom, dureemin, dureemax);
        try {
            niveau.insert(connection.getOnConnection());
            new MatiereServlet().doGet(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NiveauServlet.class.getName()).log(Level.SEVERE, null, ex);
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
