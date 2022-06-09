package it.prova.gestionesocieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente> {

	@Query(value = "SELECT * FROM dipendente d LEFT JOIN societa s ON d.societa_id = s.id HAVING s.dataFondazione < '1990-01-01' AND MIN(d.dataAssunzione)", nativeQuery = true)
	public Dipendente theOldestEmployeeInTheCompany();

}
