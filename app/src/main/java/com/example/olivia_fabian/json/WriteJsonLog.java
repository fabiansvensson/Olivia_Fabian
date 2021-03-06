package com.example.olivia_fabian.json;

import com.example.olivia_fabian.activities.Register;
import com.example.olivia_fabian.pojo.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WriteJsonLog {

    private String checkDir = null;
    private List<User> users;

    public WriteJsonLog() {
        users = new ArrayList<>();
        ReadJson rj = new ReadJson();
        users = rj.loadData(Register.getFolder().toString());
    }

    public void writeJSON(File folder, String username, String email, String password) {
        Gson gson = new Gson();
        if(users == null) users = new ArrayList<>();
        users.add(new User(username, email, password));
        String jsonString = gson.toJson(users);
        try {
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

}
