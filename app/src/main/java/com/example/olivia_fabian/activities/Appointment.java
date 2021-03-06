package com.example.olivia_fabian.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.olivia_fabian.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Appointment extends AppCompatActivity {

    private Calendar myCalendar;
    private EditText et;
    private boolean app;
    private boolean like;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askappointment);
        like = getIntent().getBooleanExtra("LIKE2", false);
        app = getIntent().getBooleanExtra("APP", false);
        myCalendar = Calendar.getInstance();

        et = findViewById(R.id.tv_mostrar_fecha_picker);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Appointment.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final Spinner dropdown = findViewById(R.id.spinner_time);

        String[] items = new String[]{"00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00",
                "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
                "08:30", "09:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
                "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
                "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00",
                "21:30", "22:00", "22:30", "23:00", "23:30", "24:00"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
        dropdown.setSelection(10);

        Button select = findViewById(R.id.select);

        select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("TIME", (String)dropdown.getSelectedItem());
                intent.putExtra("DATE2", et.getText().toString());
                setResult(RESULT_OK, intent);
                app = true;
                intent.putExtra("APPOINTMENT3", app);
                intent.putExtra("LIKE", like);
                finish();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et.setText(sdf.format(myCalendar.getTime()));
    }

}
