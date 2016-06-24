package bbabics.hu.newsreader.activities;

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
import android.text.TextUtils;
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

import bbabics.hu.newsreader.R;
import bbabics.hu.newsreader.interfaces.ScrollViewListener;
import bbabics.hu.newsreader.models.AddObject;
import bbabics.hu.newsreader.models.ScrollViewExt;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, ScrollViewListener {

    private static final String TAG = AddActivity.class.getSimpleName();
    private static final int TAKE_PICTURE = 1;
    private static Toolbar toolbar;
    private static Uri imageUri;
    private static Button dateButton;
    private static TextView dateTextView;
    private static ScrollViewExt scroll;
    private static String addTitle;
    private static String addDesc;
    private static String addDate;
    private static String addImage;
    private static String addCaption;
    private static String addEmail;

    @BindView(R.id.add_title)
    EditText title;
    @BindView(R.id.add_desc)
    EditText desc;
    @BindView(R.id.add_date_text_view)
    TextView date;
    @BindView(R.id.add_image)
    ImageView image;
    @BindView(R.id.add_caption)
    EditText caption;
    @BindView(R.id.add_email)
    EditText email;
    private String imagePath;

    public static boolean createDirIfNotExists(String path) {
        boolean ret = true;
        File file = new File(Environment.getExternalStorageDirectory(), path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TravellerLog :: ", "Problem creating Image folder");
                ret = false;
            }
        }
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.add_toolbar);
        toolbar.setTitle("Add Page");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dateButton = (Button) findViewById(R.id.add_date_button);

        scroll = (ScrollViewExt) findViewById(R.id.add_scrollview);
        scroll.setScrollViewListener(this);

        dateButton.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    AddActivity.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");
        });

        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, null));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .name("local.realm")
                .schemaVersion(42)
                .build();
        Realm.setDefaultConfiguration(configuration);
        readFromFile();
    }

    public void takePhoto(View view) {

        createDirIfNotExists("/ScolvoNews/");

        imagePath = "ScolvoNews/img_" + System.currentTimeMillis() + ".jpg";
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(), imagePath);
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
                                .resize(240, 240)
                                .centerCrop()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder_err)
                                .into(imageView);
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "." + (monthOfYear + 1) + "." + dayOfMonth + ".";
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
                            .actionListener(snackbar -> validateFields(
                                    addTitle = title.getText().toString(),
                                    addDesc = desc.getText().toString(),
                                    addDate = date.getText().toString(),
                                    addImage = imagePath,
                                    addCaption = caption.getText().toString(),
                                    addEmail = email.getText().toString())), this);
        } else {
            SnackbarManager.dismiss();
        }
    }

    public void validateFields(String titleAdd, String descAdd, String dateAdd, String imageAdd, String captionAdd, String emailAdd) {

        if (TextUtils.isEmpty(titleAdd) || TextUtils.isEmpty(descAdd) || TextUtils.isEmpty(dateAdd) || TextUtils.isEmpty(imageAdd) || TextUtils.isEmpty(captionAdd) || TextUtils.isEmpty(emailAdd)) {
            SnackbarManager.show(
                    Snackbar.with(this)
                            .text(getText(R.string.fail_text))
                            .actionLabel(R.string.fail_button)
                            .actionLabelTypeface(Typeface.DEFAULT_BOLD)
                            .color(getResources().getColor(R.color.missing_fields))
            );
            Log.d("Validation fail", TAG);
        } else {

            saveToFile(titleAdd, descAdd, dateAdd, imageAdd, captionAdd, emailAdd);
            Log.d("Validation success", TAG);
            super.onBackPressed();
        }
    }

    public void saveToFile(String titleAdd, String descAdd, String dateAdd, String imageAdd, String captionAdd, String emailAdd) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        AddObject addObject = realm.createObject(AddObject.class);

        addObject.setRealmTitle(titleAdd);
        addObject.setRealmDesc(descAdd);
        addObject.setRealmDate(dateAdd);
        addObject.setRealmImageUrl(imageAdd);
        addObject.setRealmCaption(captionAdd);
        addObject.setRealmEmail(emailAdd);
        realm.commitTransaction();
    }

    public RealmResults<AddObject> readFromFile() {

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<AddObject> query = realm.where(AddObject.class);
        RealmResults<AddObject> results = query.findAll();

        return results;
    }
}
