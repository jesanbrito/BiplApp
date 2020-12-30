package co.edu.unab.tas.ejuab.biplapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ItemLoanBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Loan;


public class LoanAdapter extends   RecyclerView.Adapter<LoanAdapter.LoanViewHolder>{

    private ArrayList<Loan> loans ;
    private OnItemClickListener onItemClickListener;

    public LoanAdapter(ArrayList<Loan> loans) {
        this.loans = loans;
        onItemClickListener = null;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public LoanAdapter.LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLoanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_loan,parent,false);
        return new LoanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanViewHolder holder, int position) {
        Loan loan = loans.get(position);
        holder.onBind(loan);

    }

    @Override
    public int getItemCount() {
        return loans.size();
    }

    public class LoanViewHolder extends RecyclerView.ViewHolder {
        private ItemLoanBinding binding;

        public LoanViewHolder(@NonNull ItemLoanBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        private void onBind(Loan loan) {
            binding.setLoan(loan);
            if (onItemClickListener != null) {
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(loan, getAdapterPosition());
                    }
                });
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Loan loan, int position);
    }
}
