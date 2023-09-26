package br.ufscar.dc.dsw.domain;

public class Filme {

    private Long id;
    private String titulo;
    private String diretor;
    private Integer ano;
    private Float preco;
    private Produtora produtora;

    public Filme(Long id) {
        this.id = id;
    }

    public Filme(String titulo, String diretor, Integer ano, Float preco,
            Produtora produtora) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.ano = ano;
        this.preco = preco;
        this.produtora = produtora;
    }

    public Filme(Long id, String titulo, String diretor, Integer ano, 
            Float preco, Produtora produtora) {
        this(titulo, diretor, ano, preco, produtora);
        this.id = id;
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

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Produtora getProdutora() {
        return produtora;
    }

    public void setProdutora(Produtora produtora) {
        this.produtora = produtora;
    }
}
