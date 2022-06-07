package com.mesinger.atoszadatak15.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mesinger.atoszadatak15.data.TypeConverters;
import com.mesinger.atoszadatak15.data.repository.TaskRepository;
import com.mesinger.atoszadatak15.model.Task;

import java.time.LocalDateTime;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> tasks;

    Task task = new Task();

    public TaskViewModel(@NonNull Application application) {
        super(application);

        repository = new TaskRepository(application);
        tasks = repository.getAllTasks();

    }

    public void setName(String name) {
        task.setName(name);
    }

    public void setDescription(String description) {
        task.setDescription(description);
    }

    public void setType(String type) {
        task.setType(type);
    }

    public void setComplexity(int complexity) {
        task.setComplexity(complexity);
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

    public void saveTask() {

        insert(task);
    }
}
