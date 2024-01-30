package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuantiteMatiereInformationProduit {
    public int id; 
    public int idMatiereStyle; 
    public int idInformationProduit; 
    public double quantite; 
    public int idUnite;
	

    // Constructeur par défaut
    public QuantiteMatiereInformationProduit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMatiereStyle() {
        return idMatiereStyle;
    }

    public void setIdMatiereStyle(int idMatiereStyle) {
        this.idMatiereStyle = idMatiereStyle;
    }

    public int getIdInformationProduit() {
        return idInformationProduit;
    }

    public void setIdInformationProduit(int idInformationProduit) {
        this.idInformationProduit = idInformationProduit;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idunite) {
        this.idUnite = idunite;
    }
    public void setIdUnite(String idunite) {
        this.idUnite = Integer.valueOf(idunite);
    }
        
    public void setIdMatiereStyle(String idmatierestyle) {this.setIdMatiereStyle(Integer.valueOf(idmatierestyle)); }
    public void setIdInformationProduit(String idInformationProduit) {this.idInformationProduit = Integer.valueOf(idInformationProduit); }
    public void setQuantite(String quantite) {this.quantite = Double.valueOf(quantite); }
    public void setIdUnite(Connection connect, String idunite) throws SQLException, Exception {
        Matierestyle ms = new Matierestyle().getById(connect, getIdMatiereStyle());
        if (new Unite().check(connect, ms.getMatiere().getIdUnite(), Integer.valueOf(idunite))) this.idUnite = Integer.valueOf(idunite); 
        else throw new Exception("Incomptatible l'unite");
    }

    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into QuantiteMatiereInformationProduit (idmatierestyle, idinformationproduit, quantite, idunite) values ("+getIdMatiereStyle()+", "+getIdInformationProduit()+", "+getQuantite()+", "+getIdUnite()+")";
        Statement stat = connect.createStatement();
        System.out.println("*********************** "+sql);
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<QuantiteMatiereInformationProduit> getAll (Connection connect) throws SQLException {
        
        ArrayList<QuantiteMatiereInformationProduit> allQuantitematierestyles = new ArrayList<QuantiteMatiereInformationProduit>();
        
        String query = "select * from QuantiteMatiereInformationProduit";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            QuantiteMatiereInformationProduit myQuantitematierestyle = new QuantiteMatiereInformationProduit();
            myQuantitematierestyle.setId(set.getInt("id")); 
            myQuantitematierestyle.setIdMatiereStyle(set.getInt("idmatierestyle")); 
            myQuantitematierestyle.setIdInformationProduit(set.getInt("idinformationproduit")); 
            myQuantitematierestyle.setQuantite(set.getDouble("quantite")); 
            myQuantitematierestyle.setIdUnite(set.getInt("idunite")); 
	
            allQuantitematierestyles.add(myQuantitematierestyle);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allQuantitematierestyles;
    }
    
     public QuantiteMatiereInformationProduit getByInfo (Connection connect, int idInfo) throws SQLException {
        
            QuantiteMatiereInformationProduit myQuantitematierestyle = new QuantiteMatiereInformationProduit();

            String query = "select * from QuantiteMatiereInformationProduit where idinformationproduit = "+idInfo;
            Statement stat = connect.createStatement();

            ResultSet set = stat.executeQuery(query);
            while(set.next()) {
                myQuantitematierestyle.setId(set.getInt("id")); 
                myQuantitematierestyle.setIdMatiereStyle(set.getInt("idmatierestyle")); 
                myQuantitematierestyle.setIdInformationProduit(set.getInt("idinformationproduit")); 
                myQuantitematierestyle.setQuantite(set.getDouble("quantite")); 
                myQuantitematierestyle.setIdUnite(set.getInt("idunite")); 
            }
           
        set.close();
        stat.close();
        connect.close();
        return myQuantitematierestyle;
    }
}


