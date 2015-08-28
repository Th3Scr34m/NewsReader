package hu.bba.snews.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContentImageResult {

    @SerializedName("results")
    ArrayList<Content> imageList;

    public ArrayList<Content> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<Content> imageList) {
        this.imageList = imageList;
    }
}
