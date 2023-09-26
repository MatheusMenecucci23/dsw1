package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Filme;
import br.ufscar.dc.dsw.domain.Produtora;

@SuppressWarnings("unchecked")
public interface IFilmeDAO extends CrudRepository<Filme, Long> {
	
	Filme findById(long id);
	
	List<Filme> findAll();
	
	Filme save(Filme filme);

	void deleteById(Long id);
	
	public List<Filme> findByTituloLikeIgnoreCase(String titulo);
	
	@Query("select f from Filme f where produtora = :produtora")
	public List<Filme> findByLocadora(@Param("produtora") Produtora produtora);
}