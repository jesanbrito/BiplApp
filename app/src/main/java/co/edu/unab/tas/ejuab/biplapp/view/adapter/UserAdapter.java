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

    private ListUserBinding userBinding;
    private ArrayList<User> users;


    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    public void setUsers(ArrayList<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        userBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_user, parent, false);

        return new UserViewHolder(userBinding.getRoot());
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


        public UserViewHolder(@NonNull View itemView) {

            super(itemView);
        }

        private void onBind(User myUser) {
            userBinding.setMyUser(myUser);
        }
    }
}
