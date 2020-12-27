package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityMainBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG_LIFE_CYCLE = "lifeCycle";
    private ActivityMainBinding mainBinding;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainViewModel viewModel = new ViewModelProvider(MainActivity.this).get(MainViewModel.class);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        mainBinding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mainBinding.getEmail();
                String pass = mainBinding.getPassword();
                viewModel.signIn(email,pass);
                viewModel.getCurrentUser().observe(MainActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user != null) {
                            Toast.makeText(MainActivity.this, "Bienvenido "+user.getName()+" "+user.getLastName()+" !!", Toast.LENGTH_SHORT).show();
                            Intent myIntent =  new Intent(MainActivity.this,ActivityBookList.class);
                            startActivity(myIntent);
                        } else {
                            Toast.makeText(MainActivity.this, "No existe usuario en el sistema..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        mainBinding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(myIntent);

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LIFE_CYCLE, "Ejecutando método onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG_LIFE_CYCLE,  "Ejecutando método onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG_LIFE_CYCLE,  "Ejecutando método onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG_LIFE_CYCLE,  "Ejecutando método onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG_LIFE_CYCLE,  "Ejecutando método onDestroy");
    }

    public static class UserFormActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_form);
        }
    }
}