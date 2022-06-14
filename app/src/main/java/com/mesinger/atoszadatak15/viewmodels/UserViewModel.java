package com.mesinger.atoszadatak15.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mesinger.atoszadatak15.data.repository.UserRepository;
import com.mesinger.atoszadatak15.model.User;

public class UserViewModel extends AndroidViewModel {
    private User user;
    private UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);

        repository = new UserRepository(application);

    }

    public void getUserFromDatabase(String username, String password){
        this.user = repository.getUser(username, password);
    }

    public User getUser(){
        return this.user;
    }





}
