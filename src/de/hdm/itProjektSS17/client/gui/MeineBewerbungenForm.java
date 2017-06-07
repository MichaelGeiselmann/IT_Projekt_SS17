package de.hdm.itProjektSS17.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;


/**
 * Diese Klasse erbt von Vertical Panel. Sie zeigt die Bewerbungen einer Person 
 * in tabellarischer Form
 * 
 * @author Tom Alender
 *
 */
public class MeineBewerbungenForm extends Showcase{
	
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	@SuppressWarnings("unchecked")
	CellTable<ausschreibungBewerbungHybrid> cellTable = new CellTable<ausschreibungBewerbungHybrid>();
	Vector<ausschreibungBewerbungHybrid> hybrid = new Vector<ausschreibungBewerbungHybrid>();
		
	HorizontalPanel panel_Bewerbung = new HorizontalPanel();

	Button btn_bewerbungloeschen = new Button("Bewerbung zurückziehen");
	Button btn_bewerbungstext = new Button ("Bewerbungstext anzeigen");
	Button btn_stellungname = new Button ("Stellungnahme anzeigen");


	
	protected String getHeadlineText(){
	return "Meine Bewerbungen";
	}
	
	protected void run() {
		
		RootPanel.get("Details").setWidth("70%");
		cellTable.setWidth("100%", true);
		cellTable.setVisibleRangeAndClearData(cellTable.getVisibleRange(),true);
		cellTable.setLoadingIndicator(null);
		
		//Stylen des Buttons
		btn_bewerbungloeschen.setStylePrimaryName("navi-button");
		btn_bewerbungstext.setStylePrimaryName("navi-button");
		btn_stellungname.setStylePrimaryName("navi-button");
		
		this.setSpacing(8);
		this.add(panel_Bewerbung);
		panel_Bewerbung.add(btn_bewerbungloeschen);

		panel_Bewerbung.add(btn_bewerbungstext);
		panel_Bewerbung.add(btn_stellungname);
		projektmarktplatzVerwaltung.getBewerbungByForeignOrganisationseinheit(IdentityMarketChoice.getSelectedIdentityAsObject(), new BewerbungAnzeigenCallback());
	
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	
		TextColumn<ausschreibungBewerbungHybrid> AusschreibungNameColumn = new TextColumn<ausschreibungBewerbungHybrid>() {

			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
			
				return object.getAusschreibungsbezeichnung();
			}
	
		};	
	
