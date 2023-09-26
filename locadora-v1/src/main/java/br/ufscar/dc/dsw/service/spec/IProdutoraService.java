package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Produtora;

public interface IProdutoraService {
	Produtora findById(long id);

	List<Produtora> findAll();

	void save(Produtora estado);

	void delete(Long id);
}