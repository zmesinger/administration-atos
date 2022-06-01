package com.mesinger.atoszadatak15.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mesinger.atoszadatak15.data.TaskDAO;
import com.mesinger.atoszadatak15.data.TypeConverters;
import com.mesinger.atoszadatak15.data.WorkerDAO;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.model.Worker;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Database(entities = {Task.class, Worker.class}, version = 1)
public abstract class ExerciseDatabase extends RoomDatabase {

    private static ExerciseDatabase instance;
    public abstract TaskDAO taskDAO();
    public abstract WorkerDAO workerDAO();


    public static synchronized ExerciseDatabase getInstance(Context context){
        if(instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ExerciseDatabase.class,
                    "exercise_database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateTasksAsyncTask extends AsyncTask<Void, Void, Void>{
        private TaskDAO taskDAO;
        private LocalDateTime startTime = LocalDateTime.now();
        private LocalDateTime endTime = LocalDateTime.now();

        private PopulateTasksAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDAO.insert(new Task("Cleaning", "Cleaning the building", "task", "open", 5,
                    15,TypeConverters.toDateString(startTime), TypeConverters.toDateString(endTime) ));
            taskDAO.insert(new Task("Cleaning", "Cleaning the building", "task", "open", 7,
                    17, TypeConverters.toDateString(startTime), TypeConverters.toDateString(endTime) ));
            taskDAO.insert(new Task("Cleaning", "Cleaning the building", "task", "open", 8,
                    19, TypeConverters.toDateString(startTime), TypeConverters.toDateString(endTime)));
            return null;
        }
    }

    private static class PopulateWorkersAsyncTask extends AsyncTask<Void, Void, Void>{
        private WorkerDAO workerDAO;

        private PopulateWorkersAsyncTask(WorkerDAO workerDAO) {
            this.workerDAO = workerDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            workerDAO.insert(new Worker(0, "John", "Doe", "cleaner", "1234567891234"));
            return null;
        }
    }

}
