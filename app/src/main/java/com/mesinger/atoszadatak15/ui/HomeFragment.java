package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.adapter.TaskAdapter;
import com.mesinger.atoszadatak15.databinding.FragmentHomeBinding;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

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

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final TaskAdapter adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        viewModel.geAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });


        navigateToAdDNewTask();
    }

    private void navigateToAdDNewTask(){
        FloatingActionButton button = binding.buttonAdd;
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addNewTaskFragment);
            }
        });
    }





}