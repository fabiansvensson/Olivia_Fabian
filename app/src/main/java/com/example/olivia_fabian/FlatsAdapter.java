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

import com.example.olivia_fabian.api.RetroFlats;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Callback;

import static com.example.olivia_fabian.R.drawable.logo;
import static com.example.olivia_fabian.R.layout.flats;

public class FlatsAdapter extends ArrayAdapter<Flat> {
    int layout=-1;
    private Flat flat;
    private final FlatsScreen main;
    LinkedList<Flat> flats;

    public FlatsAdapter(Context context, List<Flat> collection, FlatsScreen main) {
        super(context, R.layout.flats, collection);
        this.layout= R.layout.flats;
        this.main =  main;
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


           ImageView f_logo = (ImageView) row.findViewById(R.id.flatpicture);
           // if(flat.getLike() != false) {
             // ImageView f_favourtite = (ImageView) row.findViewById(R.id.likeicon);
            //}
            if(flat.getImg_api() == null || flat.getImg_api().isEmpty())  {
                Picasso.get()
                        .load(flat.getImg())
                        .into(f_logo);
            } else{
                Picasso.get()
                        .load(flat.getImg_api())
                        .into(f_logo);
            }
            TextView f_size = (TextView) row.findViewById(R.id.size);
            f_size.setText(flat.getSize());

            TextView f_price = (TextView) row.findViewById(R.id.price);
            f_price.setText(String.valueOf(flat.getPrice()));
            ImageView rowe = (ImageView) row.findViewById(R.id.row);

        }
        return row;
    }

}
