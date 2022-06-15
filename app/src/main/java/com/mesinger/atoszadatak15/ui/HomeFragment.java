package com.mesinger.atoszadatak15.ui;

import static com.mesinger.atoszadatak15.R.id.action_homeFragment_to_addNewTaskFragment;
import static com.mesinger.atoszadatak15.R.id.lin_layout_item_task;
import static com.mesinger.atoszadatak15.R.id.nav_graph;
import static com.mesinger.atoszadatak15.R.id.usernameTextField;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.adapter.TaskAdapter;
import com.mesinger.atoszadatak15.data.TypeConverters;
import com.mesinger.atoszadatak15.databinding.FragmentHomeBinding;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.model.User;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;
import com.mesinger.atoszadatak15.viewmodels.UserViewModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;




public class HomeFragment extends Fragment{
    private static final String ADMIN = "admin";
    private static final String SUPERUSER = "superuser";
    private static final String USER = "user";
    public static final int ADD_TASK_REQUEST = 1;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private TaskViewModel viewModel;
    private HomeFragmentArgs args;
    private User loggedUser;


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

        args = HomeFragmentArgs.fromBundle(getArguments());
        loggedUser = args.getUser();

        TaskAdapter adapter = setupRecyclerView();
        loadData(adapter);
        validateAddNew();
        navigateToAddNewTask();
        navigateToWorkers();
        deleteTask(adapter);

    }

    private void navigateToWorkers(){
        binding.buttonWorkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToWorkersListFragment(loggedUser);
                Navigation.findNavController(requireView()).navigate(action);
            }
        });

    }


    private void validateAddNew(){

        if(loggedUser.getRole().equals(USER)){
            binding.buttonAdd.setVisibility(View.INVISIBLE);
        }
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
        viewModel.geAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            adapter.setTasks(tasks);
        });
    }

    private void navigateToAddNewTask(){
        FloatingActionButton button = binding.buttonAdd;
        button.setOnClickListener(view -> Navigation.findNavController(view).navigate(action_homeFragment_to_addNewTaskFragment));
    }


    private void deleteTask(TaskAdapter adapter){
        if(loggedUser.getRole().equals(ADMIN)) {
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


        adapter.setOnClickListener(task -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToEditTaskFragment(task, loggedUser);
            Navigation.findNavController(requireView()).navigate(action);
            Log.d("HomeFragment", task.toString());
        });
    }



}