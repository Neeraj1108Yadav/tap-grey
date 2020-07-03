package com.example.tapcolor;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tapcolor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setTapGame(mainViewModel);
        mainViewModel.startGame();
        mainViewModel.getColor().observe(this, new Observer<TapGameModel>() {
            @Override
            public void onChanged(TapGameModel tapGameModel) {
               changeColor(tapGameModel.isOrange(),tapGameModel.isBlue(),tapGameModel.isYellow(),tapGameModel.isGreen());
            }
        });

        mainViewModel.showScore().observe(this, new Observer<TapGameModel>() {
            @Override
            public void onChanged(TapGameModel tapGameModel) {
                showDialog(tapGameModel);
            }
        });
    }

    private void changeColor(boolean isOrange,boolean isBlue,boolean isYellow, boolean isGreen){
        if(isOrange){
            binding.imageViewOrange.setBackground(getDrawable(R.drawable.square_grey));
        }else{
            binding.imageViewOrange.setBackground(getDrawable(R.drawable.square_orange));
        }

        if(isBlue){
            binding.imageViewBlue.setBackground(getDrawable(R.drawable.square_grey));
        }else{
            binding.imageViewBlue.setBackground(getDrawable(R.drawable.square_blue));
        }

        if(isYellow){
            binding.imageViewYellow.setBackground(getDrawable(R.drawable.square_grey));
        }else{
            binding.imageViewYellow.setBackground(getDrawable(R.drawable.square_yellow));
        }

        if(isGreen){
            binding.imageViewGreen.setBackground(getDrawable(R.drawable.square_grey));
        }else{
            binding.imageViewGreen.setBackground(getDrawable(R.drawable.square_green));
        }
    }

    private void showDialog(TapGameModel tapGameModel){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(true);
        alertDialog.setTitle(getResources().getString(R.string.game_over));
        alertDialog.setMessage(getResources().getString(R.string.score)+" "+String.valueOf(tapGameModel.getTapCount()));

        alertDialog.setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mainViewModel.startGame();
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}
