package hu.bba.snews.services;

import hu.bba.snews.models.ContentDataResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface ApiServices {

    @GET("/dummy")
    void getContentImageDataResponse(Callback<ContentDataResponse> callback);
}