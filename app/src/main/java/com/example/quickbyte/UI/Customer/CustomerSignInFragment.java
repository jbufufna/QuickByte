package com.example.quickbyte.UI.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quickbyte.API.DTO.BusinessOwnerDTO;
import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.API.Services.BusinessOwnerService;
import com.example.quickbyte.API.Services.UserService;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerSignInBinding;

import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

public class CustomerSignInFragment extends Fragment {

    private CustomerSignInBinding binding;
    private BusinessInfoService businessinfoservice;
    private BusinessOwnerService businessownerservice;
    private UserService userservice;
    private Facade facade;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CustomerSignInBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        binding.btnSignInCreateAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_customerCreateAccountFragment)
        );

        // Place order button logic with conditional navigation
        binding.btnSignInSignIn.setOnClickListener(v -> {

            String userNameInput = binding.textInputUsername.getText().toString();
            String passwordInput = binding.textInputPassword.getText().toString();

            attemptLogin(userNameInput, passwordInput);

            /*boolean condition;

            if(userNameInput.equals("admin")) {
                condition = false;
            } else {
                condition = true;
            }

            if (condition) {
                // Navigate to Home Page: User logged in
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_customerHomePageFragment);
            } else {
                // Navigate to IncomingOrders: Business/admin logged in
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_businessIncomingOrdersFragment);
            }

             */
        });
    }

    private void fetchBusinessInfo() {
        facade.getBusinessInfo(new Facade.DatabaseCallback<BusinessInfoDTO>() {
            @Override
            public void onSuccess(BusinessInfoDTO result) {
                populateUI(result);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), "Error fetching business info: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateUI(BusinessInfoDTO businessInfo) {
        binding.getRoot().setBackgroundColor(Color.parseColor(businessInfo.getPrimaryColor()));
    }

    private void attemptLogin(String username, String password){
        boolean inputValidated = validateInput(username, password);
        final boolean userAccount[] = {false};

        if(inputValidated) {

            facade.loginBusinessOwner(username, password, new BusinessOwnerService.ApiCallback<BusinessOwnerDTO>() {
                @Override
                public void onSuccess(BusinessOwnerDTO result) {
                    // Navigate to Home Page: User logged in
                    NavHostFragment.findNavController(CustomerSignInFragment.this)
                            .navigate(R.id.action_customerSignInFragment_to_businessIncomingOrdersFragment);
                }

                @Override
                public void onError(String errorMessage) {
                    //Toast.makeText(getContext(), "Business: " + errorMessage, Toast.LENGTH_SHORT).show();
                    userAccount[0] = true;

                    if(userAccount[0]) {
                        facade.loginUser(username, password, new UserService.ApiCallback<UserDTO>() {
                            @Override
                            public void onSuccess(UserDTO result) {
                                // Navigate to Home Page: User logged in
                                NavHostFragment.findNavController(CustomerSignInFragment.this)
                                        .navigate(R.id.action_customerSignInFragment_to_customerHomePageFragment);
                            }

                            @Override
                            public void onError(String errorMessage) {
                                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                                userAccount[0] = true;
                            }
                        });
                    }
                }
            });
        }
    }

    private boolean validateInput(String username, String password) {
        if (username.trim().isEmpty()) {
            Toast.makeText(getContext(), "Username is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.trim().isEmpty()) {
            Toast.makeText(getContext(), "Password is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}