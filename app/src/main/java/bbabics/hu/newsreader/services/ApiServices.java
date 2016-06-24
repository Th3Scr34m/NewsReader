package bbabics.hu.newsreader.services;

import bbabics.hu.newsreader.models.ContentDataResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface ApiServices {

    @GET("/dummy")
    void getContentImageDataResponse(Callback<ContentDataResponse> callback);
}