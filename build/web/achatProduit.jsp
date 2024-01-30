<%-- 
    Document   : formulaire1.jsp
    Created on : 12 déc. 2023, 16:03:36
    Author     : andry
--%>

<%@page import="model.Client"%>
<%@page import="model.Statistique"%>
<%@page import="model.Taille"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DetailsCaracteristique"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<DetailsCaracteristique> myCategories =(ArrayList<DetailsCaracteristique>) request.getAttribute("categories");
    ArrayList<DetailsCaracteristique> myStyles = (ArrayList<DetailsCaracteristique> ) request.getAttribute("styles");
    ArrayList<Taille> myTailles = (ArrayList<Taille> ) request.getAttribute("tailles");
    ArrayList<Statistique> achats =  (ArrayList<Statistique> ) request.getAttribute("achat");
    ArrayList<Client> client =  (ArrayList<Client> ) request.getAttribute("client");
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
            <h2>Faire un achat pour un produit</h2>
            <%if (request.getAttribute("error") != null){%>
                <h4 style="color: red"><%= request.getAttribute("error") %></h4>
            <% } %>
            <form action="AchatServlet" method="post">
                <!--<label for="style">Sélectionner un style :</label>--> 
                <select name="style">
                    <% for(DetailsCaracteristique s : myStyles) { %>
                    <p><option value="<%= s.getIddetailscaracteristique()%>" > <%= s.getNomdetailscaracteristique()%></p>
                    <% } %>
                </select>
                <!--<label for="style">Sélectionner un categorie :</label>--> 
                <select name="categorie">
                    <% for(DetailsCaracteristique c : myCategories) { %>
                    <p><option value="<%= c.getIddetailscaracteristique()%>" > <%= c.getNomdetailscaracteristique()%></p>
                    <% } %>
                </select>
                <!--<label for="style">Sélectionner la taille :</label>--> 
                <select name="taille">
                    <% for(Taille t : myTailles) { %>
                    <p><option value="<%= t.getId()%>" > <%= t.getNom()%>  min: <%= t.getVolumemin()%> cm3 - <%= t.getVoulumemax()%>   </p>
                    <% } %>
                </select>
                <!--<label for="style">Sélectionner le Client :</label>--> 
                <select name="client">
                    <% for(Client c : client) { %>
                    <p><option value="<%= c.getId()%>" > <%= c.getNom()%>  <%= c.getPrenom()%>  </p>
                    <% } %>
                </select>
                <input type="datetime-local" name="dateAchat" />
                <input type="text" name="volume" placeholder="Volume"/>
                <input type="text" name="nombre" placeholder="Nombre"/>
                <input type="submit" value="OK">
            </form>
                
            <form action="AchatServlet" method="get">
                <label for="style"> Genre  </label>
                <select name="genre">
                    <p><option value="0" > Tous </p>
                    <p><option value="1" > Homme </p>
                    <p><option value="2" > Femme </p>
                </select>
                <input type="submit" value="Filtrer">
                
                <!-- Table -->
                <table  class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Nom Style</th>
                            <th>Nom Categorie</th>
                            <th>Taille</th>
                            <th>Volume</th>
                            <th>Prix Vente</th>
                            <% if (request.getAttribute("ref") != null) {
                                String refValue = (String) request.getAttribute("ref"); %>
                                <%    if(refValue.equalsIgnoreCase("0")) {%>
                                        <th>Male</th>
                                        <th>Femme</th>
                                    <% } %>
                                    <% if (refValue.equalsIgnoreCase("1") ) { %>
                                        <th>Male</th>
                                    <% } %>
                                    <% if (refValue.equalsIgnoreCase("2") ) { %>
                                        <th>Femme</th>
                            <% } } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Statistique filtre : achats) {
                        %>
                        <tr>
                            <td><%= filtre.getInformationProduit().getStyle().getNomdetailscaracteristique()%></td>
                            <td><%= filtre.getInformationProduit().getCategorie().getNomdetailscaracteristique()%></td>
                            <td><%= filtre.getInformationProduit().getTaille().getNom()%></td>
                            <td><%= filtre.getInformationProduit().getVolume()%></td>
                            <td><%= filtre.getInformationProduit().getPrixVente()%></td>
                            <% if(request.getAttribute("ref").equals("0")) {%>
                                <th><%= filtre.getNombre_male()%></th>
                                <th><%= filtre.getNombre_femme()%></th>
                            <% } %>
                            <% if (request.getAttribute("ref").equals("1") ) { %>
                                <th><%= filtre.getNombre_male()%></th>
                            <% } %>
                            <% if (request.getAttribute("ref").equals("2") ) { %>
                                <th><%= filtre.getNombre_femme()%></th>
                            <% } %>

                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </form>  
         <canvas id="myChart" width="400" height="200"></canvas>
            <script src="assets/js/Chart.js"></script>
            <script>
                var ctx = document.getElementById('myChart').getContext('2d');

                // Récupérer les données de la liste 'produits' et les transformer en tableau JavaScript
                var nombreData = [];
                var produitsData = []; // Ajout de la variable pour stocker les données des produits

                <% if (request.getAttribute("ref") != null) {
                    String refValue = (String) request.getAttribute("ref");
                    for (Statistique nombre : achats) { %>
                        <% if(refValue.equalsIgnoreCase("0")) { %>
                            nombreData.push([<%= nombre.getNombre_male() %>, <%= nombre.getNombre_femme() %>]);
                        <% } else if (refValue.equalsIgnoreCase("1")) { %>
                            nombreData.push([<%= nombre.getNombre_male() %>]);
                        <% } else if (refValue.equalsIgnoreCase("2")) { %>
                            nombreData.push([<%= nombre.getNombre_femme() %>]);
                        <% }
                    }
                } %>

                <% for (Statistique produit : achats) { %>
                    produitsData.push("<%= produit.getInformationProduit().getId() %>: <%= produit.getInformationProduit().getStyle().getNomdetailscaracteristique() %> - <%= produit.getInformationProduit().getCategorie().getNomdetailscaracteristique() %> - <%= produit.getInformationProduit().getTaille().getNom() %>");
                <% } %>

                var myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: produitsData,
                        datasets: [{
                            label: 'Nombre Male',
                            data: nombreData.map(data => data[0]), // Première valeur dans chaque paire [nombre_male, nombre_femme]
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        }, {
                            label: 'Nombre Femme',
                            data: nombreData.map(data => data[1]), // Deuxième valeur dans chaque paire [nombre_male, nombre_femme]
                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            </script>


        </div>
    </body>

    </html>