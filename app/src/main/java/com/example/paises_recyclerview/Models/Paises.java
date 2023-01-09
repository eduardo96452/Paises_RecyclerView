package com.example.paises_recyclerview.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Paises {

    private String pais;
    private String urlbandera;
    private String pais_id;
    private String capital;

    public String getPais() { return pais; }

    public void setPais(String pais) { this.pais = pais; }

    public String getUrlbandera() { return urlbandera; }

    public void setUrlbandera(String urlbandera) { this.urlbandera = urlbandera; }

    public String getPais_id() { return pais_id; }

    public void setPais_id(String pais_id) { this.pais_id = pais_id; }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Paises(JSONObject a) throws JSONException {
        pais =  "Pais: " + a.getString("Name").toString();
        JSONObject countryCode = a.getJSONObject("CountryCodes");
        pais_id = countryCode.getString("iso2");
        urlbandera = "http://www.geognos.com/api/en/countries/flag/"+ pais_id +".png";
        if(!a.isNull("Capital")){
            JSONObject countrycapital = a.getJSONObject("Capital");
            capital = "Capital: " + countrycapital.getString("Name").toString();
        }else
        {
            capital = "No tiene capital";
        }
    }

    public static ArrayList<Paises> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Paises> paises = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            paises.add(new Paises(datos.getJSONObject(i)));
        }
        return paises;
    }
}
