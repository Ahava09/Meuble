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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author itu
 */
public class Employee {
    private int id;
    private String nom;
    private String prenoms;
    private Date dtn;
    private Date dateDebut;
    private int idposte;
    private String poste;
    private int duree =0; 
    private double taux =0;  
    private String niveau; 
    private double salaire;

    public Employee() {
    }

    public Employee(int id, String nom, String prenoms, Date dtn, Date dateDebut, String poste, String niveau,double salaire) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.dtn = dtn;
        this.dateDebut = dateDebut;
        this.poste = poste;
        this.niveau = niveau;
        this.setSalaire(salaire); 
    }

    public Employee(String nom, String prenoms, String dtn, String dateDebut, String idposte,String salaire) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenoms(prenoms);
        this.setDtn(dtn);
        this.setDateDebut(dateDebut);
        this.setIdposte(idposte);
        this.setSalaire(salaire);
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public void setSalaire(String salaire) {
        this.setSalaire(Double.valueOf(salaire));
    }
    

    public String getNiveau() {
        return niveau;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getDuree() {
        return duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Date getDtn() {
        return dtn;
    }
    
    public void setDtn(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = dateFormat.parse(date);
            // Convertir java.util.Date en java.sql.Date
            Timestamp d = new Timestamp(utilDate.getTime());
            this.setDtn(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDateDebut(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = dateFormat.parse(date);
            // Convertir java.util.Date en java.sql.Date
            Timestamp d = new Timestamp(utilDate.getTime());
            this.setDateDebut(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public int getIdposte() {
        return idposte;
    }

    public void setIdposte(int idposte) {
        this.idposte = idposte;
    }

    public void setIdposte(String idposte) {
        this.setIdposte(Integer.valueOf(idposte));
    }
    
    
    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into employee (nom, prenoms, dtn, dateDebut, idPoste,salaire) values ('"+getNom()+"', '"+getPrenoms()+"', '"+getDtn()+"', '"+getDateDebut()+"',  "+getIdposte()+", "+getSalaire()+")";
        Statement stat = connect.createStatement();
        System.out.println("--------------- "+sql);
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Employee> getAll (Connection connect) throws SQLException {
        
        ArrayList<Employee> allEmployees = new ArrayList<Employee>();
        
        String query = "select * from employee_niveau";
        System.out.println("   "+query);
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Employee myEmployee = new Employee();
            myEmployee.setId(set.getInt("id")); 
            myEmployee.setNom(set.getString("nom")); 
            myEmployee.setPoste(set.getString("poste")); 
            myEmployee.setPrenoms(set.getString("prenoms")); 
            myEmployee.setDateDebut(set.getDate("dateDebut")); 
            myEmployee.setDuree(set.getInt("duree"));
            myEmployee.setTaux(set.getDouble("taux"));
            myEmployee.setNiveau(set.getString("niveau"));
            myEmployee.setSalaire(set.getDouble("salaire"));
            allEmployees.add(myEmployee);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allEmployees;
    }
    public Employee getById (Connection connect,int id) throws SQLException {
        
        String query = "select * from Employee where id="+id;
            Employee myEmployee = new Employee();
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            myEmployee.setId(set.getInt("id")); 
            myEmployee.setNom(set.getString("nom")); 
            myEmployee.setIdposte(set.getInt("idposte")); 
            myEmployee.setPrenoms(set.getString("prenoms")); 
            myEmployee.setDtn(set.getDate("dtn")); 
            myEmployee.setDateDebut(set.getDate("dateDebut")); 
            myEmployee.setSalaire(set.getDouble("salaire"));
        }
           
        set.close();
        stat.close();
        connect.close();
        return myEmployee;
    }
    
    public void updateDateEntree (Connection connect, String date, String salaire) throws SQLException, Exception {
        
        String sql = "update employee set dateDebut='"+date+"' , salaire="+salaire+" where id ="+getId();
        Statement stat = connect.createStatement();
        System.out.println("--------------- "+sql);
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }

//    public void setDuree() {
//        LocalDate dateA = LocalDate.now();
//        LocalDate dateentree = convertToLocalDate(getDateDebut());
//        long diff = ChronoUnit.YEARS.between(dateentree, dateA);
//        this.setDuree((int) diff);
//    }
//
//    // Méthode pour convertir java.sql.Date en LocalDate
//    private LocalDate convertToLocalDate(Date dateToConvert) {
//        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
//    }
    
    public void setNiveau(String niveau){
        this.niveau = niveau;
    }
    
//    public void setNiveau(){
//        if (getDuree() < 2 ) this.setNiveau("Ouvrier");
//        if (getDuree() < 3 && getDuree() >= 2 ) this.setNiveau("Senior");
//        if (getDuree() >= 3  ) this.setNiveau("Expert");
//    }
//    
//   
//    public static void main(String[] args) throws ParseException {
//        java.sql.Date dateA = new java.sql.Date(2023, 1, 17);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date utilDate = (java.util.Date) dateFormat.parse("2023-01-25");
////        dateA = new java.sql.Date(0, 0, 0);
////        System.out.println(utilDate.getMonth()+" "+dateA.getYear());
//        dateA.getTime();
//        utilDate = new Timestamp(dateA.getTime());
//        System.out.println(utilDate.getMonth()+" "+dateA.getYear());
//        
//    }
}
