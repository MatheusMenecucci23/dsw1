package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Filme;

public interface IFilmeService {
	
	Filme findById(Long id);
	List<Filme> findAll();
	void save(Filme estado);
	void delete(Long id);
	
	List<Filme> findByLocadora(Long id);
	List<Filme> findByNome(String nome);
}