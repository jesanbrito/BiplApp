package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityBookListBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.BookAdapter;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookListViewModel;

public class ActivityBookList extends AppCompatActivity {

    private ArrayList<Book> bookList;
    private BookAdapter adapter;
    private ActivityBookListBinding bookListBinding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_add_book:
               /* Intent in =  new Intent(ActivityBookList.this,ActivityAddBook.class);
                startActivity(in);*/
                break;
            case R.id.mi_list_book:
                Intent inAdmin =  new Intent(ActivityBookList.this,ActivityBookListAdmin.class);
                startActivity(inAdmin);
                break;
            case R.id.mi_close_session:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Intent i =  new Intent(ActivityBookList.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookListBinding = DataBindingUtil.setContentView(ActivityBookList.this,R.layout.activity_book_list);
        BookListViewModel viewModel = new ViewModelProvider(ActivityBookList.this).get(BookListViewModel.class);
        bookListBinding.setViewModel(viewModel);
        adapter = new BookAdapter(new ArrayList<>());
        viewModel.getBooks().observe(ActivityBookList.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapter.setBooks((ArrayList<Book>) books);
            }
        });
        bookListBinding.rvBooks.setHasFixedSize(true);
        bookListBinding.rvBooks.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bookListBinding.getViewModel().loadBooks();
    }
}