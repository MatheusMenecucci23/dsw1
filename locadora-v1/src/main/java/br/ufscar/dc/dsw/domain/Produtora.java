
package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "Produtora")
public class Produtora extends AbstractEntity<Long>{
    
	@Column(nullable = false, unique = true, length = 60)
	private String CNPJ;
	

	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	// @OneToMany(mappedBy = "produtora")
	// private List<Filme> filmes;
	
	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// public List<Filme> getFilmes() {
	// 	return filmes;
	// }

	// public void setFilmes(List<Filme> filmes) {
	// 	this.filmes = filmes;
	// }
 }