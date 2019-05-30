package com.example.examenrecuperacion;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class talleresActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<taller> lista;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);

        lista = new ArrayList<>();

        // Recycler
        recyclerView = findViewById(R.id.talleresview);

        taller t1 = new taller("TallerA","Ranault, Porshe, Fiatt");
        taller t2 = new taller("TallerB","Ranault, Porshe, Fiatt");
        taller t3 = new taller("TallerC","Porshe, Fiatt");
        taller t4 = new taller("TallerD","Porshe, Fiatt");

        lista.add(t1);
        lista.add(t2);
        lista.add(t3);
        lista.add(t4);

        // Mostrar Recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

    }

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction fmt = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.talleres:
                Intent intent = new Intent(this, talleresActivity.class);
                startActivity(intent);
                break;

            case R.id.login:
                Intent intent2 = new Intent(this,login.class);
                startActivity(intent2);
                break;

        }

        fmt.commit();

        return super.onOptionsItemSelected(item);
    }

    // El Adapter se encarga de enlazar el ViewHolder con el listado de datos, los prepara para pintarlos
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>{

        public RecyclerAdapter() {

        }

        @Override
        // Dibuja en pantalla los viewholder (elementos de la lista)
        public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = getLayoutInflater();

            return new RecyclerHolder(layoutInflater, viewGroup);
        }

        @Override
        // Pones los datos en cada viewholder
        public void onBindViewHolder(@NonNull RecyclerHolder recyclerHolder , int i) {

            taller res = lista.get(i);
            recyclerHolder.nombreTaller.setText(res.getNombreTaller());
            recyclerHolder.marcas.setText(res.getMarcas());

        }

        // Metodo que devuelve la cantidad de objectos de la array.
        // Y sabremos cuantas veces recorrera el bucle que pinta los datos
        @Override
        public int getItemCount() {
            return lista.size();
        }


        /* MusicaHolder es el bloque donde pinto la info de cada objeto de cojo de la array */
        class RecyclerHolder extends RecyclerView.ViewHolder{
            TextView nombreTaller, marcas;

            public RecyclerHolder(LayoutInflater layoutInflater, ViewGroup parent) { // Busca el itemview
                super(layoutInflater.inflate(R.layout.recyclerholder, parent,false));

                nombreTaller = itemView.findViewById(R.id.nombreTaller);
                marcas = itemView.findViewById(R.id.marcas);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), listaValoraciones.class);
                        intent.putExtra("str",nombreTaller.getText().toString());
                        startActivity(intent);
                    }
                });

            }
        }

    }
}
