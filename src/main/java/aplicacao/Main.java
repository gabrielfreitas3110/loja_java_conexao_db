package aplicacao;

import dao.ProdutoDAO;
import domain.Produto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        /*
        produtoDAO.inserir("Mouse", "Periférico", 150.0, 10);
        produtoDAO.inserir("Teclado", "Periférico", 200.0, 20);
        produtoDAO.inserir("Headset", "Periférico", 120.0, 30);

        List<Produto> produtos = produtoDAO.buscarProdutos();

        for(Produto produto : produtos) {
            System.out.println(produto.toString());
        }

        Produto produto = new Produto();
        produto.setId(19L);
        produto.setNome("Monitor");
        produto.setCategoria("Periférico");
        produto.setPreco(2100.00);
        produto.setEstoque(5);
        produtoDAO.atualizarProduto(produto);

        produtos = produtoDAO.buscarProdutos();

        for(Produto p : produtos) {
            System.out.println(p.toString());
        }

        produtoDAO.deletarProduto(13L);
        produtoDAO.deletarProduto(16L);
        produtoDAO.deletarProduto(20L);


         */
        List<Produto> produtos = produtoDAO.buscaPersonalizadaProdutos("ead");

        for(Produto p : produtos) {
            System.out.println(p.toString());
        }
    }
}
