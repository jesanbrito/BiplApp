package co.edu.unab.tas.ejuab.biplapp.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityUserListBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.UserAdapter;

public class UserListActivity  extends AppCompatActivity {

    private UserAdapter myAdapter;
    private ArrayList<User> userList;
    private ActivityUserListBinding userListBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userListBinding = DataBindingUtil.setContentView(UserListActivity.this, R.layout.activity_user_list);

        userList = new ArrayList<>();
        setFakeDataUser();
        myAdapter = new UserAdapter(userList);

        userListBinding.rvUsers.setHasFixedSize(true);
        userListBinding.rvUsers.setAdapter(myAdapter);

    }

    private void setFakeDataUser() {

        User myUser1 = new User("1","Cedula","123456","Jesus","Brito","jesusbrito@gmail.com", "3111111111", "Habilitado",2);
        userList.add(myUser1);

        User myUser2 = new User("2","Cedula","24680","Juan","Toncel","juantoncel@hotmail.com", "3222222222", "Habilitado",2);
        userList.add(myUser2);

        User myUser3 = new User("3","Cedula","13579","Camilo","Chaparro","camilocha@yahoo.es", "3133333333", "Habilitado",2);
        userList.add(myUser3);

        User myUser4 = new User("4","Cedula","142536","Faustino","Asprilla","jfausto@correo.com", "3144444444", "Sancionado",2);
        userList.add(myUser4);


    }

}
