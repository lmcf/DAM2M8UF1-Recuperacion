package com.example.examenrecuperacion;

public class taller {
    private String nombreTaller;
    private String marcas;

    public taller(String nombreTaller, String marcas) {
        this.nombreTaller = nombreTaller;
        this.marcas = marcas;
    }

    public String getNombreTaller() {
        return nombreTaller;
    }

    public void setNombreTaller(String nombreTaller) {
        this.nombreTaller = nombreTaller;
    }

    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        marcas = marcas;
    }
}
