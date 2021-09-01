package models;

import java.util.HashMap;

public class Catalogo {
    HashMap<Produto, Integer> produtos;

    public Catalogo(HashMap<Produto, Integer> produtos) {
        this.produtos = produtos;
    }

    public void setProdutos(HashMap<Produto, Integer> produtos) {
        this.produtos = produtos;
    }

    public HashMap<Produto, Integer> getProdutos() {
        return produtos;
    }
}
