/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Achat;
import model.Client;
import model.InformationProduit;
import model.Statistique;
import util.connection.ConnectBD;

/**
 *
 * @author itu
 */
@WebServlet(name = "AchatServlet", urlPatterns = {"/AchatServlet"})
public class AchatServlet extends HttpServlet {
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
            out.println("<title>Servlet AchatServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AchatServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        try {
            new MatiereStyleServlet().getInfoProduit(request);
            String ref = "0";
            ArrayList<Statistique> statistiques = new Statistique().getAll(connection.getOnConnection());
            if( request.getParameter("genre") != null && !request.getParameter("genre").equalsIgnoreCase("0")){
//                statistiques =  new Statistique().getByGenre(connection.getOnConnection(), request.getParameter("genre"));
                ref = request.getParameter("genre");
            }
            request.setAttribute("achat", statistiques);
            request.setAttribute("ref", ref);
            request.setAttribute("client", new Client().getAll(connection.getOnConnection()));
            request.getRequestDispatcher("achatProduit.jsp").forward(request, response);
        } catch (Exception ex) {
            out.print(ex);
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
        info.setIdstyle(request.getParameter("style"));
        info.setIdcategorie(request.getParameter("categorie"));
        info.setVolume(request.getParameter("volume"));
        String nombre = request.getParameter("nombre");
        String dateAchat = request.getParameter("dateAchat");
        String client = request.getParameter("client");
        try {
            info.setIdtaille(request.getParameter("taille"));
            info = info.getInformationProduit(connection.getOnConnection());
            Achat achat = new Achat(client,info.getId(),nombre,dateAchat,connection.getOnConnection());
            achat.insert(connection.getOnConnection());
            doGet(request, response);
            
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
