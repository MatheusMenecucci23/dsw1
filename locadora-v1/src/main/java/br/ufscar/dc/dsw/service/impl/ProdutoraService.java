package br.ufscar.dc.dsw.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IProdutoraDAO;
import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IProdutoraService;

@Service
@Transactional(readOnly = false)
public class ProdutoraService implements IProdutoraService {

	@Autowired
	IProdutoraDAO dao;

	@Override
	@Transactional(readOnly = true)
	public Produtora findById(long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produtora> findAll() {
		return dao.findAll();
	}

	@Override
	public void save(Produtora produtora) {
		dao.save(produtora);
	}
	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
}
