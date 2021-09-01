package models;

public class Produto {
    private String nome;
    private double preco;
    private String marca;
    private String categoria;

    public Produto(String nome, double preco, String marca, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
