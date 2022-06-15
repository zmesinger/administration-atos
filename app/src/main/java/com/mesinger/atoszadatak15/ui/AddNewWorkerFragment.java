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
import android.widget.Toast;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.databinding.FragmentAddNewWorkerBinding;
import com.mesinger.atoszadatak15.viewmodels.TaskViewModel;
import com.mesinger.atoszadatak15.viewmodels.WorkerViewModel;

public class AddNewWorkerFragment extends Fragment {

    private FragmentAddNewWorkerBinding binding;
    private WorkerViewModel viewModel;

    public AddNewWorkerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewWorkerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(WorkerViewModel.class);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListeners();
        saveWorker();
    }

    private void saveWorker() {
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.saveWorker();
                Navigation.findNavController(view).navigate(R.id.action_addNewWorkerFragment_to_workersListFragment);
                Toast.makeText(requireContext(), "Worker added!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListeners() {
        setName();
        setSurname();
        setWorkPosition();
        setOib();
    }

    private void setOib() {
        binding.oibTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.setOib(binding.oibTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setWorkPosition() {
        binding.workPositionTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.setWorkPosition(binding.workPositionTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setSurname() {
        binding.surnameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.setSurname(binding.surnameTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setName() {
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
}