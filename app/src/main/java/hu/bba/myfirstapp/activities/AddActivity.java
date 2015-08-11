package hu.bba.myfirstapp.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.util.Calendar;

import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.interfaces.ScrollViewListener;
import hu.bba.myfirstapp.models.ScrollViewExt;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, ScrollViewListener {

    private static final String TAG = AddActivity.class.getSimpleName();
    private static final int TAKE_PICTURE = 1;

    private static Toolbar toolbar;
    private static Uri imageUri;
    private static Button addDate;
    private static TextView dateTextView;
    private static ScrollViewExt scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        toolbar = (Toolbar) findViewById(R.id.add_toolbar);
        toolbar.setTitle("Add Page");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addDate = (Button) findViewById(R.id.add_date_button);

        scroll = (ScrollViewExt) findViewById(R.id.add_scrollview);
        scroll.setScrollViewListener(this);

        addDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void takePhoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(), "image.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ImageView imageView = (ImageView) findViewById(R.id.add_image);
                    ContentResolver cr = getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        imageView.setImageBitmap(bitmap);
                        Toast.makeText(this, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "Picked date: " + " " + year + "." + (monthOfYear + 1) + "." + dayOfMonth + ".";
        dateTextView = (TextView) findViewById(R.id.add_date_text_view);
        dateTextView.setText(date);
    }

    @Override
    public void onScrollChanged(ScrollViewExt scrollView, int x, int y, int oldx, int oldy) {
        View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
        int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

        if (diff == 0) {
            SnackbarManager.show(
                    Snackbar.with(getApplicationContext())
                            .text(String.format(getString(R.string.save_success_text)))
                            .textColor(Color.WHITE)
                            .actionColor(Color.WHITE)
                            .color(getResources().getColor(R.color.toolbar_primary))
                            .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                            .swipeToDismiss(false)
                            .actionLabel(String.format(getString(R.string.save_button)))
                            .actionLabelTypeface(Typeface.DEFAULT_BOLD)
                            .actionListener(new ActionClickListener() {
                                @Override
                                public void onActionClicked(Snackbar snackbar) {
                                    Snackbar.with(getApplicationContext()).text(String.format(getString(R.string.save_success_text_onclick)));
                                }
                            })
                    , this);
        }
        else {
            SnackbarManager.dismiss();
        }
    }
}
