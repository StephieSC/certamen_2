package com.example.stephie.certamen2.presentador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stephie.certamen2.R;
import com.example.stephie.certamen2.modelo.Datos;

import java.util.List;

/**
 * Created by Stephie on 04-11-2016.
 */

public abstract class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder>{

        private List<Datos> mDataset;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView TituloView;
            public TextView DescripcionView;
            public TextView FechaView;
            public ViewHolder(View v) {
                super(v);
                TituloView = (TextView) v.findViewById(R.id.nombre_repo);
                DescripcionView = (TextView) v.findViewById(R.id.descripcion_repo);
               FechaView = (TextView) v.findViewById(R.id.ultima_actualizacion);
            }
        }

        public ListaAdapter(List<Datos> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public ListaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_repositorios, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Datos dato = mDataset.get(position);

            holder.TituloView.setText(dato.getTitulo());
            holder.DescripcionView.setText(dato.getDescripcion());
            holder.FechaView.setText(dato.getActualizacion());
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
}
