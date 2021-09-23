package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class
Pedido {
    private ArrayList<Produto> produtos;
    private Double preco = 0.0;
    private Date data;

    public Pedido(ArrayList<Produto> produtos, Double preco, Date data) {
        this.produtos = produtos;
        this.preco = preco;
        this.data = data;
    }

    public void add_produto(Produto produto){
        this.produtos.add(produto);
    }

    public void total_pedido(Produto produto, int qntd){
        int flag = 0;
        Double soma_produto_atual = 0.0;
        for(int i = 0; i < this.produtos.size(); i++){
            if(this.produtos.get(i) == produto && flag == 0){
                //System.out.println("antes " + this.preco);
                soma_produto_atual = produto.getPreco()*qntd;
                //System.out.println("depois " + soma_produto_atual);
                this.preco = this.preco + soma_produto_atual;
                flag = 1;
            }
            if(flag == 1 && this.produtos.get(i) != produto){
                this.preco = this.preco + soma_produto_atual;
            }
        }
    }

    public Produto busca_Ultimo_produto(){
        return this.produtos.get(this.produtos.size()-1);
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


}
