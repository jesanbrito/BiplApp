 package co.edu.unab.tas.ejuab.biplapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import co.edu.unab.tas.ejuab.biplapp.view.activity.ActivityBookListAdmin;

 public class ActivityDetailBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(ActivityDetailBook.this,R.layout.activity_detail_book);
    }
}