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
import hu.bba.myfirstapp.fragments.DetailsFragmentForJson;
import hu.bba.myfirstapp.models.ContentImage;

public class DetailsActivityForJson extends AppCompatActivity {

    private ArrayList<ContentImage> contentImages;
    private Toolbar DetailsActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        int pos = getIntent().getIntExtra("Position", 0);
        contentImages = (ArrayList<ContentImage>) getIntent().getSerializableExtra("Content");

        ButterKnife.bind(this);

        DetailsActionBarToolbar = (Toolbar) findViewById(R.id.details_toolbar);
        DetailsActionBarToolbar.setTitle("Details Page");
        setSupportActionBar(DetailsActionBarToolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.details_viewpager);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return DetailsFragmentForJson.newInstance(contentImages.get(position));
            }

            @Override
            public int getCount() {
                return contentImages.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return contentImages.get(position).getFormattedTitle();
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
