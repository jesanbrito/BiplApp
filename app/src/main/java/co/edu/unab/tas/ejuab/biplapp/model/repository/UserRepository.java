package co.edu.unab.tas.ejuab.biplapp.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import co.edu.unab.tas.ejuab.biplapp.model.entity.User;

public class UserRepository {

    private MutableLiveData<User> currentUser;


    public LiveData<User> getCurrentUser() {
        return currentUser;
    }
}
