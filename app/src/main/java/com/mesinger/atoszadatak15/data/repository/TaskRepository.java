package com.mesinger.atoszadatak15.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mesinger.atoszadatak15.data.TaskDAO;
import com.mesinger.atoszadatak15.data.database.ExerciseDatabase;
import com.mesinger.atoszadatak15.model.Task;

import java.util.List;

public class TaskRepository {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> tasks;

    public TaskRepository(Application application){
        ExerciseDatabase db = ExerciseDatabase.getInstance(application);
        taskDAO = db.taskDAO();
        tasks = taskDAO.getAllTasks();
    }

    public void insert(Task task){
        new InsertTaskAsyncTask(taskDAO).execute(task);
    }

    public void update(Task task){
        new UpdateTaskAsyncTask(taskDAO).execute(task);
    }

    public void delete(Task task){
        new DeleteTaskAsyncTask(taskDAO).execute(task);
    }

    public LiveData<Task> getTaskById(int id){
        return taskDAO.getTaskById(id);
    }

    public LiveData<List<Task>> getAllTasks(){
        return tasks;
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDAO taskDAO;

        public InsertTaskAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }


        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.insert(tasks[0]);
            return null;
        }
    }
    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDAO taskDAO ;

        public UpdateTaskAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }


        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.update(tasks[0]);
            return null;
        }
    }
    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDAO taskDAO;

        public DeleteTaskAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }


        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.delete(tasks[0]);
            return null;
        }
    }



 }
