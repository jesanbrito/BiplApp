package co.edu.unab.tas.ejuab.biplapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.model.repository.BookRepository;

public class BookViewModel extends AndroidViewModel {

    private BookRepository bookRepository;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(application);
    }

    public void loadBooks() {
        bookRepository.loadBooks();
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
    }
}
