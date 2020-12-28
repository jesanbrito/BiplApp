package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityRegisterBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding registerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterViewModel viewModel = new ViewModelProvider(RegisterActivity.this).get(RegisterViewModel.class);
        registerBinding = DataBindingUtil.setContentView(RegisterActivity.this, R.layout.activity_register);
        registerBinding.setUser(new User());

        registerBinding.btRegisterForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User myUser = registerBinding.getUser();
                String pass = registerBinding.getPassword();
                myUser.setStatus("Habilitado");
                myUser.setRole(2);

                Log.e("variables", myUser.getEmail() + " " + pass);

                viewModel.singUp(myUser, pass);

                viewModel.getCurrentUser().observe(RegisterActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if(user != null){
                            finish();
                        }
                    }
                });
            }
        });
    }
}