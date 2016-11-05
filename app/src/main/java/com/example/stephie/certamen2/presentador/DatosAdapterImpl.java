package com.example.stephie.certamen2.presentador;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.stephie.certamen2.R;
import com.example.stephie.certamen2.modelo.Datos;
import com.example.stephie.certamen2.presentador.contract.DatosAdapter;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Stephie on 30-09-2016.
 */
public class DatosAdapterImpl implements DatosAdapter{
    // Atributos
    private static final String URL_BASE = " https://api.github.com/users/";
    private static final String URL_USUARIO = MainPresentadorImpl.getUSR();
    //COMENTADO PORQUE SE CAMBIO LA URL
    private static final String URL_FINAL = URL_BASE + URL_USUARIO +"/repos";
    //private static final String URL_FINAL = "http://www.mocky.io/v2/57eee3822600009324111202";
    private static final String TAG = "DatosAdapterImpl";
    private List<Datos> items;

    public DatosAdapterImpl() {
        super();
        HttpClient httpClient = new DefaultHttpClient();

        HttpGet del =
                new HttpGet(URL_FINAL);

        del.setHeader("content-type", "application/json");

        try
        {
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());


            JSONArray respJSON = new JSONArray(respStr);

            items= parseJson(respJSON);
        }
        catch(Exception ex)
        {
            Log.e("ServicioRest","Error!", ex);
        }



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
                R.layout.repositorio,
                parent,
                false) : convertView;


        // Obtener el item actual
        Datos item = items.get(position);

        // Obtener Views
        TextView textoTitulo = (TextView) listItemView.
                findViewById(R.id.nombre_repo);
        TextView textoDescripcion = (TextView) listItemView.
                findViewById(R.id.descripcion_repo);
        TextView textoActualizacion = (TextView) listItemView.
                findViewById(R.id.ultima_actualizacion);

        // Actualizar los Views
        textoTitulo.setText(item.getTitulo());
        textoDescripcion.setText(item.getDescripcion());
        textoActualizacion.setText(item.getActualizacion());

        return listItemView;
    }

    public List<Datos> parseJson(JSONArray jsonArray){
        // Variables locales
        List<Datos> datos = new ArrayList<>();


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

                }else if(objeto.getString("description")==null){
                    Datos dato = new Datos(
                            objeto.getString("name"),
                            "Sin descripción disponible",
                            "ultima actualizacion: "+ objeto.getString("updated_at"),objeto.getString("html_url"));


                    datos.add(dato);
                }else{datos=null;}

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }


        return datos;
    }
    public List<Datos>getParse(){return items;}
}

