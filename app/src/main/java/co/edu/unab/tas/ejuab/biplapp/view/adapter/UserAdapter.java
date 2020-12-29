package co.edu.unab.tas.ejuab.biplapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ListUserBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    //private ListUserBinding userBinding;
    private ArrayList<User> users;
    private OnItemClickListener onItemClickListener;


    public UserAdapter(ArrayList<User> users) {
        this.users = users;
        onItemClickListener = null;
    }

    public void setUsers(ArrayList<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //userBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_user, parent, false);
        ListUserBinding userBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_user, parent, false);

        return new UserViewHolder(userBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User myUser = users.get(position);
        holder.onBind(myUser);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ListUserBinding userBinding;

        public UserViewHolder(@NonNull ListUserBinding itemView) {
            super(itemView.getRoot());
            userBinding = itemView;
        }

        private void onBind(User myUser) {
            userBinding.setMyUser(myUser);
            if(onItemClickListener != null){
                userBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(myUser, getAdapterPosition());
                    }
                });
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(User myUser, int position);
    }
}
