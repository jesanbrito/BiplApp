package co.edu.unab.tas.ejuab.biplapp.model.repository;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;

public class BookRepository {

    private static final String IMAGE_DIRECTORY = "image";
    private static final String BOOK_COLLECTION = "books";
    private MutableLiveData<List<Book>> bookList;
    private FirebaseFirestore firestore;
    private StorageReference reference;
    private MutableLiveData<Boolean> ready;

    public BookRepository(Context context) {
        bookList = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();
        reference = FirebaseStorage.getInstance().getReference();
        ready = new MutableLiveData<>();
        listenBooks();
    }

    public LiveData<List<Book>> getBooks()  {
        return bookList;
    }

    public void listenBooks() {
        firestore.collection(BOOK_COLLECTION).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    List<Book> list = new ArrayList<>();
                    for(DocumentSnapshot item: value.getDocuments()) {
                        Book book  = item.toObject(Book.class);
                        book.setBid(item.getId());
                        list.add(book);
                        bookList.setValue(list);
                    }
                    bookList.setValue(list);
                } else {
                    Log.e("firestore - Listen*", error.getMessage());
                }
            }
        });
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

    public void addBook(Book book, Uri imageUri) {
        if (imageUri != null) {
            String image = imageUri.toString().substring(imageUri.toString().lastIndexOf("/"));
            StorageReference myImage = reference.child(IMAGE_DIRECTORY + "/" + image);
            myImage.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    String url = task.getResult().toString();
                                    book.setCover(url);
                                    firestore.collection(BOOK_COLLECTION).add(book).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("Add-Book", "Libro Creado con Exito!!");
                                            } else {
                                                Log.e("Add-Book-Err", task.getException().getMessage());
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        } else {
            firestore.collection(BOOK_COLLECTION).add(book).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()) {
                        Log.d("AddBookExit", "Libro Creado con Exito!!");
                    } else {
                        Log.e("AddBookErr", task.getException().getMessage());
                    }
                }
            });
        }
    }

    public void updateBook(Book book, Uri imageUri) {
        if (imageUri != null) {
            String image = imageUri.toString().substring(imageUri.toString().lastIndexOf("/"));
            StorageReference myImage = reference.child(IMAGE_DIRECTORY + "/" + image);
            myImage.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    String url = task.getResult().toString();
                                    book.setCover(url);
                                    firestore.collection(BOOK_COLLECTION).document(book.getBid()).set(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                ready.setValue(true);
                                            } else {
                                                Log.e("UpdateBookErr", task.getException().getMessage());
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        } else {
            firestore.collection(BOOK_COLLECTION).document(book.getBid()).set(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        ready.setValue(true);
                    } else {
                        Log.e("UpdateBookErr", task.getException().getMessage());
                    }
                }
            });
        }
    }

    public  void removeBook(Book book) {
        firestore.collection(BOOK_COLLECTION).document(book.getBid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("RemoveBookExit","Libro Eliminado con Exito!!");
                } else {
                    Log.e("RemoveBook", task.getException().getMessage());
                }
            }
        });
    }

}
