 package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityDetailBookBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookViewModel;

 public class ActivityDetailBook extends AppCompatActivity {

     private static final int REQUEST_CODE_EDIT_BOOK = 100;
     private ActivityDetailBookBinding detailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Book book = (Book) getIntent().getSerializableExtra("book");
        BookViewModel viewModel =  new ViewModelProvider(ActivityDetailBook.this).get(BookViewModel.class);
        detailBinding = DataBindingUtil.setContentView(ActivityDetailBook.this, R.layout.activity_detail_book);
        detailBinding.setBook(book);

        detailBinding.btEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent =  new Intent(ActivityDetailBook.this, ActivityAddBook.class);
                myIntent.putExtra("book", detailBinding.getBook());
                startActivityForResult(myIntent,REQUEST_CODE_EDIT_BOOK);
            }
        });

        detailBinding.btDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(ActivityDetailBook.this);
                alerta.setMessage("Esta Seguro de Eliminar el Libro "+book.getTitle()+"?").setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.removeProduct(book);
                        setResult(RESULT_OK);
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog mensaje = alerta.create();
                mensaje.setTitle("Eliminar Registro...");
                mensaje.show();
            }
        });
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         switch (requestCode) {
             case REQUEST_CODE_EDIT_BOOK:
                 if (resultCode == RESULT_OK) {
                     Book editBook = (Book) data.getSerializableExtra("book");
                     detailBinding.setBook(editBook);
                 }
                 break;
         }
     }
}