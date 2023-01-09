package com.example.paises_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.paises_recyclerview.Adapter.PaisesAdaptador;
import com.example.paises_recyclerview.Models.Paises;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView paisrecicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paisrecicle = (RecyclerView) findViewById(R.id.paises_recicler);
        paisrecicle.setHasFixedSize(true);
        paisrecicle.setLayoutManager(new LinearLayoutManager(this));
        paisrecicle.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = this.getIntent().getExtras();
        // ...

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.geognos.com/api/en/countries/info/all.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Paises> paises = new ArrayList<Paises> ();

                        try {
                            //pais ->
                            JSONObject JSONrecicle =  new JSONObject(response);
                            JSONObject JSONlistaPais=  JSONrecicle.getJSONObject("Results");
                            Iterator<String> codigosPaises =  JSONlistaPais.keys();

                            while (codigosPaises.hasNext())
                                paises.add(new Paises(JSONlistaPais.getJSONObject(codigosPaises.next())));

                            int resId = R.anim.layout_animation_down_to_up;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                                    resId);
                            paisrecicle.setLayoutAnimation(animation);
                            PaisesAdaptador adapatorPais = new PaisesAdaptador(getApplicationContext(), paises);
                            paisrecicle.setAdapter(adapatorPais);

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),  error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}