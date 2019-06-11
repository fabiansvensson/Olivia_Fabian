package com.example.olivia_fabian.pojo;

public class User {
    private String name;
    private String email;
    private String password;


    public User(String username, String email, String password) {
        this.name = username;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User && obj != null)
            return (this.email.equals(((User)obj).getEmail()) && this.password.equals(((User)obj).getPassword()));
        else
            return false;
    }
}
