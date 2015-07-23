package hu.bba.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.adapters.DetailsLayoutAdapter;
import hu.bba.myfirstapp.models.Image;
import hu.bba.myfirstapp.models.News;

public class DetailsActivity extends AppCompatActivity {

    @Bind(R.id.description)
    TextView description;

    @Bind(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        ButterKnife.bind(this);

        // Description
        News news = (News) getIntent().getSerializableExtra("News");
        description.setText(news.getDescription());

        // Image
        Picasso.with(getApplicationContext())
                .load(news.getImage().getUrl())
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder_err)
                .into(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
