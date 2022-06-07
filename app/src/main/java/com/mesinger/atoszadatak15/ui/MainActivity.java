package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.adapter.TaskAdapter;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}