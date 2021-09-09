package Utils;

public enum StatusCliente {
    INICIAL("INICIAL"), CATALOGO_VISTO("CATALOGO_VISTO"),
    ESCOLHENDO_PRODUTO("ESCOLHENDO_PRODUTO"),
    FINALIZANDO_COMPRA("FINALIZANDO_COMPRA");

    private final String valor;

    StatusCliente(String valorOpcao){
        valor = valorOpcao;
    }
    public String getValor(){
        return valor;
    }
}
