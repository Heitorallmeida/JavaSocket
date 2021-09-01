package models;

import java.util.Date;
import java.util.HashMap;

public class Pedido {
    HashMap<Produto, Integer> produtos;
    Double preco;
    Date data;

    public HashMap<Produto, Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(HashMap<Produto, Integer> produtos) {
        this.produtos = produtos;
    }

    public Double getPreço() {
        return preco;
    }

    public void setPreço(Double preço) {
        this.preco = preço;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


}
