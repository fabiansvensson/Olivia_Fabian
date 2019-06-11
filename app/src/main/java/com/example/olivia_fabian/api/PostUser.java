package com.example.olivia_fabian.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostUser {

    @SerializedName("result")
    @Expose

    public Boolean result;
    public String error;


    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

}
