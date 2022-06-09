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

			if (societaDaEliminare.getId() != null) {
				System.out.println("Inserimento effettuato");
			}

			societaService.removeConEccezione(societaDaEliminare);

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
			nuovaSocieta = new Societa("Best Life s.r.l. " + nowInMillisecondi, "Via Normandia 66 " + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("29/05/1978"));

			societaService.inserisciNuovo(nuovaSocieta);

			nowInMillisecondi++;

			nuovoDipendente = new Dipendente("Ivan" + nowInMillisecondi, "Bendotti" + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1980"), 250000);
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

	public void testModificaDipendente() {

		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = null;
		Dipendente nuovoDipendente = null;
		try {
			nuovaSocieta = new Societa("Best Programming s.p.a. " + nowInMillisecondi,
					"Via Napoli 88 " + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("05/05/1989"));

			societaService.inserisciNuovo(nuovaSocieta);

			nowInMillisecondi++;

			nuovoDipendente = new Dipendente("Dario" + nowInMillisecondi, "Moccia" + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1980"), 250000);
			nuovoDipendente.setSocieta(nuovaSocieta);

			dipendenteService.inserisciNuovo(nuovoDipendente);
			if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1)
				throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nowInMillisecondi++;

		Dipendente dipendenteDaModificare = nuovoDipendente;
		dipendenteDaModificare.setNome("Luigi " + nowInMillisecondi);
		dipendenteDaModificare.setCognome("Verdi " + nowInMillisecondi);
		try {
			dipendenteDaModificare.setDataAssunzione(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/1980"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dipendenteDaModificare.setRedditoAnnuoLordo(27500);

		dipendenteService.aggiorna(dipendenteDaModificare);

		System.out.println("testModificaDipendente..................OK");
	}

	public void testSocietaConDipendentiConRalAPartireDa30000() {
		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = null;
		Societa nuovaSocieta2 = null;
		Societa nuovaSocieta3 = null;
		Dipendente nuovoDipendente = null;
		Dipendente nuovoDipendente2 = null;
		Dipendente nuovoDipendente3 = null;
		try {

			nuovaSocieta = new Societa("Best Programming s.p.a. " + nowInMillisecondi,
					"Via Napoli 88 " + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("05/05/1989"));

			nuovaSocieta2 = new Societa("Best Food s.r.l. " + nowInMillisecondi, "Via Ostiense 56 " + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("18/09/1900"));

			nuovaSocieta3 = new Societa("Best Car s.p.a. " + nowInMillisecondi, "Via Verona 100 " + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("27/03/1980"));

			societaService.inserisciNuovo(nuovaSocieta);
			societaService.inserisciNuovo(nuovaSocieta2);
			societaService.inserisciNuovo(nuovaSocieta3);

			nowInMillisecondi++;

			nuovoDipendente = new Dipendente("Dario" + nowInMillisecondi, "Moccia" + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1980"), 25000);

			nuovoDipendente2 = new Dipendente("Flavio" + nowInMillisecondi, "Girolami" + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("07/02/1978"), 36000);

			nuovoDipendente3 = new Dipendente("Mario" + nowInMillisecondi, "Rossi" + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1999"), 31000);

			nuovoDipendente.setSocieta(nuovaSocieta);
			nuovoDipendente2.setSocieta(nuovaSocieta2);
			nuovoDipendente3.setSocieta(nuovaSocieta3);

			dipendenteService.inserisciNuovo(nuovoDipendente);
			dipendenteService.inserisciNuovo(nuovoDipendente2);
			dipendenteService.inserisciNuovo(nuovoDipendente3);
			if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1 && nuovoDipendente2.getId() == null
					|| nuovoDipendente2.getId() < 1 || nuovoDipendente3.getId() == null || nuovoDipendente3.getId() < 1)
				throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Societa> result = societaService.cercaTutteLeSocietaConDipendentiAventiUnaRALUgualeOSuperioreA30000();

		result.forEach(item -> System.out.println(item));

		System.out.println("testSocietaConDipendentiConRalAPartireDa30000..................OK");
	}

	public void testDipendentePiuAnziano() {
		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = null;
		Societa nuovaSocieta2 = null;
		Societa nuovaSocieta3 = null;
		Dipendente nuovoDipendente = null;
		Dipendente nuovoDipendente2 = null;
		Dipendente nuovoDipendente3 = null;
		try {

			nuovaSocieta = new Societa("SAA " + nowInMillisecondi,
					"Via A 80 " + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2001"));

			nuovaSocieta2 = new Societa("SBB " + nowInMillisecondi, "Via B 50 " + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("18/09/1900"));

			nuovaSocieta3 = new Societa("SCC " + nowInMillisecondi, "Via C 99 " + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("27/03/1900"));

			societaService.inserisciNuovo(nuovaSocieta);
			societaService.inserisciNuovo(nuovaSocieta2);
			societaService.inserisciNuovo(nuovaSocieta3);

			nowInMillisecondi++;

			nuovoDipendente = new Dipendente("Franco" + nowInMillisecondi, "Merli" + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1980"), 25000);

			nuovoDipendente2 = new Dipendente("Aldo" + nowInMillisecondi, "Kelvin" + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("07/02/1978"), 36000);

			nuovoDipendente3 = new Dipendente("Luca" + nowInMillisecondi, "Rosi" + nowInMillisecondi,
					new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1999"), 31000);

			nuovoDipendente.setSocieta(nuovaSocieta);
			nuovoDipendente2.setSocieta(nuovaSocieta2);
			nuovoDipendente3.setSocieta(nuovaSocieta3);

			dipendenteService.inserisciNuovo(nuovoDipendente);
			dipendenteService.inserisciNuovo(nuovoDipendente2);
			dipendenteService.inserisciNuovo(nuovoDipendente3);
			if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1 && nuovoDipendente2.getId() == null
					|| nuovoDipendente2.getId() < 1 || nuovoDipendente3.getId() == null || nuovoDipendente3.getId() < 1)
				throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Dipendente result = dipendenteService.trovaIlDipendentePiuAnzianodelleAzienteFondatePrimaDel1990();
		System.out.println(result);
		
		System.out.println("testDipendentePiuAnziano..................OK");
	}

}
