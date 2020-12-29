package co.edu.unab.tas.ejuab.biplapp.model.repository;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Loan;

public class LoanRepository {

    public static final String BOOK_COLLECTION = "books";
    private static final String LOAN_COLLECTION = "loans";
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    public LoanRepository(Context context) {
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void addLoan(Loan loan) {
        loan.setUser_id(auth.getUid());
        firestore.collection(BOOK_COLLECTION).document(loan.getBook().getBid()).collection(LOAN_COLLECTION).add(loan).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
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
}
