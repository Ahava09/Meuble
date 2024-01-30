<%@page import="model.Client"%>
<%@page import="model.Niveau"%>
<%@page import="model.Poste"%>
<%@page import="model.Unite"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Matiere"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Client> myclients = (ArrayList<Client>) request.getAttribute("clients");
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
            <h2>All Clients</h2>
            <form   action="ClientServlet" method="post">
                <input type="text" placeholder="Nom" name="nom">
                <input type="text" placeholder="Prenom" name="prenom">
                <input type="text" placeholder="Email" name="mail">
                <select name="genre">
                    <option value="1">Male</option>
                    <option value="2">Female</option>

                </select>

                <input type="submit" value="Executer">
            </form>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Prenom</th>
                            <th>Mail</th>
                            <th>Genre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < myclients.size(); i++) {
                        %>
                        <tr>
                            <td><%= myclients.get(i).getId()%></td>
                            <td><%= myclients.get(i).getNom()%></td>
                            <td><%= myclients.get(i).getPrenom()%></td>
                            <td><%= myclients.get(i).getMail()%></td>
                            <% if (myclients.get(i).getGenre() == 1) {%>
                            <td>Male</td>

                            <% } else {%>
                            <td>Female</td>
                            <% } %>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
