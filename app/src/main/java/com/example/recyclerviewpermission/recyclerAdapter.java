package com.example.recyclerviewpermission;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<Permiso> permisoList;
    //Evento onitemclicklistener
    final recyclerAdapter.OnItemCliclkListener listener;
    public recyclerAdapter(ArrayList<Permiso> permisoList,recyclerAdapter.OnItemCliclkListener listener){
        this.permisoList = permisoList;
        this.listener = listener;
    }
    //OnItemClickListener
    public interface OnItemCliclkListener {
        void OnItemClick();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView namePermiso;
        private TextView accion;
        Permiso permisoholder;

        public MyViewHolder(final View view){
            super(view);
            namePermiso = view.findViewById(R.id.Permiso);
            accion = view.findViewById(R.id.textView);
            accion.setOnClickListener(this::onClick);
        }
        public void setData(Permiso p) {
            permisoholder = p;
            namePermiso.setText(permisoholder.getPermiso());
            accion.setText("Link");
        }
        @Override
        public void onClick(View view) {
            listener.OnItemClick();
            Intent intent = permisoholder.getAccion();
            view.getContext().startActivity(intent);
        }
        void binData(final recyclerAdapter item){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick();
                }
            });
        }
    }


    //Metodos del adaptador
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String permiso = permisoList.get(position).getPermiso();
        holder.setData(permisoList.get(position));
        holder.namePermiso.setText(permiso);
    }

    @Override
    public int getItemCount() {
        return permisoList.size();
    }
}
