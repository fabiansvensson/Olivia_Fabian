package com.example.olivia_fabian;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class Details extends AppCompatActivity {

    private String price;
    private String description, long_description;
    private String image_api;
    private boolean like;
    private String size;
    private Button appointment;
    private String time;
    private String date;
    private Button like_button;
    private boolean appointment_b;
    private int id;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        price = intent.getStringExtra("PRICE");
        description = intent.getStringExtra("DESCRIPTION");
        like = intent.getBooleanExtra("LIKE", false);
        size = intent.getStringExtra("SIZE");
        image_api = intent.getStringExtra("IMAGE_API");
        id = intent.getIntExtra("FLAT_ID", 0);
        date = intent.getStringExtra("DATE");
        time = intent.getStringExtra("TIME");
        long_description = intent.getStringExtra("LONG_DESCRIPTION");
        appointment_b = intent.getBooleanExtra("APPOINTMENT", false);

        Log.d("PRICE", "this is the price: " + price);
        Log.d("DESCRIPTION", "this is the description: " + description);
        Log.d("IMAGE_API", "this is the image: " + image_api);
        Log.d("LIKE", "this is the like: " + like);
        Log.d("SIZE", "this is the size: " + size);

        TextView vshortdesc = findViewById(R.id.shortdescription);
        ImageView vflat_img = findViewById(R.id.logoflat2);

        TextView vlongdescription = findViewById(R.id.longdescription);
        vlongdescription.setText(long_description);

        if(image_api == null || image_api.isEmpty())  {
            Picasso.get()
                    .load(R.drawable.logo)
                    .into(vflat_img);
        } else{
            Picasso.get()
                    .load(image_api)
                    .into(vflat_img);
        }
        TextView vsize = findViewById(R.id.sizenumberdetails);
        TextView vprice = findViewById(R.id.prizedetails2);
        ImageView vlike = findViewById(R.id.likedescription);
        like_button = findViewById(R.id.setfavourite);

        if(like == false) {
            Picasso.get()
                    .load(R.drawable.transparent)
                    .into(vlike);
            like_button.setText("SET AS FAVOURITE");


        } else{
            Picasso.get()
                    .load(R.drawable.like)
                    .into(vlike);
            like_button.setText("REMOVE AS FAVOURITE");

        }
        vshortdesc.setText(description);
        vsize.setText(size);
        vprice.setText(price);
        appointment = findViewById(R.id.askappointment);

        if(appointment_b) {
            TextView tv = findViewById(R.id.dateandtime);
            tv.setText("Tienes una cita el " + date + "a las " + time);
            tv.setVisibility(View.VISIBLE);
            appointment.setVisibility(View.INVISIBLE);
        }

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView vlike = findViewById(R.id.likedescription);

                if(like == false) {
                    like = true;
                    Picasso.get()
                            .load(R.drawable.like)
                            .into(vlike);
                    like_button.setText("REMOVE AS FAVOURITE");
                } else{
                    like = false;
                    Picasso.get()
                            .load(R.drawable.transparent)
                            .into(vlike);
                    like_button.setText("SET AS FAVOURITE");
                }

            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createIntent();
            }

        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                time = data.getStringExtra("TIME");
                date = data.getStringExtra("DATE2");
                id = data.getIntExtra("ID", 0);
                like = data.getBooleanExtra("LIKE", false);
                appointment_b = data.getBooleanExtra("APPOINTMENT3", false);

                if(like == false) {
                    like_button.setText("SET AS FAVOURITE");
                } else {
                    like_button.setText("REMOVE AS FAVOURITE");
                }
                if(appointment_b) {
                    TextView tv = findViewById(R.id.dateandtime);
                    tv.setText("Tienes una cita el " + date + "a las " + time);
                    tv.setVisibility(View.VISIBLE);
                    appointment.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void createIntent() {
        Intent intent = new Intent(this, Appointment.class);
        intent.putExtra("APP", appointment_b);
        intent.putExtra("LIKE2", like);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("TIME", time);
        intent.putExtra("DATE", date);
        intent.putExtra("ID", id);
        intent.putExtra("LIKE", like);
        intent.putExtra("APPOINTMENT2", appointment_b);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

}
