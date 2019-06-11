package com.example.olivia_fabian.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.olivia_fabian.Flat;
import com.example.olivia_fabian.FlatsAdapter;
import com.example.olivia_fabian.R;
import com.example.olivia_fabian.UserManager;
import com.example.olivia_fabian.api.RetroFlats;
import com.example.olivia_fabian.api.RetrofitClientInstance;


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
    private static List <Flat> flats_n;
    private static List <Flat> flats_favourite, flats_appointment, flats_image;
    private FlatsAdapter adapter, adapter_fav, adapter_app, adapter_im;
    private String time;
    private String date;
    private boolean like;
    private int id;
    private Button e, c, f;
    private boolean e_b, c_b, f_b;
    private boolean appointment;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        flats_appointment = new LinkedList<Flat>();
        flats_favourite = new LinkedList<Flat>();
        flats_image = new LinkedList<Flat>();
        flats_n = new LinkedList<Flat>();


        setContentView(R.layout.activity_flats);
        activityMain = findViewById(R.id.flatpicture);
        makeCallToApi(this);


        adapter_app = new FlatsAdapter(getApplicationContext(), flats_appointment, this);
        adapter_fav = new FlatsAdapter(getApplicationContext(), flats_favourite, this);
        adapter_im = new FlatsAdapter(getApplicationContext(), flats_image, this);
        adapter = new FlatsAdapter(getApplicationContext(), flats_n, this);

        lv = findViewById(R.id.listview);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = lv.getItemAtPosition(position);
                createIntent((Flat)item);
            }
        });
        c = findViewById(R.id.button_c);
        c.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c_b) {
                    c_b = false;
                    lv.setAdapter(adapter);
                } else{
                    c_b = true;
                    lv.setAdapter(adapter_app);
                }
            }
        });
        f = findViewById(R.id.button_f);
        f.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f_b) {
                    f_b = false;
                    lv.setAdapter(adapter);
                } else{
                    f_b = true;
                    lv.setAdapter(adapter_fav);
                }
            }
        });
        e = findViewById(R.id.button_e);
        e.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e_b) {
                    e_b = false;
                    lv.setAdapter(adapter);
                } else{
                    e_b = true;
                    lv.setAdapter(adapter_im);
                }
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
        intent.putExtra("IMAGE_API", flat.getImg_api());
        intent.putExtra("FLAT_ID", flat.getId());
        intent.putExtra("LONG_DESCRIPTION", flat.getLong_description());
        intent.putExtra("DATE", flat.getDate());
        intent.putExtra("TIME", flat.getTime());
        intent.putExtra("APPOINTMENT", flat.isAppointment());

        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                time = data.getStringExtra("TIME");
                date = data.getStringExtra("DATE");
                like = data.getBooleanExtra("LIKE", false);
                id = data.getIntExtra("ID", 0);
                appointment = data.getBooleanExtra("APPOINTMENT2", false);
                Log.d("STRING TIME", "CHECK HERE IS THE TIME IN THE FLATSCREEN: " + time);
                Log.d("DATE TIME", "CHECK HERE IS THE DATE IN THE FLATSCREEN: " + date);
                Log.d("FLAT LIKE", "CHECK HERE IS THE LIKE IN THE FLATSCREEN: " + like);
                Log.d("FLAT ID", "CHECK HERE IS THE ID IN THE FLATSCREEN: " + id);
                flats_n.get(id).setAppointment(appointment);
                flats_n.get(id).setLike(like);
                flats_n.get(id).setDate(date);
                flats_n.get(id).setTime(time);
                boolean flag = false;
                if (like ) {
                    for(Flat f: flats_favourite) {
                        if (f.getId() == id) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        flats_favourite.add(flats_n.get(id));
                    }
                } else {
                    flag = false;
                    for(Flat f: flats_favourite) {
                        if (f.getId() == id) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        flats_favourite.remove(id);
                    }
                }
                if (appointment) {
                    for(Flat f: flats_appointment) {
                        if (f.getId() == id) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        flats_appointment.add(flats_n.get(id));
                    }
                } else {
                    flag = false;
                    for(Flat f: flats_appointment) {
                        if (f.getId() == id) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        flats_appointment.remove(id);
                    }
                }
                adapter_fav.notifyDataSetChanged();
                adapter_app.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void makeCallToApi(final Context context) {
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
                int j = 0;
               for (RetroFlats rf: flats) {
                   Flat f = new Flat(rf.getPrice(), rf.getShortdescription(), 5, R.drawable.logo, false, rf.getImage(), j, rf.getLongdescription(), false);
                    flats_n.add(f);
                    if(!(rf.getImage().isEmpty()) && !(rf.getImage() == "")) {
                        flats_image.add(flats_n.get(j));
                    }
                    j++;
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<RetroFlats>> call, Throwable t) {
                Log.d("FAIL", "FAILURE HAS OCCURED!");
                Toast.makeText(getApplicationContext(), "FAIL!", Toast.LENGTH_LONG).show();
            }
        });

    }

}
