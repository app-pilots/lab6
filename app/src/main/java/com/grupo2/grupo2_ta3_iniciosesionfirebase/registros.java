package com.grupo2.grupo2_ta3_iniciosesionfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.Toast;

public class registros extends AppCompatActivity {
    DatabaseReference db_reference;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        context=getApplicationContext();
        db_reference = FirebaseDatabase.getInstance().getReference().child("Registros");

        leerRegistros();
    }

    public void leerRegistros(){

        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mostrarRegistrosPorPantalla(snapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Error",error.toException().toString());
            }
        });
    }

    public void mostrarRegistrosPorPantalla(DataSnapshot snapshot){

        LinearLayout contTemp = (LinearLayout) findViewById(R.id.ContenedorTemp);
        LinearLayout contAxis = (LinearLayout) findViewById(R.id.ContenedorAxis);
        String tempVal = String.valueOf(snapshot.child("temp").getValue());
        String axisVal = String.valueOf(snapshot.child("axi").getValue());
        TextView temp = new TextView(getApplicationContext());
        temp.setText(tempVal+" °C");
        contTemp.addView(temp);
        TextView axis = new TextView(getApplicationContext());
        axis.setText(axisVal);
        contAxis.addView(axis);
    }






}