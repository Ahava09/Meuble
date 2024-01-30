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
public class Poste {
    private int id;
    private String nom;
    private double salaire;

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

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = Double.valueOf(salaire);
    }

    public Poste() {
    }

    public Poste(String nom, double salaire) {
        this.setNom(nom);
        this.setSalaire(salaire);
    }
    
    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into poste (nom, salaire) values ('"+getNom()+"', "+getSalaire()+")";
        System.out.println("--------------******** "+sql);
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Poste> getAll (Connection connect) throws SQLException {
        
        ArrayList<Poste> allPostes = new ArrayList<Poste>();
        
        String query = "select * from Poste";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Poste myPoste = new Poste();
            myPoste.setId(set.getInt("id")); 
            myPoste.setNom(set.getString("nom")); 
            myPoste.setSalaire(set.getDouble("salaire")); 
	
            allPostes.add(myPoste);
        }
           
        set.close();
        stat.close();
//        connect.close();
        return allPostes;
    }
    
    public Poste getByid (Connection connect, int id) throws SQLException {
        
        Poste poste = new Poste();
        
        String query = "select * from poste where id = "+id;
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            poste.setId(set.getInt("id")); 
            poste.setNom(set.getString("nom")); 
            poste.setSalaire(set.getDouble("salaire")); 
        }
           
        set.close();
        stat.close();
//        connect.close();
        return poste;
    }
    
}
