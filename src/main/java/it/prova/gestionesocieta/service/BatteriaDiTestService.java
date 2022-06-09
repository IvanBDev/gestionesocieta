package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;

	@Autowired
	private DipendenteService dipendenteService;

	public void testInserisciNuovaSocieta() {
		
		Societa nuovaSocieta = null;
		try {
			nuovaSocieta = new Societa("Solving s.p.a", "Via Kepler 19",
					new SimpleDateFormat("dd/MM/yyyy").parse("05/09/1889"));

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

}
