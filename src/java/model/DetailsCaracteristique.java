package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.connection.ConnectBD;

public class DetailsCaracteristique {
    
    public int iddetailscaracteristique;     
    public int idcaracteristique; 
    public String nomdetailscaracteristique; 
    public String nomcaracteristique; 
	

    // Constructeur par défaut
    public DetailsCaracteristique() {
    }

    public int getIddetailscaracteristique() {
        return iddetailscaracteristique;
    }

    public void setIddetailscaracteristique(int iddetailscaracteristique) {
        this.iddetailscaracteristique = iddetailscaracteristique;
    }

    public int getIdcaracteristique() {
        return idcaracteristique;
    }

    public void setIdcaracteristique(int idcaracteristique) {
        this.idcaracteristique = idcaracteristique;
    }
    public void setIdcaracteristique(String idcaracteristique) {
        this.idcaracteristique = Integer.valueOf(idcaracteristique);
    }

    public String getNomdetailscaracteristique() {
        return nomdetailscaracteristique;
    }

    public void setNomdetailscaracteristique(String nomdetailscaracteristique) {
        this.nomdetailscaracteristique = nomdetailscaracteristique;
    }

    public String getNomcaracteristique() {
        return nomcaracteristique;
    }

    public void setNomcaracteristique(String nomcaracteristique) {
        this.nomcaracteristique = nomcaracteristique;
    }

    

    //insert
    public void insert (Connection connect) throws SQLException, Exception {
            String sql = "insert into detailsCaractestique (nom, idcaracteristique) values ('"+getNomdetailscaracteristique()+"', "+getIdcaracteristique()+")";
        System.out.println(sql);
        Statement stat = connect.createStatement();
        System.out.println(sql);
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<DetailsCaracteristique> getAll (Connection connect) throws SQLException {
        
        ArrayList<DetailsCaracteristique> allDetailscaractestiques = new ArrayList<DetailsCaracteristique>();
        
        String query = "select d.id iddetailscaracteristique, d.nom nomdetailscaracteristique, c.id idcaracteristique, c.nom nomcaracteristique from detailsCaractestique d join caracteristique c on c.id=d.idcaracteristique";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            DetailsCaracteristique myDetailscaractestique = new DetailsCaracteristique();
            myDetailscaractestique.setIddetailscaracteristique(set.getInt("iddetailscaracteristique")); 
            myDetailscaractestique.setNomdetailscaracteristique(set.getString("nomdetailscaracteristique")); 
            myDetailscaractestique.setIdcaracteristique(set.getInt("idcaracteristique")); 
            myDetailscaractestique.setNomcaracteristique(set.getString("nomcaracteristique")); 
	
            allDetailscaractestiques.add(myDetailscaractestique);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allDetailscaractestiques;
    }
    
    public ArrayList<DetailsCaracteristique> getAllWithWhere (Connection connect, int idcateristique) throws SQLException { 
        
        ArrayList<DetailsCaracteristique> allDetailscaractestiques = new ArrayList<DetailsCaracteristique>();
        
        String query = "select d.id iddetailscaracteristique, d.nom nomdetailscaracteristique, c.id idcaracteristique, c.nom nomcaracteristique from detailsCaractestique d join caracteristique c on c.id=d.idcaracteristique where c.id = "+idcateristique;
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            DetailsCaracteristique myDetailscaractestique = new DetailsCaracteristique();
            myDetailscaractestique.setIddetailscaracteristique(set.getInt("iddetailscaracteristique")); 
            myDetailscaractestique.setNomdetailscaracteristique(set.getString("nomdetailscaracteristique")); 
            myDetailscaractestique.setIdcaracteristique(set.getInt("idcaracteristique")); 
            myDetailscaractestique.setNomcaracteristique(set.getString("nomcaracteristique")); 
	
            allDetailscaractestiques.add(myDetailscaractestique);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allDetailscaractestiques;
    }
    
    public DetailsCaracteristique getByid (Connection connect, int id) throws SQLException { 
        
        DetailsCaracteristique detailscaractestique = new DetailsCaracteristique();
        
        String query = "select d.id iddetailscaracteristique, d.nom nomdetailscaracteristique, c.id idcaracteristique, c.nom nomcaracteristique from Detailscaractestique d join caracteristique c on c.id=d.idcaracteristique  where d.id = "+id;
        
        System.out.println("-----------------"+query);
        Statement stat = connect.createStatement();
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            detailscaractestique.setIddetailscaracteristique(set.getInt("iddetailscaracteristique")); 
            detailscaractestique.setNomdetailscaracteristique(set.getString("nomdetailscaracteristique")); 
            detailscaractestique.setIdcaracteristique(set.getInt("idcaracteristique")); 
            detailscaractestique.setNomcaracteristique(set.getString("nomcaracteristique")); 
        }
           
//        set.close();
//        stat.close();
//        connect.close();
        return detailscaractestique;
    }
        public DetailsCaracteristique getLast (Connection connect, String id) throws SQLException {
        
        DetailsCaracteristique detailscaractestique = new DetailsCaracteristique();
        
        String query = "select * from Detailscaractestique where nomDetailscaractestique = '"+id+"'";
        Statement stat = connect.createStatement();
            System.out.println("model.Detailscaractestique.getLast() "+ query);
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            detailscaractestique.setIddetailscaracteristique(set.getInt("iddetailscaracteristique")); 
            detailscaractestique.setNomdetailscaracteristique(set.getString("nomdetailscaracteristique")); 
            detailscaractestique.setIdcaracteristique(set.getInt("idcaracteristique")); 
            detailscaractestique.setNomcaracteristique(set.getString("nomcaracteristique")); 
        }
           
        set.close();
        stat.close();
        connect.close();
        return detailscaractestique;
    }
    
}


