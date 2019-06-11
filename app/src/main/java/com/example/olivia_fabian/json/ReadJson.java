package com.example.olivia_fabian.json;

import com.example.olivia_fabian.pojo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class ReadJson {
    private static Gson gson = new GsonBuilder().create();
    public List<User> loadData(String folder) {
        List<User> users = null;
        System.out.println("Folder: " + folder);
        try
        {
            FileReader f = new FileReader(folder + "/users_file.json");
            Type listType = new TypeToken<List<User>>(){}.getType();
            users = gson.fromJson(f, listType);
        }
        catch(FileNotFoundException fe)
        {
            System.out.println("File not found!");
        }
        return users;
    }
}
