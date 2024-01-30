<%@page import="model.Niveau"%>
<%@page import="model.Poste"%>
<%@page import="model.Unite"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Matiere"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Matiere> matieres = (ArrayList<Matiere>)request.getAttribute("matieres");
    ArrayList<Unite> unites = (ArrayList<Unite>)request.getAttribute("unites");
    ArrayList<Unite> listunites = (ArrayList<Unite>)request.getAttribute("listunites");
    ArrayList<Poste> postes = (ArrayList<Poste>)request.getAttribute("postes");
    ArrayList<Niveau> niveau = (ArrayList<Niveau>)request.getAttribute("niveau");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Styles</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
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
        
        select {
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
        .table thead th {
            background-color: #f2f2f2;
        }
        .table {
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .table-striped tbody tr:hover {
            background-color: #f5f5f5;
        }
        .table td, .table th {
            border-top: 1px solid #dee2e6;
        }
        @media (max-width: 767px) {
            .table-responsive {
                width: 100%;
                margin-bottom: 1rem;
                overflow-x: auto;
                -webkit-overflow-scrolling: touch;
                -ms-overflow-style: -ms-autohiding-scrollbar;
                border: 1px solid #dee2e6;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>All Matieres</h2>
        <form   action="MatiereServlet" method="post">
            <input type="text" placeholder="Matieres" name="matiere">
            <select name="unite">
                <% for(int i = 0; i<unites.size(); i++ ) { %>
                <option value="<%= unites.get(i).getId() %>"><%= unites.get(i).getNom() %></option>
                <% } %>
            </select>
            <input type="submit" value="Executer">
        </form>
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Unite</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (int i=0; i<matieres.size(); i++) { 
                    %>
                    <tr>
                        <td><%= matieres.get(i).getId()%></td>
                        <td><%= matieres.get(i).getNom()%></td>
                        <td><%= listunites.get(i).getNom()%></td>
                    </tr>
                    <% }  %>
                </tbody>
            </table>
        </div>
        <h2>Entree Matiere</h2>
        <form   action="EntreeMatiereServlet" method="post">
            <select name="matiere">
                <% for(int k=0; k<matieres.size(); k++) { %>
                <option value="<%= matieres.get(k).getId() %>"><%= matieres.get(k).getNom() %></option>
                <% } %>
            </select>
            <input type="text" name="prix" placeholder="prix"/>
            <input type="text" name="quantite" placeholder="quantite"/>
            <select name="unite">
                <% for(int i = 0; i<unites.size(); i++ ) { %>
                <option value="<%= unites.get(i).getId() %>"><%= unites.get(i).getNom() %></option>
                <% } %>
            </select>
            <input type="datetime-local" name="dateEntree" />
            <input type="submit" value="Executer">
        </form>
            
        
        <h2>Poste</h2>
        <form   action="PosteServlet" method="post">
            <input type="text" name="poste" placeholder="poste"/>
            <input type="text" name="prix" placeholder="prix"/>
            <input type="submit" value="Inserer">
        </form>
        
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Poste</th>
                        <th>Salaire</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (int k=0; k<postes.size(); k++) { 
                    %>
                    <tr>
                        <td><%= postes.get(k).getId()%></td>
                        <td><%= postes.get(k).getNom()%></td>
                        <td><%= postes.get(k).getSalaire()%></td>
                    </tr>
                    <% }  %>
                </tbody>
            </table>
        </div>
                
        <h2>All Niveau</h2>
        <form   action="NiveauServlet" method="post">
            <input type="text" placeholder="Niveau" name="nom">
            <input type="text" placeholder="Duree Min" name="dureemin">
            <input type="text" placeholder="Duree Max" name="dureemax">
            <input type="submit" value="Executer">
        </form>
        <div class="table-responsive">
        <form   action="NiveauServlet" method="get">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Niveau</th>
                        <th>Duree Min</th>
                        <th>Duree Max</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (int i=0; i<niveau.size(); i++) { 
                    %>
                    <form   action="NiveauServlet" method="get">
                        <tr>
                            <td><input type="text" name="id" value="<%= niveau.get(i).getId()%>" placeholder="<%= niveau.get(i).getId()%>"/></td>
                            <td><input type="text" name="nom" value="<%= niveau.get(i).getNom()%>" placeholder="<%= niveau.get(i).getNom()%>"/></td>
                            <td><input type="text" name="dureemin" value="<%= niveau.get(i).getDureemin()%>" placeholder="<%= niveau.get(i).getDureemin()%>"/></td>
                            <td><input type="text" name="dureemax" value="<%= niveau.get(i).getDureemax()%>" placeholder="<%= niveau.get(i).getDureemax()%>"/></td>
                            <td><input type="submit" value="Updater"></td>
                        </tr>
                    </form>
                    <% }  %>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
