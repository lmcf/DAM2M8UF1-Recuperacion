package com.example.examenrecuperacion;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class listaValoraciones extends AppCompatActivity implements valorarTalleres.OnFragmentInteractionListener{
    Button nueva_valoracion;
    valorarTalleres valorarTalleres;

    RecyclerView recyclerView;
    List<valoraciones> lista;
    RecyclerAdapter recyclerAdapter;
    FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_valoraciones);

        final String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("str");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("str");
        }

        lista = new ArrayList<>();

        // Recycler
        recyclerView = findViewById(R.id.comentariosTaller);
        db = FirebaseDatabase.getInstance();

        // Mostrar Recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        Log.i("LOSSS",newString);
        db.getReference().child(newString).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // Este Metodo cada vez que encuentra un nodo saltara este metodo y
                // retornara toodo lo que este en ese nodo
                // Datasnapshot compacta la info y eres tu quien debe saber lo que va venir
                // Se ha de mapear

                // Guardo en la arraylist el datasnapshot de tipo reserva
                lista.add(dataSnapshot.getValue(valoraciones.class));
                Log.i("RESERVAS", dataSnapshot.getValue(valoraciones.class).toString());

                // Aviso al Recycler que hay un nuevo elemento que hay que mostrar
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nueva_valoracion = findViewById(R.id.nuevoComentario);

        nueva_valoracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorarTalleres = new valorarTalleres().newInstance(newString);
                getSupportFragmentManager().beginTransaction().add(R.id.valorar,valorarTalleres).commit();
            }
        });
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

            valoraciones res = lista.get(i);
            recyclerHolder.valora.setText(res.getValor());
            recyclerHolder.coment.setText(res.getComents());

        }

        // Metodo que devuelve la cantidad de objectos de la array.
        // Y sabremos cuantas veces recorrera el bucle que pinta los datos
        @Override
        public int getItemCount() {
            return lista.size();
        }


        /* MusicaHolder es el bloque donde pinto la info de cada objeto de cojo de la array */
        class RecyclerHolder extends RecyclerView.ViewHolder{
            TextView valora, coment;

            public RecyclerHolder(LayoutInflater layoutInflater, ViewGroup parent) { // Busca el itemview
                super(layoutInflater.inflate(R.layout.valoraholder, parent,false));

                valora = itemView.findViewById(R.id.valora);
                coment = itemView.findViewById(R.id.coment);



            }
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
