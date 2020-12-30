package co.edu.unab.tas.ejuab.biplapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.model.repository.BookRepository;

public class BookListViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Book>> books;
    private BookRepository bookRepository;

    public BookListViewModel(@NonNull Application application) {
        super(application);
        books =  new MutableLiveData<>();
        bookRepository = new BookRepository(application);
    }

    public LiveData<List<Book>> getBooks() {
        return bookRepository.getBooks();
    }

    public void loadBooks() {
        bookRepository.loadBooks();
    }

    public void loadFiltersBook(String filter, String value) {
        bookRepository.loadFiltersBook(filter,value);
    }

    public void loadSearchBook(Integer filter){
        bookRepository.loadSearchBook(filter);
    }
}
