package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.domain.Filme;

public class FilmeDAO extends GenericDAO {

    public void insert(Filme filme) {

        String sql = "INSERT INTO Filme (titulo, diretor, ano, preco, produtora_id) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, filme.getTitulo());
            statement.setString(2, filme.getDiretor());
            statement.setInt(3, filme.getAno());
            statement.setFloat(4, filme.getPreco());
            statement.setLong(5, filme.getProdutora().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Filme> getAll() {

        List<Filme> listafilmes = new ArrayList<>();

        String sql = "SELECT * from Filme l, Produtora e where l.produtora_ID = e.ID order by l.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titulo = resultSet.getString("titulo");
                String diretor = resultSet.getString("diretor");
                int ano = resultSet.getInt("ano");
                float preco = resultSet.getFloat("preco");
                Long produtora_id = resultSet.getLong(6);
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Produtora produtora = new Produtora(produtora_id, cnpj, nome);
                Filme filme = new Filme(id, titulo, diretor, ano, preco, produtora);
                listafilmes.add(filme);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listafilmes;
    }

    public void delete(Filme filme) {
        String sql = "DELETE FROM Filme where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, filme.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Filme filme) {
        String sql = "UPDATE Filme SET titulo = ?, diretor = ?, ano = ?, preco = ?";
        sql += ", produtora_id = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, filme.getTitulo());
            statement.setString(2, filme.getDiretor());
            statement.setInt(3, filme.getAno());
            statement.setFloat(4, filme.getPreco());
            statement.setFloat(5, filme.getProdutora().getId());
            statement.setLong(6, filme.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Filme get(Long id) {
        Filme filme = null;

        String sql = "SELECT * from Filme f, Produtora e where f.id = ? and f.PRODUTORA_ID = e.ID";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String diretor = resultSet.getString("diretor");
                int ano = resultSet.getInt("ano");
                float preco = resultSet.getFloat("preco");

                Long FilmeID = resultSet.getLong("produtora_id");
                Produtora produtora = new ProdutoraDAO().get(FilmeID);

                filme = new Filme(id, titulo, diretor, ano, preco, produtora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filme;
    }
}
