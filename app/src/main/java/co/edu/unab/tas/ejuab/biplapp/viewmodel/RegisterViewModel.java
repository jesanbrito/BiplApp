package co.edu.unab.tas.ejuab.biplapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.model.repository.UserRepository;

public class RegisterViewModel extends AndroidViewModel {

    public UserRepository userRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> getCurrentUser(){

        return userRepository.getCurrentUser();
    }

    public void singUp(User myUser, String pass){
        userRepository.singUp(myUser, pass);
    }
}
