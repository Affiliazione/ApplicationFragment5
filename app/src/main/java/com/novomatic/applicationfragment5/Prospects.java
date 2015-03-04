package com.novomatic.applicationfragment5;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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

        edittext = (EditText) rootView.findViewById(R.id.editText);

        Button b = (Button) rootView.findViewById(R.id.btnTest);
        b.setOnClickListener(this);

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


