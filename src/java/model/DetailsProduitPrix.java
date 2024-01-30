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
public class DetailsProduitPrix {
    private int idInformationProduit;
    private int idMatiere;
    private String nomMatiere;
    private String nomStyle;
    private String nomCategorie;
    private double quantite=0;
    private double prixUnitaireMatiere=0;
    private double prixMatiere=0;
    private double prixTotal=0;

    public DetailsProduitPrix() {
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getNomStyle() {
        return nomStyle;
    }

    public void setNomStyle(String nomStyle) {
        this.nomStyle = nomStyle;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaireMatiere() {
        return prixUnitaireMatiere;
    }

    public void setPrixUnitaireMatiere(double prixUnitaireMatiere) {
        this.prixUnitaireMatiere = prixUnitaireMatiere;
    }

    public double getPrixMatiere() {
        return prixMatiere;
    }

    public void setPrixMatiere(double prixMatiere) {
        this.prixMatiere = prixMatiere;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getIdInformationProduit() {
        return idInformationProduit;
    }

    public void setIdInformationProduit(int id) {
        this.idInformationProduit = id;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }
    
    public ArrayList<DetailsProduitPrix> getAll(Connection connect, double pmin, double prixmax) throws SQLException {
        ArrayList<DetailsProduitPrix> resultList = new ArrayList<DetailsProduitPrix>();
        String sql = "SELECT * FROM DetailsProduitPrix WHERE prixTotal >= "+pmin+" and prixTotal <=" + prixmax;
//        try {
         System.out.println("-------- "+sql);
        Statement stat = connect.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                DetailsProduitPrix votreObjet = new DetailsProduitPrix();
                votreObjet.setIdInformationProduit(resultSet.getInt("idinformationproduit"));
                votreObjet.setIdMatiere(resultSet.getInt("idMatiere"));
                votreObjet.setNomMatiere(resultSet.getString("nomMatiere"));
                votreObjet.setNomStyle(resultSet.getString("nomStyle"));
                votreObjet.setQuantite(resultSet.getDouble("quantite"));
                votreObjet.setNomCategorie(resultSet.getString("nomCategorie"));
                votreObjet.setPrixMatiere(resultSet.getDouble("prixmatiere"));
                votreObjet.setPrixUnitaireMatiere(resultSet.getDouble("prixUnitaireMatiere"));

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
    
    public ArrayList<DetailsProduitPrix> getAll(Connection connect) throws SQLException {
        ArrayList<DetailsProduitPrix> resultList = new ArrayList<DetailsProduitPrix>();
        String sql = "SELECT * FROM DetailsProduitPrix ";
//        try {
         System.out.println("-------- "+sql);
        Statement stat = connect.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                DetailsProduitPrix votreObjet = new DetailsProduitPrix();
                votreObjet.setIdInformationProduit(resultSet.getInt("idinformationproduit"));
                votreObjet.setNomMatiere(resultSet.getString("nomMatiere"));
                votreObjet.setIdMatiere(resultSet.getInt("idMatiere"));
                votreObjet.setNomStyle(resultSet.getString("nomStyle"));
                votreObjet.setQuantite(resultSet.getDouble("quantite"));
                votreObjet.setNomCategorie(resultSet.getString("nomCategorie"));
                votreObjet.setPrixMatiere(resultSet.getDouble("prixmatiere"));
                votreObjet.setPrixUnitaireMatiere(resultSet.getDouble("prixUnitaireMatiere"));

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
    
    public DetailsProduitPrix getByIdInfromationProduit(Connection connect, int idInformationProduit) throws SQLException {
        String sql = "SELECT * FROM DetailsProduitPrix where idInformationProduit="+idInformationProduit;
//        try {
        DetailsProduitPrix votreObjet = new DetailsProduitPrix();
         System.out.println("-------- "+sql);
        Statement stat = connect.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                votreObjet.setIdInformationProduit(resultSet.getInt("idinformationproduit"));
                votreObjet.setNomMatiere(resultSet.getString("nomMatiere"));
                votreObjet.setIdMatiere(resultSet.getInt("idMatiere"));
                votreObjet.setNomStyle(resultSet.getString("nomStyle"));
                votreObjet.setQuantite(resultSet.getDouble("quantite"));
                votreObjet.setNomCategorie(resultSet.getString("nomCategorie"));
                votreObjet.setPrixMatiere(resultSet.getDouble("prixmatiere"));
                votreObjet.setPrixUnitaireMatiere(resultSet.getDouble("prixUnitaireMatiere"));
                votreObjet.setPrixTotal(resultSet.getDouble("prixTotal"));
                
            }
//        } catch (Exception e) {
//            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans un vrai projet
//        }
        stat.close();
//        connect.close();
        return votreObjet;
    }
    
    
    public ArrayList<DetailsProduitPrix> getAllInfromationProduit(Connection connect, int idInformationProduit) throws SQLException {
        ArrayList<DetailsProduitPrix> resultList = new ArrayList<DetailsProduitPrix>();
        String sql = "SELECT * FROM DetailsProduitPrix where idInformationProduit="+idInformationProduit;
//        try {
         System.out.println("-------- "+sql);
        Statement stat = connect.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                DetailsProduitPrix votreObjet = new DetailsProduitPrix();
                votreObjet.setIdInformationProduit(resultSet.getInt("idinformationproduit"));
                votreObjet.setNomMatiere(resultSet.getString("nomMatiere"));
                votreObjet.setIdMatiere(resultSet.getInt("idMatiere"));
                votreObjet.setNomStyle(resultSet.getString("nomStyle"));
                votreObjet.setQuantite(resultSet.getDouble("quantite"));
                votreObjet.setNomCategorie(resultSet.getString("nomCategorie"));
                votreObjet.setPrixMatiere(resultSet.getDouble("prixmatiere"));
                votreObjet.setPrixUnitaireMatiere(resultSet.getDouble("prixUnitaireMatiere"));
                votreObjet.setPrixTotal(resultSet.getDouble("prixTotal"));


                resultList.add(votreObjet);
            }
//        } catch (Exception e) {
//            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans un vrai projet
//        }
        stat.close();
//        connect.close();
        return resultList;
    }
    
}
