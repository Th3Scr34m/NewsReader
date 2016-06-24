package bbabics.hu.newsreader.activities;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import bbabics.hu.newsreader.R;
import bbabics.hu.newsreader.fragments.AlertDialogFragment;
import bbabics.hu.newsreader.fragments.MainFragment;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private Unbinder unbinder;
    @BindView(R.id.main_toolbar)
    public Toolbar toolbar;
    @BindView(R.id.main_tab_layout)
    public TabLayout tabLayout;
    @BindView(R.id.main_viewpager)
    public ViewPager viewPager;
    private ArrayList<bbabics.hu.newsreader.fragments.MainFragment> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        unbinder = ButterKnife.bind(this);

        toolbar.setTitle("Main Page");
        setSupportActionBar(toolbar);

        content = new ArrayList<>(Arrays.asList(MainFragment.newInstance(), MainFragment.newInstance()));

        if (isOnline()) {

            viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

                @Override
                public Fragment getItem(int position) {
                    return content.get(position);
                }

                @Override
                public int getCount() {
                    return content.size();
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

            alertDialog.setCallback((retry) -> {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null) {
            unbinder.unbind();
        }
    }
}
