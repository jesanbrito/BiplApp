package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityAddBookBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookViewModel;

public class ActivityAddBook extends AppCompatActivity {

    private static final int CODE_REQUEST_CAMARA = 100;
    private static final int CODE_REQUEST_GALLERY = 111;
    ActivityAddBookBinding activityAddBookBinding;
    private String categorySelected = "";
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BookViewModel viewModel = new ViewModelProvider(ActivityAddBook.this).get(BookViewModel.class);
        Book myBook  = (Book) getIntent().getSerializableExtra("book");
        activityAddBookBinding = DataBindingUtil.setContentView(ActivityAddBook.this, R.layout.activity_add_book);

        String[] textCategory = getResources().getStringArray(R.array.categorys);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityAddBook.this,R.layout.text_view_spinner,textCategory);
        adapter.setDropDownViewResource(R.layout.text_view_spinner);
        activityAddBookBinding.spCategoryBookAdd.setAdapter(adapter);

        activityAddBookBinding.spCategoryBookAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (myBook != null) {
            activityAddBookBinding.setBook(myBook);
            activityAddBookBinding.btAddBook.setText("Editar Libro");
            activityAddBookBinding.btAddBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book editBook =  activityAddBookBinding.getBook();
                    editBook.setCategory(categorySelected);
                    viewModel.updateBook(myBook,imageUri);
                    Intent data = new Intent();
                    data.putExtra("book",editBook);
                    setResult(RESULT_OK,data);
                    finish();
                }
            });
        } else {
            activityAddBookBinding.setBook(new Book());
            activityAddBookBinding.btAddBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book nBook = activityAddBookBinding.getBook();
                    nBook.setCategory(categorySelected);
                    Log.e("add", nBook.toString());
                    viewModel.addBook(nBook,imageUri);
                    finish();
                }
            });
        }

        activityAddBookBinding.ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (myIntent.resolveActivity(getPackageManager()) != null) {
                    File image = null;
                    try {
                        image = createPhoto();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (image != null) {
                        imageUri = FileProvider.getUriForFile(ActivityAddBook.this,"co.edu.unab.tas.ejuab.biplapp.fileprovider", image);
                        myIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(myIntent, CODE_REQUEST_CAMARA);
                    }
                }
            }
        });

        activityAddBookBinding.ibGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (myIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(myIntent,CODE_REQUEST_GALLERY);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_REQUEST_CAMARA:
                Glide.with(ActivityAddBook.this).load(imageUri).into(activityAddBookBinding.ivBookCoverAdd);
                break;

            case CODE_REQUEST_GALLERY:
                imageUri = data.getData();
                Glide.with(ActivityAddBook.this).load(imageUri).into(activityAddBookBinding.ivBookCoverAdd);
                break;
        }
    }

    private File createPhoto() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }
}