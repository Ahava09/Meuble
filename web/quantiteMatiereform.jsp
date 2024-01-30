<%-- 
    Document   : formulaire1.jsp
    Created on : 12 déc. 2023, 16:03:36
    Author     : andry
--%>

<%@page import="model.EmployeeProduit"%>
<%@page import="model.Unite"%>
<%@page import="model.Matierestyle"%>
<%@page import="model.Taille"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.InformationProduit"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<InformationProduit> listProduit =(ArrayList<InformationProduit>) request.getSession().getAttribute("listProduit");
    ArrayList<Matierestyle> matieres =(ArrayList<Matierestyle>) request.getAttribute("matieres");
    ArrayList<EmployeeProduit> postes =(ArrayList<EmployeeProduit>) request.getAttribute("postes");
    ArrayList<Unite> unites =(ArrayList<Unite>) request.getAttribute("unites");
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
            <h2>Insertion Information produit pour plus de détails matiere</h2>
            <form action="QuantiteMatiereServlet" method="post" id="detailsForm">
                <label for="style">Sélectionner le volume :</label> 
                <select name="volume">
                    <% for(InformationProduit i : listProduit) { %>
                        <option value="<%= i.getId()%>" > <%= i.getVolume()%></option>
                    <% } %>
                </select>

                <div id="matiereSection">
                    <label for="style"> les matieres </label> 
                    <select name="matiere">
                        <% for(Matierestyle m : matieres) { %>
                            <option value="<%= m.getMatiere().getId()%>" > <%= m.getMatiere().getNom()%></option>
                        <% } %>
                    </select>
                    <input name="quantite" type="text" placeholder="Quantite"/>
                    <select name="unite">
                        <% for(Unite u : unites) { %>
                            <option value="<%= u.getId()%>" > <%= u.getNom()%></option>
                        <% } %>
                    </select>

                    <button type="button" onclick="addMatiereSection()">Add</button>
                </div>
                    

                <div id="posteSection">
                    <label for="style"> les postes </label> 
                    <select name="poste">
                        <% for(EmployeeProduit ep : postes) { %>
                            <option value="<%= ep.getPoste().getId()%>" > <%= ep.getPoste().getNom()%></option>
                        <% } %>
                    </select>
                    <input name="nombre" type="text" placeholder="Nombre"/>
                    <input name="duree" type="text" placeholder="Duree"/>

                    <button type="button" onclick="addPosteSection()">Add</button>
                </div>

                <input type="submit" value="OK">
            </form>

            <script>
                function addMatiereSection() {
                    var matiereSection = document.getElementById("matiereSection");

                    // Cloner la section existante de matière, quantité et unité
                    var newMatiereSection = matiereSection.cloneNode(true);

                    // Ajouter le clone à la fin du formulaire
                    document.getElementById("detailsForm").appendChild(newMatiereSection);
                }
                function addPosteSection() {
                    var posteSection = document.getElementById("posteSection");

                    // Cloner la section existante de matière, quantité et unité
                    var newPosteSection = posteSection.cloneNode(true);

                    // Ajouter le clone à la fin du formulaire
                    document.getElementById("detailsForm").appendChild(newPosteSection);
                }
            </script>
        </div>
    </body>
</html>