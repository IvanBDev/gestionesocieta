package it.prova.gestionesocieta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.exception.SocietaConAbitantiException;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService {

	@Autowired
	private SocietaRepository societaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Societa> findAllSocieta() {
		// TODO Auto-generated method stub
		return (List<Societa>) societaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Societa caricaSingolaSocieta(Long id) {
		// TODO Auto-generated method stub
		return societaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Societa societaInstance) {
		// TODO Auto-generated method stub
		societaRepository.save(societaInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Societa societaInstance) {
		// TODO Auto-generated method stub
		societaRepository.save(societaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Societa societaInstance) {
		// TODO Auto-generated method stub
		societaRepository.delete(societaInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Societa> findByExample(Societa example) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);

		return (List<Societa>) societaRepository.findAll(Example.of(example, matcher));
	}

	@Override
	@Transactional
	public void removeConEccezione(Societa societaInstance) {
		// TODO Auto-generated method stub
		if (societaInstance.getDipendenti() != null && !societaInstance.getDipendenti().isEmpty())
			throw new SocietaConAbitantiException("Impossibile eliminare la societa con i dipendenti");

		societaRepository.delete(societaInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Societa> cercaTutteLeSocietaConDipendentiAventiUnaRALUgualeOSuperioreA30000() {
		// TODO Auto-generated method stub
		return societaRepository.findAllDistinctByDipendenti_ral();

	}
}
