package com.example.olivia_fabian.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class ReadJson {
    private static Gson gson = new GsonBuilder().create();
    public User[] loadData(String filename) {
        User[] users = null;
        try
        {
            FileReader f = new FileReader("log/"+filename+".json");
            Type listType = new TypeToken<List<User>>(){}.getType();
            List<User> activities_list = gson.fromJson(f, listType);
            users = activities_list.toArray(new User[activities_list.size()]);
        }
        catch(FileNotFoundException fe)
        {
            System.out.println("File not found!");
        }
        return users;
    }
}
