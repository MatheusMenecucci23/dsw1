package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.IFilmeDAO;
import br.ufscar.dc.dsw.dao.IProdutoraDAO;
import br.ufscar.dc.dsw.domain.Filme;
import br.ufscar.dc.dsw.domain.Produtora;

import java.math.BigDecimal;

@SpringBootApplication
public class FilmeRSApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmeRSApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IFilmeDAO filmeRepository, IProdutoraDAO produtoraRepository) {
        return (args) -> {
            // Criar uma produtora
            Produtora produtora = new Produtora();
            produtora.setNome("Exemplo Produtora");
            produtora.setCNPJ("43366749856");
            produtoraRepository.save(produtora);

            // Criar um filme
            Filme filme = new Filme();
            filme.setTitulo("Exemplo Filme");
            filme.setDiretor("Diretor do Filme");
            filme.setAno(2023);
            filme.setPreco(BigDecimal.valueOf(20.0));
            filme.setProdutora(produtora);
            filmeRepository.save(filme);

            System.out.println("Dados inseridos com sucesso!");
        };
    }
}
