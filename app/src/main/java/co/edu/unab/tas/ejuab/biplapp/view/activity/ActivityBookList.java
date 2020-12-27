package co.edu.unab.tas.ejuab.biplapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityBookListBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.Book;
import co.edu.unab.tas.ejuab.biplapp.view.adapter.BookAdapter;

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
               /* Intent in =  new Intent(ActivityBookList.this,  );
                startActivity(in);*/
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                break;
            case R.id.mi_add_book:
                /*Intent in =  new Intent(ProductListActivity.this,CartActivity.class);
                startActivity(in);*/
                break;
            case R.id.mi_close_session:
               /* FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Intent i =  new Intent(ProductListActivity.this,MainActivity.class);
                startActivity(i);
                finish();*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(ActivityBookList.this, R.layout.activity_book_list);
        bookListBinding = DataBindingUtil.setContentView(ActivityBookList.this,R.layout.activity_book_list);
        bookList = new ArrayList<>();
        loadBook();
        adapter = new BookAdapter(bookList);
        bookListBinding.rvBooks.setAdapter(adapter);
    }

    private void loadBook() {
        Book b1 =  new Book("Cazadores de Sombras 1. Ciudad de Hueso","Cassandra Clare","UNAM","https://www.planetadelibros.com.pe/usuaris/libros/fotos/183/m_libros/182450_9788408083801.jpg",1);
        bookList.add(b1);
        Book b2 =  new Book("Cazadores de Sombras 2. Ciudad de Ceniza","Cassandra Clare","Destino","https://images.cdn2.buscalibre.com/fit-in/360x360/44/f9/44f9a269f6a66bbb3bdb5c1f48dcfbd1.jpg",1);
        bookList.add(b2);
        Book b3 =  new Book("Cazadores de Sombras 3. Ciudad de Cristal","Cassandra Clare","UNAM","https://images-na.ssl-images-amazon.com/images/I/51xRoMXPiIL._SX356_BO1,204,203,200_.jpg",1);
        bookList.add(b3);
        Book b4 =  new Book("Cazadores de Sombras 4. Ciudad de los Angeles Caídos","Cassandra Clare","UNAM","https://images-na.ssl-images-amazon.com/images/I/51xRhUdofcL._SX355_BO1,204,203,200_.jpg",1);
        bookList.add(b4);
        Book b5 =  new Book("Cazadores de Sombras 5. Ciudad de las Almas Perdidas","Cassandra Clare","UNAM","https://libropolis.com.co/image/cache/catalog/portadas/Cazadores%20de%20sombras%20-%205%20Ciudad%20de%20las%20almas%20perdidas%20booket-550x550h.jpg",1);
        bookList.add(b5);
    }
}