package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.databinding.FragmentEditTaskBinding;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.model.User;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;


public class EditTaskFragment extends Fragment {

    private static final String ADMIN = "admin";
    private static final String SUPERUSER = "superuser";
    private static final String USER = "user";
    private FragmentEditTaskBinding binding;
    private TaskViewModel viewModel;
    private EditTaskFragmentArgs args;
    private Task task;
    private User loggedUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = EditTaskFragmentArgs.fromBundle(getArguments());
        task = args.getTask();
        loggedUser = args.getUser();

        displayData(task);
        disableUpdate();
        updateTask(setUpdatedTaskValues());


    }

    private void disableUpdate(){
        if(!loggedUser.equals(ADMIN)){
            binding.nameTextField.setFocusable(false);
            binding.descriptionTextField.setFocusable(false);
            binding.typeTextField.setFocusable(false);
            binding.complexityTextField.setFocusable(false);
            binding.timeSpentTextField.setFocusable(false);
            binding.startDateTextField.setFocusable(false);
            binding.endDateTextField.setFocusable(false);
            binding.statusTextField.setFocusable(false);
        }
    }



    private void displayData(Task task){
        binding.nameTextField.setText(task.getName());
        binding.descriptionTextField.setText(task.getDescription());
        binding.typeTextField.setText(task.getType());
        binding.complexityTextField.setText(String.valueOf(task.getComplexity()));
        binding.timeSpentTextField.setText(String.valueOf(task.getTimeSpent()));
        binding.startDateTextField.setText(task.getStartDateTime());
        binding.endDateTextField.setText(task.getEndDateTime());
        binding.statusTextField.setText(task.getStatus());
    }

    private void updateTask(Task task){
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.update(task);
                Toast.makeText(requireContext(), "Task updated!", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireView()).navigate(R.id.action_editTaskFragment_to_homeFragment);
            }
        });

    }





    public Task setUpdatedTaskValues(){

        Task updatedTask = new Task();

        updatedTask.setId(task.getId());

        binding.nameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedTask.setName(binding.nameTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.descriptionTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedTask.setDescription(binding.descriptionTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.statusTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){
                updatedTask.setStatus(binding.statusTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.complexityTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedTask.setComplexity(Integer.parseInt(binding.complexityTextField.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.typeTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedTask.setType(binding.typeTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.timeSpentTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedTask.setTimeSpent(Integer.parseInt(binding.timeSpentTextField.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return updatedTask;
    }






}