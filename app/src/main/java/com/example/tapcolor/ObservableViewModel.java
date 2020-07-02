package com.example.tapcolor;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

public class ObservableViewModel extends ViewModel implements Observable {

    private PropertyChangeRegistry mCallBack = new PropertyChangeRegistry();

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        mCallBack.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        mCallBack.add(callback);
    }

    public void notifyPropertyChange(int fieldId){
        mCallBack.notifyCallbacks(this,fieldId,null);
    }
}
