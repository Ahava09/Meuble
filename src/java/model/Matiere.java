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
public class Matiere {
    public int id; 
    public String nom; 
    public int idUnite; 
	

    // Constructeur par défaut
    public Matiere() {
    }

    // Constructeur avec paramètres
    public Matiere(int id, String nom,String idUnite) {
        // Initialiser les attributs avec les paramètres
        this.setId(id); 
	this.setNom(nom); 
	this.setIdUnite(idUnite); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = Integer.valueOf(idUnite);
    }
    
    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }
    
    
    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into Matiere (nom,idunite) values ('"+getNom()+"', "+getIdUnite()+")";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Matiere> getAll (Connection connect) throws SQLException {
        
        ArrayList<Matiere> allMatieres = new ArrayList<Matiere>();
        
        String query = "select * from Matiere";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Matiere myMatiere = new Matiere();
            myMatiere.setId(set.getInt("id")); 
            myMatiere.setNom(set.getString("nom")); 
            myMatiere.setIdUnite(set.getString("idUnite")); 
	
            allMatieres.add(myMatiere);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allMatieres;
    }
    
    public Matiere getbyId (Connection connect, int id) throws SQLException {
        
        Matiere matiere = new Matiere();
        
        String query = "select * from Matiere where id = "+id;
        System.out.println(query);
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            matiere.setId(set.getInt("id")); 
            matiere.setNom(set.getString("nom")); 
            matiere.setIdUnite(set.getString("idUnite")); 
        }
           
        set.close();
        stat.close();
//        connect.close();
        return matiere;
    }
    
//       public Matiere getLast (Connection connect, String id) throws SQLException {
//        
//        Caracteristique caracteristique = new Caracteristique();
//        
//        String query = "select * from Caracteristique where nomCaracteristique = "+id;
//        Statement stat = connect.createStatement();
//        
//        ResultSet set = stat.executeQuery(query);
//        while(set.next()) {
//            caracteristique.setIdcaracteristique(set.getInt("idcaracteristique")); 
//            caracteristique.setNomcaracteristique(set.getString("nomcaracteristique")); 
//        }
//           
//        set.close();
//        stat.close();
//        connect.close();
//        return caracteristique;
//    }
}
