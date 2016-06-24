package bbabics.hu.newsreader.activities;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import bbabics.hu.newsreader.R;
import bbabics.hu.newsreader.fragments.DetailsFragment;
import bbabics.hu.newsreader.models.Content;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();
    @BindView(R.id.details_toolbar)
    Toolbar toolbar;
    @BindView(R.id.details_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.details_viewpager)
    ViewPager viewPager;
    private ArrayList<Content> content;

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
