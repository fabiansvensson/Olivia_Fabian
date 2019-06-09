package com.example.olivia_fabian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.olivia_fabian.api.RetroFlats;
import com.example.olivia_fabian.api.RetrofitClientInstance;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.squareup.picasso.Picasso;

public class FlatsScreen extends AppCompatActivity {
    ListView lv;
    Object item;
    ImageView activityMain;
    private static List<RetroFlats> flats;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flats);
        Context context = getApplicationContext();
        ImageView imageView, aux;
        int id = 1;
        List<Flat> deviceCollection = new ArrayList<>();//aqui ens ho hem de descarregar tot de la api i posarho

        makeCallToApi();

        deviceCollection = new ArrayList<>();
        activityMain = findViewById(R.id.flatpicture);


        deviceCollection.add(new Flat(100, "hejhopp1", 1, R.drawable.ic_launcher_foreground, R.drawable.like));
        deviceCollection.add(new Flat(150, "hejhopp2", 2, R.drawable.ic_launcher_foreground, R.drawable.like));
        deviceCollection.add(new Flat(751, "hejhopp3", 3, R.drawable.ic_launcher_foreground, R.drawable.like));
        deviceCollection.add(new Flat(200, "hejhopp4", 4, R.drawable.ic_launcher_foreground, R.drawable.like));

        FlatsAdapter adapter = new FlatsAdapter(getApplicationContext(), deviceCollection, this);

        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = lv.getItemAtPosition(position);
                createIntent((Flat)item);
            }
        });

    }

    public void createIntent(Flat flat) {
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("PRICE", flat.getPrice());
        intent.putExtra("DESCRIPTION", flat.getDescription());
        intent.putExtra("IMAGE", flat.getImg());
        intent.putExtra("LIKE", flat.getLike());
        intent.putExtra("SIZE", flat.getSize());

        startActivity(intent);
    }

    public void makeCallToApi() {
        final UserManager myapplication = (UserManager)getApplication();

        RetrofitClientInstance controller = new RetrofitClientInstance();
        controller.onStart(new Callback<List<RetroFlats>>() {
            @Override
            public void onResponse(Call<List<RetroFlats>> call, Response<List<RetroFlats>> response) {

                myapplication.flats=response.body();
                flats = response.body();

                for(RetroFlats rf : flats) {
                    Log.d("TAG", rf.getShortdescription());
                }
                //codi dels flats
            }

            @Override
            public void onFailure(Call<List<RetroFlats>> call, Throwable t) {

            }
        });

        //flats.size();

    }

}
