package co.edu.unab.tas.ejuab.biplapp.view.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindAdapter {

    @BindingAdapter("image")
    public static void loadImage(ImageView imageView, String url){
        if(url == null || url.equals("")) {
            Glide.with(imageView.getContext()).load("https://www.edicionesencuentro.com/wp-content/uploads/2019/01/NODISPONIBLE.gif").into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }

    }

    @BindingAdapter("imageuser")
    public static void loadImageUser(ImageView imageView, String url){
        if(url == null || url.equals("")) {
            Glide.with(imageView.getContext()).load("https://www.informador.mx/export/sites/elinformador/arte/usuario_null.jpg_1931756597.jpg").into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }

    }
}
