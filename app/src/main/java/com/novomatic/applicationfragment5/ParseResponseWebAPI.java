package com.novomatic.applicationfragment5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by fpirazzi on 04/03/2015.
 */
public class ParseResponseWebAPI {

    private String jsonMessagge;
    private ArrayList<Prospect> prospectsList;

    public ParseResponseWebAPI(String jsonMessagge){

        prospectsList = new ArrayList<Prospect>();
        this.jsonMessagge = jsonMessagge;
        Parse();
    }

    private void Parse(){

        try {
            JSONObject reader = new JSONObject(jsonMessagge);

            String errore = (String) reader.get("Errore");
            boolean isPasswordScaduta = (boolean) reader.get("IsPasswordScaduta");
            JSONObject apk = reader.getJSONObject("apk");

            String versione = (String) apk.get("versione");

            JSONObject profile = reader.getJSONObject("Profile");

            JSONArray prospects =  reader.optJSONArray("Prospects");

            for (int i = 0; i < prospects.length(); i++){

                JSONObject item = prospects.getJSONObject(i);
                String denominazione = item.getString("Denominazione");

                Prospect prospect = new Prospect();
                prospect.setDenominazione(item.getString("Denominazione"));
                prospect.setCAP(item.getString("CAP"));
                prospect.setComune(item.getString("Comune"));

                prospectsList.add(prospect);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Prospect> GetProspects(){

        return prospectsList;
    }





}
