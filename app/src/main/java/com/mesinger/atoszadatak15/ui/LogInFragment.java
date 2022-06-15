package com.mesinger.atoszadatak15.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.databinding.FragmentLogInBinding;
import com.mesinger.atoszadatak15.model.User;
import com.mesinger.atoszadatak15.viewmodels.UserViewModel;


public class LogInFragment extends Fragment {

    public LogInFragment() {

    }

    private FragmentLogInBinding binding;
    private UserViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLogInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkUserInput();

    }

    private void checkUserInput() {
        binding.logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.usernameTextField.getText().toString();
                String password = binding.passwordTextField.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(requireActivity(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }else{
                    viewModel.setUser(viewModel.getUserFromDatabase(username, password));

                    if(viewModel.getUser() == null) {
                        Toast.makeText(requireActivity(), "Please check your username and password!", Toast.LENGTH_SHORT).show();
                    }else {
                        Log.d("LogInFragment", "fetched user from database");
                        NavDirections action = LogInFragmentDirections.actionLogInFragmentToHomeFragment(viewModel.getUser());
                        Navigation.findNavController(requireView()).navigate(action);
                    }

                }

            }
        });


    }
}