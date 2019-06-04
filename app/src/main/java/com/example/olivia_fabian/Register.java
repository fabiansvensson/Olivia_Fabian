package com.example.olivia_fabian;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.olivia_fabian.json.WriteJsonLog;

import java.io.File;
import java.io.FileOutputStream;

public class Register extends AppCompatActivity {

    EditText email;
    EditText name;
    EditText password;
    Button create;
    private String semail;
    private String spassword;
    private String sname;
    private static File folder;

    @Override
    public File getFilesDir() {
        return super.getFilesDir();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        name = findViewById(R.id.loginname);
        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.password_login);
        create = findViewById(R.id.createbutton);

        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkName()) {
                    dialogName();
                } else if(!checkEmail()) {
                    dialogEmail();
                } else if(!checkPassword()) {
                    dialogEmail();
                } else {
                    folder = getFilesDir();
                    WriteJsonLog wjl = new WriteJsonLog(folder, sname, semail, spassword);
                    testToast(wjl.getTest());
                    intentCreate();
                }
            }
        });

    }

    public void intentCreate() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    public boolean checkName() {
        sname = name.getText().toString();
        return sname.length() > 0;
    }

    public boolean checkPassword() {
        spassword = password.getText().toString();
        return spassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$");
    }

    public boolean checkEmail() {
        semail = email.getText().toString();
        if(semail.equals("test")) semail = "fabian.sitges@gmail.com";
        return (!TextUtils.isEmpty(semail) && Patterns.EMAIL_ADDRESS.matcher(semail).matches());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }
    //and this to handle actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.logo) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dialogName() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Register.this);
        alertDialogBuilder.setTitle("Incorrect Name Format");
        alertDialogBuilder.setMessage("Name filed is empty!").setCancelable(true);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void dialogPassword() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Register.this);
        alertDialogBuilder.setTitle("Incorrect Password Format");
        alertDialogBuilder.setMessage("Error password field cannot be empty and must contain\n" +
                "at least one number, one lowercase and one uppercase letter").setCancelable(true);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void dialogEmail() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Register.this);
        alertDialogBuilder.setTitle("Incorrect Email Format");
        alertDialogBuilder.setMessage("Error email needs to be set and \n" +
                "have a valid expression").setCancelable(true);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static File getFolder() {
        return folder;
    }

    public void testToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

}
