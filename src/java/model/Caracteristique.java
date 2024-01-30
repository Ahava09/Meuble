package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.connection.ConnectBD;

public class Caracteristique {
    
    public int id; 
    public String nom; 
	

    // Constructeur par défaut
    public Caracteristique() {
    }

    // Constructeur avec paramètres
    public Caracteristique(int id, String nom) {
        // Initialiser les attributs avec les paramètres
        this.setId(id); 
	this.setNom(nom); 
	
    }

    // Getters
    public int getId() { return id; } 
    public String getNom() { return nom; } 
	

    // Setters
    public void setId(int id) {this.id = id; }
    public void setNom(String nom) {this.nom = nom; }
	

    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into Caracteristique (nom) values ('"+getNom()+"')";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Caracteristique> getAll (Connection connect) throws SQLException {
        
        ArrayList<Caracteristique> allCaracteristiques = new ArrayList<Caracteristique>();
        
        String query = "select * from Caracteristique";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Caracteristique myCaracteristique = new Caracteristique();
            myCaracteristique.setId(set.getInt("id")); 
            myCaracteristique.setNom(set.getString("nom")); 
	
            allCaracteristiques.add(myCaracteristique);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allCaracteristiques;
    }
    
    public Caracteristique getbyId (Connection connect, int id) throws SQLException {
        
        Caracteristique caracteristique = new Caracteristique();
        
        String query = "select * from Caracteristique where id = "+id;
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            caracteristique.setId(set.getInt("id")); 
            caracteristique.setNom(set.getString("nom")); 
        }
           
        set.close();
        stat.close();
        connect.close();
        return caracteristique;
    }
}


