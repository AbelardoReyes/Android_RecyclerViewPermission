package com.example.recyclerviewpermission;

import android.content.Intent;

import java.io.Serializable;

public class Permiso implements Serializable {
    private String permiso;
    private Intent accion;

    public Permiso(String permiso, Intent accion) {
        this.permiso = permiso;
        this.accion = accion;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public Intent getAccion() {
        return accion;
    }

    public void setAccion(Intent accion) {
        this.accion = accion;
    }
}
