package hu.bba.snews.activities;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bba.snews.R;
import hu.bba.snews.fragments.DetailsFragment;
import hu.bba.snews.models.Content;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();
    private ArrayList<Content> content;

    @Bind(R.id.details_toolbar)
    Toolbar toolbar;
    @Bind(R.id.details_tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.details_viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        ButterKnife.bind(this);

        int pos = getIntent().getIntExtra("Position", 0);
        content = (ArrayList<Content>) getIntent().getSerializableExtra("Content");

        toolbar.setTitle("Details Page");
        setSupportActionBar(toolbar);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return DetailsFragment.newInstance(content.get(position));
            }

            @Override
            public int getCount() {
                return content.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return content.get(position).getFormattedTitle();
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
