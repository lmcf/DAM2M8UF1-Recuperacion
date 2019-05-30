package com.example.examenrecuperacion;

public class MyBBDD_Schema {
    static final String SQLCreate =
            "CREATE TABLE "+ EntradaBBDD.TABLE_NAME + " (" +
                    "_ID INTEGER PRIMARY KEY, " +
                    EntradaBBDD.COLUMNA1 + " TEXT, " +
                    EntradaBBDD.COLUMNA2 + " TEXT) ";
    static final String SQLUpgrade =
            "DROP TABLE IF EXISTS "+ EntradaBBDD.TABLE_NAME;
    private MyBBDD_Schema(){};
    public class EntradaBBDD {
        static final String TABLE_NAME = "USUARIS";
        static final String COLUMNA1 = "USERNAME";
        static final String COLUMNA2 = "PASSWORD";
    }
}