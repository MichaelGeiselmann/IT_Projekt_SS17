package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

/**
 * Mapper fuer ein Unternehmen-Objekt
 */
public class UnternehmenMapper {

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static UnternehmenMapper unternehmenMapper = null;

    /**
     * Geschuetzter Konstruktor
     */
    protected UnternehmenMapper() {
        // TODO implement here
    }

    /**
     * @return unternehmenMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse.
     */
    public static UnternehmenMapper unternehmenMapper() {
        if(unternehmenMapper == null){
        	unternehmenMapper = new UnternehmenMapper();
        }
        return unternehmenMapper;
    }

    /**
     * @param id 
     * @return Liefert ein Unternehmen entsprechend der ï¿½bergebenen id zurueck
     */
    public Unternehmen findById(int id) {

    	Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Unternehmen_Id, Name"
					+ " FROM unternehmen " + "WHERE Unternehmen_Id=" + 
			id + "ORDER BY Name");
			
			
			if(rs.next()){
				Unternehmen u = new Unternehmen();
				u.setId(rs.getInt("Unternehmen_Id"));
				u.setName(rs.getString("Name"));
				

				
				Organisationseinheit utemp= OrganisationseinheitMapper.organisationseinheitMapper().findById(id);
				
				u.setStrasse(utemp.getStrasse());
				u.setHausnummer(utemp.getHausnummer());
				u.setOrt(utemp.getOrt());
				u.setPlz(utemp.getPlz());
				u.setProjektmarktplatzId(utemp.getProjektmarktplatzId());
				u.setPartnerprofilId(utemp.getPartnerprofilId());
				
				
				
				return u;
				} 
			}   
		catch (SQLException e) {
		e.printStackTrace();
		return null;
		}
		
		return null;
	}
    
    
    /**
     * 
     * @param u
     * @return Liefert ein Unternehmen entsprechend des Ã¼bergebenen Objekts zurueck.
     */
    public Unternehmen findByObject(Unternehmen u){
		return u;
    	
    }

    /**
     * @param u 
     * @return Zielentitaet aus der Datenbank gemaess des uebergebenen Objekts loeschen.
     */
    public void delete(Unternehmen u) {
        // TODO implement here
    	  Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM unternehmen " + "WHERE Unternehmen_Id=" + u.getId());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
	  }
    

    /**
     * @param u 
     * @return Zielentitaet aus der Datenbank gemaess den Informationen des uebergebenen Objekts aktualisieren.
     */
    public Unternehmen update(Unternehmen u) {
    	 Connection con = DBConnection.connection();
		    
		    

		    try {
		    	
		      OrganisationseinheitMapper.organisationseinheitMapper().update(u);
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE unternehmen " + "\", " + "Name=\"" + u.getName() + "\" "
		          + "WHERE Unternehmen_Id=" + u.getId());

		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    // Um Analogie zu insert(Person p) zu wahren, geben wir p zurÃ¼ck
		    return u;
	  }
    

    /**
     * @param u 
     * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
     */
    public Unternehmen insert(Unternehmen u) {
    	
    	//DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
		    	 //Leeres SQL-Statement anlegen
		        Statement stmt = con.createStatement();
		    	/*
			       * ZunÃ¤chst schauen wir nach, welches der momentan hÃ¶chste
			       * PrimÃ¤rschlÃ¼sselwert ist.
			       */
		    	
			      ResultSet rs = stmt.executeQuery("SELECT MAX(Organisationseinheit_Id) AS maxid "
			          + "FROM organisationseinheit ");

			      // Wenn wir etwas zurÃ¼ckerhalten, kann dies nur einzeilig sein
			      if (rs.next()) {
			        /*
			         * b erhÃ¤lt den bisher maximalen, nun um 1 inkrementierten
			         * PrimÃ¤rschlÃ¼ssel.
			         */
			        u.setId(rs.getInt("maxid") + 1);
			        
		      
			        OrganisationseinheitMapper.organisationseinheitMapper().insert(u);
			       

		      
		        
		        //Leeres SQL-Statement fÃ¼r die Insert- AusfÃ¼hrung anlegen.
		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsÃ¤chliche EinfÃ¼geoperation
		        stmt.executeUpdate("INSERT INTO unternehmen (Unternehmen_Id, Name) "
		            + "VALUES (" + u.getId() + ",'" + u.getName() + "')");
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		return u;
		  
	  }
    }

