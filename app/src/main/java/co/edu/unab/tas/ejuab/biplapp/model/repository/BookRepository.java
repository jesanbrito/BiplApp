package co.edu.unab.tas.ejuab.biplapp.model.repository;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;

public class BookRepository {

    private static final String BOOK_COLLECTION = "books";
    private MutableLiveData<List<Book>> bookList;
    private FirebaseFirestore firestore;

    public BookRepository(Context context) {
        bookList = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();
        loadBooks();
    }

    public LiveData<List<Book>> getBooks()  {
        return bookList;
    }

    public void loadBooks() {
        firestore.collection(BOOK_COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Book> list = new ArrayList<>();
                    for(DocumentSnapshot item: task.getResult().getDocuments()) {
                        Book book  = item.toObject(Book.class);
                        book.setBid(item.getId());
                        list.add(book);
                        bookList.setValue(list);
                    }
                } else {
                    Log.e("firestore", task.getException().getMessage());
                }
            }
        });
    }

    public void addBook(Book book) {
            firestore.collection(BOOK_COLLECTION).add(book).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()) {
                        Log.d("Add-Book", "Libro Creado con Exito!!");
                    } else {
                        Log.e("firestore", task.getException().getMessage());
                    }
                }
            });
        }
}
