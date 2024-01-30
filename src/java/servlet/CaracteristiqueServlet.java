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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Caracteristique;
import model.DetailsCaracteristique;
import util.connection.ConnectBD;

/**
 *
 * @author itu
 */
@WebServlet(name = "CaracteristiqueServlet", urlPatterns = {"/CaracteristiqueServlet"})
public class CaracteristiqueServlet extends HttpServlet {
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
            out.println("<title>Servlet CaracteristiqueServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CaracteristiqueServlet at " + request.getContextPath() + "</h1>");
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
            getCaracteristiques(request);
            request.getRequestDispatcher("caracteristiqueform.jsp").forward(request, response);
        } catch (Exception ex) {
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
        String caracteristique = request.getParameter("caracteristique");
        String idcaracteristique = request.getParameter("idcaracteristique");
        String detailscaracteristique = request.getParameter("detailscaracteristique");
        
        try {
            if (caracteristique != null) {
                Caracteristique c = new Caracteristique();
                c.setNom(caracteristique);
                c.insert(connection.getOnConnection());
            }else {
                DetailsCaracteristique d = new DetailsCaracteristique();
                d.setIdcaracteristique(idcaracteristique);
                d.setNomdetailscaracteristique(detailscaracteristique);
                d.insert(connection.getOnConnection());
            }
        } catch (Exception ex) {
        }
        doGet(request, response);
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

    public void getCaracteristiques (HttpServletRequest request) throws SQLException{

        ArrayList<Caracteristique> caracteristiques = new Caracteristique().getAll(connection.getOnConnection());

        request.setAttribute("caracteristiques", caracteristiques);
        request.setAttribute("detailscaracteristique", new DetailsCaracteristique().getAll(connection.getOnConnection()));
    }
}
