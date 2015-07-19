package hu.bba.myfirstapp.services;

import hu.bba.myfirstapp.models.NewsResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface ApiServices {

    @GET("/exam/")
    void getNewsResponse(Callback<NewsResponse> callback);
}