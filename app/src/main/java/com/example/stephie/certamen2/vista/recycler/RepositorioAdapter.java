package com.example.stephie.certamen2.vista.recycler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stephie.certamen2.R;
import com.example.stephie.certamen2.modelo.Datos;

import java.util.List;

/**
 * Created by Stephie on 16-10-2016.
 */
public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.RepositorioViewHolder>{

    private List<Datos> items;

    public RepositorioAdapter(List<Datos> items) {
        this.items = items;
    }

    public class RepositorioViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView fecha;
        public TextView descripcion;

        public RepositorioViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_repo);
            fecha = (TextView) v.findViewById(R.id.ultima_actualizacion);
            descripcion = (TextView) v.findViewById(R.id.descripcion_repo);
        }
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RepositorioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.repositorio, viewGroup, false);
        return new RepositorioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RepositorioViewHolder viewHolder, int i) {
        Datos datos = items.get(i);
        viewHolder.nombre.setText(datos.getTitulo());
        viewHolder.fecha.setText(datos.getActualizacion());
        viewHolder.descripcion.setText(datos.getDescripcion());
    }

}
