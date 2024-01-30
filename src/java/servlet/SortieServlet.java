/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EmployeeProduit;
import model.InformationProduit;
import model.Matierestyle;
import model.SortieProduit;
import model.Unite;
import util.connection.ConnectBD;

/**
 *
 * @author itu
 */
@WebServlet(name = "SortieServlet", urlPatterns = {"/SortieServlet"})
public class SortieServlet extends HttpServlet {
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
            out.println("<title>Servlet SortieServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SortieServlet at " + request.getContextPath() + "</h1>");
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
        try {
            new MatiereStyleServlet().getInfoProduit(request);
            request.getRequestDispatcher("sortieproduit.jsp").forward(request, response);
            
        } catch (SQLException ex) {
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
        PrintWriter out = response.getWriter();
        InformationProduit info = new InformationProduit();
        Matierestyle ms = new Matierestyle();
        EmployeeProduit ep = new EmployeeProduit();
        Unite unite = new Unite();
        info.setIdstyle(request.getParameter("style"));
        info.setIdcategorie(request.getParameter("categorie"));
        info.setVolume(request.getParameter("volume"));
        try {
            info.setIdtaille(request.getParameter("taille"));
            info = info.getInformationProduit(connection.getOnConnection());
            SortieProduit s = new SortieProduit();
            s.setDatesortie(request.getParameter("date"));
            s.setIdInformationProduit(info.getId());
            s.setNombre(request.getParameter("nombre"));
            s.insertProduitMatiere(connection.getOnConnection());
            
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            doGet(request, response);
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