		TextColumn<ausschreibungBewerbungHybrid> erstellungsdatumColumn = new TextColumn<ausschreibungBewerbungHybrid>() {
		
			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
			
			return object.getErstellungsdatum().toString();
			
			}
		};
		
		TextColumn<ausschreibungBewerbungHybrid> AusschreibenderColumn = new TextColumn<ausschreibungBewerbungHybrid>() {
			
			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
			
			
			return object.getAnrede()+ " " +object.getAusschreibungsbezeichnername();
			
			}
		};
		
		TextColumn<ausschreibungBewerbungHybrid> AusschreibenderTeamColumn = new TextColumn<ausschreibungBewerbungHybrid>() {

			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
				// TODO Auto-generated method stub
				return object.getTeam();
			}
		};

		
		TextColumn<ausschreibungBewerbungHybrid> AusschreibenderUnternehmenColumn = new TextColumn<ausschreibungBewerbungHybrid>() {

			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
				// TODO Auto-generated method stub
				return object.getUnternehmen();
			}
		};
		
		
		TextColumn<ausschreibungBewerbungHybrid> statusColumn = new TextColumn<ausschreibungBewerbungHybrid>() {
			
			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
			
			return object.getStatusBewerbungsstatus().toString();
			
			}
		};
		
		TextColumn<ausschreibungBewerbungHybrid> BewertungColumn = new TextColumn<ausschreibungBewerbungHybrid>() {
			
			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
				double wert = object.getBewertungWert();
				String stringWert = Double.toString(wert);
				return stringWert;
			}
		};
		
		cellTable.addColumn(AusschreibungNameColumn, "Stelle");
		cellTable.addColumn(AusschreibenderColumn, "Ausschreibender");
		cellTable.addColumn(AusschreibenderTeamColumn, "Team");
		cellTable.addColumn(AusschreibenderUnternehmenColumn, "Unternehmen");
		cellTable.addColumn(erstellungsdatumColumn, "Erstellungsdatum");
		cellTable.addColumn(statusColumn, "Status");
		cellTable.addColumn(BewertungColumn, "B");
	
		
		cellTable.setWidth("100%");
		
		final SingleSelectionModel<ausschreibungBewerbungHybrid> selectionModel = new SingleSelectionModel<>();
		cellTable.setSelectionModel(selectionModel);	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		btn_bewerbungstext.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (selectionModel.getSelectedObject() == null)
				{
					Window.alert("Bitte wählen Sie eine Bewerbung aus");
				}
				DialogBoxBewerbungstext text = new DialogBoxBewerbungstext(selectionModel.getSelectedObject().getBewerbungstext());
				int left = Window.getClientWidth() / 3;
				int top = Window.getClientHeight() / 8;
				text.setPopupPosition(left, top);
				text.show();
		}
		});
		btn_bewerbungloeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				if (selectionModel.getSelectedObject() == null)
				{
					Window.alert("Bitte wählen Sie die zu löschende Bewerbung aus");
				}else{				
					/*for(ausschreibungBewerbungHybrid abH : hybrid){
						if (selectionModel.getSelectedObject().getBewerbungId()==abH.getBewerbungId())
						{
							hybrid.remove(selectionModel.getSelectedObject());
						}
					}*/
						Bewerbung tempBew = new Bewerbung();
						tempBew.setId(selectionModel.getSelectedObject().getBewerbungId());
						projektmarktplatzVerwaltung.deleteBewerbung(tempBew, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Bewerbung konnte nicht zurückgezogen werden");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Bewerbung wurde zurückgezogen!");
								Navigation.reload();
							}
						});
				}


				
			
			}
		});
		
		btn_stellungname.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DialogBox db_Stellungnahme = new DialogBox();
				TextArea txta_Stellungnahme = new TextArea();
				FlexTable ft_Stellungname = new FlexTable();
				Button ok_Stellungnahme = new Button();
				ft_Stellungname.setWidget(0, 0, txta_Stellungnahme);
				ft_Stellungname.setWidget(1, 0, ok_Stellungnahme);
				
			}
		});
		
		this.add(cellTable);
	}

	private class ausschreibungBewerbungHybrid{
		
		private int bewerbungId;
		private String bewerbungstext;
		private String anrede;
		
		private String ausschreibungsbezeichnung;
		private String ausschreibungsbezeichnername;
		private Date erstellungsdatum;
		private Bewerbungsstatus statusBewerbungsstatus;
		private String Team;
		private String Unternehmen;
		
		private Double bewertungWert;
		
		public String getAnrede() {
			return anrede;
		}
		public void setAnrede(String anrede) {
			this.anrede = anrede;
		}
		
		public String getBewerbungstext() {
			return bewerbungstext;
		}
		public void setBewerbungstext(String bewerbungstext){
			this.bewerbungstext=bewerbungstext;
		}
		public int getBewerbungId() {
			return bewerbungId;
		}
		public void setBewerbungId(int bewerbungId) {
			this.bewerbungId = bewerbungId;
		}
		public String getAusschreibungsbezeichnung() {
			return ausschreibungsbezeichnung;
		}
		public void setAusschreibungsbezeichnung(String ausschreibungsbezeichnung) {
			this.ausschreibungsbezeichnung = ausschreibungsbezeichnung;
		}
		public String getAusschreibungsbezeichnername() {
			return ausschreibungsbezeichnername;
		}
		public void setAusschreibungsbezeichnername(String ausschreibungsbezeichnername) {
			this.ausschreibungsbezeichnername = ausschreibungsbezeichnername;
		}
		public Date getErstellungsdatum() {
			return erstellungsdatum;
		}
		public void setErstellungsdatum(Date erstellungsdatum) {
			this.erstellungsdatum = erstellungsdatum;
		}
		public Bewerbungsstatus getStatusBewerbungsstatus() {
			return statusBewerbungsstatus;
		}
		public void setStatusBewerbungsstatus(Bewerbungsstatus statusBewerbungsstatus) {
			this.statusBewerbungsstatus = statusBewerbungsstatus;
		}
		public String getTeam() {
			return Team;
		}
		public void setTeam(String team) {
			Team = team;
		}
		public String getUnternehmen() {
			return Unternehmen;
		}
		public void setUnternehmen(String unternehmen) {
			Unternehmen = unternehmen;
		}
		public Double getBewertungWert() {
			return bewertungWert;
		}
		public void setBewertungWert(Double bewertungWert) {
			this.bewertungWert = bewertungWert;
		}
		
		
	}

	
	private class BewerbungAnzeigenCallback implements AsyncCallback<Vector<Bewerbung>>	{
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Bewerbungen ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			
			for(int i=0;i<result.size();i++){
				final Bewerbung localBewerbung = result.get(i);
				final ausschreibungBewerbungHybrid localHybrid = new ausschreibungBewerbungHybrid();
			
				
				projektmarktplatzVerwaltung.getAusschreibungById(result.get(i).getAusschreibungId(), new AsyncCallback<Ausschreibung>() {
					
					@Override
					public void onFailure(Throwable caught) {
						
						Window.alert("Das Anzeigen der Bewerbungen ist fehlgeschlagen!");
						
					}
					@Override
					public void onSuccess(Ausschreibung result) {
					//final ausschreibungBewerbungHybrid localHybrid = new ausschreibungBewerbungHybrid();
					final Ausschreibung a = result;
					localHybrid.setAusschreibungsbezeichnung(result.getBezeichnung());
					projektmarktplatzVerwaltung.getProjektById(result.getProjektId(), new AsyncCallback<Projekt>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Projekt result) {
							// TODO Auto-generated method stub
							if (IdentityMarketChoice.getSelectedProjectMarketplaceId()==result.getProjektmarktplatzId()){
								
								projektmarktplatzVerwaltung.getPersonById(a.getAusschreibenderId(), new AsyncCallback<Person>() { 
									
														@Override
															public void onFailure(Throwable caught) {
																// TODO Auto-generated method stub
																
															}
									
															@Override
														public void onSuccess(Person result) {
																
																projektmarktplatzVerwaltung.getBewertungByForeignBewerbung(localBewerbung, new AsyncCallback<Bewertung>() {
																	
																	@Override
																	public void onFailure(Throwable caught) {
																		Window.alert("Fehler: "+caught.toString());					
																	}

																	@Override
																	public void onSuccess(Bewertung result) {	
																		if(result != null){	
																		localHybrid.setBewertungWert(result.getWert());	
																		cellTable.setRowCount(hybrid.size(), true);
																		cellTable.setRowData(0,hybrid);				
																		}
																		else{
																			localHybrid.setBewertungWert(0.0);
																			cellTable.setRowCount(hybrid.size(), true);
																			cellTable.setRowData(0,hybrid);	
																		}
																	}
																});
																
																localHybrid.setAnrede(result.getAnrede());
																localHybrid.setAusschreibungsbezeichnername(result.getNachname());
																localHybrid.setBewerbungId(localBewerbung.getId());
																localHybrid.setErstellungsdatum(localBewerbung.getErstellungsdatum());
																localHybrid.setStatusBewerbungsstatus(localBewerbung.getStatus());
																if(localBewerbung.getBewerbungstext()=="null"){
																	localHybrid.setBewerbungstext("Kein Text vorhanden");
																}else if (localBewerbung.getBewerbungstext()==""){
																	localHybrid.setBewerbungstext("Kein Text vorhanden");
																}else{
																	localHybrid.setBewerbungstext(localBewerbung.getBewerbungstext());
																}
																Person p = result;
																if(p.getId()!=IdentityMarketChoice.getUser().getId()){
																	if(p.getTeamId()==null && p.getUnternehmenId()==null){
																		
																		localHybrid.setTeam("Kein Team");
																		localHybrid.setUnternehmen("Kein Unternehmen");
																		hybrid.add(localHybrid);
																		cellTable.setRowCount(hybrid.size(), true);
																		cellTable.setRowData(0,hybrid);
																		
																	}else if(p.getTeamId()!=null && p.getUnternehmenId()==null){
																		
																		projektmarktplatzVerwaltung.getTeamById(result.getTeamId(), new AsyncCallback<Team>() {
																			
																			@Override
																			public void onFailure(Throwable caught) {
																				Window.alert("Team zu Ausschreibendem konnte nicht geladen werden.");
																			}

																			@Override
																			public void onSuccess(Team result) {
																				localHybrid.setUnternehmen("Kein Unternehmen");
																				localHybrid.setTeam(result.getName());
																				hybrid.add(localHybrid);
																				cellTable.setRowCount(hybrid.size(), true);
																				cellTable.setRowData(0,hybrid);
																			}
																		});
																		
																	}else if(p.getTeamId()==null && p.getUnternehmenId()!=null){
																		
																		projektmarktplatzVerwaltung.getUnternehmenById(result.getUnternehmenId(), new AsyncCallback<Unternehmen>() {

																			@Override
																			public void onFailure(Throwable caught) {
																				Window.alert("Unternehmen zu Ausschreibendem konnte nicht geladen werden.");
																			}

																			@Override
																			public void onSuccess(Unternehmen result) {
																				localHybrid.setTeam("Kein Team");
																				localHybrid.setUnternehmen(result.getName());
																				hybrid.add(localHybrid);
																				cellTable.setRowCount(hybrid.size(), true);
																				cellTable.setRowData(0,hybrid);
																			}
																		});
																		
																	}else if(p.getTeamId()!=null && p.getUnternehmenId()!=null){
																		
																		projektmarktplatzVerwaltung.getTeamById(result.getTeamId(), new AsyncCallback<Team>() {
																			
																			@Override
																			public void onFailure(Throwable caught) {
																				Window.alert("Team zu Ausschreibendem konnte nicht geladen werden.");
																			}

																			@Override
																			public void onSuccess(Team result) {
																				localHybrid.setTeam(result.getName());
																				projektmarktplatzVerwaltung.getUnternehmenById(result.getUnternehmenId(), new AsyncCallback<Unternehmen>() {

																					@Override
																					public void onFailure(Throwable caught) {
																						Window.alert("Unternehmen zu Ausschreibendem konnte nicht geladen werden.");
																					}

																					@Override
																					public void onSuccess(Unternehmen result) {
																						localHybrid.setUnternehmen(result.getName());
																						hybrid.add(localHybrid);
																						cellTable.setRowCount(hybrid.size(), true);
																						cellTable.setRowData(0,hybrid);
																				
																					}
																				});
																				
																			}
																		});
																		
																	}
																}
																
															}
														});
													
													}
												}
											});
										}
						
									});
					
								}	
							}
	
					 };
	}