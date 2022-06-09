package com.mesinger.atoszadatak15.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mesinger.atoszadatak15.data.repository.WorkerRepository;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.model.Worker;

import java.util.List;

public class WorkerViewModel extends AndroidViewModel {
    private WorkerRepository repository;
    private LiveData<List<Worker>> workers;

    private Worker worker = new Worker();

    public WorkerViewModel(@NonNull Application application) {
        super(application);

        repository = new WorkerRepository(application);
        workers = repository.getAllWorkers();

    }
    public void insert(Worker worker){
        repository.insert(worker);
    }

    public void update(Worker worker){
        repository.update(worker);
    }

    public void delete(Worker worker){
        repository.delete(worker);
    }

    public void setName(String name) {
        this.worker.setName(name);
    }

    public void setSurname(String surname) {
        this.worker.setSurname(surname);
    }

    public void setWorkPosition(String position) {
        this.worker.setWorkPosition(position);
    }

    public void setOib(String oib){
        this.worker.setOib(oib);
    }

    public void saveWorker() {
        insert(worker);
    }
}
