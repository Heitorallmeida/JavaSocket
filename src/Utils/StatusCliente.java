package Utils;

public enum StatusCliente {
    INICIAL("INICIAL"), ESCOLHENDO_PRODUTO("ESCOLHENDO_PRODUTO"),
    QUANTIDADE_PRODUTO("QUANTIDADE_PRODUTO");

    private final String valor;

    StatusCliente(String valorOpcao){
        valor = valorOpcao;
    }
    public String getValor(){
        return valor;
    }
}
