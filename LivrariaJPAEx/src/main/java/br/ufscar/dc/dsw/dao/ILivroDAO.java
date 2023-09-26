package br.ufscar.dc.dsw.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Livro;

public interface ILivroDAO extends JpaRepository<Livro, Long>{
}