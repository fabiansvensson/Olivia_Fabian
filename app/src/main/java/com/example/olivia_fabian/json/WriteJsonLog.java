package com.example.olivia_fabian.json;

import android.os.Environment;
import android.widget.Toast;

import com.example.olivia_fabian.Register;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WriteJsonLog {

    private String checkDir = null;

    public WriteJsonLog(File folder, String username, String email, String password) {
        Gson gson = new Gson();
        User gi = new User(username, email, password);
        String jsonString = gson.toJson(gi);
        try {
            System.out.println("Hello");
            if(!folder.exists()) {
                if(!folder.mkdir()){
                    checkDir = "can't be created";
                } else {
                    checkDir = "can be created";
                }
            } else {
                checkDir = "already exists";
            }
            File file = new File(folder, "users_file.json");
            FileWriter f = new FileWriter(file);
            f.write(jsonString);
            f.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getTest(){
        return checkDir;
    }

}
