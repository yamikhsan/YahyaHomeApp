package com.banana.yahya.homestay;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ViewModelCenter extends AndroidViewModel {

    private LiveData<List<User>> readAll;
    private RepositoryCenter center;

    public ViewModelCenter(@NonNull Application application) {
        super(application);

        center = new RepositoryCenter(application);
        readAll = center.readAll();

    }

    public LiveData<List<User>> readAll(){
        return readAll;
    }

    public void insert(User u){
        center.insert(u);
    }


}

