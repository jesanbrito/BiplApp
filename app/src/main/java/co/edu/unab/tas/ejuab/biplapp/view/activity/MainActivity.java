package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import co.edu.unab.tas.ejuab.biplapp.R;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG_LIFE_CYCLE = "lifeCycle";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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