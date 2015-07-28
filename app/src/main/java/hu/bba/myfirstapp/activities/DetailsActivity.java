package hu.bba.myfirstapp.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.fragments.DetailsFragment;
import hu.bba.myfirstapp.models.News;

public class DetailsActivity extends AppCompatActivity {

    private ArrayList<News> news;
    private Toolbar DetailsActionBarToolbar;
//
//    @Bind(R.id.details_viewpager)
//    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        int pos = getIntent().getIntExtra("Position", 0);
        news = (ArrayList<News>) getIntent().getSerializableExtra("News");

        ButterKnife.bind(this);

        DetailsActionBarToolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(DetailsActionBarToolbar);
        getSupportActionBar().setTitle("Details Page");

        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.details_viewpager);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return DetailsFragment.newInstance(news.get(position));
            }

            @Override
            public int getCount() {
                return news.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return news.get(position).getName();
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(pos);
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
