package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.databinding.FragmentEditWorkerBinding;
import com.mesinger.atoszadatak15.model.Worker;


public class EditWorkerFragment extends Fragment {

    private FragmentEditWorkerBinding binding;
    private EditWorkerFragmentArgs args;
    private Worker worker;


    public EditWorkerFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditWorkerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = EditWorkerFragmentArgs.fromBundle(getArguments());
        worker = args.getWorker();

        displayData(worker);


    }

    private void displayData(Worker worker) {
        binding.nameTextField.setText(worker.getName());
        binding.surnameTextField.setText(worker.getSurname());
        binding.workPositionTextField.setText(worker.getWorkPosition());
        binding.oibTextField.setText(worker.getOib());
    }
}