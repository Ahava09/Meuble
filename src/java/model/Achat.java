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
public class Achat {
    private int id;
    private int idClient;
    private InformationProduit informationProduit;
    private int nombre;
    private Timestamp dateAchat;

    public Achat(int id, int idClient, InformationProduit informationProduit, int nombre, Timestamp dateAchat) {
        this.id = id;
        this.idClient = idClient;
        this.informationProduit = informationProduit;
        this.setNombre(nombre);
        this.dateAchat = dateAchat;
    }
    
    public Achat(int idClient, int idInformationProduit, String nombre, String dateAchat,Connection connection) throws Exception {
        this.setIdClient(idClient);
        this.setInformationProduit(idInformationProduit,connection);
        this.setDateAchat(dateAchat);
        this.setNombre(nombre);
    }
    
    public Achat(String idClient, int idInformationProduit, String nombre, String dateAchat,Connection connection) throws Exception {
        this.setIdClient(idClient);
        this.setInformationProduit(idInformationProduit,connection);
        this.setDateAchat(dateAchat);
        this.setNombre(nombre);
    }

    public Achat() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdClient(String idClient) {
        this.setIdClient(Integer.valueOf(idClient));
    }

    public InformationProduit getInformationProduit() {
        return informationProduit;
    }

    public void setInformationProduit(InformationProduit informationProduit) {
        this.informationProduit = informationProduit;
    }


    public void setInformationProduit(int informationProduit, Connection connection) throws Exception {
        InformationProduit i = new InformationProduit().getById(connection, informationProduit);
        this.setInformationProduit(i.getInformationProduit(i, connection));
    }

    public Timestamp getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Timestamp dateAchat) {
        this.dateAchat = dateAchat;
    }
    
    public void setDateAchat(String dureedebut) {
        // Définir le format de la chaîne de caractères de date+" 12:00:01"
        System.out.println("Achat  ****** " + dureedebut);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            // Analyser la chaîne de caractères en un objet Date
            java.util.Date utilDate = dateFormat.parse(dureedebut);
            // Convertir java.util.Date en java.sql.Date
            Timestamp d = new Timestamp(utilDate.getTime());
            setDateAchat(d);
        } catch (ParseException e) {
            e.printStackTrace(); // À adapter selon vos besoins
        }
    }
    
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into achat (idInformationProduit, idClient,nombre, dateAchat) values ("+getInformationProduit().getId()+", "+getIdClient()+" ,"+getNombre()+", '"+getDateAchat()+"')";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Achat> getAll (Connection connect) throws SQLException, Exception {
        
        ArrayList<Achat> allAchats = new ArrayList<Achat>();
        
        String query = "select * from achat";
        System.out.println(" "+query);
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Achat myAchat = new Achat();
            myAchat.setId(set.getInt("id")); 
            myAchat.setInformationProduit(set.getInt("idInformationProduit"),connect); 
            myAchat.setIdClient(set.getInt("idClient")); 
            myAchat.setDateAchat(set.getTimestamp("dateAchat")); 
	
            allAchats.add(myAchat);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allAchats;
    }
}
