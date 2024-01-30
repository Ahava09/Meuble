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
public class NombreEmployeeInformationProduit {
    private int id;
    private int idInformationProduit;
    private Poste poste;
    private int nombre;
    private double duree;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInformationProduit() {
        return idInformationProduit;
    }

    public void setIdInformationProduit(int informationProduit) {
        this.idInformationProduit = informationProduit;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
    
    public void setPoste(int idposte,Connection connection) throws SQLException {
        this.setPoste(new Poste().getByid(connection, idposte));
    }
    public void setPoste(String idposte,Connection connection) throws SQLException {
        this.setPoste(new Poste().getByid(connection, Integer.valueOf(idposte)));
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.setNombre(Integer.valueOf(nombre));
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public void setDuree(String duree) {
        this.setDuree(Double.valueOf(duree));
    }
    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into NombreEmployeeInformationProduit (idPoste, idinformationproduit, nombre, duree) values ("+getPoste().getId()+", "+getIdInformationProduit()+", "+getNombre()+", "+getDuree()+")";
        Statement stat = connect.createStatement();
        System.out.println("*********************** "+sql);
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<NombreEmployeeInformationProduit> getAll (Connection connect) throws SQLException {
        
        ArrayList<NombreEmployeeInformationProduit> allQuantitematierestyles = new ArrayList<NombreEmployeeInformationProduit>();
        
        String query = "select * from NombreEmployeeInformationProduit";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            NombreEmployeeInformationProduit myNombreEmployeeInformationProduit = new NombreEmployeeInformationProduit();
            myNombreEmployeeInformationProduit.setId(set.getInt("id")); 
            myNombreEmployeeInformationProduit.setIdInformationProduit(set.getInt("idinformationproduit")); 
            myNombreEmployeeInformationProduit.setPoste(set.getInt("idPoste"),connect); 
            myNombreEmployeeInformationProduit.setDuree(set.getDouble("duree")); 
            myNombreEmployeeInformationProduit.setNombre(set.getInt("nombre")); 
	
            allQuantitematierestyles.add(myNombreEmployeeInformationProduit);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allQuantitematierestyles;
    }
    
     public NombreEmployeeInformationProduit getByInfo (Connection connect, int idInfo) throws SQLException {
        
            NombreEmployeeInformationProduit myNombreEmployeeInformationProduit = new NombreEmployeeInformationProduit();

            String query = "select * from NombreEmployeeInformationProduit where idinformationproduit = "+idInfo;
            Statement stat = connect.createStatement();

            ResultSet set = stat.executeQuery(query);
            while(set.next()) {
                myNombreEmployeeInformationProduit.setId(set.getInt("id")); 
                myNombreEmployeeInformationProduit.setIdInformationProduit(set.getInt("idinformationproduit")); 
                myNombreEmployeeInformationProduit.setPoste(set.getInt("idPoste"),connect); 
                myNombreEmployeeInformationProduit.setDuree(set.getDouble("duree")); 
                myNombreEmployeeInformationProduit.setNombre(set.getInt("nombre")); 
            }
           
        set.close();
        stat.close();
        connect.close();
        return myNombreEmployeeInformationProduit;
    }
    
    
}
