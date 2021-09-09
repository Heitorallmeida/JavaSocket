package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Estoque {
    HashMap<Produto, Integer> produtos;

    public Estoque(HashMap<Produto, Integer> produtos) {
        this.produtos = produtos;
    }

    public void setProdutos(HashMap<Produto, Integer> produtos) {
        this.produtos = produtos;
    }

    public HashMap<Produto, Integer> getProdutos() {
        return produtos;
    }

    public ArrayList<String> retornaCatalogo(){
        ArrayList<String> catalogo =  new ArrayList<String>();

        Iterator<Produto> produtosIterator = produtos.keySet().iterator();
        while(produtosIterator.hasNext()) {
            Produto produto = produtosIterator.next();
            System.out.println(produto.getNome());
            String item = produto.getNome() + "-----" +produto.getPreco();
            catalogo.add(item);
        }

        return catalogo;
    }

}
