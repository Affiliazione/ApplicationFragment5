package com.novomatic.applicationfragment5;

import android.renderscript.Int3;

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
    private Profile profile;

    public ParseResponseWebAPI(String jsonMessagge){

        prospectsList = new ArrayList<Prospect>();
        profile = new Profile();
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

            JSONObject JSONprofile = reader.getJSONObject("Profile");

            profile.setNome(JSONprofile.getString("Nome"));
            profile.setCognome(JSONprofile.getString("Cognome"));
            profile.setIntermediarioId(Integer.parseInt(JSONprofile.getString("IntermediarioId")));
            profile.setUserID(Integer.parseInt(JSONprofile.getString("UserID")));


            JSONArray prospects =  reader.optJSONArray("Prospects");



            for (int i = 0; i < prospects.length(); i++){

                JSONObject item = prospects.getJSONObject(i);
                String denominazione = item.getString("Denominazione");

                Prospect prospect = new Prospect();

                JSONArray listaContratti =  reader.optJSONArray("ListaContratti");
                int contratti = listaContratti.length();
                prospect.setContratti(contratti);

                prospect.setProspectID(Integer.parseInt(item.getString("ProspectID")));
                prospect.setIntermediario_id(Integer.parseInt(item.getString("intermediario_id")));
                prospect.setDenominazione(item.getString("Denominazione"));
                prospect.setCAP(item.getString("CAP"));
                prospect.setComune(item.getString("Comune"));
                prospect.setTelefono(item.getString("Telefono"));
                prospect.setnCivico("NCivico");

                if (profile.getIntermediarioId() == prospect.getIntermediario_id()) {
                    prospectsList.add(prospect);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Prospect> GetProspects(){
        return prospectsList;
    }

    public Profile GetProfile(){
        return profile;
    }





}
