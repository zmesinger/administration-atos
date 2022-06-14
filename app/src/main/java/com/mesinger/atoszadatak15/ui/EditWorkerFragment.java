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
import com.mesinger.atoszadatak15.databinding.FragmentEditWorkerBinding;
import com.mesinger.atoszadatak15.model.Worker;
import com.mesinger.atoszadatak15.viewmodels.WorkerViewModel;


public class EditWorkerFragment extends Fragment {

    private FragmentEditWorkerBinding binding;
    private EditWorkerFragmentArgs args;
    private Worker worker;
    private WorkerViewModel viewModel;


    public EditWorkerFragment() {
        // Required empty public constructor
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditWorkerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(WorkerViewModel.class);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = EditWorkerFragmentArgs.fromBundle(getArguments());
        worker = args.getWorker();

        displayData(worker);
        updateWorker(setUpdatedWorkerValues());



    }



    private void updateWorker(Worker worker) {
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.update(worker);
                Toast.makeText(requireContext(), "Worker updated!", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireView()).navigate(R.id.action_editWorkerFragment_to_workersListFragment);
            }
        });
    }

    private Worker setUpdatedWorkerValues() {
        Worker updatedWorker = new Worker();

        updatedWorker.setId(worker.getId());

        binding.nameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedWorker.setName(binding.nameTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.surnameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedWorker.setSurname(binding.surnameTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.workPositionTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedWorker.setWorkPosition(binding.workPositionTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.oibTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedWorker.setOib(binding.oibTextField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        return updatedWorker;

    }


    private void displayData(Worker worker) {
        binding.nameTextField.setText(worker.getName());
        binding.surnameTextField.setText(worker.getSurname());
        binding.workPositionTextField.setText(worker.getWorkPosition());
        binding.oibTextField.setText(worker.getOib());
    }


}