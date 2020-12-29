package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityUserDetailBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.UserListViewModel;

public class UserDetailActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_EDIT_USER = 220;
    private ActivityUserDetailBinding detailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_detail);

        User myUser = (User) getIntent().getSerializableExtra("user");

        UserListViewModel viewModel = new ViewModelProvider(UserDetailActivity.this).get(UserListViewModel.class);

        detailBinding = DataBindingUtil.setContentView(UserDetailActivity.this, R.layout.activity_user_detail);
        detailBinding.setUser(myUser);

        detailBinding.btEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserDetailActivity.this, ActivityUserForm.class);
                myIntent.putExtra("user", detailBinding.getUser());
                startActivityForResult(myIntent, REQUEST_CODE_EDIT_USER);
            }
        });

        detailBinding.btDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.removeUser(myUser);
                setResult(RESULT_OK);
                finish();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_EDIT_USER:
                if(resultCode == RESULT_OK){
                    User myEditUser = (User) data.getSerializableExtra("user");
                    detailBinding.setUser(myEditUser);
                }
                break;
        }
    }
}