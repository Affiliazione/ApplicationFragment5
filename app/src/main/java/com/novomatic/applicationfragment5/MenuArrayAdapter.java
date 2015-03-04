package com.novomatic.applicationfragment5;

/**
 * Created by fpirazzi on 27/02/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuArrayAdapter extends ArrayAdapter<NovoMenuItem> {
    private final Context context;
    private final NovoMenuItem[] values;

    public MenuArrayAdapter(Context context, NovoMenuItem[] values) {
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

        firstLine.setText(values[position].Nome);
        secondLine.setText(values[position].Icona);

        return rowView;
    }
}
