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
public class Statistique {
    
    private InformationProduit informationProduit;
    private  int idInformationProduit;
    private int nombre_male ;
    private int nombre_femme;

    public InformationProduit getInformationProduit() {
        return informationProduit;
    }

//    public Statistique(InformationProduit informationProduit, int idgenre, String nomgenre) {
//        this.setInformationProduit(informationProduit);
//        this.setIdgenre(idgenre);
//        this.setNomgenre(nomgenre);
//    }

    public void setInformationProduit(InformationProduit informationProduit) {
        this.informationProduit = informationProduit;
    }

    public int getNombre_male() {
        return nombre_male;
    }

    public void setNombre_male(int nombre_male) {
        this.nombre_male = nombre_male;
    }

    public int getNombre_femme() {
        return nombre_femme;
    }

    public void setNombre_femme(int nombre_femme) {
        this.nombre_femme = nombre_femme;
    }



    public int getIdInformationProduit() {
        return idInformationProduit;
    }

    public void setIdInformationProduit(int idInformationProduit) {
        this.idInformationProduit = idInformationProduit;
    }

    public Statistique() {
    }
    
//        public ArrayList<Statistique> getByGenre (Connection connect,String idgenre) throws SQLException, Exception {
//        
//        ArrayList<Statistique> allStatistiques = new ArrayList<Statistique>();
//        
//        String query = "select * from Statistique where idgenre ="+idgenre;
//        System.out.println(" "+query);
//        Statement stat = connect.createStatement();
//        
//        ResultSet set = stat.executeQuery(query);
//        while(set.next()) {
//            Statistique myStatistique = new Statistique();
//            myStatistique.setInformationProduit(set.getInt("idInformationProduit"),connect); 
//            myStatistique.setNombre(set.getInt("nombre")); 
//            myStatistique.setIdgenre(set.getInt("idgenre")); 
//            myStatistique.setNomgenre(set.getString("nomgenre")); 
//	
//            allStatistiques.add(myStatistique);
//        }
//           
//            System.out.println("model.Statistique.getAll()--------------------------------------------------");
//        set.close();
//        stat.close();
//        connect.close();
//        return allStatistiques;
//    }
    
    public ArrayList<Statistique> getAll (Connection connect) throws SQLException, Exception {
        
        ArrayList<Statistique> allStatistiques = new ArrayList<Statistique>();
        
        String query = "select * from StatistiqueParProduit";
        System.out.println(" "+query);
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Statistique myStatistique = new Statistique();
            myStatistique.setInformationProduit(set.getInt("idInformationProduit"),connect); 
            myStatistique.setNombre_male(set.getInt("male")); 
            myStatistique.setNombre_femme(set.getInt("femme")); 
           
            System.out.println(myStatistique.getNombre_male()+"model.Statistique.getAll()--------------------------------------------------"+myStatistique.getNombre_femme());
	
            allStatistiques.add(myStatistique);
        }
        set.close();
        stat.close();
        connect.close();
        return allStatistiques;
    }
  
    
    public void setInformationProduit(int informationProduit, Connection connection) throws Exception {
        InformationProduit i = new InformationProduit().getById(connection, informationProduit);
        this.setInformationProduit(i.getInformationProduit(i, connection));
    }
     
//    public ArrayList<Statistique> getId (Connection connect, int id) throws SQLException, Exception {
//        
//        ArrayList<Statistique> allStatistiques = new ArrayList<Statistique>();
//        
//        String query = "select * from Statistique where idInformationProduit= "+id;
//        System.out.println(" "+query);
//        Statement stat = connect.createStatement();
//        
//        ResultSet set = stat.executeQuery(query);
//        while(set.next()) {
//            Statistique myStatistique = new Statistique();
//            myStatistique.setIdInformationProduit(set.getInt("idInformationProduit")); 
//            myStatistique.setNombre(set.getInt("nombre")); 
//            myStatistique.setIdgenre(set.getInt("idgenre")); 
//            myStatistique.setNomgenre(set.getString("nomgenre")); 
//            allStatistiques.add(myStatistique);
//        }
//           
//        set.close();
//        stat.close();
////        connect.close();
//        return allStatistiques;
//    }
    
}
