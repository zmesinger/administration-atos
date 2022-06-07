package com.mesinger.atoszadatak15.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.data.TypeConverters;
import com.mesinger.atoszadatak15.databinding.FragmentAddNewTaskBinding;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;

import java.time.LocalDateTime;

import kotlinx.coroutines.scheduling.Task;

public class AddNewTaskFragment extends Fragment {

    public static final String EXTRA_NAME = "com.mesinger.atoszadatak15.ui.EXTRA_NAME";
    public static final String EXTRA_DESCRIPTION = "com.mesinger.atoszadatak15.ui.EXTRA_DESCRIPTION";
    public static final String EXTRA_TYPE = "com.mesinger.atoszadatak15.ui.EXTRA_TYPE";
    public static final String EXTRA_COMPLEXITY = "com.mesinger.atoszadatak15.ui.EXTRA_COMPLEXITY";
    public static final String EXTRA_START_DATE_TIME = "com.mesinger.atoszadatak15.ui.EXTRA_START_DATE_TIME";
    public static final String EXTRA_END_DATE_TIME = "com.mesinger.atoszadatak15.ui.EXTRA_END_DATE_TIME";

    public AddNewTaskFragment(){

    }

    private FragmentAddNewTaskBinding binding;
    private TaskViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewTaskBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListeners();
        saveTask();
    }


    public void initListeners(){
        setName();
        setDescription();
        setType();
        setComplexity();
    }

    private void setComplexity() {
        binding.complexityTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    viewModel.setComplexity(Integer.parseInt(binding.complexityTextField.getText().toString()));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setType() {
        binding.typeTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    viewModel.setType(binding.typeTextField.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setDescription() {
        binding.descriptionTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    viewModel.setDescription(binding.descriptionTextField.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setName(){
        binding.nameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    viewModel.setName(binding.nameTextField.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void saveTask(){
        Button saveButton = binding.saveButton;
        saveButton.setOnClickListener(view -> viewModel.saveTask());
    }


}