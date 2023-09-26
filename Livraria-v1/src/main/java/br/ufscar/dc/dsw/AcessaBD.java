package br.ufscar.dc.dsw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD {

	public static void main(String[] args) {
		try {
			/* Setup para uso do banco de dados MySQL */
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Livraria";
			Connection con = DriverManager.getConnection(url, "root", "root");

			/* Adicionar uma nova editora */
			String cnpj = "87.557.922/0001-82";
			String nomeEditora = "Seguinte";
			adicionarEditora(con, cnpj, nomeEditora);

			/* Adicionar um novo livro */
			String tituloLivro1 = "O Dia do Curinga";
			String autorLivro1 = "Jostein Gaarder";
			int anoLivro1 = 1996;
			float precoLivro1 = 29.99f;
			int editoraLivro1 = 4;
			adicionarLivro(con, tituloLivro1, autorLivro1, anoLivro1, precoLivro1, editoraLivro1);

			/* Adicionar um novo livro */
			String tituloLivro2 = "A Revolução dos Bichos";
			String autorLivro2 = "George Orwell";
			int anoLivro2 = 2007;
			float precoLivro2 = 23.90f;
			int editoraLivro2 = 1;
			adicionarLivro(con, tituloLivro2, autorLivro2, anoLivro2, precoLivro2, editoraLivro2);

			/* Imprimir os livros ordenados pela coluna preço */
			imprimirLivrosOrdenadosPorPreco(con);

			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe do driver de conexão não foi encontrada!");
		} catch (SQLException e) {
			System.out.println("O comando SQL não pode ser executado!");
		}
	}

	private static void adicionarEditora(Connection con, String cnpj, String nomeEditora) throws SQLException {
		String sql = "INSERT INTO Editora (CNPJ, Nome) VALUES (?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, cnpj);
		stmt.setString(2, nomeEditora);
		stmt.executeUpdate();
		stmt.close();
		System.out.println("Editora adicionada com sucesso!");
	}

	private static void adicionarLivro(Connection con, String titulo, String autor, int ano, float preco,
			int editora) throws SQLException {
		String sql = "INSERT INTO Livro (Titulo, Autor, Ano, Preco, editora_id) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, titulo);
		stmt.setString(2, autor);
		stmt.setInt(3, ano);
		stmt.setFloat(4, preco);
		stmt.setInt(5, editora);
		stmt.executeUpdate();
		stmt.close();
		System.out.println("Livro adicionado com sucesso!");
	}
	private static void imprimirLivrosOrdenadosPorPreco(Connection con) throws SQLException {
		String sql = "SELECT Livro.Titulo, Livro.Autor, Livro.Ano, Livro.Preco, Editora.Nome " +
					 "FROM Livro JOIN Editora ON Livro.editora_id = Editora.id " +
					 "ORDER BY Livro.Preco";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
	
		while (rs.next()) {
			String titulo = rs.getString("Titulo");
			String autor = rs.getString("Autor");
			int ano = rs.getInt("Ano");
			float preco = rs.getFloat("Preco");
			String nomeEditora = rs.getString("Nome");
	
			System.out.print(titulo);
			System.out.print(", " + autor);
			System.out.print(", " + nomeEditora);
			System.out.print(", " + ano);
			System.out.println(" (R$ " + preco + ")");
		}
	
		stmt.close();
		rs.close();
	}
	
}
