package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufscar.dc.dsw.domain.Editora;
import java.util.List;

@Repository
public interface IEditoraDAO extends  CrudRepository<Editora, Long> {
    Editora findByNome(String nome);
    List<Editora> findAll();
    Editora findById(long id);
}