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
import model.InformationProduit;
import model.Matierestyle;
import model.NombreEmployeeInformationProduit;
import model.QuantiteMatiereInformationProduit;
import util.connection.ConnectBD;

/**
 *
 * @author itu
 */
@WebServlet(name = "QuantiteMatiereServlet", urlPatterns = {"/QuantiteMatiereServlet"})
public class QuantiteMatiereServlet extends HttpServlet {
    
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
            out.println("<title>Servlet QuantiteMatiereServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuantiteMatiereServlet at " + request.getContextPath() + "</h1>");
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
        out.println("Message : ");
        // Récupérer les valeurs fixes du formulaire
        InformationProduit info = new InformationProduit();
        String volume = request.getParameter("volume");
        info = info.getInformation((ArrayList<InformationProduit>) request.getSession().getAttribute("listProduit"), Double.valueOf(volume));
        String[] matieres = request.getParameterValues("matiere");
        String[] quantites = request.getParameterValues("quantite");
        String[] unites = request.getParameterValues("unite");
        String[] postes = request.getParameterValues("poste");
        String[] nombres = request.getParameterValues("nombre");
        String[] duree = request.getParameterValues("duree");
        try {
                for (int i = 0; i < quantites.length; i++) {
                    if (!quantites[i].isEmpty()){
                        QuantiteMatiereInformationProduit qm = new QuantiteMatiereInformationProduit();
                        qm.setQuantite(quantites[i]);
                        qm.setIdMatiereStyle((new Matierestyle().getIdMatierestyle(connection.getOnConnection(), info.getIdstyle(), Integer.valueOf(matieres[i]))).getId());
                        qm.setIdUnite(unites[i]);
                        qm.setIdInformationProduit(info.getId());
                    qm.insert(connection.getOnConnection());
                   } 
                }
            
                for (int k = 0; k < nombres.length; k++) {
                    if (!nombres[k].isEmpty()){
                        NombreEmployeeInformationProduit nb = new NombreEmployeeInformationProduit();
                        out.print(nombres[k]+" "+postes[k]+" "+duree[k]);
                        nb.setNombre(nombres[k]);
                        nb.setIdInformationProduit(info.getId());
                        nb.setPoste(postes[k], connection.getOnConnection());
                        nb.setDuree(duree[k]);
                        nb.insert(connection.getOnConnection());
                    }
                } 
                
            new ProduitServlet().doGet(request, response);
        }catch (Exception ex) {
            out.println("Message : "+ex);
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
