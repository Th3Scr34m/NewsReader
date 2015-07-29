package hu.bba.myfirstapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.adapters.CustomLayoutAdapterForJson;
import hu.bba.myfirstapp.models.ContentImage;
import hu.bba.myfirstapp.models.ContentImageDataResponse;
import hu.bba.myfirstapp.models.News;
import hu.bba.myfirstapp.services.ApiServices;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity {

    private static CharSequence text = "The newsfeed is not available";
    private static int duration = Toast.LENGTH_LONG;
    private ArrayList<News> news;
    private ArrayList<ContentImage> contentImage;
    private Toolbar ActionBarToolbar;

//    // To XML
//    private CustomLayoutAdapter adapter;

    // To Json
    private CustomLayoutAdapterForJson adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context activityContext = this;

//        // SimpleXML - Retrofit - to XML
//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint("http://aff-test.azurewebsites.net/")
//                .setConverter(new SimpleXmlConverter())
//                .build();
//
//        ApiServices apiService = restAdapter.create(ApiServices.class);
//
//        news = new ArrayList<>();
//        Callback<NewsResponse> callback = new Callback<NewsResponse>() {
//            @Override
//            public void success(NewsResponse newsResponse, Response response) {
//                news = newsResponse.getNewsList();
//                adapter.initList(news);
//            }
//
//            @Override
//            public void failure(RetrofitError retrofitError) {
//                Toast toast = Toast.makeText(activityContext, text, duration);
//                toast.show();
//            }

        // Gson - Retrofit - to JSON
        Gson gson = new GsonBuilder()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://ajax.googleapis.com/ajax/services/search/")
                .setConverter(new GsonConverter(gson))
                .build();

        ApiServices apiService = restAdapter.create(ApiServices.class);

        contentImage = new ArrayList<>();

        Callback<ContentImageDataResponse> callback = new Callback<ContentImageDataResponse>() {
            @Override
            public void success(ContentImageDataResponse contentImageDataResponse, Response response) {
                contentImage = contentImageDataResponse.getResultList().getImageList();
                adapter.initList(contentImage);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast toast = Toast.makeText(activityContext, text, duration);
                toast.show();
            }
        };

////        To XML
//        apiService.getNewsResponse(callback);

        // To Json
        apiService.getContentImageDataResponse(callback);

        ActionBarToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(ActionBarToolbar);
        getSupportActionBar().setTitle("Main Page");

        ListView myListView = (ListView) findViewById(R.id.main_listView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(myListView);

//        // To XML
//        adapter = new CustomLayoutAdapter();
//        myListView.setAdapter(adapter);

        // To Json
        adapter = new CustomLayoutAdapterForJson();
        myListView.setAdapter(adapter);

        // To XML
//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
//                myIntent.putExtra("News", news);
//                myIntent.putExtra("Position", position);
//                startActivity(myIntent);
//            }
//        });

        // To Json
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(MainActivity.this, DetailsActivityForJson.class);
                myIntent.putExtra("Content", contentImage);
                myIntent.putExtra("Position", position);
                startActivity(myIntent);
            }
        });
    }
}
