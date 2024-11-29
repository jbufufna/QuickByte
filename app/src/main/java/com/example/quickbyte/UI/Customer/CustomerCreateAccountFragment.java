package com.example.quickbyte.UI.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerCreateAccountBinding;
import com.example.quickbyte.API.DTO.UserCreationRequestDTO;
import com.example.quickbyte.API.Services.UserService;

public class CustomerCreateAccountFragment extends Fragment {

    private CustomerCreateAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CustomerCreateAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCreateAccount.setOnClickListener(v -> createAccount());

        binding.textViewAlreadyHaveAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerCreateAccountFragment.this)
                        .navigate(R.id.action_customerCreateAccountFragment_to_customerSignInFragment)
        );
    }

    private void createAccount() {
        String username = binding.editTextUsername.getText().toString();
        String email = binding.editTextEmail.getText().toString();
        String password = binding.editTextPassword.getText().toString();
        String firstName = binding.editTextFirstName.getText().toString();
        String lastName = binding.editTextLastName.getText().toString();
        String phoneNumber = binding.editTextPhoneNumber.getText().toString();

        if (validateInputs(username, email, password, firstName, lastName, phoneNumber)) {
            UserCreationRequestDTO userRequest = new UserCreationRequestDTO(username, email, password, firstName, lastName, phoneNumber);

            UserService.getInstance().createUser(userRequest, new UserService.ApiCallback<UserDTO>() {
                @Override
                public void onSuccess(UserDTO result) {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Account created successfully!", Toast.LENGTH_SHORT).show();
                        NavHostFragment.findNavController(CustomerCreateAccountFragment.this)
                                .navigate(R.id.action_customerCreateAccountFragment_to_customerHomePageFragment);
                    });
                }

                @Override
                public void onError(String errorMessage) {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Error creating account: " + errorMessage, Toast.LENGTH_LONG).show();
                    });
                }
            });
        }
    }

    private boolean validateInputs(String username, String email, String password, String firstName, String lastName, String phoneNumber) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Add more validation as needed
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}