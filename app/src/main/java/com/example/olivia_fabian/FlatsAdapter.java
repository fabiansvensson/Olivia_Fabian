package com.example.olivia_fabian;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

public class FlatsAdapter extends ArrayAdapter<Flat> {
    int layout=-1;
    Button button;
    private Flat flat;
    private final FlatsScreen main;
    LinkedList<Flat> flats;

    public FlatsAdapter(Context context, List<Flat> collection, FlatsScreen main) {
        super(context, R.layout.flats, collection);
        this.layout= R.layout.flats;
        this.main = main;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View row =null;

        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(layout, parent, false);

        //1. Get the flat that I want to render
        flat = getItem(position);
        if (flat !=null){

            final TextView f_description = (TextView) row.findViewById(R.id.description);
            f_description.setText(flat.getDescription());
            f_description.setText("Hola a todos soy la descripcion");


            ImageView f_logo = (ImageView) row.findViewById(R.id.flatpicture);
            //if(flat.getLike() != null) {
              //  ImageView f_favourtite = (ImageView) row.findViewById(R.id.likeicon);
            ///}

            Picasso.get()
                    .load("https://img3.idealista.com/blur/WEB_DETAIL_TOP-XL-L/0/id.pro.es.image.master/27/a3/e4/275711097.jpg")
                    .into(f_logo);


            TextView f_size = (TextView) row.findViewById(R.id.size);
            f_size.setText(flat.getSize());
            f_size.setText("123");

            TextView f_price = (TextView) row.findViewById(R.id.price);
            f_price.setText(String.valueOf(flat.getPrice()));
            f_price.setText(String.valueOf(124));
            ImageView rowe = (ImageView) row.findViewById(R.id.row);

        }
        return row;
    }

}
