package br.ufscar.dc.dsw;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import br.ufscar.dc.dsw.dao.IEditoraDAO;
import br.ufscar.dc.dsw.dao.ILivroDAO;
import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Livro;
import java.util.List;


@SpringBootApplication
public class LivrariaJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(LivrariaJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LivrariaJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IEditoraDAO editoraDAO, ILivroDAO livroDAO) {
		return (args) -> {

			// Recupere todos livros


			Editora editora1 = new Editora("87.557.922/0001-82", "Seguinte");
			editoraDAO.save(editora1);
			log.info("Editora adicionada: " + editora1.toString());

			Editora editoraSeguinte = editoraDAO.findByNome("Seguinte");
			if (editoraSeguinte != null) {
				Livro livro1 = new Livro("O Dia do Curinga", "Jostein Gaarder", 1996, new BigDecimal("29.99"),
						editoraSeguinte);
				livroDAO.save(livro1);
				log.info("Livro adicionado: " + livro1.toString());
			} else {
				log.error("Editora 'Seguinte' não encontrada.");
			}

			Editora editoraCompanhiaDasLetras = editoraDAO.findByNome("Companhia das Letras");
			if (editoraCompanhiaDasLetras != null) {
				Livro livro2 = new Livro("A Revolução dos Bichos", "George Orwell", 2007, new BigDecimal("23.90"),
						editoraCompanhiaDasLetras);
				livroDAO.save(livro2);
				log.info("Livro adicionado: " + livro2.toString());
			} else {
				log.error("Editora 'Companhia das Letras' não encontrada");
			}
			List<Livro> livrosOrdenadosPorPreco = livroDAO.findAll(Sort.by(Sort.Direction.ASC, "preco"));
			log.info("Livros recuperados por preço-- findAll():");
			log.info("---------------------------------------");
			for (Livro livro : livrosOrdenadosPorPreco) {
				log.info(livro.toString());
			}
			log.info("");
		};
	}
}
