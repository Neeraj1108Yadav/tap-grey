package com.example.tapcolor;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainViewModel extends ObservableViewModel {

    public String tapCount;
    private int count;
    private int newPosition=0,oldPosition=0;

    private Random random = new Random();
    private MutableLiveData<TapGameModel> colorBox = new MutableLiveData<>();
    private MutableLiveData<TapGameModel> score = new MutableLiveData<>();
    private TapGameModel tapGameModel = new TapGameModel();
    private Timer timer;

    @Bindable
    public String getTapCount() {
        return tapCount;
    }

    public void setTapCount(String tapCount) {
        this.tapCount = tapCount;
        notifyPropertyChange(BR.tapCount);
    }

    public void startGame() {
        count = 0;
        recordTapCount();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(tapGameModel.isTapped()){
                    switch (generateRandomNumber()){
                        case 1: setColorValues(true,false,false,false);
                            oldPosition = newPosition;
                            break;
                        case 2: setColorValues(false,true,false,false);
                            oldPosition = newPosition;
                            break;
                        case 3: setColorValues(false,false,true,false);
                            oldPosition = newPosition;
                            break;
                        case 4: setColorValues(false,false,false,true);
                            oldPosition = newPosition;
                            break;
                    }
                }else{
                    onGameOver();
                }
            }
        }, 0, 1000);
    }

    public LiveData<TapGameModel> getColor(){
        return colorBox;
    }

    public LiveData<TapGameModel> showScore() {
        return score;
    }

    public void onOrangeClick() {
        if(tapGameModel.isOrange()){
            count++;
            recordTapCount();
        }else{
            onGameOver();
        }
    }

    public void onBlueClick() {
        if(tapGameModel.isBlue()){
            count++;
            recordTapCount();
        }else{
            onGameOver();
        }
    }

    public void onYellowClick() {
        if(tapGameModel.isYellow()){
            count++;
            recordTapCount();
        }else{
            onGameOver();
        }
    }

    public void onGreenClick() {
        if(tapGameModel.isGreen()){
            count++;
            recordTapCount();
        }else{
            onGameOver();
        }
    }

    private void recordTapCount(){
        setColorValues(false,false,false,false);
        tapGameModel.setTapped(true);
        setTapCount("Score : "+String.valueOf(count));
        tapGameModel.setTapCount(count);
    }
    private int generateRandomNumber() {
        newPosition =  random.nextInt(4) + 1;
        if(newPosition == oldPosition){
            switch (newPosition)
            {
                case 1:
                    newPosition = newPosition + 1;
                    break;
                case 2:
                    newPosition = newPosition - 1;
                    break;
                case 3:
                    newPosition = newPosition + 1 ;
                    break;
                case 4:
                    newPosition = newPosition -1 ;
                    break;
            }
        }
        return  newPosition;
    }

    private void setColorValues(boolean isOrange,boolean isBlue,boolean isYellow,boolean isGreen){
       tapGameModel.setTapped(false);
       tapGameModel.setOrange(isOrange);
       tapGameModel.setBlue(isBlue);
       tapGameModel.setYellow(isYellow);
       tapGameModel.setGreen(isGreen);
       colorBox.postValue(tapGameModel);
    }

    private void onGameOver(){
        score.postValue(tapGameModel);
        timer.cancel();
        setColorValues(false,false,false,false);
    }
}
