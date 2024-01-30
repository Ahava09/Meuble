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
public class Entree {
    private int id;
    private Matiere matiere;
    private Unite unite;
    private double prix;
    private double quantite;
    private Timestamp dateEntree;

    public Entree() {
    }

    public Entree(int id, Matiere matiere, Unite unite, double prix, double quantite, Timestamp dateEntree) {
        this.id = id;
        this.matiere = matiere;
        this.unite = unite;
        this.prix = prix;
        this.quantite = quantite;
        this.dateEntree = dateEntree;
    }

    public Entree(int idmatiere, int idunite, double prix, double quantite, String dateEntree,Connection connection) throws SQLException {
        this.setMatiere(connection, idmatiere);
        this.setUnite(connection, idunite);
        this.setPrix(prix);
        this.setQuantite(quantite);
        this.setDateEntree(dateEntree);
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
    
    public void setMatiere(Connection connect, int idmatiere) throws SQLException {
        this.matiere = new Matiere().getbyId(connect, idmatiere);
    }
    public void setMatiere(Connection connect, String idmatiere) throws SQLException {
        this.matiere = new Matiere().getbyId(connect, Integer.valueOf(idmatiere));
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public void setUnite(Connection connect, int idunite) throws SQLException {
        this.unite = new Unite().getById(connect, idunite);
    }

    public void setUnite(Connection connect, String idunite) throws SQLException {
        setUnite(connect, Integer.valueOf(idunite));
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Timestamp getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Timestamp dateEntree) {
        this.dateEntree = dateEntree;
    }

    public void setDateEntree(String dateEntree) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
             // Analyser la chaîne de caractères en un objet Date
            java.util.Date utilDate = dateFormat.parse(dateEntree);
           
            // Convertir java.util.Date en java.sql.Date
            Timestamp d = new Timestamp(utilDate.getTime());
            this.setDateEntree(d);
        } catch (ParseException e) {
            // Gérer l'exception en cas d'échec de la conversion
            e.printStackTrace(); // À adapter selon vos besoins
        }
    }
    
        
    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into entree (idmatiere,idunite,prix,quantite,dateentree) values ("+getMatiere().getId()+", "+getUnite().getId()+", "+getPrix()+", "+getQuantite()+", '"+getDateEntree()+"')";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        insertSortie(connect);
        stat.close();
        connect.close();
    }
    
    public void insertSortie (Connection connect) throws SQLException, Exception {
        SortieMatiere s = new SortieMatiere();
        s.setDatesortie(this.getDateEntree());
        s.setMatiere(this.getMatiere());
        s.setQuantite(0);
        s.insert(connect);
    }
}
