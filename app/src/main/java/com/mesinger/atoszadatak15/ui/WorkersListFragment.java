package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.adapter.TaskAdapter;
import com.mesinger.atoszadatak15.adapter.WorkerAdapter;
import com.mesinger.atoszadatak15.databinding.FragmentWorkersListBinding;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;
import com.mesinger.atoszadatak15.viewmodels.WorkerViewModel;


public class WorkersListFragment extends Fragment {

    private FragmentWorkersListBinding binding;
    private WorkerViewModel viewModel;
    private RecyclerView recyclerView;

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

        WorkerAdapter adapter = setupRecyclerView();
        loadData(adapter);

    }


    private WorkerAdapter setupRecyclerView(){
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final WorkerAdapter adapter = new WorkerAdapter();
        recyclerView.setAdapter(adapter);

        return adapter;
    }

    private void loadData(WorkerAdapter adapter){
        viewModel = new ViewModelProvider(this).get(WorkerViewModel.class);
        viewModel.getAllWorkers().observe(getViewLifecycleOwner(), workers -> {
            adapter.setWorkers(workers);
        });

        }
    }
