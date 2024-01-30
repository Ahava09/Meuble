package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InformationProduit {

    public int id;
    public int idstyle;
    private DetailsCaracteristique style;
    public int idcategorie;
    private DetailsCaracteristique categorie;
    public int idtaille;
    public Taille taille;
    public double volume;
    public double prixvente;
    public Timestamp dureedebut;
    public Timestamp dureefin;

    // Constructeur par défaut
    public InformationProduit() {
    }

    // Constructeur avec paramètres
    public InformationProduit(int id, int idstyle, int idcategorie, int idtaille, double volume, double prix, Timestamp dureedebut, Timestamp dureefin) throws Exception {
        // Initialiser les attributs avec les paramètres
        this.setId(id);
        this.setIdstyle(idstyle);
        this.setIdcategorie(idcategorie);
        this.setIdtaille(idtaille);
        this.setVolume(volume);
        this.setPrixVente(prix);
        this.setDureedebut(dureedebut);
        this.setDureefin(dureefin);

    }
    
    public InformationProduit getInformationProduit(InformationProduit info,Connection connection) throws SQLException {
        info.setStyle(connection);
        info.setCategorie(connection);
        info.setTaille(connection);
        return info;
    }

    public ArrayList<InformationProduit> getInformationProduits(ArrayList<InformationProduit> list,Connection connection) throws SQLException {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setStyle(connection);
            list.get(i).setCategorie(connection);
            list.get(i).setTaille(connection);
        }
        return list;
    }

    public void setStyle(DetailsCaracteristique style) {
        this.style = style;
    }

    public DetailsCaracteristique getStyle() {
        return style;
    }

    public void setStyle(Connection connection) throws SQLException {
        this.setStyle(new DetailsCaracteristique().getByid(connection, getIdstyle()));
    }

    public DetailsCaracteristique getCategorie() {
        return categorie;
    }

    public void setCategorie(DetailsCaracteristique categorie) {
        this.categorie = categorie;
    }

    public void setCategorie(Connection connection) throws SQLException {
        this.setCategorie(new DetailsCaracteristique().getByid(connection, getIdcategorie()));
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public void setTaille(Connection connection) throws SQLException {
        this.setTaille(new Taille().getByid(connection, getIdtaille()));
    }

    public double getPrixvente() {
        return prixvente;
    }

    public void setPrixvente(double prixvente) {
        this.prixvente = prixvente;
    }
    
    
    
    
    
    

    // Getters
    public int getId() {
        return id;
    }

    public int getIdstyle() {
        return idstyle;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public int getIdtaille() {
        return idtaille;
    }

    public double getVolume() {
        return volume;
    }

    public double getPrixVente() {
        return prixvente;
    }

    public Timestamp getDureedebut() {
        return dureedebut;
    }

    public Timestamp getDureefin() {
        return dureefin;
    }

    // Setters
    public void setId(int idinformation) {
        this.id = idinformation;
    }

    public void setIdstyle(int idstyle) {
        this.idstyle = idstyle;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public void setIdtaille(int idtaille) throws Exception {
        this.idtaille = idtaille;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setPrixVente(double prix) {
        this.prixvente = prix;
    }

    public void setDureedebut(Timestamp dureedebut) {
        this.dureedebut = dureedebut;
    }

    public void setDureefin(Timestamp dureefin) {
        this.dureefin = dureefin;
    }

    public void setId(String idinformation) {
        this.setId(Integer.valueOf(idinformation));
    }

    public void setIdstyle(String idstyle) {
        this.setIdstyle(Integer.valueOf(idstyle));
    }

    public void setIdcategorie(String idcategorie) {
        this.setIdcategorie(Integer.valueOf(idcategorie));
    }

    public void setIdtaille(String idtaille) throws Exception {
        this.setIdtaille(Integer.valueOf(idtaille));

    }

    public void setIdtaille(Connection c, String idtaille) throws Exception {

        Taille t = new Taille();
        t = t.getByid(c, Integer.valueOf(idtaille));
        System.out.println();
        if (t.check(this.getVolume())) {
            this.setIdtaille(Integer.valueOf(idtaille));
        } else {
            throw new Exception("La volume n'est pas faite pour la taille");
        }

    }

    public void setVolume(String volume) {
        this.setVolume(Double.valueOf(volume));
    }

    public void setPrixVente(String prix) {
        this.setPrixVente(Double.valueOf(prix));
    }

    public void setDureedebut(String dureedebut) {
        // Définir le format de la chaîne de caractères de date+" 12:00:01"
        System.out.println("****** " + dureedebut);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            // Analyser la chaîne de caractères en un objet Date
            java.util.Date utilDate = dateFormat.parse(dureedebut);
            // Convertir java.util.Date en java.sql.Date
            Timestamp d = new Timestamp(utilDate.getTime());
            setDureedebut(d);
        } catch (ParseException e) {
            e.printStackTrace(); // À adapter selon vos besoins
        }
    }

    public void setDureefin(String dureefin) {
        // Définir le format de la chaîne de caractères de date
        System.out.println("****** " + dureefin);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            // Analyser la chaîne de caractères en un objet Date
            java.util.Date utilDate = dateFormat.parse(dureefin);
            // Convertir java.util.Date en java.sql.Date
            Timestamp d = new Timestamp(utilDate.getTime());
            setDureefin(d);
        } catch (ParseException e) {
            e.printStackTrace(); // À adapter selon vos besoins
        }
    }

    //insert
    public void insert(Connection connect) throws SQLException, Exception {
        String sql = "insert into InformationProduit (idstyle, idcategorie, idtaille, volume, prixvente, dureedebut, dureefin) values (" + getIdstyle() + ", " + getIdcategorie() + ", " + getIdtaille() + ", " + getVolume() + ", " + getPrixVente()+ ", '" + getDureedebut() + "', '" + getDureefin() + "')";
        Statement stat = connect.createStatement();
        System.out.println("m----------------------- " + sql);
        stat.executeUpdate(sql);

        stat.close();
        connect.close();
    }

    //getAll
    public ArrayList<InformationProduit> getAll(Connection connect) throws SQLException, Exception {

        ArrayList<InformationProduit> allInformations = new ArrayList<InformationProduit>();

        String query = "select * from InformationProduit";
        Statement stat = connect.createStatement();

        ResultSet set = stat.executeQuery(query);
        while (set.next()) {
            InformationProduit myInformation = new InformationProduit();
            myInformation.setId(set.getInt("id"));
            myInformation.setIdstyle(set.getInt("idstyle"));
            myInformation.setIdcategorie(set.getInt("idcategorie"));
            myInformation.setVolume(set.getDouble("volume"));
            myInformation.setIdtaille(set.getInt("idtaille"));
            myInformation.setPrixVente(set.getDouble("prixvente"));
            myInformation.setDureedebut(set.getTimestamp("dureedebut"));
            myInformation.setDureefin(set.getTimestamp("dureefin"));

            allInformations.add(myInformation);
        }

        set.close();
        stat.close();
//        connect.close();
        return allInformations;
    }

    public ArrayList<InformationProduit> getWithWhere(Connection connect) throws SQLException, Exception {

        ArrayList<InformationProduit> allInformations = new ArrayList<InformationProduit>();

        String query = "select * from Informationproduit where idstyle= " + getIdstyle() + " and idcategorie =" + getIdcategorie() + " and idtaille= " + getIdtaille();
        Statement stat = connect.createStatement();
        System.out.println(query);
        ResultSet set = stat.executeQuery(query);
        while (set.next()) {
            InformationProduit myInformation = new InformationProduit();
            myInformation.setId(set.getInt("id"));
            myInformation.setIdstyle(set.getInt("idstyle"));
            myInformation.setIdcategorie(set.getInt("idcategorie"));
            myInformation.setVolume(set.getDouble("volume"));
            myInformation.setIdtaille(set.getInt("idtaille"));
            myInformation.setPrixVente(set.getDouble("prixvente"));
            myInformation.setDureedebut(set.getTimestamp("dureedebut"));
            myInformation.setDureefin(set.getTimestamp("dureefin"));

            allInformations.add(myInformation);
        }

        set.close();
        stat.close();
//        connect.close();
        return allInformations;
    }

    public InformationProduit getWithWhere(Connection connect, int idStyle, int idCateg, int idTaille) throws SQLException, Exception {
        InformationProduit myInformation = new InformationProduit();

        String query = "select * from InformationProduit where idstyle= " + idStyle + " and idcategorie =" + idCateg + " and idtaille= " + idTaille;
        Statement stat = connect.createStatement();
        System.out.println(query);
        ResultSet set = stat.executeQuery(query);
        while (set.next()) {
            myInformation.setId(set.getInt("id"));
            myInformation.setIdstyle(set.getInt("idstyle"));
            myInformation.setIdcategorie(set.getInt("idcategorie"));
            myInformation.setVolume(set.getDouble("volume"));
            myInformation.setIdtaille(set.getInt("idtaille"));
            myInformation.setPrixVente(set.getDouble("prixvente"));
            myInformation.setDureedebut(set.getTimestamp("dureedebut"));
            myInformation.setDureefin(set.getTimestamp("dureefin"));

        }

        set.close();
        stat.close();
//        connect.close();
        return myInformation;
    }

    public InformationProduit getInformationProduit(Connection connect) throws SQLException, Exception {
        InformationProduit myInformation = new InformationProduit();

        String query = "select * from InformationProduit where idstyle= " + getIdstyle() + " and idcategorie =" + getIdcategorie() + " and idtaille= " + getIdtaille(); //+" and volume = "+getVolume()
        Statement stat = connect.createStatement();
        System.out.println(" ------------- "+query);
        ResultSet set = stat.executeQuery(query);
        while (set.next()) {
            myInformation.setId(set.getInt("id"));
            myInformation.setIdstyle(set.getInt("idstyle"));
            myInformation.setIdcategorie(set.getInt("idcategorie"));
            myInformation.setVolume(set.getDouble("volume"));
            myInformation.setIdtaille(set.getInt("idtaille"));
            myInformation.setPrixVente(set.getDouble("prixvente"));
            myInformation.setDureedebut(set.getTimestamp("dureedebut"));
            myInformation.setDureefin(set.getTimestamp("dureefin"));
            return myInformation;
        }

        set.close();
        stat.close();
        throw new Exception("On n'a pas cette produit ");
            
    }

    public InformationProduit getById(Connection connect, int id) throws SQLException, Exception {
        InformationProduit myInformation = new InformationProduit();

        String query = "select * from InformationProduit where id= " +id;
        Statement stat = connect.createStatement();
        System.out.println(query);
        ResultSet set = stat.executeQuery(query);
        while (set.next()) {
            myInformation.setId(set.getInt("id"));
        System.out.println(query);
            myInformation.setIdstyle(set.getInt("idstyle"));
            myInformation.setIdcategorie(set.getInt("idcategorie"));
            myInformation.setVolume(set.getDouble("volume"));
            myInformation.setIdtaille(set.getInt("idtaille"));
            myInformation.setPrixVente(set.getDouble("prixvente"));
            myInformation.setDureedebut(set.getTimestamp("dureedebut"));
            myInformation.setDureefin(set.getTimestamp("dureefin"));

        }
        System.out.println(query);

        set.close();
        stat.close();
//        connect.close();
        return myInformation;
    }

    public InformationProduit getInformation(ArrayList<InformationProduit> list, double volume) {
        InformationProduit info = new InformationProduit();
        for (int i = 0; i < list.size(); i++) {
            info = list.get(i);
        }
        return info;
    }

}
