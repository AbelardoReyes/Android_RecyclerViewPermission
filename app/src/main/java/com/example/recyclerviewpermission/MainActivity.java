package com.example.recyclerviewpermission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Permiso> permisoList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.reciclador);
        permisoList = new ArrayList<>();
        setPermisoInfo();
        setAdapter();
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(permisoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setPermisoInfo() {
        permisoList.add(new Permiso("Abrir agenda", new Intent(Intent.ACTION_DIAL, Uri.parse("tel:8714733996"))));
        permisoList.add(new Permiso("Realizar llamada", new Intent(Intent.ACTION_CALL, Uri.parse("tel:8714733996"))));
        permisoList.add(new Permiso("Abrir link", new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.mx/"))));
    }
}