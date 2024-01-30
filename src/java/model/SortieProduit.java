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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author itu
 */
public class SortieProduit {
    
    private int id;
    private int idInformationProduit;
    private int nombre;
    private Timestamp datesortie;

    public int getId() {
        return id;
    }

    public void setId(int idSortieproduit) {
        this.id = idSortieproduit;
    }

    public int getIdInformationProduit() {
        return idInformationProduit;
    }

    public void setIdInformationProduit(int idInformation) {
        this.idInformationProduit = idInformation;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = Integer.valueOf(nombre);
    }

    public Timestamp getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(Timestamp datesortie) {
        this.datesortie = datesortie;
    }
    public void setDatesortie(String dateentree) throws Exception {
        // Définir le format de la chaîne de caractères de date+" 12:00:01"
        try {
            System.out.println(dateentree);
            if (isValidDateFormat(dateentree)) {
                // Définir le format de la chaîne de caractères de date au format sans le 'T'
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                // Analyser la chaîne de caractères en un objet Date
                java.util.Date utilDate = inputFormat.parse(dateentree);

                // Convertir java.util.Date en java.sql.Timestamp
                Timestamp d = new Timestamp(utilDate.getTime());

                // Utiliser votre méthode setDateentree pour définir la date
                setDatesortie(d);
            } else {
                throw new Exception("Format de date invalide");
            }
            
        } catch (ParseException e) {
            throw new Exception("Impossible de transformer en Timestamp");
        }
    }

    public static boolean isValidDateFormat(String date) {
        // Validez ici si la chaîne de caractères a le bon format
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public ArrayList<DetailsProduitPrix> getAllInfromationProduit(Connection connection, int idinformationproduit) throws SQLException, Exception {
         ArrayList<DetailsProduitPrix> allmatieres = new DetailsProduitPrix().getAllInfromationProduit(connection, idinformationproduit);
         ArrayList<EtatStock> etat = new EtatStock().getAll(connection);
//         System.out.println("Size: "+allmatieres.size()+" "+etat.size());
         ArrayList<DetailsProduitPrix> matieres  = new ArrayList<DetailsProduitPrix> ();
         for (int i = 0; i < allmatieres.size(); i++) {
            for (int j = 0; j < etat.size(); j++) {
                System.out.println("Size: "+allmatieres.size()+" "+etat.size());
                System.out.println(allmatieres.get(i).getIdMatiere()+" "+etat.get(j).getMatiere().getId()+"  "+etat.get(j).check(allmatieres.get(i).getQuantite()));
                if (allmatieres.get(i).getIdMatiere() == etat.get(j).getMatiere().getId()){
                    if(etat.get(j).check(allmatieres.get(i).getQuantite())) {
                        matieres.add(allmatieres.get(i));
                        break;
                    }
                    throw new Exception("Stock insufissant");
                }             
            }
            
        }
         return matieres;
    }
    
    public void insertProduitMatiere (Connection connection) throws Exception {
        ArrayList<DetailsProduitPrix> d = getAllInfromationProduit(connection, getIdInformationProduit());
        this.insert(connection);
        for (int i = 0; i < d.size(); i++) {
            DetailsProduitPrix get = d.get(i);
            SortieMatiere s= new SortieMatiere();
            s.setMatiere(get.getIdMatiere(), connection);
            s.setQuantite(get.getQuantite());
            s.setDatesortie(this.getDatesortie());
            s.insert(connection);
        }
        connection.close();
    }
    
     public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into sortieproduit (idinformationproduit, nombre, datesortie) values ("+getIdInformationProduit()+", "+getNombre()+", '"+getDatesortie()+"')";
        System.out.println("................... "+sql);
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
//        connect.close();
    }
     
     public SortieProduit getLast(Connection connect) throws SQLException {
         String sql = "select * from sortieproduit where datesortie=(select max(dateSortie) from sortieproduit)";
           Statement stat = connect.createStatement();
            SortieProduit myQuantitematierestyle = new SortieProduit();
            ResultSet set = stat.executeQuery(sql);
        while(set.next()) {
            myQuantitematierestyle.setId(set.getInt("id")); 
            myQuantitematierestyle.setIdInformationProduit(set.getInt("idinformationproduit")); 
            myQuantitematierestyle.setDatesortie(set.getTimestamp("datesortie")); 
            myQuantitematierestyle.setNombre(set.getInt("nombre")); 
	
        }
           
        set.close();
        stat.close();
        connect.close();
        return myQuantitematierestyle;
     }
    
    
}
