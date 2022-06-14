package com.mesinger.atoszadatak15.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mesinger.atoszadatak15.data.repository.UserRepository;
import com.mesinger.atoszadatak15.model.User;

public class UserViewModel extends AndroidViewModel {
    private User user;
    private UserRepository repository;




    public UserViewModel(@NonNull Application application) {
        super(application);

        repository = new UserRepository(application);

    }

    public User getUserFromDatabase(String username, String password){

        return repository.getUser(username, password);
    }

    public User getUser(){ return this.user; }

    public void setUser(User user){
        this.user = user;
    }


}
