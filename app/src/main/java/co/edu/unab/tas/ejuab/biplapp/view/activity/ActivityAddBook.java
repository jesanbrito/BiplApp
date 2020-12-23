package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityAddBookBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookViewModel;

public class ActivityAddBook extends AppCompatActivity {

    ActivityAddBookBinding activityAddBookBinding;
    private String categorySelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BookViewModel viewModel = new ViewModelProvider(ActivityAddBook.this).get(BookViewModel.class);
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

        activityAddBookBinding.setBook(new Book());
        activityAddBookBinding.btAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book nBook = activityAddBookBinding.getBook();
                nBook.setCategory(categorySelected);
                Log.e("add", nBook.toString());
                viewModel.addBook(nBook);
                finish();
            }
        });
    }

}