package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "Livro")
public class Livro {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 60)
	private String titulo;

	@Column(nullable = false, unique = true, length = 60)
	private String autor;
    
	@Column(nullable = false, length = 5)
	private Integer ano;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.0")
	private BigDecimal preco;
    
	@ManyToOne
	@JoinColumn(name = "editora_id")
	private Editora editora;
	//insert into Livro(titulo, autor, ano, preco, editora_id) values ('Ensaio sobre a Cegueira', 'José Saramago', 1995, 54.9, 1);
	public Livro() {
		setTitulo("Sem titulo");
		setAutor("Sem autor");
		setAno(0000);
		setPreco(new BigDecimal(29.99));
		setEditora(new Editora("00000","teste"));
	}

	public Livro(String Titulo,String Autor,int Ano,BigDecimal Preco,Editora Editora) {
		setTitulo(Titulo);
		setAutor(Autor);
		setAno(Ano);
		setPreco(Preco);
		setEditora(Editora);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("Título: " + titulo + ", ");
		sb.append("Autor: " + autor + ", ");
		sb.append("Ano: " + ano + ", ");
		sb.append("Preço: " + preco + ", ");
		sb.append("Editora: " + editora);
		sb.append("]");
		return sb.toString(); 
	}
}