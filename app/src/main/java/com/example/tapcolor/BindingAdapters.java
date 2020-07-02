package com.example.tapcolor;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    @BindingAdapter({"colorBackground"})
    public static void setSquareBackGround(ImageView imageView, Drawable drawable){
        Log.i("TAP-COLOR","CALLED");
        imageView.setBackground(drawable);
    }
}
