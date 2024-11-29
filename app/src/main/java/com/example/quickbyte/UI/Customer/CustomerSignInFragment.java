package com.example.quickbyte.UI.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.API.Services.UserService;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerSignInBinding;
import android.widget.Toast;

public class CustomerSignInFragment extends Fragment {

    private CustomerSignInBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CustomerSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCreateAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_customerCreateAccountFragment)
        );

        binding.buttonSignIn.setOnClickListener(v -> {
            String username = binding.editTextUsername.getText().toString();
            String password = binding.editTextPassword.getText().toString();

            UserService.getInstance().loginUser(username, password, new UserService.ApiCallback<UserDTO>() {
                @Override
                public void onSuccess(UserDTO result) {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        if ("admin".equals(result.getUsername())) {
                            NavHostFragment.findNavController(CustomerSignInFragment.this)
                                    .navigate(R.id.action_customerSignInFragment_to_businessIncomingOrdersFragment);
                        } else {
                            NavHostFragment.findNavController(CustomerSignInFragment.this)
                                    .navigate(R.id.action_customerSignInFragment_to_customerHomePageFragment);
                        }
                    });
                }

                @Override
                public void onError(String errorMessage) {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    });
                }
            });
        });

        // Remove the back button listener as it's not present in the new layout
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}