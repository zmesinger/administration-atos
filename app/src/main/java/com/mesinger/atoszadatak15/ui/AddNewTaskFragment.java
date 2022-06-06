package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mesinger.atoszadatak15.databinding.FragmentAddNewTaskBinding;

class AddNewTaskFragment extends Fragment {



    private FragmentAddNewTaskBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewTaskBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }
}