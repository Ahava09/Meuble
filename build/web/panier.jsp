<%-- 
    Document   : panier
    Created on : 30 janv. 2024, 08:41:26
    Author     : itu
--%>

<%@page import="model.Client"%>
<%@page import="model.Statistique"%>
<%@page import="model.Taille"%>
<%@page import="model.DetailsCaracteristique"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<DetailsCaracteristique> myCategories =(ArrayList<DetailsCaracteristique>) request.getAttribute("categories");
    ArrayList<DetailsCaracteristique> myStyles = (ArrayList<DetailsCaracteristique> ) request.getAttribute("styles");
    ArrayList<Taille> myTailles = (ArrayList<Taille> ) request.getAttribute("tailles");
    ArrayList<Client> client =  (ArrayList<Client> ) request.getAttribute("client");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="assets/vendors/flag-icon-css/css/flag-icon.min.css">
        <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <link rel="stylesheet" href="assets/vendors/font-awesome/css/font-awesome.min.css" />
        <link rel="stylesheet" href="assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="assets/css/style.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="assets/images/favicon.png" />
        <title>Achat billet activite</title>
    </head>
    <body>
        <div class="container-scroller">
        <!-- partial:../../partials/_navbar.html -->
        <jsp:include page="nav/nav.jsp" />
        <!-- partial -->
        <div class="container-fluid page-body-wrapper">
        <!-- partial:../../partials/_sidebar.html -->
        <jsp:include page="nav/sidebar.jsp" />
        <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="page-header">
                    <h3 class="page-title"> Achat billet activite</h3>
                </div>
                <div class="row">
                    <div class="col-md-3 grid-margin stretch-card"></div>
                    <div class="col-md-6 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <form class="forms-sample" action="./AchatServlet" method="post">
                                <div class="form-group">
                                    <label for="exampleInputUsername1">Client</label>
                                    <select class="form-control" id="exampleSelectGender" name="client">
                                        <% 
                                            for (int i = 0; i < client.size(); i++) { %>
                                                <option value="<%=client.get(i).getId() %>"><%= client.get(i).getNom() %> <%= client.get(i).getPrenom()%></option>
                                            <% } %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputUsername1">Date</label>
                                    <input type="date" class="form-control" id="exampleInputUsername1" placeholder="" name="datepanier">
                                </div>
                                <div id="activite"> 
                                <div class="row">
                                    <div class="form-group" style="margin-left: 10px; width: 220px">
                                        <label for="exampleInputUsername1">Style</label>
                                        <select class="form-control" id="exampleSelectGender" name="style[]">
                                            <% 
                                                for (int i = 0; i < myStyles.size(); i++) { %>
                                                    <option value="<%=myStyles.get(i).getIddetailscaracteristique()%>"><%=myStyles.get(i).getNomdetailscaracteristique()%></option>
                                                <% } %>
                                        </select>
                                    </div>
                                    <div class="form-group" style="margin-left: 10px; width: 220px">
                                        <label for="exampleInputUsername1">Categorie</label>
                                        <select class="form-control" id="exampleSelectGender" name="categorie[]">
                                            <% 
                                                for(DetailsCaracteristique c : myCategories) { %>
                                                    <option value="<%= c.getIddetailscaracteristique()%>" >></option>
                                                <% } %>
                                        </select>
                                    </div>
                                    <div class="form-group" style="margin-left: 10px; width: 220px">
                                        <label for="exampleInputUsername1">Taille</label>
                                        <select class="form-control" id="exampleSelectGender" name="taille[]">
                                            <% 
                                                for (Taille t : myTailles) { %>
                                                    <option value="<%= t.getId()%>" > <%= t.getNom()%>  min: <%= t.getVolumemin()%> cm3 - <%= t.getVoulumemax()%>  </option>
                                                <% } %>
                                        </select>
                                    </div>
                                    <div class="form-group" style="margin-left: 15px;">
                                        <label for="exampleInputUsername1">Quantite</label>
                                        <input type="text" class="form-control" id="exampleInputUsername1" placeholder="Quantite" name="qty[]">
                                    </div>
                                    <div class="form-group" style="margin-left: 15px;">
                                        <label for="exampleInputUsername1"></label>
                                        <input type="button" class="form-control" id="exampleSelectGender" value="X" onclick="deleteActivite('activite' + divCount)" style="background-color: red; color: #fff;">
                                    </div>   
                                </div>
                                </div>
                                <input type="button" class="btn btn-primary" value="Ajouter" onclick="addActivite()">
                                <button type="submit" class="btn btn-primary mr-2" style="background-color:#44ce42;border-color:#44ce42">Submit</button>
                            </form>
                        </div>
                    </div>
                    </div>
                    <div class="col-md-3 grid-margin stretch-card"></div>
                </div>
            </div>
            
            
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <footer class="footer">
                <div class="footer-inner-wraper">
                    <div class="d-sm-flex justify-content-center justify-content-sm-between">
                        <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © bootstrapdash.com 2020</span>
                        <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center"> Free <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap dashboard templates</a> from Bootstrapdash.com</span>
                    </div>
                </div>
            </footer>
          <!-- partial -->
        </div>
        <!-- main-panel ends -->
        </div>
      <!-- page-body-wrapper ends -->
        </div>
       <!-- plugins:js -->
        <script src="assets/vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <script src="assets/vendors/chart.js/Chart.min.js"></script>
        <script src="assets/vendors/jquery-circle-progress/js/circle-progress.min.js"></script>
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="assets/js/off-canvas.js"></script>
        <script src="assets/js/hoverable-collapse.js"></script>
        <script src="assets/js/misc.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="assets/js/dashboard.js"></script>
        <!-- End custom js for this page -->
        <script>
            function deleteActivite(divId) {
                var divToRemove = document.getElementById(divId);
                if (divToRemove) {
                    divToRemove.parentNode.removeChild(divToRemove);
                }
            }

            function addActivite() {
                var activiteDiv = document.getElementById("activite");

                var newDiv = document.createElement("div");
                var divCount = document.querySelectorAll('#activite .form-group').length + 1; // Unique identifier
                newDiv.classList.add("form-group");

                var rowDiv = document.createElement("div");
                rowDiv.classList.add("row");
                newDiv.appendChild(rowDiv);

                // Adjusted for your specific use case
                var activiteSelectDiv = document.createElement("div");
                activiteSelectDiv.classList.add("form-group");
                var activiteSelect = document.createElement("select");
                activiteSelect.classList.add("form-control");
                activiteSelect.setAttribute("id", "exampleSelectGender");
                activiteSelect.setAttribute("name", "activite[]");
                activiteSelectDiv.style.marginLeft = "10px";
                activiteSelectDiv.style.width = "220px";

                

                activiteSelectDiv.appendChild(activiteSelect);
                rowDiv.appendChild(activiteSelectDiv);


                var qtyInputDiv = document.createElement("div");
                qtyInputDiv.classList.add("form-group");
                var qtyInput = document.createElement("input");
                qtyInput.setAttribute("type", "text");
                qtyInput.classList.add("form-control");
                qtyInput.setAttribute("id", "exampleSelectGender");
                qtyInput.setAttribute("placeholder", "Quantite");
                qtyInput.setAttribute("name", "qty[]");
                qtyInput.style.marginLeft = "15px";
                qtyInput.style.width = "195px";
                qtyInputDiv.appendChild(qtyInput);
                rowDiv.appendChild(qtyInputDiv);

                var deleteButtonDiv = document.createElement("div");
                deleteButtonDiv.classList.add("form-group");
                var deleteButton = document.createElement("input");
                deleteButton.setAttribute("type", "button");
                deleteButton.classList.add("form-control");
                deleteButton.setAttribute("id", "exampleSelectGender");
                deleteButton.setAttribute("value", "X");
                deleteButton.style.marginLeft = "15px";
                deleteButton.style.backgroundColor = "red";
                deleteButton.style.color = "#fff";
                deleteButton.style.width = "53px";
                deleteButton.onclick = function () {
                    deleteActivite('activite' + divCount);
                };
                deleteButtonDiv.appendChild(deleteButton);
                rowDiv.appendChild(deleteButtonDiv);

                activiteDiv.appendChild(newDiv);
            }
        </script>

    </body>
</html>
