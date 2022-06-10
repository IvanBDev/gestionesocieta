package it.prova.gestionesocieta.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	public DipendenteRepository dipendenteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		// TODO Auto-generated method stub
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendente(Long id) {
		// TODO Auto-generated method stub
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.save(dipendenteInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.save(dipendenteInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.delete(dipendenteInstance);
	}

	@Override
	public Dipendente trovaIlDipendentePiuAnzianodelleAzienteFondatePrimaDel1990() {
		// TODO Auto-generated method stub
		try {
			return dipendenteRepository.findFirstBySocieta_DataFondazioneBeforeOrderByDataAssunzione(
					new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1990"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> findByExample(Dipendente example) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);

		return (List<Dipendente>) dipendenteRepository.findAll(Example.of(example, matcher));
	}

}
