package com.mesinger.atoszadatak15.ui;

import static com.mesinger.atoszadatak15.R.id.action_homeFragment_to_addNewTaskFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.adapter.TaskAdapter;
import com.mesinger.atoszadatak15.data.TypeConverters;
import com.mesinger.atoszadatak15.databinding.FragmentHomeBinding;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;

import java.time.LocalDateTime;
import java.util.List;

public class HomeFragment extends Fragment {
    public static final int ADD_TASK_REQUEST = 1;

    public HomeFragment(){

    }



    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private TaskViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        TaskAdapter adapter = setupRecyclerView();
        loadData(adapter);
        navigateToAddNewTask();
        deleteTask(adapter);

    }




    private TaskAdapter setupRecyclerView(){
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final TaskAdapter adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        return adapter;
    }

    private void loadData(TaskAdapter adapter){
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        viewModel.geAllTasks().observe(getViewLifecycleOwner(), tasks ->
                adapter.setTasks(tasks));
    }

    private void navigateToAddNewTask(){
        FloatingActionButton button = binding.buttonAdd;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(action_homeFragment_to_addNewTaskFragment);
            }
        });
    }

    private void deleteTask(TaskAdapter adapter){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                viewModel.delete(adapter.getTaskAt(viewHolder.getAdapterPosition()));
                Toast.makeText(requireContext(), "Task deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }









}