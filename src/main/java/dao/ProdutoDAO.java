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

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, categoria = ?, preco = ?, estoque = ? " +
                "WHERE id = ? ";

        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.setLong(5, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarProduto(Long id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Produto> buscaPersonalizadaProdutos(String nome) {
        String sql = "SELECT * FROM produto WHERE nome LIKE ? ";
        Connection connection = ConnectionFactory.getConnection();

        List<Produto> produtos = new ArrayList<>(1);

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");

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
