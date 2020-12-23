package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.ActivityDetailBook;
import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityBookListAdminBinding;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityBookListBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.BookAdapter;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookListViewModel;

public class ActivityBookListAdmin extends AppCompatActivity {

    private static final int REQUEST_CODE_BOOK_DETAIL = 100;
    private BookAdapter adapter;
    private ActivityBookListAdminBinding bookListAdminBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookListAdminBinding = DataBindingUtil.setContentView(ActivityBookListAdmin.this,R.layout.activity_book_list_admin);
        BookListViewModel viewModel = new ViewModelProvider(ActivityBookListAdmin.this).get(BookListViewModel.class);
        bookListAdminBinding.setViewModelAdmin(viewModel);
        adapter = new BookAdapter(new ArrayList<>());
        viewModel.getBooks().observe(ActivityBookListAdmin.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapter.setBooks((ArrayList<Book>) books);
            }
        });
        bookListAdminBinding.rvBooksAdmin.setHasFixedSize(true);
        bookListAdminBinding.rvBooksAdmin.setAdapter(adapter);
        bookListAdminBinding.btAddBookAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =  new Intent(ActivityBookListAdmin.this,ActivityAddBook.class);
                startActivity(in);
            }
        });

        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book, int position) {
                Intent myIntent = new Intent(ActivityBookListAdmin.this, ActivityDetailBook.class);
              //  myIntent.putExtra("book",book);
                startActivityForResult(myIntent, REQUEST_CODE_BOOK_DETAIL);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        bookListAdminBinding.getViewModelAdmin().loadBooks();
    }
}