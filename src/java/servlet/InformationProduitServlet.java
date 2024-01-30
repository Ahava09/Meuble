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
import model.DetailsCaracteristique;
import model.InformationProduit;
import model.Matierestyle;
import model.Taille;
import util.connection.ConnectBD;

/**
 *
 * @author itu
 */
@WebServlet(name = "InformationProduitServlet", urlPatterns = {"/InformationProduitServlet"})
public class InformationProduitServlet extends HttpServlet {
    
    private ConnectBD connection = new ConnectBD();

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
            out.println("<title>Servlet InformationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InformationServlet at " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("informationProduitform.jsp").forward(request, response);

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
        Taille taille = new Taille();
        Matierestyle ms = new Matierestyle();
        info.setIdstyle(request.getParameter("style"));
        info.setIdcategorie(request.getParameter("categorie"));
        info.setVolume(request.getParameter("volume"));
        info.setPrixVente(request.getParameter("prix"));
        try {
            info.setIdtaille(connection.getOnConnection(),request.getParameter("taille"));
            info.setDureedebut(request.getParameter("datedebut"));
            info.setDureefin(request.getParameter("datefin"));
            out.print(" Date Debut: "+info.getDureedebut());
            info.insert(connection.getOnConnection());
            
            new MatiereStyleServlet().getInfoProduit(request);
            request.getRequestDispatcher("choiceProduit.jsp").forward(request, response);
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
