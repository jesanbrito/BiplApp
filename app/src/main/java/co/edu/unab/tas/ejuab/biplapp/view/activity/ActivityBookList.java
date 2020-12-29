package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityBookListBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Loan;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.BookAdapter;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookListViewModel;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookViewModel;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.LoanViewModel;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.RegisterViewModel;

public class ActivityBookList extends AppCompatActivity {

    private ArrayList<Book> bookList;
    private BookAdapter adapter;
    private ActivityBookListBinding bookListBinding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_peril:
                Intent in =  new Intent(ActivityBookList.this, ActivityUserForm.class  );
                startActivity(in);
                break;
            case R.id.mi_close_session:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Intent i =  new Intent(ActivityBookList.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookListBinding = DataBindingUtil.setContentView(ActivityBookList.this,R.layout.activity_book_list);
        BookListViewModel viewModel = new ViewModelProvider(ActivityBookList.this).get(BookListViewModel.class);
        BookViewModel viewModelBook =  new ViewModelProvider(ActivityBookList.this).get(BookViewModel.class);
        LoanViewModel viewModelLoan =  new ViewModelProvider(ActivityBookList.this).get(LoanViewModel.class);
        RegisterViewModel viewModelUser = new ViewModelProvider(ActivityBookList.this).get(RegisterViewModel.class);

        bookListBinding.setViewModel(viewModel);
        adapter = new BookAdapter(new ArrayList<>());
        viewModel.getBooks().observe(ActivityBookList.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                if (books.isEmpty()){
                   Log.d("book", "No se encontro libro");
                }
                adapter.setBooks((ArrayList<Book>) books);
            }
        });
        bookListBinding.rvBooks.setHasFixedSize(true);
        bookListBinding.rvBooks.setAdapter(adapter);

        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book, int position) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(ActivityBookList.this);
                alerta.setMessage("Esta Seguro de Reservar el Libro "+book.getTitle()+"?. Recuerde, tiene 3 días hábiles para retirarlo de la Biblioteca.").setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int codReserva = (int) Math.floor(Math.random()*100);
                        book.setStatus(2);
                        viewModelBook.updateBook(book, null);

                        long date = System.currentTimeMillis();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = sdf.format(date);

                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DAY_OF_YEAR,3);
                        Date deliverTime = calendar.getTime();
                        String deliverString = sdf.format(deliverTime);

                        Loan loan = new Loan();
                        loan.setRegistry_date(dateString);
                        loan.setBook(book);
                        loan.setStatus(true);
                        loan.setDeliver_date(deliverString);
                        loan.setCodigo_reserva("RV00"+codReserva);
                        viewModelLoan.addLoan(loan);
                        setResult(RESULT_OK);
                        Toast.makeText(ActivityBookList.this,"Prestamo No. RV00"+codReserva+" asignado", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog mensaje = alerta.create();
                mensaje.setTitle("Reservar Libro!!");
                mensaje.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bookListBinding.getViewModel().loadBooks();
    }

}