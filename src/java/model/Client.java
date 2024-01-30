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
 * @author andry
 */
public class Client {
    int id;
    String nom;
    String prenom;
    String mail;
    int genre;

    public Client(int id, String nom, String prenom, String mail, int genre) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.genre = genre;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Client() {
    }
    
    public void insert(Connection connect) throws SQLException {
        String sql = "insert into client(nom, prenom, mail, idgenre) values ('"+getNom()+"', '"+getPrenom()+"', '"+getMail()+"', "+getGenre()+" )";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    public ArrayList<Client> getAll (Connection connect) throws SQLException {
        
        ArrayList<Client> allNiveau = new ArrayList<Client>();
        
        String query = "select * from Client";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Client myClient = new Client();
            myClient.setId(set.getInt("id")); 
            myClient.setNom(set.getString("nom")); 
            myClient.setPrenom(set.getString("prenom")); 
            myClient.setMail(set.getString("mail")); 
            myClient.setGenre(set.getInt("idgenre")); 

	
            allNiveau.add(myClient);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allNiveau;
    }
    
    public Client getById (Connection connect,int id) throws SQLException {
        
        String query = "select * from Client where id = "+id;
        Statement stat = connect.createStatement();
            Client myClient = new Client();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            myClient.setId(set.getInt("id")); 
            myClient.setNom(set.getString("nom")); 
            myClient.setPrenom(set.getString("prenom")); 
            myClient.setMail(set.getString("mail")); 
            myClient.setGenre(set.getInt("idgenre")); 
        }
           
        set.close();
        stat.close();
        connect.close();
        return myClient;
    }
}
