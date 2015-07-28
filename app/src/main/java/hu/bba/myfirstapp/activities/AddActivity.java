package hu.bba.myfirstapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import hu.bba.myfirstapp.R;

public class AddActivity extends AppCompatActivity {

    private Toolbar ActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBarToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(ActionBarToolbar);
        getSupportActionBar().setTitle("Add New News Page");
    }
}
