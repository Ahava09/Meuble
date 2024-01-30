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
import model.Matiere;
import model.Matierestyle;
import model.Poste;
import model.Taille;
import model.Unite;
import util.connection.ConnectBD;

/**
 *
 * @author itu
 */
@WebServlet(name = "MatiereStyleServlet", urlPatterns = {"/MatiereStyleServlet"})
public class MatiereStyleServlet extends HttpServlet {
    
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
            out.println("<title>Servlet ajoutMatiereStyle</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ajoutMatiereStyle at " + request.getContextPath() + "</h1>");
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
            getInfoProduit(request);
            new MatiereServlet().getMatiereUnite(request);
            request.getRequestDispatcher("matiereStyle.jsp").forward(request, response);
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

        String[] matieres = request.getParameterValues("check[]");
        Matierestyle ms = new Matierestyle();
//        ms.setIdstyle(request.getParameter("style"));
            
        try {
            ms.setStyle(connection.getOnConnection(), request.getParameter("style"));
            for (int i = 0; i < matieres.length; i++) {
                ms.setMatiere(connection.getOnConnection(), matieres[i]);
                ms.insert(connection.getOnConnection());
                request.getRequestDispatcher("index.html").forward(request, response);
            }
        }catch (Exception ex) {

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
    
    public void getInfoProduit (HttpServletRequest request) throws SQLException{
        request.setAttribute("unites", new Unite().getAll(connection.getOnConnection()));
        request.setAttribute("matieres", new Matiere().getAll(connection.getOnConnection()));
        request.setAttribute("styles", new DetailsCaracteristique().getAllWithWhere(connection.getOnConnection(), 1));
        request.setAttribute("categories", new DetailsCaracteristique().getAllWithWhere(connection.getOnConnection(), 2));
        request.setAttribute("tailles", new Taille().getAll(connection.getOnConnection()));
        request.setAttribute("postes", new Poste().getAll(connection.getOnConnection()));
        
    }
}
