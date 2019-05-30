package com.example.examenrecuperacion;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements FragmentBienvenida.OnFragmentInteractionListener {
    FragmentBienvenida bienvenida;

    String talleres;
    String marca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MiHilo hilo = new MiHilo();
        hilo.execute("https://jdarestaurant.firebaseio.com/talleres.json");


        bienvenida = new FragmentBienvenida().newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,bienvenida).commit();

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MiHilo extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection;
            URL url;
            connection = null;
            String result;
            result ="";

            try{

                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while(data != -1){
                    result += (char) data;
                    data = inputStream.read();
                }

            }catch (Exception e){

                e.printStackTrace();

            }

            Log.i("RESULT", result);

            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("marcas");

                Log.i("WEATHER", jsonArray.toString());

                for(int i=0; i<jsonArray.length(); i++){

                    JSONObject jsonitem = jsonArray.getJSONObject(i);
                    //tiempo.setText(jsonitem.getString("description"));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


}
