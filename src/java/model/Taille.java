package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Taille {
    
    public int id; 
    public String nom; 
    public double volumemin; 
    public double voulumemax; 
	

    // Constructeur par défaut
    public Taille() {
    }

    // Constructeur avec paramètres
    public Taille(int id, String nom, double volumemin, double voulumemax) {
        // Initialiser les attributs avec les paramètres
        this.setId(id); 
	this.setNom(nom); 
	this.setVolumemin(volumemin); 
	this.setVoulumemax(voulumemax); 
	
    }

    // Getters
    public int getId() { return id; } 
    public String getNom() { return nom; } 
    public double getVolumemin() { return volumemin; } 
    public double getVoulumemax() { return voulumemax; } 
	

    // Setters
    public void setId(int idtaille) {this.id = idtaille; }
    public void setNom(String nomtaille) {this.nom = nomtaille; }
    public void setVolumemin(double volumemin) {this.volumemin = volumemin; }
    public void setVoulumemax(double voulumemax) {this.voulumemax = voulumemax; }
	

    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into Tailles (nomtaille, volumemin, voulumemax) values ('"+getNom()+"', "+getVolumemin()+", "+getVoulumemax()+")";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Taille> getAll (Connection connect) throws SQLException {
        
        ArrayList<Taille> allTailles = new ArrayList<Taille>();
        
        String query = "select * from Taille";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Taille myTaille = new Taille();
            myTaille.setId(set.getInt("id")); 
            myTaille.setNom(set.getString("nom")); 
            myTaille.setVolumemin(set.getDouble("volumemin")); 
            myTaille.setVoulumemax(set.getDouble("voulumemax")); 
	
            allTailles.add(myTaille);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allTailles;
    }
    
    public Taille getByid (Connection connect, int id) throws SQLException {
        
        Taille taille = new Taille();
        
        String query = "select * from Taille where id = "+id;
        Statement stat = connect.createStatement();
        System.out.print(query);
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            taille.setId(set.getInt("id")); 
            taille.setNom(set.getString("nom")); 
            taille.setVolumemin(set.getDouble("volumemin")); 
            taille.setVoulumemax(set.getDouble("voulumemax")); 
        }
           
        set.close();
        stat.close();
//        connect.close();
        return taille;
    }
    
    public boolean check (double volume) {
        System.out.println("min: "+this.getVolumemin()+" max: "+ this.getVoulumemax()+" volume: "+volume);
        if ( this.getVolumemin() <= volume && this.getVoulumemax() >= volume) {
            return true;
        } 
        return false; 
    }
}


