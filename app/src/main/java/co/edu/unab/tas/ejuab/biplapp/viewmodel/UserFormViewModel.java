package co.edu.unab.tas.ejuab.biplapp.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.model.repository.UserRepository;

public class UserFormViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserFormViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void editUser(User myUser, Uri imageUri){
        userRepository.updateProfile(myUser, imageUri);
    }

    public LiveData<User> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

   public LiveData<Boolean> getReady(){
        return userRepository.getReady();
   }
}
