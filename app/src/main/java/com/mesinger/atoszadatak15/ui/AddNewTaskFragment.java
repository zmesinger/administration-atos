package com.mesinger.atoszadatak15.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.databinding.FragmentAddNewTaskBinding;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNewTaskFragment extends Fragment {



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
        setStartTime();
        setEndTime();
    }

    private void setStartTime() {
        binding.startDateTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartDateTimeDialog(binding.startDateTextField);
                viewModel.setStartTime(binding.startDateTextField.getText().toString());
            }
        });
    }

    private void setEndTime(){
        binding.endDateTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndDateTimeDialog(binding.endDateTextField);
                viewModel.setEndTime(binding.endDateTextField.getText().toString());
            }
        });
    }


    private void showStartDateTimeDialog(TextInputEditText startDateTextField) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        binding.startDateTextField.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void showEndDateTimeDialog(TextInputEditText startDateTextField) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        binding.endDateTextField.setText(simpleDateFormat.format(calendar.getTime()));

                    }
                };
                new TimePickerDialog(requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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
        saveButton.setOnClickListener(view -> {
            viewModel.saveTask();
            Navigation.findNavController(view).navigate(R.id.action_addNewTaskFragment_to_homeFragment);
            Toast.makeText(requireContext(), "Task added!", Toast.LENGTH_SHORT).show();
        });


    }


}