package com.mesinger.atoszadatak15.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mesinger.atoszadatak15.data.repository.TaskRepository;
import com.mesinger.atoszadatak15.model.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> tasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        repository = new TaskRepository(application);
        tasks = repository.getAllTasks();

    }

    public void insert(Task task){
        repository.insert(task);
    }

    public void update(Task task){
        repository.update(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

    public LiveData<List<Task>> geAllTasks(){
        return tasks;
    }
}
