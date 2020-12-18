package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG_LIFE_CYCLE = "lifeCycle";

    private ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        //mainBinding.setEmail("jesus@correo.com");
        //mainBinding.setPassword("123456");

        mainBinding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mainBinding.getEmail();
                String pass = mainBinding.getPassword();


                if(email.equals("jesus@correo.com")&&pass.equals("123456")){
                    Toast.makeText(MainActivity.this, "Bienvenido...", Toast.LENGTH_LONG).show();

                    Intent myIntent = new Intent(MainActivity.this, ActivityBookList.class);
                    startActivity(myIntent);

                }else{
                    Toast.makeText(MainActivity.this, "Datos errados...", Toast.LENGTH_LONG).show();
                }

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

}