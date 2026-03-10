package aplicacao;

import dao.ProdutoDAO;
import domain.Produto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        produtoDAO.inserir("Mouse", "Periférico", 150.0, 10);
        produtoDAO.inserir("Teclado", "Periférico", 200.0, 20);
        produtoDAO.inserir("Headset", "Periférico", 120.0, 30);

        List<Produto> produtos = produtoDAO.buscarProdutos();

        for(Produto produto : produtos) {
            System.out.println(produto.toString());
        }
    }
}
