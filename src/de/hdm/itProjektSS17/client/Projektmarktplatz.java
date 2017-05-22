package de.hdm.itProjektSS17.client;

import de.hdm.itProjektSS17.client.gui.MeineProjektForm;
import de.hdm.itProjektSS17.client.gui.OrganisationseinheitverwaltenForm;
import de.hdm.itProjektSS17.shared.FieldVerifier;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point Klasse des Projektmarktplatzes. Dafür benötigen wir die Methode
 * <code>onModuleLoad()</code>.
 */
public class Projektmarktplatz implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		//ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getBankVerwaltung();
		
	    VerticalPanel navPanel = new VerticalPanel();

	    RootPanel.get("Navigator").add(navPanel);
		
	    
	    //TopPanel für Logut
	    VerticalPanel topPanel = new VerticalPanel();
	    RootPanel.get("Header").add(topPanel);
	    //Erstellen Projektmarktzplatz Button
	    final Button Logut = new Button("Logout");

	    Logut.setStylePrimaryName("projektmarktplatz-logout");

	    navPanel.add(Logut);
	   
	    Logut.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				///Session beenden
			}
		});
	    
	    //Erstellen Projektmarktzplatz Button
	    final Button findCustomerButtonProjektmarktplatz = new Button("Projektmarktplatz");

	    findCustomerButtonProjektmarktplatz.setStylePrimaryName("projektmarktplatz-menubutton");

	    navPanel.add(findCustomerButtonProjektmarktplatz);
	   
	    findCustomerButtonProjektmarktplatz.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new GetPersonalInformation();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
	    
	    // Erstellen Startseite Button
	    final Button findNavButtonStartseite = new Button("Startseite");

	    findNavButtonStartseite.setStylePrimaryName("projektmarktplatz-menubutton");

	    navPanel.add(findNavButtonStartseite);
	    
	    findNavButtonStartseite.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new GetPersonalInformation();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
	    
	    
	    
	    
	    // Erstellen Stellenausschreibung Button
	    final Button findNavButtonStellenausschreibung = new Button("Stellenausschreibung");

	    findNavButtonStellenausschreibung.setStylePrimaryName("projektmarktplatz-menubutton");

	    navPanel.add(findNavButtonStellenausschreibung);
	    
	    findNavButtonStellenausschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new GetPersonalInformation();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
	    
	    
	    // Erstellen Stellenausschreibung Button
	    final Button findNavButtonMeineBewerbung = new Button("Meine Bewerbung");

	    findNavButtonMeineBewerbung.setStylePrimaryName("projektmarktplatz-menubutton");

	    navPanel.add(findNavButtonMeineBewerbung);
	    
	    findNavButtonMeineBewerbung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new GetPersonalInformation();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
	    
	    
	    
	    // Erstellen Meine Projekte Button
	    final Button findNavButtonMeineProjekte = new Button("Meine Projekte");

	    findNavButtonMeineProjekte.setStylePrimaryName("projektmarktplatz-menubutton");

	    navPanel.add(findNavButtonMeineProjekte);
	    
	    findNavButtonMeineProjekte.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new MeineProjektForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
	    
	    
	    // Erstellen Organisationseinheit Button
	    final Button findNavButtonOrganisationseinheit= new Button("Organisationseinheit");

	    findNavButtonOrganisationseinheit.setStylePrimaryName("projektmarktplatz-menubutton");

	    navPanel.add(findNavButtonOrganisationseinheit);
	    
	    findNavButtonOrganisationseinheit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new OrganisationseinheitverwaltenForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
	    
	    
	    
	    
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
	
	}
	
	
}
