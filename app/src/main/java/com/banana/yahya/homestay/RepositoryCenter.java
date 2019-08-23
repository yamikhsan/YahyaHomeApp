package com.banana.yahya.homestay;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class RepositoryCenter {

    private UserDao dao;
    private LiveData<List<User>> readAll;

    public RepositoryCenter(Application application){

        DatabaseCenter bd = DatabaseCenter.getAppDatabase(application);
        dao = bd.barangDao();
        readAll = dao.readAll();

    }

    public LiveData<List<User>> readAll(){
        return  readAll;
    }

    public void insert(User u){
        new insertAsync(dao).execute(u);
    }

    private static class insertAsync extends AsyncTask<User, Void, Void> {

        private UserDao daoAsync;

        insertAsync(UserDao o){
            this.daoAsync = o;
        }

        @Override
        protected Void doInBackground(User... models) {
            daoAsync.insert(models[0]);
            return null;
        }
    }

}
