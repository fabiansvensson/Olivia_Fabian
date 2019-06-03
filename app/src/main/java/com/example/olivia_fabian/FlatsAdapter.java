package com.example.olivia_fabian;

import android.bluetooth.BluetoothClass;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class FlatsAdapter extends ArrayAdapter<Flat> {
    int layout=-1;
    Button button;
    private Flat flat;
    private final FlatsScreen main;

    public FlatsAdapter(Context context, List<Flat> collection, FlatsScreen main) {
        super(context, R.layout.activity_flats, collection);
        this.layout= R.layout.activity_flats;
        this.main = main;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View row =null;

        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(this.layout, parent, false);

        //1. Get the flat that I want to render
        flat = getItem(position);
        if (flat !=null){

            final TextView f_description = (TextView) row.findViewById(R.id.description);
            f_description.setText(flat.getDescription());

            ImageView f_logo = (ImageView) row.findViewById(R.id.flatpicture);
            f_logo.setBackgroundResource(flat.getImg());

            ImageView f_like = (ImageView) row.findViewById(R.id.likeicon);
            f_like.setBackgroundResource(flat.getLike());

            TextView f_size = (TextView) row.findViewById(R.id.size);
            f_size.setText(flat.getSize());

            TextView f_price = (TextView) row.findViewById(R.id.price);
            f_price.setText(String.valueOf(flat.getPrice()));


        }
        return row;
    }

}
