package hu.bba.myfirstapp.services;

import hu.bba.myfirstapp.models.ContentImageDataResponse;
import hu.bba.myfirstapp.models.NewsResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface ApiServices {

    //    To XML
    @GET("/exam/")
    void getNewsResponse(Callback<NewsResponse> callback);

//    To Json
@GET("/images?v=1.0&q=counter%20strike/")
void getContentImageDataResponse(Callback<ContentImageDataResponse> callback);

}