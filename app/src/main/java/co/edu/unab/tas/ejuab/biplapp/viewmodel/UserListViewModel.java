package co.edu.unab.tas.ejuab.biplapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.model.repository.UserRepository;

public class UserListViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<User>> userList;
    private UserRepository userRepository;


    public UserListViewModel(@NonNull Application application) {
        super(application);
        userList = new MutableLiveData<>();
        userRepository = new UserRepository(application);
    }

    public LiveData<List<User>> getUserList(){
        return userRepository.getUsers();
    }

    public void loadUsers(){
        userRepository.loadUsers();
    }
}
