package models;

import Utils.StatusCliente;

public class Status {
    String statusCliente;
    public String getStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(String statusCliente) {
        this.statusCliente = statusCliente;
    }

    public Status(String statusCliente) {
        this.statusCliente = statusCliente;
    }



}
