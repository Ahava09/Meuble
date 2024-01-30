
<%@page import="model.DetailsCaracteristique"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Caracteristique"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Caracteristique> caracteristiques = (ArrayList<Caracteristique>)request.getAttribute("caracteristiques");
    ArrayList<DetailsCaracteristique> detailscaracteristique = (ArrayList<DetailsCaracteristique>)request.getAttribute("detailscaracteristique");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Caracteristiques</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- Styles Bootstrap -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- ===============================================-->
        <!--    Document Title-->
        <!-- ===============================================-->
        <title>Selection</title>


        <!-- ===============================================-->
        <!--    Favicons-->
        <!-- ===============================================-->
        <link rel="apple-touch-icon" sizes="180x180" href="assets/img/favicons/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="assets/img/favicons/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="assets/img/favicons/favicon-16x16.png">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicons/favicon.ico">
        <link rel="manifest" href="assets/img/favicons/manifest.json">
        <meta name="msapplication-TileImage" content="assets/img/favicons/mstile-150x150.png">
        <meta name="theme-color" content="#ffffff">


        <!-- ===============================================-->
        <!--    Stylesheets-->
        <!-- ===============================================-->
        <link href="assets/css/theme.css" rel="stylesheet" />
    <style>

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
        <div class="container">
            <main class="main" id="top">
                <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" data-navbar-on-scroll="data-navbar-on-scroll">
                    <div class="container"><a class="navbar-brand d-flex align-items-center fw-bold fs-2" href="index.html">
                            <div class="text-success">Meuble' </div>
                            <div class="text-1000">Deco</div>
                        </a>
                        <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse border-top border-lg-0 mt-4 mt-lg-0" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto pt-2 pt-lg-0">
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="DetailsProduitServlet" role="button">All Details Produit</a> </li>
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="CaracteristiqueServlet" role="button">AddCaracteristiques</a></li>
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="MatiereStyleServlet " role="button">AddMatiereStyle</a>
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="ProduitServlet" role="button">AddQuantiteMatiereStyle</a></li>
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="InformationProduitServlet" role="button">Informations Produits</a></li>
                            </ul>

                        </div>

                    </div>
                    </div>
                </nav>
        <h2>Information/Liste Caracteristiques</h2>
        <div class="table-responsive">
            <div class="row align-items-center py-8">
                <div class="col-md-5 col-lg-6 order-md-1 text-center text-md-end"></div>
                <div class="col-md-7 col-lg-6 text-center text-md-start"><span class="badge bg-light rounded-pill text-dark align-items-center d-flex flex-row-reverse justify-content-end mx-auto mx-md-0 ps-0 w-75 w-sm-50 w-md-75 w-xl-50 mb-3">#1 des meilleurs apks de vente<img class="img-fluid float-start me-3" src="assets/img/illustrations/arrow-right.png" alt=""/></span>

                <form   action="CaracteristiqueServlet" method="post">
                    <input type="text" placeholder="Caracteristique" name="caracteristique" required>
                    <input type="submit" value="Executer">
                </form>
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name Caracteristique</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            for (int i=0; i<caracteristiques.size(); i++) { 
                        %>
                        <tr>
                            <td><%= caracteristiques.get(i).getId()%></td>
                            <td><%= caracteristiques.get(i).getNom()%></td>
                        </tr>
                        <% }  %>
                    </tbody>
                </table>
            </div>

            <h2>Information/Liste Details Caracteristiques</h2>
            <div class="table-responsive">
                <form   action="CaracteristiqueServlet" method="post">
                    <select name="idcaracteristique">
                        <% for (Caracteristique c : caracteristiques) { %>
                            <option value="<%= c.getId() %>"><%= c.getNom() %></option>
                        <% } %>
                    </select>
                    <input type="text" placeholder="Nom" name="detailscaracteristique" required>
                    <input type="submit" value="Executer">
                </form>
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Name Caracteristique</th>
                            <th>Name DetailsCaracteristique</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            for (int k=0; k<detailscaracteristique.size(); k++) { 
                        %>
                        <tr>
                            <td><%= detailscaracteristique.get(k).getNomcaracteristique()%></td>
                            <td><%= detailscaracteristique.get(k).getNomdetailscaracteristique()%></td>
                        </tr>
                        <% }  %>
                    </tbody>
                </table>
            </div>
            </div>
        </div>
                    
                                <a class="btn btn-lg btn-primary rounded-pill hover-top" href="index.html" role="button">Retour</a>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

                <section class="py-0" id="home">
                    <div class="bg-holder" style="background-image:url(./assets/img/illustrations/hero-bg.png);background-position:bottom;background-size:cover;">
                    </div>

                    <!--/.bg-holder-->

                    <div class="container position-relative">

                    </div>
                    <!-- end of .container-->

                </section>
                <!-- <section> close ============================-->
                <!-- ============================================-->


            </main>
    </body>
</html>
