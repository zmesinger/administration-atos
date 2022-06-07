package com.mesinger.atoszadatak15.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.data.TypeConverters;
import com.mesinger.atoszadatak15.databinding.FragmentAddNewTaskBinding;
import com.mesinger.atoszadatak15.model.Task;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;

import java.time.LocalDateTime;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewTaskBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveTask();
    }

    private void save() {
        String name = binding.nameTextField.getText().toString();
        String description = binding.descriptionTextField.getText().toString();
        String type = binding.typeTextField.getText().toString();
        int complexity = Integer.parseInt(binding.complexityTextField.getText().toString());
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_TYPE, type);
        data.putExtra(EXTRA_COMPLEXITY, complexity);
        data.putExtra(EXTRA_START_DATE_TIME, startTime);
        data.putExtra(EXTRA_END_DATE_TIME, endTime);

        getActivity().setResult(Activity.RESULT_OK, data);
        getActivity().finish();


    }

    private void saveTask(){
        Button saveButton = binding.saveButton;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }


}