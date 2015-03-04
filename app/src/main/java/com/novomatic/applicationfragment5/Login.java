package com.novomatic.applicationfragment5;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by fpirazzi on 02/03/2015.
 */
public class Login extends Fragment implements View.OnClickListener {

    ProgressDialog progress=null;

    String userName = "";
    String password = "";

    String uuid = "";
    String version = "";
    String model = "";

    EditText editText_userName;
    EditText editText_password;

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static Login newInstance() {
        Login fragment = new Login();
        return fragment;
    }

    public Login() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container,
                false);

        editText_userName = (EditText) rootView.findViewById(R.id.editText_name);
        editText_password = (EditText) rootView.findViewById(R.id.editText_password);


        Button b = (Button) rootView.findViewById(R.id.button_login);
        b.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(2);

        TelephonyManager tManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

        uuid = tManager.getDeviceId();
        version = BuildConfig.VERSION_NAME;
        model = Build.MODEL;

    }

    @Override
    public void onClick(View view){

        userName = editText_userName.getText().toString();
        password = editText_password.getText().toString();

        if(userName != "" && password != "") {
            new NetworkAdapter(getActivity()).execute();
        }else{
            Toast.makeText(view.getContext(),"Inserisci le tue credenziali",Toast.LENGTH_LONG).show();
        }

    }

    private class NetworkAdapter extends AsyncTask<Void, Integer, String>{

        private ProgressDialog dialog;


        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {

            InputStream in = entity.getContent();
            StringBuffer out = new StringBuffer();

            int n = 1;
            while (n>0) {
                byte[] b = new byte[4096];
                n =  in.read(b);
                if (n>0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }

        Activity mActivity;


        public NetworkAdapter(Activity activity){

            mActivity = activity;


            dialog = new ProgressDialog(activity);
        }

        @Override
        protected String doInBackground(Void... params) {

            String appResponse = "";

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://vas-val.gmatica.it/api/App");

            String postResult = "";

            try
            {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("UserName",userName);
                jsonObject.put("Password",password);
                jsonObject.put("UUID",uuid);
                jsonObject.put("Model",model);
                jsonObject.put("Version", version);

                StringEntity entityObj = new StringEntity("=" + jsonObject.toString(), HTTP.UTF_8);

                httppost.setEntity(entityObj);

                httppost.setHeader("User-Agent", "Fiddler");
                httppost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

                httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Custom user agent");

                HttpResponse response = httpclient.execute(httppost);


                if(response.getStatusLine().getStatusCode() == 200) {

                    HttpEntity entity = response.getEntity();
                    appResponse = getASCIIContentFromEntity(entity);

                    JSONObject reader = new JSONObject(appResponse);

                    String errore = (String) reader.get("Errore");
                    boolean isPasswordScaduta = (boolean) reader.get("IsPasswordScaduta");

                    JSONObject apk = reader.getJSONObject("apk");

                    String versione = (String) apk.get("versione");

                    JSONObject profile = reader.getJSONObject("Profile");

                    JSONArray prospects =  reader.optJSONArray("Prospects");

                    ArrayList<Prospect> prospectsList = new ArrayList<Prospect>();

                    for (int i = 0; i < prospects.length(); i++){

                        JSONObject item = prospects.getJSONObject(i);
                        String denominazione = item.getString("Denominazione");

                        Prospect prospect = new Prospect();
                        prospect.Denominazione = item.getString("Denominazione");
                        prospect.CAP = item.getString("CAP");
                        prospect.Comune = item.getString("Comune");

                        prospectsList.add(prospect);
                    }


                    StoreManager storeManager = new StoreManager();
                    storeManager.SaveData(mActivity.getBaseContext(),appResponse);

                    postResult = "Eseguito";


                }else {
                    postResult =  "Result: " + response.getStatusLine().getStatusCode();
                }

            }catch (Exception ex){
                postResult = ex.getMessage();
            }

            return postResult;

        }

        @Override
        protected void onPreExecute(){
            //EditText et = (EditText)findViewById(R.id.my_edit);
            //et.setText("In Esecuzione");

            //super.onPreExecute();
            //progress.setProgress(0);
            //progress.show();
            //Toast.makeText(mContext, "Inizio",   Toast.LENGTH_SHORT).show();
            dialog.setMessage("Recupero informazioni in corso...");
            dialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values ){
            //super.onProgressUpdate(values);
            //progress.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(String result) {

            //super.onPostExecute(result);
            //progress.dismiss();
            //Toast.makeText(mContext, "Fine",   Toast.LENGTH_SHORT).show();
            if (dialog.isShowing()) {
                dialog.dismiss();
            }



            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, Info.newInstance())
                    .commit();


        }
    }



}