package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivitySearchBookBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.BookAdapter;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookListViewModel;

public class SearchBookActivity extends AppCompatActivity {

    //private ActivityBookListAdminBinding bookListAdminBinding;
    private ActivitySearchBookBinding searchBookBinding;
    private BookAdapter myAdapter;
    private String filterSelect= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search_book);

        searchBookBinding = DataBindingUtil.setContentView(SearchBookActivity.this, R.layout.activity_search_book);
        BookListViewModel viewModel = new ViewModelProvider(SearchBookActivity.this).get(BookListViewModel.class);
        searchBookBinding.setViewModelAdmin(viewModel);
        myAdapter = new BookAdapter(new ArrayList<>());

        viewModel.getBooks().observe(SearchBookActivity.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                myAdapter.setBooks((ArrayList<Book>) books);
            }
        });

        searchBookBinding.rvSearchBook.setHasFixedSize(true);
        searchBookBinding.rvSearchBook.setAdapter(myAdapter);

        String[] textFilter = getResources().getStringArray(R.array.statusbook);
        ArrayAdapter<String> adapterSP = new ArrayAdapter<String>(SearchBookActivity.this, R.layout.text_view_spinner, textFilter);
        adapterSP.setDropDownViewResource(R.layout.text_view_spinner);
        searchBookBinding.spSearch.setAdapter(adapterSP);

        searchBookBinding.spSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterSelect = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchBookBinding.searchBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer filter = 1;

                if (filterSelect.equals("Reservado")) {
                    filter = 2;
                } else if (filterSelect.equals("Prestado")) {
                    filter = 3;
                }
                viewModel.loadSearchBook(filter);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        searchBookBinding.getViewModelAdmin().loadBooks();
    }
}