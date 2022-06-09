package it.prova.gestionesocieta.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente>{
	
	@Query("SELECT Min(d) FROM Dipendente d LEFT JOIN d.societa s WHERE s.dataFondazione < '1990-01-01'")
	public Set<Dipendente> theOldestEmployeeInTheCompanySince1990() throws Exception;
	
}
