package com.example.stephie.certamen2;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Stephie on 30-09-2016.
 */
public class DatosAdapter{
    // Atributos
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    private static final String URL_BASE = " https://api.github.com/users/";
    private static final String URL_USUARIO = MainListaActivity.getUSR();
    //COMENTADO PORQUE SE CAMBIO LA URL
    //private static final String URL_FINAL = URL_BASE + URL_USUARIO +"/repos";
    private static final String URL_FINAL = "http://www.mocky.io/v2/57eee3822600009324111202";
    private static final String TAG = "DatosAdapter";
    List<Datos> items;

    public DatosAdapter(Context context) {
        super();

        // Crear nueva cola de peticiones
        requestQueue= Volley.newRequestQueue(context);

        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_FINAL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        items = parseJson(response);
                        //notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );

        // Añadir petición a la cola
        requestQueue.add(jsArrayRequest);
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Referencia del view procesado
        View listItemView;

        //Comprobando si el View no existe
        listItemView = null == convertView ? layoutInflater.inflate(
                R.layout.datos,
                parent,
                false) : convertView;


        // Obtener el item actual
        Datos item = items.get(position);

        // Obtener Views
        TextView textoTitulo = (TextView) listItemView.
                findViewById(R.id.textoTitulo);
        TextView textoDescripcion = (TextView) listItemView.
                findViewById(R.id.textoDescripcion);
        TextView textoActualizacion = (TextView) listItemView.
                findViewById(R.id.textoActualizacion);

        // Actualizar los Views
        textoTitulo.setText(item.getTitulo());
        textoDescripcion.setText(item.getDescripcion());
        textoActualizacion.setText(item.getActualizacion());

        return listItemView;
    }

    public List<Datos> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Datos> datos = new ArrayList<>();
        JSONArray jsonArray= null;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);
                    if (objeto.getString("description")!=null){
                        Datos dato = new Datos(
                                objeto.getString("name"),
                                objeto.getString("description"),
                                "ultima actualizacion: "+ objeto.getString("actualizacion"),
                                objeto.getString("html_url"));


                        datos.add(dato);

                    }else{
                        Datos dato = new Datos(
                                objeto.getString("name"),
                                "Sin descripción disponible",
                                "ultima actualizacion: "+ objeto.getString("actualizacion"),objeto.getString("html_url"));


                        datos.add(dato);
                    }

                } catch (JSONException e) {
                    datos=null;

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return datos;
    }
}

