<%@page import="model.InformationProduit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<InformationProduit> produits = (ArrayList<InformationProduit>) request.getAttribute("produits");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <canvas id="myChart" width="400" height="200"></canvas>

    <script src="assets/js/Chart.js"></script>
    <script>
        var ctx = document.getElementById('myChart').getContext('2d');
        
        // Récupérer les données de la liste 'produits' et les transformer en tableau JavaScript
        var produitsData = [];
        <%
            for (InformationProduit produit : produits) {
        %>
            produitsData.push(<%= produit.getVolume() %>); // Assurez-vous d'ajuster la méthode pour récupérer la valeur appropriée
        <%
            }
        %>

        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: [<%= produits.get(0).getId() %>, 'P2', 'P3', 'P4', 'P5', 'P6'], // Assurez-vous d'ajuster ces labels en fonction de vos données
                datasets: [{
                    label: 'Number of Votes',
                    data: produitsData,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
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
</body>
</html>
