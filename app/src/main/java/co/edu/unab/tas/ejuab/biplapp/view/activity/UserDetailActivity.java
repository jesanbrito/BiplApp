package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_administrador, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent in;
        switch (item.getItemId()){
            case R.id.mi_close_session:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                in = new Intent(UserDetailActivity.this, MainActivity.class);
                startActivity(in);
                finish();
                break;
            case R.id.mi_admin_user:
                in = new Intent(UserDetailActivity.this, UserListActivity.class);
                startActivity(in);
                break;
        }
        return super.onOptionsItemSelected(item);
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