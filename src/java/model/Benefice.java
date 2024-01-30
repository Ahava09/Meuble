/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author itu
 */
public class Benefice {
    private InformationProduit informationProduit;
    private DetailsProduitPrix prixMatieres;
    private DetailsProduitPoste prixPoste;
    private double beneficie = 0;

    public Benefice() {
    }

    public InformationProduit getInformationProduit() {
        return informationProduit;
    }

    public void setInformationProduit(InformationProduit informationProduit) {
        this.informationProduit = informationProduit;
    }

    public void setInformationProduit(int informationProduit, Connection connection) throws Exception {
        this.setInformationProduit(new InformationProduit().getById(connection, informationProduit));
    }

    public DetailsProduitPrix getPrixMatieres() {
        return prixMatieres;
    }

    public void setPrixMatieres(DetailsProduitPrix prixMatieres) {
        this.prixMatieres = prixMatieres;
    }

    public DetailsProduitPoste getPrixPoste() {
        return prixPoste;
    }

    public void setPrixPoste(DetailsProduitPoste prixPoste) {
        this.prixPoste = prixPoste;
    }

    public double getBenefice() {
        return beneficie;
    }

    public void setBenefice(double beneficie) {
        this.beneficie = beneficie;
    }
    
    public Benefice (int idInformationProduit,Connection connection) throws Exception{
        this.setInformationProduit(idInformationProduit,connection);
        this.setPrixMatieres(new DetailsProduitPrix().getByIdInfromationProduit(connection, idInformationProduit));
        this.setPrixPoste(new DetailsProduitPoste().getByIdInfromationProduit(connection, idInformationProduit));
        System.out.println(this.getInformationProduit().getPrixVente()+" --- "+this.getPrixMatieres().getPrixTotal()+"  "+this.getPrixPoste().getPrixTotal());
        this.setBenefice( (this.getInformationProduit().getPrixVente()-(this.getPrixMatieres().getPrixTotal()+this.getPrixPoste().getPrixTotal())) );
    }
    
    public Benefice (InformationProduit informationProduit,Connection connection) throws Exception{
        this.setInformationProduit(informationProduit);
        this.setPrixMatieres(new DetailsProduitPrix().getByIdInfromationProduit(connection, informationProduit.getId()));
        this.setPrixPoste(new DetailsProduitPoste().getByIdInfromationProduit(connection, informationProduit.getId()));
        System.out.println(this.getInformationProduit().getPrixVente()+" --- "+this.getPrixMatieres().getPrixTotal()+"  "+this.getPrixPoste().getPrixTotal());
        this.setBenefice( (this.getInformationProduit().getPrixVente()-(this.getPrixMatieres().getPrixTotal()+this.getPrixPoste().getPrixTotal())) );
    }
    
    public ArrayList<Benefice> getAll (Connection connection) throws Exception {
        ArrayList<InformationProduit> list = new InformationProduit().getAll(connection);
        ArrayList<Benefice> benefices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            InformationProduit get = list.get(i);
            benefices.add(new Benefice(get, connection));
        }
        connection.close();
        return benefices;
    }
    
    public ArrayList<Benefice> getMinMax (Connection connection, String min , String max) throws Exception {
        ArrayList<InformationProduit> list = new InformationProduit().getAll(connection);
        ArrayList<Benefice> benefices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            InformationProduit get = list.get(i);
            Benefice benefice = new Benefice(get, connection);
            if (benefice.getBenefice()<= Double.valueOf(min) && benefice.getBenefice()>= Double.valueOf(max)) benefices.add(benefice);
        }
        connection.close();
        return benefices;
    }
    
    public ArrayList<Benefice> getMinMax (Connection connection, double min , double max) throws Exception {
        ArrayList<Benefice> benefices = getAll(connection);
        ArrayList<Benefice> newbenefices = new ArrayList<>();
        for (int i = 0; i < benefices.size(); i++) {
            Benefice get = benefices.get(i);
            System.out.println(get.getBenefice()+" ----------> "+min+" --------- "+max);
            if (get.getBenefice()>= min && get.getBenefice()<= max) newbenefices.add(get);
        }
        connection.close();
        return newbenefices;
    }
    
}
