package com.novomatic.applicationfragment5;

/**
 * Created by fpirazzi on 27/02/2015.
 */

import android.content.Context;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Callable;

public class ProspectArrayAdapter extends ArrayAdapter<Prospect> {
    private final Context context;
    private final Prospect[] values;

    public ProspectArrayAdapter(Context context, Prospect[] values) {
        super(context, R.layout.prospect_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.prospect_item, parent, false);

        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        firstLine.setText(values[position].getDenominazione());
        secondLine.setText(values[position].getIndirizzo());

        int contratti = values[position].getContratti();

        if (contratti > 0){
            imageView.setImageResource(R.drawable.ic_action_paste);
        }else {
            imageView.setImageResource(R.drawable.ic_action_new_label);
        }

        return rowView;
    }
}
