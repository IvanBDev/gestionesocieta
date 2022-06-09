package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;

	@Autowired
	private DipendenteService dipendenteService;

	public void testListAllSocieta() {

		List<Societa> listaSocieta = null;
		try {
			listaSocieta = societaService.findAllSocieta();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listaSocieta.forEach(item -> System.out.println(item));

	}

	public void testInserisciNuovaSocieta() {

		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = null;
		try {
			nuovaSocieta = new Societa("E corp. " + nowInMillisecondi, "Via Dalmata 102 " + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("05/09/1895"));

			if (nuovaSocieta.getId() != null)
				throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("testInserisciNuovaSocieta........OK");
	}

	public void testFindByExampleSocieta() {

		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = null;
		try {
			nuovaSocieta = new Societa("New Heaven corp. " + nowInMillisecondi,
					"Via dei Registri 09 " + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("01/11/1995"));

			if (nuovaSocieta.getId() != null)
				throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		Date dataSocietaExample = null;
		try {
			dataSocietaExample = new SimpleDateFormat("dd/MM/yyyy").parse("01/11/1995");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Societa societaExample = new Societa();
		societaExample.setRagioneSociale("hea");
		societaExample.setIndirizzo("");
		societaExample.setDataFondazione(dataSocietaExample);

		System.out.println(societaService.findByExample(societaExample));

		System.out.println("testFindByExampleSocieta........OK");

	}

	public void testRimozioneSocieta() {

		try {
			Societa societaDaEliminare = new Societa("SocietaF f.r.t", "via Delete 45",
					new SimpleDateFormat("dd/MM/yyyy").parse("24/08/1990"));

			if (societaDaEliminare.getId() != null)
				throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
			
			societaService.inserisciNuovo(societaDaEliminare);
			
			if(societaDaEliminare.getId() != null) {
				System.out.println("Inserimento effettuato");
			}
			
			societaService.rimuovi(societaDaEliminare);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("testRimozioneSocieta........OK");
	}
	
	public void testInserisciDipendenti() {
		
		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = null;
		Dipendente nuovoDipendente = null;
		try {
			nuovaSocieta = new Societa("Best Life s.r.l. " + nowInMillisecondi,
					"Via Normandia 66 " + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("29/05/1978"));
			
			societaService.inserisciNuovo(nuovaSocieta);
			
			nowInMillisecondi++;
			
			nuovoDipendente = new Dipendente("Ivan", "Bendotti", new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1980"), 250000);
			nuovoDipendente.setSocieta(nuovaSocieta);
			
			dipendenteService.inserisciNuovo(nuovoDipendente);
			if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1)
				throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("testInserisciDipendenti........OK");
	}

}
