package com.mesinger.atoszadatak15.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mesinger.atoszadatak15.data.TaskDAO;
import com.mesinger.atoszadatak15.data.WorkerDAO;
import com.mesinger.atoszadatak15.data.database.ExerciseDatabase;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.model.Worker;

import java.util.List;

public class WorkerRepository {
    private WorkerDAO workerDAO;
    private LiveData<List<Worker>> workers;

    public WorkerRepository(Application application) {
        ExerciseDatabase db = ExerciseDatabase.getInstance(application);
        workerDAO = db.workerDAO();
        workers = workerDAO.getAllWorkers();
    }

    public void insert(Worker worker){
        new InsertWorkerAsyncTask(workerDAO).execute(worker);
    }

    public void update(Worker worker){
        new UpdateWorkerAsyncTask(workerDAO).execute(worker);
    }

    public void delete(Worker worker){
        new DeleteWorkerAsyncTask(workerDAO).execute(worker);
    }

    public LiveData<List<Worker>> getAllWorkers(){
        return workers;
    }


    private static class InsertWorkerAsyncTask extends AsyncTask<Worker, Void, Void>{
        private WorkerDAO workerDAO;

        public InsertWorkerAsyncTask(WorkerDAO workerDAO) {
            this.workerDAO = workerDAO;
        }


        @Override
        protected Void doInBackground(Worker... workers) {
            workerDAO.insert(workers[0]);
            return null;
        }
    }
    private static class UpdateWorkerAsyncTask extends AsyncTask<Worker, Void, Void>{
        private WorkerDAO workerDAO;

        public UpdateWorkerAsyncTask(WorkerDAO workerDAO) {
            this.workerDAO = workerDAO;
        }


        @Override
        protected Void doInBackground(Worker... workers) {
            workerDAO.update(workers[0]);
            return null;
        }
    }
    private static class DeleteWorkerAsyncTask extends AsyncTask<Worker, Void, Void>{
        private WorkerDAO workerDAO;

        public DeleteWorkerAsyncTask(WorkerDAO workerDAO) {
            this.workerDAO = workerDAO;
        }


        @Override
        protected Void doInBackground(Worker... workers) {
            workerDAO.delete(workers[0]);
            return null;
        }
    }

}
