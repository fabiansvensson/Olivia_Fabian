package com.example.olivia_fabian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    Button enter;
    EditText password;
    EditText email;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.);

        enter = findViewById(R.id.enter);
        password = findViewById(R.id.loginpassword);
        email = findViewById(R.id.loginemail);
        register = findViewById(R.id.loginregister);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkEmail()) {
                    dialogEmail();
                } else if(passOk()) {
                    dialogPassword();
                } else {
                    intentCreate();
                }
            }
        });

    }

    public void intentCreate() {
        /*Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
        */
    }

    public boolean passOk() {
        String s = password.toString();
        if(s.length() >=  6) return true;
        return false;
    }

    public boolean checkEmail() {
        String s = email.toString();
        if(s.length() > 0) return true;
        return false;
    }

    public void dialogPassword() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
        alertDialogBuilder.setTitle("Password Incorrect");
        alertDialogBuilder.setMessage("Error password min 6 chars and\n" +
                "Error email needs to be set").setCancelable(false);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void dialogEmail() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
        alertDialogBuilder.setTitle("Email Incorrect");
        alertDialogBuilder.setMessage("Error email needs to be set").setCancelable(false);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
