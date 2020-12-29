package co.edu.unab.tas.ejuab.biplapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityUserListBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.UserAdapter;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.UserListViewModel;

public class UserListActivity  extends AppCompatActivity {

    private static final int REQUEST_CODE_USER_DETAIL = 221 ;
    private UserAdapter userAdapter;
    private ArrayList<User> userList;
    private ActivityUserListBinding userListBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userListBinding = DataBindingUtil.setContentView(UserListActivity.this, R.layout.activity_user_list);
        UserListViewModel viewModel = new ViewModelProvider(UserListActivity.this).get(UserListViewModel.class);

        userListBinding.setViewModel(viewModel);

        userAdapter = new UserAdapter(new ArrayList<>());

        viewModel.getUserList().observe(UserListActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users.isEmpty()){

                }
                userAdapter.setUsers((ArrayList<User>) users);
            }
        });

        //userList = new ArrayList<>();
        //myAdapter = new UserAdapter(userList);

        userListBinding.rvUsers.setHasFixedSize(true);
        userListBinding.rvUsers.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User myUser, int position) {
                Toast.makeText(UserListActivity.this, "Hice Click al Usuario: " +myUser.getName()+" "+myUser.getLastName(), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(UserListActivity.this, UserDetailActivity.class);
                myIntent.putExtra("user", myUser);
                startActivityForResult(myIntent, REQUEST_CODE_USER_DETAIL);
            }
        });


        userListBinding.btCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserListActivity.this, ActivityUserForm.class);
                startActivity(myIntent);
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
    }
}
