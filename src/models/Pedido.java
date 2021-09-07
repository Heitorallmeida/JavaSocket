package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Pedido {
    //HashMap<Produto, Integer> produtos; não seria melhor uma lista de produtos pra compor o pedido?
    private ArrayList<Produto> produtos;
    private Double preco = 0.0;
    private Date data;

    public void add_produto(Produto produto){
        this.produtos.add(produto);
    }

    public Double total_pedido(){
        for(int i = 0; i < this.produtos.size(); i++){
            this.preco = this.produtos.get(i).getPreco() + this.preco;
        }
        return this.preco;
    }

    public void remove_produto(Produto produto){
        for(int i = 0; i < this.produtos.size(); i++){
            if(this.produtos.get(i).getNome().equals(produto.getNome())){
                this.preco = this.preco - produto.getPreco();
                this.produtos.remove(i);
            }
        }
    }

    public ArrayList<Produto> getProdutos() { return produtos; }

    public void setProdutos(ArrayList<Produto> produtos) { this.produtos = produtos; }

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
