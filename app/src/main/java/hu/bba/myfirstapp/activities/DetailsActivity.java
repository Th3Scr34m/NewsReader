package hu.bba.myfirstapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.fragments.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {


    @Bind(R.id.details_viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        ButterKnife.bind(this);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return DetailsFragment.newInstance(0, "");
            }

            @Override
            public int getCount() {
                return 10;
            }
        });


        // Description
//        News news = (News) getIntent().getSerializableExtra("News");
//        description.setText(news.getDescription());
//
//        // Image
//        Picasso.with(getApplicationContext())
//                .load(news.getImage().getUrl())
//                .placeholder(R.mipmap.placeholder)
//                .error(R.mipmap.placeholder_err)
//                .into(image);
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
