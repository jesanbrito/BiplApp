package co.edu.unab.tas.ejuab.biplapp.model.repository;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
import co.edu.unab.tas.ejuab.biplapp.model.entity.Loan;

public class LoanRepository {

    public static final String BOOK_COLLECTION = "books";
    private static final String LOAN_COLLECTION = "loans";
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private MutableLiveData<List<Loan>> loanList;

    public LoanRepository(Context context) {
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        loanList = new MutableLiveData<>();
        listenLoans();
    }

    public LiveData<List<Loan>> getLoan()  {
        return loanList;
    }

    public void addLoan(Loan loan) {
        loan.setUser_id(auth.getUid());
        firestore.collection(LOAN_COLLECTION).add(loan).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Log.d("AddBookExit", "Prestamo Registrado!!");
                } else {
                    Log.e("AddBookErr", task.getException().getMessage());
                }
            }
        });
    }

    public void listenLoans() {
        firestore.collection(LOAN_COLLECTION).whereEqualTo("user_id",auth.getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    List<Loan> list = new ArrayList<>();
                    for(DocumentSnapshot item: value.getDocuments()) {
                        Loan loan  = item.toObject(Loan.class);
                        loan.setLid(item.getId());
                        list.add(loan);
                        loanList.setValue(list);
                    }
                    loanList.setValue(list);
                } else {
                    Log.e("firestore - Listen*", error.getMessage());
                }
            }
        });
    }

    public void loadLoansUser() {
        Log.d("user",auth.getUid());
        firestore.collection(LOAN_COLLECTION).whereEqualTo("user_id",auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Loan> list = new ArrayList<>();
                    for (DocumentSnapshot item : task.getResult().getDocuments()) {
                        Loan loan = item.toObject(Loan.class);
                        loan.setLid(item.getId());
                        firestore.collection(BookRepository.BOOK_COLLECTION).document(loan.getBook_id()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                Book book = task.getResult().toObject(Book.class);
                                loan.setBook(book);
                                list.add(loan);
                                loanList.setValue(list);
                            }
                        });
                    }
                    loanList.setValue(list);
                } else {
                    Log.e("ListLoan", task.getException().getMessage());
                }
            }
        });
    }

    public  void removeLoan(Loan loan) {
        firestore.collection(LOAN_COLLECTION).document(loan.getLid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("RemoveLoanExit","Reserva Eliminada con Exito!!");
                } else {
                    Log.e("RemoveLoan", task.getException().getMessage());
                }
            }
        });
    }
}
