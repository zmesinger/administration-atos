package com.mesinger.atoszadatak15.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mesinger.atoszadatak15.model.Worker;

import java.util.List;

@Dao
public interface WorkerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Worker worker);

    @Update
    void update(Worker worker);

    @Delete
    void delete(Worker worker);

    @Query("SELECT * FROM workers")
    LiveData<List<Worker>> getAllWorkers();


}
