package com.mesinger.atoszadatak15.data.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mesinger.atoszadatak15.data.TaskDAO;
import com.mesinger.atoszadatak15.data.TypeConverters;
import com.mesinger.atoszadatak15.data.UserDAO;
import com.mesinger.atoszadatak15.data.WorkerDAO;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.model.User;
import com.mesinger.atoszadatak15.model.Worker;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;




@Database(entities = {Task.class, Worker.class, User.class}, version = 1)
public abstract class ExerciseDatabase extends RoomDatabase {
    private static final String TAG = "ExerciseDatabase";

    private static ExerciseDatabase instance;
    public abstract TaskDAO taskDAO();
    public abstract WorkerDAO workerDAO();
    public abstract UserDAO userDAO();


    public static synchronized ExerciseDatabase getInstance(Context context){
        if(instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ExerciseDatabase.class,
                    "exercise_database").allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .addCallback(workerCallback)
                    .addCallback(usersCallback)
                    .build();

            Log.d(TAG, "getInstance");

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateTasksAsyncTask(instance).execute();
            Log.d(TAG,"roomCallback");
        }

    };

    private static RoomDatabase.Callback workerCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateWorkersAsyncTask(instance).execute();
            Log.d(TAG,"roomCallback");
        }

    };

    private static RoomDatabase.Callback usersCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateUsersAsyncTask(instance).execute();
            Log.d(TAG, "usersCallback");
        }
    };

    private static class PopulateTasksAsyncTask extends AsyncTask<Void, Void, Void>{
        private TaskDAO taskDAO;
        private LocalDateTime startTime = LocalDateTime.now();
        private LocalDateTime endTime = LocalDateTime.now();

        private PopulateTasksAsyncTask(ExerciseDatabase db) {
            taskDAO = db.taskDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDAO.insert(new Task("Cleaning", "Cleaning the building", "task", "open", 5,
                    15,TypeConverters.toDateString(startTime), TypeConverters.toDateString(endTime) ));
            taskDAO.insert(new Task("Cleaning", "Cleaning the building", "task", "open", 7,
                    17, TypeConverters.toDateString(startTime), TypeConverters.toDateString(endTime) ));
            taskDAO.insert(new Task("Cleaning", "Cleaning the building", "task", "open", 8,
                    19, TypeConverters.toDateString(startTime), TypeConverters.toDateString(endTime)));

            Log.d(TAG, "populateTasksDoInBackground");
            return null;
        }
    }

    private static class PopulateWorkersAsyncTask extends AsyncTask<Void, Void, Void>{
        private WorkerDAO workerDAO;

        private PopulateWorkersAsyncTask(ExerciseDatabase db) {
            workerDAO = db.workerDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            workerDAO.insert(new Worker(99, "John", "Doe", "cleaner" ,"1234567891234"));
            return null;
        }
    }

    private static class PopulateUsersAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDAO userDAO;

        private PopulateUsersAsyncTask(ExerciseDatabase db) { userDAO = db.userDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDAO.insert(new User(1, "Superuser", "superuser", "superuser"));
            userDAO.insert(new User(2, "Admin", "admin", "admin"));
            userDAO.insert(new User(3, "User", "user", "user"));
            return null;
        }
    }


}
