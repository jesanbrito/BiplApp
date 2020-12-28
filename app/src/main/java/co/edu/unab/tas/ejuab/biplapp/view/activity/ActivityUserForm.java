package co.edu.unab.tas.ejuab.biplapp.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.unab.tas.ejuab.biplapp.R;
import co.edu.unab.tas.ejuab.biplapp.databinding.ActivityUserFormBinding;
import co.edu.unab.tas.ejuab.biplapp.model.entity.User;
import co.edu.unab.tas.ejuab.biplapp.viewmodel.UserFormViewModel;

public class ActivityUserForm extends AppCompatActivity {

    private static final int CODE_REQUEST_CAMERA = 100;
    private static final int CODE_REQUEST_GALLERY = 111;
    private Uri imageUri;
    ActivityUserFormBinding userFormBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        UserFormViewModel viewModel = new ViewModelProvider(ActivityUserForm.this).get(UserFormViewModel.class);

        User myUser = (User) getIntent().getSerializableExtra("user");
        userFormBinding = DataBindingUtil.setContentView(ActivityUserForm.this, R.layout.activity_user_form);

        if(myUser != null){
            userFormBinding.setUser(myUser);
            userFormBinding.btEditUserForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User myEditUser = userFormBinding.getUser();
                    viewModel.editUser(myEditUser, imageUri);
                    Intent data = new Intent();
                    data.putExtra("user", myEditUser);

                    setResult(RESULT_OK, data);
                    viewModel.getReady().observe(ActivityUserForm.this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                finish();
                            }
                        }
                    });
                }
            });
        }else{

            userFormBinding.setUser(new User());
            userFormBinding.btEditUserForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User myUser = userFormBinding.getUser();
                    viewModel.editUser(myUser, imageUri);
                    viewModel.getReady().observe(ActivityUserForm.this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                finish();
                            }
                        }
                    });
                }
            });

        }

        userFormBinding.ibCameraForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(myIntent.resolveActivity(getPackageManager()) != null){

                    File image = null;
                    try {
                        image = createPhoto();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    if(image != null){
                        imageUri = FileProvider.getUriForFile(ActivityUserForm.this, "co.edu.unab.tas.ejuab.biplapp.fileprovider", image);
                        myIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(myIntent, CODE_REQUEST_CAMERA);
                    }
                }
            }
        });

        userFormBinding.ibGalleryForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(myIntent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(myIntent, CODE_REQUEST_GALLERY);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CODE_REQUEST_CAMERA:
                Glide.with(ActivityUserForm.this).load(imageUri).into(userFormBinding.ivUserForm);
                break;
            case CODE_REQUEST_GALLERY:
                imageUri = data.getData();
                Glide.with(ActivityUserForm.this).load(imageUri).into(userFormBinding.ivUserForm);
                break;
        }
    }

    private File createPhoto() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        return image;
    }
}
