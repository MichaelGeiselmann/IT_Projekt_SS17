package de.hdm.itProjektSS17.shared.bo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Projekt extends BusinessObject{
	
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	   * Startdatum des Projekt.
	   */
	private Date startdatum = null; 
	
	/**
	   * Enddatum des Projekt
	   */
	private Date enddatum = null;
	
	/**
	   * Name des Projekt
	   */
	private String name;
	
	/**
	   * Beschreibung des Projekt
	   */
	private String beschreibung;
	
	/**
	 * Realisierung der Beziehung zu einem Projektmarktplatz durch einen Fremdschl�ssel.
	 */
	private int projektmarktplatzId = 0;
	
	/**
	 * Realisierung der Beziehung zu einer Person durch einen Fremdschl�ssel.
	 */
	private int projektleiterId = 0;
	
	 /**
	   * Auslesen des Startdatum.
	   */
	public Date getStartdatum(){
		return startdatum;
	}
	
	/**
	   * Setzen des Startdatum.
	   */
	public void setStartdatum(Date startdatum){
		this.startdatum = startdatum;
	}
	
	 /**
	   * Auslesen des Enddatum.
	   */
	public Date getEnddatum() {
		return enddatum;
	}
	
	/**
	   * Setzen des Enddatum.
	   */
	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}
	
	 /**
	   * Auslesen des Namen.
	   */
	public String getName() {
		return name;
	}
	
	/**
	   * Setzen des Namen.
	   */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	   * Auslesen der Beschreibung.
	   */
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	   * Setzen der Beschreibung.
	   */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return Gibt den Fremdschl�ssel projektmarktplatzId zur�ck.
	 */
	public int getProjektmarktplatzId() {
		return projektmarktplatzId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel projektmarktplatzId.
	 */
	public void setProjektmarktplatzId(int projektmarktplatzId) {
		this.projektmarktplatzId = projektmarktplatzId;
	}

	/**
	 * @return Gibt den Fremdschl�ssel projektleiterId zur�ck.
	 */
	public int getProjektleiterId() {
		return projektleiterId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel projektleiterId.
	 */
	public void setProjektleiterId(int projektleiterId) {
		this.projektleiterId = projektleiterId;
	}
	
	
}
