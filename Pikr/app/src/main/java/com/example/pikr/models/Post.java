package com.example.pikr.models;

import android.view.View;

import com.example.pikr.models.Picture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Post {

    private String title;
    private String description;
    private String datetime;
    private ArrayList<View> pictures;
    private boolean deleted;

    public Post(){
        deleted = false;
    }

    public Post(String title, String description, String datetime, String pictures){
        this.title = title;
        this.description = description;
        this.datetime = datetime;
//        if (pictures.equals("")){                                       //FIX THIS
//            this.pictures = new ArrayList<>();
//        }
//        else this.pictures = jsonPicsToArray(pictures);
        deleted = false;
    }

    private ArrayList<Picture> jsonPicsToArray(String pictures) {
        JSONArray array = null;
        ArrayList<Picture> result = new ArrayList<>();
        try {
            array = new JSONArray(pictures);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert array != null;
        for (int i = 0; i<array.length(); i++){
            String uri = "";
            String filePath = "";
            try {
                JSONObject object = array.getJSONObject(i);
                uri = object.getString("uri");
                filePath = object.getString("filepath");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            result.add(new Picture(uri, filePath));
        }
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ArrayList<View> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<View> pictures) {
        this.pictures = pictures;
    }

    public void setDeleted(boolean isDeleted){
        deleted = isDeleted;
    }

    public boolean getDeleted(){
        return deleted;
    }
}
