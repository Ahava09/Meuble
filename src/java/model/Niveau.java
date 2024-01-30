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
public class Niveau {
    private int id;
    private String nom;
    private int dureemin;
    private int dureemax;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(String id) {
        this.setId(Integer.valueOf(id));
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDureemin() {
        return dureemin;
    }

    public void setDureemin(int dureemin) {
        this.dureemin = dureemin;
    }

    public int getDureemax() {
        return dureemax;
    }

    public void setDureemax(int dureemax) {
        this.dureemax = dureemax;
    }

    public void setDureemax(String dureemax) {
        this.setDureemax(Integer.valueOf(dureemax));
    }

    public void setDureemin(String dureemin) {
        this.setDureemin(Integer.valueOf(dureemin));
    }

    public Niveau() {
    }

    public Niveau(String nom, String dureemin, String dureemax) {
        this.setNom(nom);
        this.setDureemin(dureemin);
        this.setDureemax(dureemax);
    }

    public Niveau(String id,String nom, String dureemin, String dureemax) {
        this.setId(id);
        this.setNom(nom);
        this.setDureemin(dureemin);
        this.setDureemax(dureemax);
    }
    
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into niveau (nom,dureemin,dureemax) values ('"+getNom()+"', "+getDureemin()+", "+getDureemax()+")";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    public void update (Connection connect) throws SQLException, Exception {
        String sql = "update niveau set nom ='"+getNom()+"' , dureemin="+getDureemin()+" , dureemax="+getDureemax()+" where id ="+getId();
        Statement stat = connect.createStatement();
        System.out.println("*********** "+sql);
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Niveau> getAll (Connection connect) throws SQLException {
        
        ArrayList<Niveau> allNiveau = new ArrayList<Niveau>();
        
        String query = "select * from niveau";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Niveau myNiveau = new Niveau();
            myNiveau.setId(set.getInt("id")); 
            myNiveau.setNom(set.getString("nom")); 
            myNiveau.setDureemin(set.getInt("dureemin")); 
            myNiveau.setDureemax(set.getInt("dureemax")); 
	
            allNiveau.add(myNiveau);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allNiveau;
    }
    
}
