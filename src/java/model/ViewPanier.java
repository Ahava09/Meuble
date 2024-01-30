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
public class ViewPanier {
    private int idPanier;
    private int idClient;
    private Client client;
    private Timestamp datepanier;
    private int IdInformationProduit;
    private InformationProduit informationProduit;
    private int nombre;

    public ViewPanier() {
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setClient( Connection connection) throws Exception {
        Client i = new Client().getById(connection, getIdClient());
        this.setClient(i);
    }

    public Timestamp getDatepanier() {
        return datepanier;
    }

    public void setDatepanier(Timestamp datepanier) {
        this.datepanier = datepanier;
    }

    public int getIdInformationProduit() {
        return IdInformationProduit;
    }

    public void setInformationProduit(int informationProduit, Connection connection) throws Exception {
        InformationProduit i = new InformationProduit().getById(connection, informationProduit);
        this.setInformationProduit(i.getInformationProduit(i, connection));
    }

    public void setIdInformationProduit(int IdInformationProduit) {
        this.IdInformationProduit = IdInformationProduit;
    }

    public InformationProduit getInformationProduit() {
        return informationProduit;
    }

    public void setInformationProduit(InformationProduit informationProduit) {
        this.informationProduit = informationProduit;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    
    public void setDatepanier(String dureedebut) {
        // Définir le format de la chaîne de caractères de date+" 12:00:01"
        System.out.println("Achat  ****** " + dureedebut);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            // Analyser la chaîne de caractères en un objet Date
            java.util.Date utilDate = dateFormat.parse(dureedebut);
            // Convertir java.util.Date en java.sql.Date
            Timestamp d = new Timestamp(utilDate.getTime());
            setDatepanier(d);
        } catch (ParseException e) {
            e.printStackTrace(); // À adapter selon vos besoins
        }
    }
    
    public void insertPanier (Connection connect) throws SQLException, Exception {
        String sql = "insert into panier (idClient,datepanier) values ("+getIdClient()+", '"+getDatepanier()+"')";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
//        connect.close();
    }
    
    public void insertDetailsPanier (Connection connect) throws SQLException, Exception {
        String sql = "insert into detailsPanier (idPanier,idInformationProduit,nombre) values ("+getIdPanier()+" ,"+getIdInformationProduit()+" ,"+getNombre()+")";
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
//        connect.close();
    }
    
    //getAll
    public ArrayList<ViewPanier> getAll (Connection connect) throws SQLException, Exception {
        
        ArrayList<ViewPanier> allViewPaniers = new ArrayList<ViewPanier>();
        
        String query = "select * from viewPanier";
        System.out.println(" "+query);
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            ViewPanier myViewPanier = new ViewPanier();
            myViewPanier.setIdClient(set.getInt(idClient)); 
            myViewPanier.setClient(connect);
            myViewPanier.setInformationProduit(set.getInt("idInformationProduit"),connect); 
            myViewPanier.setIdInformationProduit(set.getInt("idInformationProduit")); 
            myViewPanier.setDatepanier(set.getTimestamp("datepanier"));
            myViewPanier.setNombre(set.getInt("nombre")); 
	
            allViewPaniers.add(myViewPanier);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allViewPaniers;
       
    }
    public ArrayList<ViewPanier> getByIdPanier (Connection connect,int idPanier) throws SQLException, Exception {
        
        ArrayList<ViewPanier> allViewPaniers = new ArrayList<ViewPanier>();
        
        String query = "select * from viewPanier where idPanier = "+idPanier;
        System.out.println(" "+query);
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            ViewPanier myViewPanier = new ViewPanier();
            myViewPanier.setIdClient(set.getInt(idClient)); 
            myViewPanier.setClient(connect);
            myViewPanier.setInformationProduit(set.getInt("idInformationProduit"),connect); 
            myViewPanier.setIdInformationProduit(set.getInt("idInformationProduit")); 
            myViewPanier.setDatepanier(set.getTimestamp("datepanier"));
            myViewPanier.setNombre(set.getInt("nombre")); 
	
            allViewPaniers.add(myViewPanier);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allViewPaniers;
    }
    
    
   
}
