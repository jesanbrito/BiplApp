package co.edu.unab.tas.ejuab.biplapp.view.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ItemBookBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private ItemBookBinding binding;
    private ArrayList<Book> books ;

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_book,parent,false);
        return  new BookViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.onBind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        private void onBind(Book book) {
            binding.setBook(book);
        }
    }
}
