package co.edu.unab.tas.ejuab.biplapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import co.edu.unab.tas.ejuab.biplapp.model.entity.Loan;
import co.edu.unab.tas.ejuab.biplapp.model.repository.LoanRepository;


public class LoanViewModel extends AndroidViewModel {

    private LoanRepository loanRepository;

    public LoanViewModel(@NonNull Application application) {
        super(application);
        loanRepository = new LoanRepository(application);
    }

    public void addLoan(Loan loan) {
        loanRepository.addLoan(loan);
    }
}
