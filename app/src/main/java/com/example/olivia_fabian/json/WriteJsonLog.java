package com.example.olivia_fabian.json;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJsonLog {
    public WriteJsonLog(String filename, String username, String email, String password) {
        Gson gson = new Gson();
        User gi = new User(username, email, password);
        String jsonString = gson.toJson(gi);
        try {
            File file = new File("log/"+filename+".json");
            FileWriter f = new FileWriter(file);
            f.write(jsonString);
            f.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
