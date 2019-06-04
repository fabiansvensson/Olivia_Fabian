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
    public User[] loadData(String folder) {
        User[] users = null;
        System.out.println("Folder: " + folder);
        try
        {
            FileReader f = new FileReader(folder + "/users_file.json");
            Type listType = new TypeToken<List<User>>(){}.getType();
            User activities_list = gson.fromJson(f, User.class);
            //List<User> activities_list = gson.fromJson(f, listType);
            users = new User[4];
            users[0] = activities_list;
            //users = activities_list.toArray(new User[activities_list.size()]);
        }
        catch(FileNotFoundException fe)
        {
            System.out.println("File not found!");
        }
        return users;
    }
}
