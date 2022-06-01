package com.mesinger.atoszadatak15.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mesinger.atoszadatak15.data.TaskDAO;
import com.mesinger.atoszadatak15.data.WorkerDAO;
import com.mesinger.atoszadatak15.data.database.ExerciseDatabase;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.model.Worker;

public class WorkerRepository {
    private WorkerDAO workerDAO;
    private LiveData<Worker> worker;

    public WorkerRepository(Application application) {
        ExerciseDatabase db = ExerciseDatabase.getInstance(application);
        workerDAO = db.workerDAO();
    }

    public void insert(Task task){
    }

    public void update(Task task){
    }

    public void delete(Task task){
    }

}
