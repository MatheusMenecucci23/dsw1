package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IFilmeDAO;
import br.ufscar.dc.dsw.dao.IProdutoraDAO;
import br.ufscar.dc.dsw.domain.Filme;
import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IFilmeService;

@Service
@Transactional(readOnly = false)
public class FilmeService implements IFilmeService {

	@Autowired
	IFilmeDAO dao;
	
	@Autowired
	IProdutoraDAO estadoDAO;

	@Override
	@Transactional(readOnly = true)
	public Filme findById(Long id) {
		return dao.findById(id.longValue());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Filme> findAll() {
		return dao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Filme> findByLocadora(Long id) {
		Produtora estado = estadoDAO.findById(id.longValue());
		return dao.findByLocadora(estado);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Filme> findByNome(String titulo) {
		return dao.findByTituloLikeIgnoreCase(titulo);
	}

	@Override
	public void save(Filme filme) {
		dao.save(filme);
	}
	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
}
