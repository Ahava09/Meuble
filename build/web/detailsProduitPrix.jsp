<%-- 
    Document   : filtre
    Created on : 3 janv. 2024, 12:57:15
    Author     : itu
--%>
<%@page import="model.DetailsProduitPrix"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
    
    ArrayList<DetailsProduitPrix> list = (ArrayList<DetailsProduitPrix>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="CaracteristiqueDataServlet" role="button">AddCaracteristiques</a></li>
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="MatiereStyleServlet" role="button">AddMatiereStyle</a>
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="ProduitServlet" role="button">AddQuantiteMatiereStyle</a></li>
                                <li > <a class="btn btn-lg btn-primary rounded-pill hover-top" href="InformationProduitServlet" role="button">Informations Produits</a></li>
                            </ul>

                        </div>

                    </div>
                    </div>
                </nav>

                <section class="py-0" id="home">
                    <div class="bg-holder" style="background-image:url(./assets/img/illustrations/hero-bg.png);background-position:bottom;background-size:cover;">
                    </div>
                    <!--/.bg-holder-->

                    <div class="container position-relative">
                        <div class="row align-items-center py-8">
                            <div class="col-md-5 col-lg-6 order-md-1 text-center text-md-end"><img class="img-fluid" src="./assets/img/illustrations/mobile.png" width="350" alt="" /></div>
                            <div class="col-md-7 col-lg-6 text-center text-md-start"><span class="badge bg-light rounded-pill text-dark align-items-center d-flex flex-row-reverse justify-content-end mx-auto mx-md-0 ps-0 w-75 w-sm-50 w-md-75 w-xl-50 mb-3">#1 des meilleurs apks de vente<img class="img-fluid float-start me-3" src="assets/img/illustrations/arrow-right.png" alt=""/></span>
                                <form action="DetailsProduitPrixServlet" method="post">
                               

                                    <input type="text" name="prixMin">                                   
                                    <input type="text" name="prixMax">

                                    <input type="submit" value="Envoyer">
                                </form>

                                <!-- Table -->
                                <table  class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Nom Style</th>
                                            <th>Nom Categorie</th>
                                            <th>Nom Matière</th>
                                            <th>Quantité</th>
                                            <th>Prix Unitaire</th>
                                            <th>Prix Total Matiere </th>
                                            <th>Prix Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (DetailsProduitPrix filtre : list) {
                                        %>
                                        <tr>
                                            <td><%= filtre.getNomStyle()%></td>
                                            <td><%= filtre.getNomCategorie()%></td>
                                            <td><%= filtre.getNomMatiere()%></td>
                                            <td><%= filtre.getQuantite()%></td>
                                            <td><%= filtre.getPrixUnitaireMatiere()%></td>
                                            <td><%= filtre.getPrixMatiere()%></td>
                                            <td><%= filtre.getPrixTotal()%></td>

                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                                <a class="btn btn-lg btn-primary rounded-pill hover-top" href="index.html" role="button">Retour</a>
                                <a class="btn btn-lg btn-primary rounded-pill hover-top" href="DetailsProduitPrixServlet" role="button">Voir les details de prix pour les matieres</a>
                                <a class="btn btn-lg btn-primary rounded-pill hover-top" href="index.html" role="button">Voir les details prix pour les postes </a>
                                <a class="btn btn-lg btn-primary rounded-pill hover-top" href="BeneficeServlet" role="button">Voir les details avec Benefices </a>
                            </div>
                        </div>
                         <a class="btn btn-lg btn-primary rounded-pill hover-top" href="index.html" role="button">Retour</a>
                    </div>
                </section>
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
