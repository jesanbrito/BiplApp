package co.edu.unab.tas.ejuab.biplapp.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.tas.ejuab.biplapp.model.entity.User;

public class UserRepository {

    public static final String USER_COLLECTION = "users";
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private MutableLiveData<User> currentUser;

    public UserRepository(Context context){

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        currentUser = new MutableLiveData<>();

    }

    public LiveData<User> getCurrentUser() {

        return currentUser;
    }

    public void singUp(User myUser, String pass) {
        auth.createUserWithEmailAndPassword(myUser.getEmail(), pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firestore.collection(USER_COLLECTION).document(auth.getUid()).set(myUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                myUser.setUid(auth.getUid());
                                currentUser.setValue(myUser);

                            }else{
                                Log.e("signup", task.getException().getMessage());
                            }
                        }
                    });
                }else{
                    Log.e("signup", task.getException().getMessage());
                }
            }
        });
    }

    public void signIn(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firestore.collection(USER_COLLECTION).document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult() != null) {
                                    User myUser = task.getResult().toObject(User.class);
                                    myUser.setUid(auth.getUid());
                                    currentUser.setValue(myUser);
                                }
                            } else {
                                Log.e("signin",task.getException().getMessage());
                            }

                        }
                    });
                } else {
                    Log.e("signin",task.getException().getMessage());
                }
            }
        });
    }


}
