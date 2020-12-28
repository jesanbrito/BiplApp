package co.edu.unab.tas.ejuab.biplapp.model.repository;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Loan;

public class LoanRepository {

    private static final String LOAN_COLLECTION = "loans";
    private FirebaseFirestore firestore;

    public LoanRepository(Context context) {
        firestore = FirebaseFirestore.getInstance();
    }

    public void addLoan(Loan loan) {
        firestore.collection(LOAN_COLLECTION).add(loan).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
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
