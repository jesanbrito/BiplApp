package co.edu.unab.tas.ejuab.biplapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.model.repository.UserRepository;

public class MainViewModel  extends AndroidViewModel {

    private UserRepository userRepository;

        public MainViewModel(@NonNull Application application) {
            super(application);
            userRepository = new UserRepository(application);
        }

        public LiveData<User> getCurrentUser() {
            return  userRepository.getCurrentUser();
        }

        public void signIn (String email, String pass) {
            userRepository.signIn(email,pass);
        }
    }
