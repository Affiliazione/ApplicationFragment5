package com.novomatic.applicationfragment5;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class Info extends Fragment implements View.OnClickListener {

    /**
     * Returns a new instance of this fragment for the given section number.
     */

    private static EditText edittext;

    public static Info newInstance() {
        Info fragment = new Info();
        return fragment;
    }

    public Info() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container,
                false);

        StoreManager storeManager = new StoreManager();

        String jsonMessage = storeManager.GetData(getActivity().getBaseContext());

        ParseResponseWebAPI parseResponseWebAPI = new ParseResponseWebAPI(jsonMessage);

        ArrayList<Prospect> prospects = parseResponseWebAPI.GetProspects();
        Profile profile = parseResponseWebAPI.GetProfile();


        TextView textView = (TextView) rootView.findViewById(R.id.textView_profile_name);

        textView.setText(profile.getNome() + " " + profile.getCognome());

        Button buttonInfo = (Button) rootView.findViewById(R.id.button_info);

        buttonInfo.setText(prospects.size() + " Punti vendita da gestire");
        buttonInfo.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(2);
    }

    @Override
    public void onClick(View view){

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, Prospects.newInstance())
                .commit();

    }

}


