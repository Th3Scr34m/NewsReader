package hu.bba.myfirstapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContentImageResult {

    @SerializedName("results")
    ArrayList<ContentImage> imageList;

    public ArrayList<ContentImage> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<ContentImage> imageList) {
        this.imageList = imageList;
    }
}
