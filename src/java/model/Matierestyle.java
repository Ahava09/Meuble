package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Matierestyle {
    
    public int id; 
    public Matiere matiere; 
    public DetailsCaracteristique style; 
	

    // Constructeur par défaut
    public Matierestyle() {
    }
    
    public int getId() {return id;
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

    public DetailsCaracteristique getStyle() {
        return style;
    }

    // Getters
    public void setStyle(DetailsCaracteristique style) {
        this.style = style;
    }
    public void setStyle(Connection connect, int idstyle) throws SQLException {
        this.style = new DetailsCaracteristique().getByid(connect, idstyle);
        connect.close();
    }
    public void setStyle(Connection connect, String idstyle) throws SQLException {
        this.style = new DetailsCaracteristique().getByid(connect, Integer.valueOf(idstyle));
        connect.close();
    }

    public void setId(String idmatierestyle) {
        this.id = Integer.valueOf(idmatierestyle);
    }
    public void setIdmatiere(String idmatiere) {this.getMatiere().setId(Integer.valueOf(idmatiere)); }
    public void setIdstyle(String idstyle) {this.getStyle().setIddetailscaracteristique(Integer.valueOf(idstyle)); }
	

    //insert
    public void insert (Connection connect) throws SQLException, Exception {
        String sql = "insert into Matierestyle (idmatiere, idstyle) values ("+getMatiere().getId()+", "+getStyle().getIddetailscaracteristique()+")";
        System.out.println("................... "+sql);
        Statement stat = connect.createStatement();
        
        stat.executeUpdate(sql);
        
        stat.close();
        connect.close();
    }
    
    //getAll
    public ArrayList<Matierestyle> getAll (Connection connect) throws SQLException {
        
        ArrayList<Matierestyle> allMatierestyles = new ArrayList<Matierestyle>();
        String query = "select ms.id id, ms.idStyle idstyle, d.nom nomStyle, ms.idMatiere idmatiere, m.nom from Matierestyle "+
                " join matiere m on m.id = ms.idmatiere "+
                " join detailscaracteristique d on d.id = ms.idStyle";
        Statement stat = connect.createStatement();
        
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Matierestyle myMatierestyle = new Matierestyle();
            myMatierestyle.setId(set.getInt("id")); 
            myMatierestyle.setMatiere(new Matiere().getbyId(connect, set.getInt("idmatiere"))); 
            myMatierestyle.setStyle(new DetailsCaracteristique().getByid(connect, set.getInt("idstyle"))); 
	
            allMatierestyles.add(myMatierestyle);
        }
           
        set.close();
        stat.close();
        connect.close();
        return allMatierestyles;
    }
    
    //getAll
    public ArrayList<Matierestyle> getIdstyle (Connection connect, int idstyle) throws SQLException {
        
        ArrayList<Matierestyle> allMatierestyles = new ArrayList<Matierestyle>();
        
        String query = "select ms.id id, ms.idStyle idstyle, d.nom nomStyle, ms.idMatiere idmatiere, m.nom from Matierestyle ms"+
                " join matiere m on m.id = ms.idmatiere "+
                " join detailscaractestique d on d.id = ms.idStyle where ms.idStyle = "+idstyle;
        Statement stat = connect.createStatement();
        System.out.println("---------------------------------"+query);
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            Matierestyle myMatierestyle = new Matierestyle();
            myMatierestyle.setId(set.getInt("id")); 
            myMatierestyle.setMatiere(new Matiere().getbyId(connect, set.getInt("idmatiere"))); 
            myMatierestyle.setStyle(new DetailsCaracteristique().getByid(connect, set.getInt("idstyle"))); 
	
            allMatierestyles.add(myMatierestyle);
        }
           
        return allMatierestyles;
    }
    
//    public ArrayList<Matierestyle> getAllMatiereStyle (Connection c, int idstyle) throws SQLException {
//        ArrayList<Matierestyle> list = getIdstyle(c, idstyle);
//        Detailscaractestique d = new Detailscaractestique();
//        ArrayList<Detailscaractestique> listes = new ArrayList<Detailscaractestique>();
//        
//        for (int i = 0; i < list.size(); i++) {
//            Matierestyle get = list.get(i);
//            System.out.println(idstyle+" ************************** "+list.size()+  " *********************** "+get.getIdmatiere());
//            d = d.getByid(c, get.getIdmatiere());
//            listes.add(d);
//            d = new Detailscaractestique();
//        }
//        c.close();
//        
//        return listes;
//    }
    
    public Matierestyle getIdMatierestyle (Connection connect, int idstyle, int idmatiere) throws SQLException {
        String query = "select * from Matierestyle where idstyle ="+idstyle+" and idmatiere ="+idmatiere;
        Statement stat = connect.createStatement();
        System.out.println("---------------------------------"+query);
        ResultSet set = stat.executeQuery(query);
        Matierestyle ms = new Matierestyle();
        while(set.next()) {
            ms.setId(set.getInt("id")); 
            ms.setMatiere(new Matiere().getbyId(connect, set.getInt("idmatiere"))); 
            ms.setStyle(new DetailsCaracteristique().getByid(connect, set.getInt("idstyle"))); 
        }
        set.close();
        stat.close();
//        connect.close();
        return ms;
        
    }
    
    
    
    public Matierestyle getById (Connection connect, int id) throws SQLException {
        String query = "select * from Matierestyle where id ="+id;
        Statement stat = connect.createStatement();
        Matierestyle ms = new Matierestyle();
        ResultSet set = stat.executeQuery(query);
        while(set.next()) {
            ms.setId(set.getInt("id")); 
            ms.setMatiere(new Matiere().getbyId(connect, set.getInt("idmatiere"))); 
            ms.setStyle(new DetailsCaracteristique().getByid(connect, set.getInt("idstyle"))); 
            
        }
           
//        set.close();
//        stat.close();
//        connect.close();
        return ms;
        
    }
}


