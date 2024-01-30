<%-- 
    Document   : formulaire1.jsp
    Created on : 12 déc. 2023, 16:03:36
    Author     : andry
--%>

<%@page import="model.Taille"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DetailsCaracteristique"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<DetailsCaracteristique> myCategories =(ArrayList<DetailsCaracteristique>) request.getAttribute("categories");
    ArrayList<DetailsCaracteristique> myStyles = (ArrayList<DetailsCaracteristique> ) request.getAttribute("styles");
    ArrayList<Taille> myTailles = (ArrayList<Taille> ) request.getAttribute("tailles");
%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Page intermédiaire</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            
            .container {
                width: 50%;
                margin: 100px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            
            h2 {
                text-align: center;
            }
            
            form {
                text-align: center;
            }
            
            input[type="text"],
            input[type="password"],
            input[type="submit"] {
                width: 70%;
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            
            input[type="number"] {
                width: 70%;
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            
            input[type="number"]:focus {
                outline: none;
                /* Supprime le contour lorsqu'il est en focus */
                border-color: #3498db;
                /* Change la couleur de la bordure lorsqu'il est en focus */
                box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
                /* Ajoute une ombre lorsqu'il est en focus */
            }
            
            input[type="submit"] {
                background-color: #3498db;
                color: #fff;
                cursor: pointer;
            }
            
            input[type="submit"]:hover {
                background-color: #2980b9;
            }
        </style>
        <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/css/bootstrap-theme.css">
    <link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="assets/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/npm.js"></script>
    <script src="assets/js/jquery.min.js"></script>

    </head>

    <body>
        <div class="container">
            <h2>Insertion Information produit pour plus detaille</h2>
            <form action="ProduitServlet" method="post">
                <label for="style">Sélectionner un style :</label> 
                <select name="style">
                    <% for(DetailsCaracteristique s : myStyles) { %>
                    <p><option value="<%= s.getIddetailscaracteristique()%>" > <%= s.getNomdetailscaracteristique()%></p>
                    <% } %>
                </select>
                <label for="style">Sélectionner un categorie :</label> 
                <select name="categorie">
                    <% for(DetailsCaracteristique c : myCategories) { %>
                    <p><option value="<%= c.getIddetailscaracteristique()%>" > <%= c.getNomdetailscaracteristique()%></p>
                    <% } %>
                </select>
                <label for="style">Sélectionner la taille :</label> 
                <select name="taille">
                    <% for(Taille t : myTailles) { %>
                    <p><option value="<%= t.getId()%>" > <%= t.getNom()%>  min: <%= t.getVolumemin()%> cm3 - <%= t.getVoulumemax()%>   </p>
                    <% } %>
                </select>
                <input type="submit" value="OK">
            </form>
            <a class="btn btn-lg btn-primary rounded-pill hover-top" href="index.html" role="button">Retour</a>
        </div>
    </body>

    </html>