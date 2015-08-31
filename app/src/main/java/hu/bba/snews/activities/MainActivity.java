package hu.bba.snews.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bba.snews.R;
import hu.bba.snews.fragments.AlertDialogFragment;
import hu.bba.snews.fragments.DetailsFragment;
import hu.bba.snews.fragments.MainFragment;
import hu.bba.snews.models.Content;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.main_toolbar)
    public Toolbar toolbar;
    @Bind(R.id.main_tab_layout)
    public TabLayout tabLayout;
    @Bind(R.id.main_viewpager)
    public ViewPager viewPager;
    private ArrayList<Fragment> content;
    private Content detailsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_layout);

        ButterKnife.bind(this);

        toolbar.setTitle("Main Page");
        setSupportActionBar(toolbar);

        content = new ArrayList<>(Arrays.asList(MainFragment.newInstance(), DetailsFragment.newInstance(detailsContent)));

        if (isOnline()) {

            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }

            viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return content.get(position);
                }

                @Override
                public int getCount() {
                    return 2;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    if (position == 0) {
                        return "Online";
                    } else {
                        return "Local";
                    }
                }
            });

            tabLayout.setupWithViewPager(viewPager);

        } else {
            AlertDialogFragment alertDialog = AlertDialogFragment.newInstance(R.string.internet_dialog, R.string.internet_dialog_text);

            alertDialog.setCallback(retry -> {
                if (retry) {
                    recreate();
                } else {
                    finish();
                    System.exit(0);
                }
            });
            alertDialog.show(this.getSupportFragmentManager(), TAG);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
