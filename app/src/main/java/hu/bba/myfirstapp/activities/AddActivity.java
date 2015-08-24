package hu.bba.myfirstapp.activities;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.util.Calendar;

import butterknife.Bind;
import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.interfaces.ScrollViewListener;
import hu.bba.myfirstapp.models.AddObject;
import hu.bba.myfirstapp.models.ScrollViewExt;
import io.realm.Realm;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, ScrollViewListener {

    private static final String TAG = AddActivity.class.getSimpleName();
    private static final int TAKE_PICTURE = 1;
    private static Toolbar toolbar;
    private static Uri imageUri;
    private static Button addDate;
    private static TextView dateTextView;
    private static ScrollViewExt scroll;

    @Bind(R.id.add_title)
    EditText editTextTitle;
    @Bind(R.id.add_desc)
    EditText editTextDesc;
    @Bind(R.id.add_date_text_view)
    TextView textViewDate;
    @Bind(R.id.add_image)
    ImageView imageViewImage;
    @Bind(R.id.add_caption)
    EditText editTextCaption;
    @Bind(R.id.add_email)
    EditText editTextEmail;

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

        addDate.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    AddActivity.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");
        });

        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
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

                    try {
                        Picasso.with(this)
                                .load(selectedImage)
                                .resize(200, 200)
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder_err)
                                .into(imageView);
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
                            .text(getString(R.string.save_success_text))
                            .textColor(Color.WHITE)
                            .actionColor(Color.WHITE)
                            .color(getResources().getColor(R.color.toolbar_primary))
                            .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                            .swipeToDismiss(false)
                            .actionLabel(getString(R.string.save_button))
                            .actionLabelTypeface(Typeface.DEFAULT_BOLD)
                            .actionListener(snackbar -> Snackbar.with(getApplicationContext()).text(getString(R.string.save_success_text_onclick))), this);
        }
        else {
            SnackbarManager.dismiss();
        }
    }

    public void saveToFile() {
        Realm realm = Realm.getInstance(this);

        realm.beginTransaction();
        AddObject addObject = realm.createObject(AddObject.class);
//            addObject.setRealmTitle(toString(editTextTitle.toString()));
//            addObject.setRealmDesc(toString(editTextDesc.toString()));
//            addObject.setRealmDate(toString(textViewDate.toString()));
//            addObject.setRealmImageUrl(toString());
//            addObject.setRealmCaption(toString(editTextCaption.toString()));
//            addObject.setRealmEmail(toString(editTextEmail.toString()));
        realm.commitTransaction();
    }
}
