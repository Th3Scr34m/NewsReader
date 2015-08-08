package hu.bba.myfirstapp.services;

import hu.bba.myfirstapp.models.ContentDataResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface ApiServices {

    @GET("/dummy")
    void getContentImageDataResponse(Callback<ContentDataResponse> callback);
}