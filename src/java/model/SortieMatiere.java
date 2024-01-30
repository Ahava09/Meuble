/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author itu
 */
public class SortieMatiere {
    
    private int id;
    private Matiere matiere;
    private double quantite;
    private Timestamp datesortie;

    public SortieMatiere() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public void setMatiere(int idmatiere, Connection connection) throws SQLException {
        this.setMatiere(new Matiere().getbyId(connection, idmatiere));
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
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
    
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into sortiematiere (idMatiere, quantite, datesortie) values ("+getMatiere().getId()+", "+getQuantite()+", '"+getDatesortie()+"')";
        System.out.println("................... "+sql);
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    
    
}
