/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author itu
 */
public class DetailsProduitPoste {
    private int idInformationProduit;
    private Poste poste;
    private int nombre =0;
    private double duree=0;
    private double salairePoste=0;
    private double prixDureeTotalPoste=0;
    private double prixTotal=0;

    public int getIdInformationProduit() {
        return idInformationProduit;
    }

    public void setIdInformationProduit(int idinformationproduit) {
        this.idInformationProduit = idinformationproduit;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public void setPoste(int idposte,Connection connection) throws SQLException {
        this.setPoste(new Poste().getByid(connection, idposte));
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public double getPrixDureeTotalPoste() {
        return prixDureeTotalPoste;
    }

    public void setPrixDureeTotalPoste(double prixDureeTotalPoste) {
        this.prixDureeTotalPoste = prixDureeTotalPoste;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public double getSalairePoste() {
        return salairePoste;
    }

    public void setSalairePoste(double salairePoste) {
        this.salairePoste = salairePoste;
    }
    
    
    public ArrayList<DetailsProduitPoste> getAll(Connection connect) throws SQLException {
        ArrayList<DetailsProduitPoste> resultList = new ArrayList<DetailsProduitPoste>();
        String sql = "SELECT * FROM DetailsProduitPoste ";
//        try {
         System.out.println("-------- "+sql);
        Statement stat = connect.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                DetailsProduitPoste votreObjet = new DetailsProduitPoste();
                votreObjet.setIdInformationProduit(resultSet.getInt("idinformationproduit"));
                votreObjet.setPoste(resultSet.getInt("idposte"),connect);
                votreObjet.setDuree(resultSet.getDouble("duree"));
                votreObjet.setNombre(resultSet.getInt("nombre"));
                votreObjet.setPrixDureeTotalPoste(resultSet.getDouble("prixDureeTotalPoste"));
                votreObjet.setSalairePoste(resultSet.getDouble("salairePoste"));
                votreObjet.setPrixTotal(resultSet.getDouble("prixTotal"));


                resultList.add(votreObjet);
            }
//        } catch (Exception e) {
//            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans un vrai projet
//        }
        stat.close();
        connect.close();
        return resultList;
    }
    public DetailsProduitPoste getByIdInfromationProduit(Connection connect, int idInformationProduit) throws SQLException {
        String sql = "SELECT * FROM DetailsProduitPoste where idInformationProduit="+idInformationProduit;
//        try {
        DetailsProduitPoste votreObjet = new DetailsProduitPoste();
         System.out.println("-------- "+sql);
        Statement stat = connect.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                votreObjet.setIdInformationProduit(resultSet.getInt("idinformationproduit"));
                votreObjet.setPoste(resultSet.getInt("idposte"),connect);
                votreObjet.setDuree(resultSet.getDouble("duree"));
                votreObjet.setNombre(resultSet.getInt("nombre"));
                votreObjet.setPrixDureeTotalPoste(resultSet.getDouble("prixDureeTotalPoste"));
                votreObjet.setSalairePoste(resultSet.getDouble("salairePoste"));
                votreObjet.setPrixTotal(resultSet.getDouble("prixTotal"));
                
            }
//        } catch (Exception e) {
//            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans un vrai projet
//        }
        stat.close();
//        connect.close();
        return votreObjet;
    }
}
