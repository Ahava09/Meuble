package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Unite {
    
    public int id; 
    public String nom;
    public int famille;
    public double convertion;
	

    // Constructeur par défaut
    public Unite() {
    }

    // Constructeur avec paramètres
    public Unite(int id, String nom, int famille, double convertion) {
        // Initialiser les attributs avec les paramètres
        this.setId(id); 
	this.setNom(nom);
        this.setFamille(famille); 
	this.setConvertion(convertion);
        
	
    }

    // Getters
    public int getId() { return id; } 
	public String getNom() { return nom; } 
	

    // Setters
    public void setId(int id) {this.id= id; }
	public void setNom(String nom) {this.nom = nom; }   

    public int getFamille() {
        return famille;
    }

    public void setFamille(int famille) {
        this.famille = famille;
    }

    public double getConvertion() {
        return convertion;
    }

    public void setConvertion(double convertion) {
        this.convertion = convertion;
    }
    
    
	

    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into Unite (nom, famille, convertion) values ('"+getNom()+"', "+getFamille()+", "+getConvertion()+")";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Unite> getAll (Connection connect) throws SQLException {
        
        ArrayList<Unite> allUnites = new ArrayList<Unite>();
        
        String query = "select * from Unite";
        Statement stat = connect.createStatement();
        System.out.println("model.Unite.getAll() "+query);
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Unite myUnite = new Unite();
            myUnite.setId(set.getInt("id")); 
            myUnite.setNom(set.getString("nom")); 
            myUnite.setFamille(set.getInt("famille")); 
            myUnite.setConvertion(set.getDouble("convertion"));
	
            allUnites.add(myUnite);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allUnites;
    }
    
    
    public Unite getById (Connection connect, int id) throws SQLException {
        Unite myUnite = new Unite();
        
        String query = "select * from Unite where id = "+id;
        Statement stat = connect.createStatement();
        System.out.println("model.Unite.getAll() "+query);
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            myUnite.setId(set.getInt("id")); 
            myUnite.setNom(set.getString("nom")); 
            myUnite.setFamille(set.getInt("famille")); 
            myUnite.setConvertion(set.getDouble("convertion"));
        }
           
//        set.close();
//        stat.close();
//        connect.close();
        return myUnite;
    }
    
    public ArrayList<Unite> getById (Connection connect,ArrayList<Matiere> matieres ) throws SQLException {
        ArrayList<Unite> unites = new ArrayList<Unite>();
        
        for (int i = 0; i < matieres.size(); i++) {
            Unite unite = getById(connect, matieres.get(i).getIdUnite());
            unites.add(unite);
        }
        return unites;
    }
    
    public boolean check(Connection connection, int id1, int id2) throws SQLException {
        Unite unite1 = new Unite().getById(connection, id1);
        Unite unite2 = new Unite().getById(connection, id2);
        
        if (unite1.getFamille() == unite2.getFamille()) return true;
        return false;
    } 
}


