package com.mesinger.atoszadatak15.ui;

import static com.mesinger.atoszadatak15.R.id.action_workersListFragment_to_addNewWorkerFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mesinger.atoszadatak15.adapter.WorkerAdapter;
import com.mesinger.atoszadatak15.databinding.FragmentWorkersListBinding;
import com.mesinger.atoszadatak15.model.User;
import com.mesinger.atoszadatak15.viewmodels.UserViewModel;
import com.mesinger.atoszadatak15.viewmodels.WorkerViewModel;


public class WorkersListFragment extends Fragment {

    private static final String ADMIN = "admin";
    private static final String SUPERUSER = "superuser";
    private static final String USER = "user";
    private WorkersListFragmentArgs args;
    private FragmentWorkersListBinding binding;
    private WorkerViewModel workerViewModel;
    private RecyclerView recyclerView;
    private User loggedUser;

    public WorkersListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkersListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = WorkersListFragmentArgs.fromBundle(getArguments());
        loggedUser = args.getUser();

        WorkerAdapter adapter = setupRecyclerView();
        loadData(adapter);
        validateAddNew();
        navigateToAddNewWorker();
        deleteWorker(adapter);

    }

    private void validateAddNew(){
        if(loggedUser.getRole().equals(USER)){
            binding.buttonAdd.setVisibility(View.INVISIBLE) ;
        }
    }


    private WorkerAdapter setupRecyclerView() {
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final WorkerAdapter adapter = new WorkerAdapter();
        recyclerView.setAdapter(adapter);

        return adapter;
    }

    private void loadData(WorkerAdapter adapter) {
        workerViewModel = new ViewModelProvider(this).get(WorkerViewModel.class);
        workerViewModel.getAllWorkers().observe(getViewLifecycleOwner(), workers -> {
            adapter.setWorkers(workers);
        });

    }

    private void navigateToAddNewWorker() {
        FloatingActionButton button = binding.buttonAdd;
        button.setOnClickListener(view -> Navigation.findNavController(view).navigate(action_workersListFragment_to_addNewWorkerFragment));
    }


    private void deleteWorker(WorkerAdapter adapter) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                workerViewModel.delete(adapter.getWorkerAt(viewHolder.getAdapterPosition()));
                Toast.makeText(requireContext(), "Worker deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnClickListener(worker -> {
            NavDirections action = WorkersListFragmentDirections.actionWorkersListFragmentToEditWorkerFragment(worker);
            Navigation.findNavController(requireView()).navigate(action);
            Log.d("WorkersListFragment", worker.getName());
        });


    }
}

