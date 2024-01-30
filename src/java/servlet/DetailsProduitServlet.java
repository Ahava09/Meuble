/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetailsProduit;
import model.Matierestyle;
import util.connection.ConnectBD;

/**
 *
 * @author itu
 */
@WebServlet(name = "DetailsProduitServlet", urlPatterns = {"/DetailsProduitServlet"})
public class DetailsProduitServlet extends HttpServlet {
    private ConnectBD connection = new ConnectBD();
    private MatiereStyleServlet param = new MatiereStyleServlet();

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
            out.println("<title>Servlet DetailsProduitServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailsProduitServlet at " + request.getContextPath() + "</h1>");
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
            param.getInfoProduit(request);
            request.setAttribute("list", new DetailsProduit().getAll(connection.getOnConnection()));
            request.getRequestDispatcher("detailsProduit.jsp").forward(request, response);
        } catch (SQLException ex) {
            out.println(ex.getMessage());
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
        try {
            param.getInfoProduit(request);
            String[] name = {"idstyle","idcategorie","idmatiere","idtaille"};
            String[] value = {request.getParameter("idstyle"),request.getParameter("idcategorie"),request.getParameter("idmatiere"),request.getParameter("idtaille")};
            request.setAttribute("list", new DetailsProduit().getAllWithWhere(connection.getOnConnection(),name, value));
            out.print("ok");
            request.getRequestDispatcher("detailsProduit.jsp").forward(request, response);
        } catch (SQLException ex) {
            out.println(ex.getMessage());
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
