package dao;

import domain.Produto;
import jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    //INSERT, READ (SELECT), Update, DELETE

    public void inserir(String nome, String categoria, double preco, int estoque) {
        String sql = "INSERT INTO produto (nome, categoria, preco, estoque) " +
                "VALUES(?, ?, ?, ?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, categoria);
            stmt.setDouble(3, preco);
            stmt.setInt(4, estoque);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConnectionFactory.closeConnection(connection);
    }

    public List<Produto> buscarProdutos() {
        String sql = "SELECT * FROM produto";
        Connection connection = ConnectionFactory.getConnection();

        List<Produto> produtos = new ArrayList<>(1);

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setEstoque(rs.getInt("estoque"));

                produtos.add(produto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConnectionFactory.closeConnection(connection);

        return produtos;
    }
}
