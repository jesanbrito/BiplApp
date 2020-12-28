package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityBookListAdminBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.BookAdapter;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookListViewModel;

public class ActivityBookListAdmin extends AppCompatActivity {

    private static final int REQUEST_CODE_BOOK_DETAIL = 100;
    private BookAdapter adapter;
    private ActivityBookListAdminBinding bookListAdminBinding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_administrador, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_close_session:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Intent i =  new Intent(ActivityBookListAdmin.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.mi_admin_user:
                Intent myIntent = new Intent(ActivityBookListAdmin.this, UserListActivity.class);
                startActivity(myIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

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

        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book, int position) {
                Log.d("Seleccion",book.toString());
                Intent intent = new Intent(ActivityBookListAdmin.this, ActivityDetailBook.class);
                intent.putExtra("book", book);
                startActivityForResult(intent, REQUEST_CODE_BOOK_DETAIL);
            }
        });

        bookListAdminBinding.btAddBookAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =  new Intent(ActivityBookListAdmin.this,ActivityAddBook.class);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bookListAdminBinding.getViewModelAdmin().loadBooks();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_BOOK_DETAIL:
                Toast.makeText(this,"Regresando del detalle",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    
}