package com.example.examenrecuperacion;

public class valoraciones {

    private String valor;
    private String coments;

    public valoraciones() {
    }

    public valoraciones(String valor, String coments) {
        this.valor = valor;
        this.coments = coments;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getComents() {
        return coments;
    }

    public void setComents(String coments) {
        this.coments = coments;
    }
}
