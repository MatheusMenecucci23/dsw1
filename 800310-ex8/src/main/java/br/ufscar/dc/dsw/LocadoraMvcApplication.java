package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.IProdutoraDAO;
import br.ufscar.dc.dsw.dao.IFilmeDAO;
import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.domain.Filme;

@SpringBootApplication
public class LocadoraMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocadoraMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IProdutoraDAO produtoraDAO, IFilmeDAO filmeDAO) {
        return (args) -> {

            Produtora p1 = new Produtora();
            p1.setCNPJ("55.789.390/0008-99");
            p1.setNome("Warner Bros. Pictures");
            produtoraDAO.save(p1);

            Produtora p2 = new Produtora();
            p2.setCNPJ("71.150.470/0001-40");
            p2.setNome("Universal Pictures");
            produtoraDAO.save(p2);

            Produtora p3 = new Produtora();
            p3.setCNPJ("32.106.536/0001-82");
            p3.setNome("20th Century Studios");
            produtoraDAO.save(p3);

            Filme f1 = new Filme();
            f1.setTitulo("Inception");
            f1.setDiretor("Christopher Nolan");
            f1.setAno(2010);
            f1.setPreco(BigDecimal.valueOf(29.9));
            f1.setProdutora(p1);
            filmeDAO.save(f1);

            Filme f2 = new Filme();
            f2.setTitulo("Jurassic Park");
            f2.setDiretor("Steven Spielberg");
            f2.setAno(1993);
            f2.setPreco(BigDecimal.valueOf(34.9));
            f2.setProdutora(p2);
            filmeDAO.save(f2);

            Filme f3 = new Filme();
            f3.setTitulo("Avatar");
            f3.setDiretor("James Cameron");
            f3.setAno(2009);
            f3.setPreco(BigDecimal.valueOf(39.9));
            f3.setProdutora(p3);
            filmeDAO.save(f3);
        };
    }
}
