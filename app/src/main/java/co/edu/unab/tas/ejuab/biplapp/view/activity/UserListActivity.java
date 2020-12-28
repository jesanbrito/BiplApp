package co.edu.unab.tas.ejuab.biplapp.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityUserListBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.UserAdapter;

public class UserListActivity  extends AppCompatActivity {

    private UserAdapter myAdapter;
    private ArrayList<User> userList;
    private ActivityUserListBinding userListBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userListBinding = DataBindingUtil.setContentView(UserListActivity.this, R.layout.activity_user_list);


        userList = new ArrayList<>();
        myAdapter = new UserAdapter(userList);

        userListBinding.rvUsers.setHasFixedSize(true);
        userListBinding.rvUsers.setAdapter(myAdapter);

    }

}
