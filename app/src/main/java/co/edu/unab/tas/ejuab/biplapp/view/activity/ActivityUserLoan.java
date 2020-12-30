package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityUserLoanBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Loan;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.LoanAdapter;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.BookViewModel;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.LoanViewModel;

public class ActivityUserLoan extends AppCompatActivity {
    private ActivityUserLoanBinding loanBinding;
    private LoanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loanBinding = DataBindingUtil.setContentView(ActivityUserLoan.this,R.layout.activity_user_loan);
        LoanViewModel viewModel =  new ViewModelProvider(ActivityUserLoan.this).get(LoanViewModel.class);
        BookViewModel viewModelBook =  new ViewModelProvider(ActivityUserLoan.this).get(BookViewModel.class);

        loanBinding.setViewModel(viewModel);
        adapter = new LoanAdapter(new ArrayList<>());
        viewModel.getLoans().observe(ActivityUserLoan.this, new Observer<List<Loan>>() {
            @Override
            public void onChanged(List<Loan> loan) {
                Log.d("loan", loan.toString());
                if (loan.isEmpty()){
                    Log.d("loan", "No se encontro Prestamos");
                }
                adapter.setLoans((ArrayList<Loan>) loan);
            }
        });
        loanBinding.rvLoans.setHasFixedSize(true);
        loanBinding.rvLoans.setAdapter(adapter);

        adapter.setOnItemClickListener(new LoanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Loan loan, int position) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(ActivityUserLoan.this);
                alerta.setMessage("Esta Seguro de Cancelar la Reserva "+loan.getCodigo_reserva()+"?. Este proceso es Irreversible.").setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Book book = loan.getBook();
                        book.setBid(loan.getBook_id());
                        book.setStatus(1);
                        viewModelBook.updateBook(book,null);
                        viewModel.removeLoan(loan);
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
                mensaje.setTitle("Cancelar Reserva!!");
                mensaje.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loanBinding.getViewModel().loadLoansUser();
    }
}