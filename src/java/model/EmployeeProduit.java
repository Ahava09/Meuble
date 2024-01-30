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
public class EmployeeProduit {
    
    public int id; 
    public Poste poste; 
    public DetailsCaracteristique style;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public DetailsCaracteristique getStyle() {
        return style;
    }

    public void setStyle(DetailsCaracteristique style) {
        this.style = style;
    }
    
    public void setPoste(int idposte,Connection connection) throws SQLException {
        this.setPoste(new Poste().getByid(connection, idposte));
    }
    public void setPoste(String idposte,Connection connection) throws SQLException {
        this.setPoste(new Poste().getByid(connection, Integer.valueOf(idposte)));
    }
    public void setStyle(Connection connect, int idstyle) throws SQLException {
        this.style = new DetailsCaracteristique().getByid(connect, idstyle);
//        connect.close();
    }
    public void setStyle(Connection connect, String idstyle) throws SQLException {
        this.style = new DetailsCaracteristique().getByid(connect, Integer.valueOf(idstyle));
//        connect.close();
    }

    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into EmployeeProduit (idposte, idstyle) values ("+getPoste().getId()+", "+getStyle().getIddetailscaracteristique()+")";
        System.out.println("................... "+sql);
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
//    public ArrayList<Matierestyle> getAll (Connection connect) throws SQLException {
//        
//        ArrayList<Matierestyle> allMatierestyles = new ArrayList<Matierestyle>();
//        String query = "select ms.id id, ms.idStyle idstyle, d.nom nomStyle, ms.idMatiere idmatiere, m.nom from Matierestyle "+
//                " join matiere m on m.id = ms.idmatiere "+
//                " join detailscaracteristique d on d.id = ms.idStyle";
//        Statement stat = connect.createStatement();
//        
//        ResultSet set = stat.executeQuery(query);
//        while(set.next()) {
//            Matierestyle myMatierestyle = new Matierestyle();
//            myMatierestyle.setId(set.getInt("id")); 
//            myMatierestyle.setMatiere(new Matiere().getbyId(connect, set.getInt("idmatiere"))); 
//            myMatierestyle.setStyle(new DetailsCaracteristique().getByid(connect, set.getInt("idstyle"))); 
//	
//            allMatierestyles.add(myMatierestyle);
//        }
//           
//        set.close();
//        stat.close();
//        connect.close();
//        return allMatierestyles;
//    }
    
    //getAll
    public ArrayList<EmployeeProduit> getIdstyle (Connection connect, int idstyle) throws SQLException {
        
        ArrayList<EmployeeProduit> allEmployeeProduits = new ArrayList<EmployeeProduit>();
        
        String query = "select ep.id id, ep.idStyle idstyle, d.nom nomStyle, ep.idPoste idPoste, p.nom from EmployeeProduit ep"+
                " join poste p on p.id = ep.idPoste "+
                " join detailscaractestique d on d.id = ep.idStyle where ep.idStyle = "+idstyle;
        Statement stat = connect.createStatement();
        System.out.println("---------------------------------"+query);
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            EmployeeProduit myEmployeeProduit = new EmployeeProduit();
            myEmployeeProduit.setId(set.getInt("id")); 
            myEmployeeProduit.setPoste(new Poste().getByid(connect, set.getInt("idposte"))); 
            myEmployeeProduit.setStyle(new DetailsCaracteristique().getByid(connect, set.getInt("idstyle"))); 
	
            allEmployeeProduits.add(myEmployeeProduit);
        }
           
        return allEmployeeProduits;
    }
    
    
    public EmployeeProduit getEmployeeProduit (Connection connect, int idstyle, int idposte) throws SQLException {
        String query = "select * from EmployeeProduit where idstyle ="+idstyle+" and idposte ="+idposte;
        Statement stat = connect.createStatement();
        System.out.println("---------------------------------"+query);
        ResultSet set = stat.executeQuery(query);
        EmployeeProduit ms = new EmployeeProduit();
        while(set.next()) {
            ms.setId(set.getInt("id")); 
            ms.setPoste(new Poste().getByid(connect, set.getInt("idposte"))); 
            ms.setStyle(new DetailsCaracteristique().getByid(connect, set.getInt("idstyle"))); 
        }
        set.close();
        stat.close();
//        connect.close();
        return ms;
        
    }
    
    
    
    public EmployeeProduit getById (Connection connect, int id) throws SQLException {
        String query = "select * from EmployeeProduit where id ="+id;
        Statement stat = connect.createStatement();
        EmployeeProduit ms = new EmployeeProduit();
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            ms.setId(set.getInt("id")); 
            ms.setPoste(new Poste().getByid(connect, set.getInt("idposte"))); 
            ms.setStyle(new DetailsCaracteristique().getByid(connect, set.getInt("idstyle"))); 
            
        }
           
        set.close();
        stat.close();
//        connect.close();
        return ms;
        
    }

}
