package hu.bba.myfirstapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentImageResult {

    @SerializedName("results")
    List<ContentImage> imageList;

    public List<ContentImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<ContentImage> imageList) {
        this.imageList = imageList;
    }
}
