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
public class DetailsProduit {
    private int idMatiereStyle;
    private int idMatiere;
    private String nomMatiere;
    private int idCategorie;
    private String nomCategorie;
    private int idStyle;
    private String nomStyle;
    private double quantite;
    private String taille;
    private int idTaille;
    private String nomUnite;
    private double prixvente;

    public DetailsProduit() {
    }

    public int getIdMatiereStyle() {
        return idMatiereStyle;
    }

    public void setIdMatiereStyle(int idMatiereStyle) {
        this.idMatiereStyle = idMatiereStyle;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    public String getNomStyle() {
        return nomStyle;
    }

    public void setNomStyle(String nomStyle) {
        this.nomStyle = nomStyle;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(int idTaille) {
        this.idTaille = idTaille;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public double getPrixvente() {
        return prixvente;
    }

    public void setPrixvente(double prixvente) {
        this.prixvente = prixvente;
    }
    //getAll
    public ArrayList<DetailsProduit> getAll (Connection connect) throws SQLException {
        
        ArrayList<DetailsProduit> allDeatailsProduits = new ArrayList<DetailsProduit>();
        
        String query = "select * from DetailsProduit ";
        Statement stat = connect.createStatement();
        ResultSet resultSet = stat.executeQuery(query);
            while (resultSet.next()) {
                DetailsProduit votreObjet = new DetailsProduit();
                votreObjet.setIdMatiereStyle(resultSet.getInt("idMatiereStyle"));
                votreObjet.setNomMatiere(resultSet.getString("nomMatiere"));
                votreObjet.setNomStyle(resultSet.getString("nomStyle"));
                votreObjet.setIdStyle(resultSet.getInt("idStyle"));
                votreObjet.setIdMatiere(resultSet.getInt("idMatiere"));
                votreObjet.setQuantite(resultSet.getDouble("quantite"));
                votreObjet.setIdCategorie(resultSet.getInt("idCategorie"));
                votreObjet.setTaille(resultSet.getString("nomTaille"));
                votreObjet.setIdTaille(resultSet.getInt("idTaille"));
                votreObjet.setNomUnite(resultSet.getString("nomUnite"));
                votreObjet.setNomCategorie(resultSet.getString("nomCategorie"));
                votreObjet.setPrixvente(resultSet.getDouble("prixvente"));


                allDeatailsProduits.add(votreObjet);
            }
//        } catch (Exception e) {
//            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans un vrai projet
//        }
        stat.close();
        connect.close();
        return allDeatailsProduits;
    }
    
    public String getRequete (String[] name, String[] value  ) {
        String where = " Where ";
        for (int i=0; i<name.length; i++) {
            
            if (!value[i].equalsIgnoreCase("0")) where += name[i]+" = "+value[i];
            if (i+1<name.length) if (!value[i+1].equalsIgnoreCase("0")) where += " and ";
        }
        return where;
    }
    
    public ArrayList<DetailsProduit> getAllWithWhere (Connection connect,String[] name, String[] value) throws SQLException {
        ArrayList<DetailsProduit> resultList = new ArrayList<DetailsProduit>();
        String sql = "SELECT * FROM detailsProduit "+getRequete(name, value);
//        try {
        Statement stat = connect.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                DetailsProduit votreObjet = new DetailsProduit();
                votreObjet.setIdMatiereStyle(resultSet.getInt("idMatiereStyle"));
                votreObjet.setNomMatiere(resultSet.getString("nomMatiere"));
                votreObjet.setNomStyle(resultSet.getString("nomStyle"));
                votreObjet.setIdStyle(resultSet.getInt("idStyle"));
                votreObjet.setIdMatiere(resultSet.getInt("idMatiere"));
                votreObjet.setQuantite(resultSet.getDouble("quantite"));
                votreObjet.setIdCategorie(resultSet.getInt("idCategorie"));
                votreObjet.setTaille(resultSet.getString("nomTaille"));
                votreObjet.setIdTaille(resultSet.getInt("idTaille"));
                votreObjet.setNomUnite(resultSet.getString("nomUnite"));
                votreObjet.setNomCategorie(resultSet.getString("nomCategorie"));
                votreObjet.setPrixvente(resultSet.getDouble("prixvente"));
                resultList.add(votreObjet);
            }
//        } catch (Exception e) {
//            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans un vrai projet
//        }
        stat.close();
        connect.close();
        return resultList;
    }
    
    
    
}
