package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        viewModel.geAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

    }
}