package com.mesinger.atoszadatak15.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mesinger.atoszadatak15.data.UserDAO;
import com.mesinger.atoszadatak15.data.database.ExerciseDatabase;
import com.mesinger.atoszadatak15.model.User;

public class UserRepository {
    private UserDAO userDAO;
    private User user;

    public UserRepository(Application application){
        ExerciseDatabase db = ExerciseDatabase.getInstance(application);
        userDAO = db.userDAO();
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    public User getUser(String username, String password){
        return userDAO.getUser(username, password);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        public InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }


        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            return null;
        }
    }
}
