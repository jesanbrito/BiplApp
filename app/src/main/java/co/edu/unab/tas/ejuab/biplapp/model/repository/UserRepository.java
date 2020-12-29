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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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

import co.edu.unab.tas.ejuab.biplapp.model.entity.User;

public class   UserRepository {

    public static final String USER_COLLECTION = "users";
    private static final String IMAGE_DIRECTORY = "images" ;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private MutableLiveData<User> currentUser;
    private StorageReference myReference;
    private MutableLiveData<Boolean> ready;
    private MutableLiveData<List<User>> userList;

    public UserRepository(Context context){
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        currentUser = new MutableLiveData<>();
        myReference = FirebaseStorage.getInstance().getReference();
        ready = new MutableLiveData<>();
        userList = new MutableLiveData<>();
        listenUsers();
        loadUsers();
    }

    public MutableLiveData<List<User>> getUsers() {
        return userList;
    }

    public void listenUsers(){
        firestore.collection(USER_COLLECTION).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error == null){
                    List<User> list = new ArrayList<>();
                    for (DocumentSnapshot item: value.getDocuments()) {
                        User myUser = item.toObject(User.class);
                        myUser.setUid(item.getId());
                        list.add(myUser);
                        userList.setValue(list);
                    }
                    userList.setValue(list);
                }else{
                    Log.e("firestore - Listen", error.getMessage());
                }
            }
        });
    }

    public void loadUsers(){
        firestore.collection(USER_COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    List<User> list = new ArrayList<>();
                    for (DocumentSnapshot item: task.getResult().getDocuments()){
                        User user = item.toObject(User.class);
                        user.setUid(item.getId());
                        list.add(user);
                        userList.setValue(list);
                    }
                } else{
                    Log.e("firestore", task.getException().getMessage());
                }
            }
        });
    }

    public LiveData<Boolean> getReady() {
        return ready;
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

    public void updateProfile(User myUser, Uri imageUri){

        Log.d("usuario", myUser.getDocument());

        if(imageUri != null){
            String image = imageUri.toString().substring(imageUri.toString().lastIndexOf("/"));
            StorageReference myImage = myReference.child(IMAGE_DIRECTORY + "/" + image);
            myImage.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.isSuccessful()){
                                    String url = task.getResult().toString();
                                    myUser.setUrlImage(url);
                                    firestore.collection(USER_COLLECTION).document(myUser.getUid()).set(myUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                ready.setValue(true);
                                            }else{
                                                Log.e("perfil", task.getException().getMessage());
                                            }
                                        }
                                    });
                                }

                            }
                        });
                    }
                }
            });
        }else{
            firestore.collection(USER_COLLECTION).document(myUser.getUid()).set(myUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        ready.setValue(true);
                    }else{
                        Log.e("perfil", task.getException().getMessage());
                    }
                }
            });
        }

    }

    public void removeUser(User myUser){
        firestore.collection(USER_COLLECTION).document(myUser.getUid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    loadUsers();
                }else{
                    Log.e("firestore", task.getException().getMessage());
                }
            }
        });
    }

}
