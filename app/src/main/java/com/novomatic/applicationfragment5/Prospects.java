package com.novomatic.applicationfragment5;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Prospects extends Fragment implements View.OnClickListener {

    /**
     * Returns a new instance of this fragment for the given section number.
     */

    private static EditText edittext;

    public static Prospects newInstance() {
        Prospects fragment = new Prospects();
        return fragment;
    }

    public Prospects() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prospects, container,
                false);

        final ListView listview = (ListView) rootView.findViewById(R.id.listProspect);

        StoreManager storeManager = new StoreManager();

        ArrayList<Prospect> prospects = new ParseResponseWebAPI(
                storeManager.GetData(
                        getActivity().getBaseContext()
                )
        ).GetProspects();

        Prospect[] prospectsArray = new Prospect[prospects.size()];
        prospectsArray = prospects.toArray(prospectsArray);

        ProspectArrayAdapter adapter = new ProspectArrayAdapter(this.getActivity().getBaseContext(),prospectsArray);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView firstLane = (TextView) view.findViewById(R.id.firstLine);

                Toast.makeText(view.getContext(), firstLane.getText(), Toast.LENGTH_LONG).show();

            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(2);
    }

    @Override
    public void onClick(View view){

        String value = edittext.getText().toString();

        Toast.makeText(view.getContext(),"test ok", Toast.LENGTH_LONG).show();

    }

}


