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
public class EtatStock {
    private Matiere matiere;
    private double quantite;
    private String unite;

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public void setMatiere(int idmatiere, Connection connection) throws SQLException {
        this.setMatiere(new Matiere().getbyId(connection, idmatiere));
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    
    //getAll
    public ArrayList<EtatStock> getAll (Connection connect) throws SQLException {
        
        ArrayList<EtatStock> allEtatStocks = new ArrayList<EtatStock>();
        
        String query = "select * from EtatStock";
        System.out.println("model.EtatStock.getAll() "+query);
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {        
            EtatStock etat = new EtatStock();

            etat.setMatiere(set.getInt("idmatiere"),connect); 
            etat.setQuantite(set.getDouble("quantite")); 
            etat.setUnite(set.getString("nom")); 
	
            allEtatStocks.add(etat);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allEtatStocks;
    }
    
    public ArrayList<EtatStock> getByid (Connection connect, int id) throws SQLException {
        
        ArrayList<EtatStock> allEtatStocks = new ArrayList<EtatStock>();
        
        String query = "select * from etatstock where idMatiere = "+id;
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {        
            EtatStock etat = new EtatStock();

            etat.setMatiere(set.getInt("idmatiere"),connect); 
            etat.setQuantite(set.getDouble("quantite")); 
            etat.setUnite(set.getString("nom")); 
	
            allEtatStocks.add(etat);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allEtatStocks;
    }
    
    public boolean check (double quantite) throws Exception {
//        System.out.println("min: "+this.getVolumemin()+" max: "+ this.getVoulumemax()+" volume: "+volume);
        if ( this.getQuantite() >= quantite) {
            return true;
        } 
        throw new Exception("Stock insufissant"); 
    }
    
}
