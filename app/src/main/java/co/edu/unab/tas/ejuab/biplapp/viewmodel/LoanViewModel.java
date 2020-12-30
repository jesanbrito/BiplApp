package co.edu.unab.tas.ejuab.biplapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Loan;
import co.edu.unab.tas.ejuab.biplapp.model.repository.LoanRepository;


public class LoanViewModel extends AndroidViewModel {

    private LoanRepository loanRepository;

    public LoanViewModel(@NonNull Application application) {
        super(application);
        loanRepository = new LoanRepository(application);
    }

    public LiveData<List<Loan>> getLoans() {
        return loanRepository.getLoan();
    }

    public void loadLoansUser() {
        loanRepository.loadLoansUser();
    }

    public void addLoan(Loan loan) {
        loanRepository.addLoan(loan);
    }

    public void removeLoan(Loan loan) {
        loanRepository.removeLoan(loan);
    }
}
