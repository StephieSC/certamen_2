package com.example.stephie.certamen2.presentador.contract;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Stephie on 04-11-2016.
 */

public interface MainPresentador {
    void onCreate(Bundle savedInstanceState);
    public void onItemClick(AdapterView<?> parent, View view, int pos);
}
