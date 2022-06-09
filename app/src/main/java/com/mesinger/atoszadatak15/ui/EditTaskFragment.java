package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavArgs;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.databinding.FragmentEditTaskBinding;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;


public class EditTaskFragment extends Fragment {

    private FragmentEditTaskBinding binding;
    private TaskViewModel viewModel;
    private EditTaskFragmentArgs args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = EditTaskFragmentArgs.fromBundle(getArguments());
it 
        Task task = args.getTask();

        binding.nameTextField.setText(task.getName());
        binding.descriptionTextField.setText(task.getDescription());
        binding.typeTextField.setText(task.getType());
        binding.complexityTextField.setText(String.valueOf(task.getComplexity()));
        binding.timeSpentTextField.setText(String.valueOf(task.getTimeSpent()));
        binding.startDateTextField.setText(task.getStartDateTime());
        binding.endDateTextField.setText(task.getEndDateTime());
        binding.statusTextField.setText(task.getStatus());

    }






}