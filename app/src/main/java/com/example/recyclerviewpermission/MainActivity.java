package com.example.recyclerviewpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

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
        recyclerAdapter adapter = new recyclerAdapter(permisoList, new recyclerAdapter.OnItemCliclkListener() {
            @Override
            public void OnItemClick() {
                pedirPermisos();
            }
        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==255){
            if(permissions.length>=0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permisos Aceptados",Toast.LENGTH_SHORT).show();
                ejecutarLlamada();
            }else{
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CALL_PHONE)){
                    Toast.makeText(this,"Permisos Rechazado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Tu necesitas habilitar los permisos de manera manual",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void ejecutarLlamada() {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8714733996"));
        startActivity(i);
    }
    private void pedirPermisos(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            ejecutarLlamada();
        }else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},255);
        }
    }
}