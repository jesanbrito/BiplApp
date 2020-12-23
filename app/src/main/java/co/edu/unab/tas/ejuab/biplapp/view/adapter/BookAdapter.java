package co.edu.unab.tas.ejuab.biplapp.view.adapter;

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

    private ArrayList<Book> books ;
    private OnItemClickListener onItemClickListener;

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
        onItemClickListener = null;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_book,parent,false);
        return  new BookViewHolder(binding);
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
        private ItemBookBinding binding;

        public BookViewHolder(@NonNull ItemBookBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        private void onBind(Book book) {
            binding.setBook(book);
            if (onItemClickListener != null) {
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(book,getAdapterPosition());
                    }
                });
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Book book, int position);
    }
}
