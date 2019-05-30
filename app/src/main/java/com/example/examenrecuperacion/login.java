package com.example.examenrecuperacion;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.w3c.dom.Text;

import static com.example.examenrecuperacion.R.id.user;

public class login extends AppCompatActivity {

    MyBBDD_Schema sche;
    MyBBDD_Helper help;
    SQLiteDatabase db;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.password);


        help = new MyBBDD_Helper(this);

        db = help.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("USERNAME",user.toString());
        cv.put("PASSWORD",pass.toString());

        db.insert("USUARIS", null,cv);




    }
}
