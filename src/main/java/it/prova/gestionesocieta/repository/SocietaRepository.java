package it.prova.gestionesocieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Societa;

public interface SocietaRepository extends CrudRepository<Societa, Long>, QueryByExampleExecutor<Societa>{
	
	@Query("SELECT DISTINCT s FROM Societa s LEFT JOIN s.dipendenti d WHERE d.redditoAnnuoLordo >= 30000")
	List<Societa> findAllCompanyWhereHisEmployeesHaveRALGreaterOrEqualsThan30000() throws Exception;
	
}
