<%@page import="model.Poste"%>
<%@page import="model.Matiere"%>
<%@page import="model.DetailsCaracteristique"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Matiere> myMatieres =(ArrayList<Matiere>) request.getAttribute("matieres");
    ArrayList<DetailsCaracteristique> myStyles = (ArrayList<DetailsCaracteristique> ) request.getAttribute("styles");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ajouter MatiereStyle</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1>Ajouter une matièreStyle</h1>
        <div class="row">
            <div class="col-md-6">
                <form action="MatiereStyleServlet" method="post">
                    <div class="form-group">
                        <label for="styleSelect">Sélectionner un style :</label>
                        <select class="form-control" id="styleSelect" name="style">
                            <% for(DetailsCaracteristique s : myStyles) { %>
                                <option value="<%= s.getIddetailscaracteristique()%>"><%= s.getNomdetailscaracteristique()%></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="matiereCheck">Sélectionner une matière :</label>
                            <% for(Matiere m : myMatieres) { %>
                            <p><input type="checkbox" name="check[]" value="<%= m.getId()%>" > <%= m.getNom()%></p>
                            <% } %>
                    </div>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                    
                </form>
            </div>
        </div>
                    
        <h1>Ajouter une posteproduit</h1>
        <div class="row">
            <div class="col-md-6">
                <form action="PosteStyleServlet" method="post">
                    <div class="form-group">
                        <label for="styleSelect">Sélectionner un style :</label>
                        <select class="form-control" id="styleSelect" name="style">
                            <% for(DetailsCaracteristique s : myStyles) { %>
                                <option value="<%= s.getIddetailscaracteristique()%>"><%= s.getNomdetailscaracteristique()%></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="matiereCheck">Sélectionner une poste :</label>
                        <% for(Poste p : (ArrayList<Poste>)request.getAttribute("postes")) { %>
                            <p><input type="checkbox" name="check[]" value="<%= p.getId()%>" > <%= p.getNom()%></p>
                            <% } %>
                    </div>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                    
                </form>
            </div>
        </div>
    </div>
    <!-- Include Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
