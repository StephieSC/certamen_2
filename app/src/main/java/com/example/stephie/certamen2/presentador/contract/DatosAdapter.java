package com.example.stephie.certamen2.presentador.contract;

import android.view.View;
import android.view.ViewGroup;

import com.example.stephie.certamen2.modelo.Datos;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Stephie on 04-11-2016.
 */

public interface DatosAdapter {
    public List<Datos> parseJson(JSONArray Result);
    public View getView(int position, View convertView, ViewGroup parent);
    public int getCount();
}
