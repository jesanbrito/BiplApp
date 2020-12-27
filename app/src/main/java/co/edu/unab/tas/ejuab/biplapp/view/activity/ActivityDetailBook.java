 package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityDetailBookBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;

 public class ActivityDetailBook extends AppCompatActivity {

     private ActivityDetailBookBinding detailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Book book = (Book) getIntent().getSerializableExtra("book");
        detailBinding = DataBindingUtil.setContentView(ActivityDetailBook.this, R.layout.activity_detail_book);
        detailBinding.setBook(book);
    }
}