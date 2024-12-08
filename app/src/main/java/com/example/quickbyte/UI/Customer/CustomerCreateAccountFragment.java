package com.example.quickbyte.UI.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerCreateAccountBinding;

import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.DTO.UserCreationRequestDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.API.Services.UserService;
import com.example.quickbyte.Facade.Facade;

//import android.util.Log;

public class CustomerCreateAccountFragment extends Fragment {

    private CustomerCreateAccountBinding binding;
    private BusinessInfoService businessInfoService;
    private UserService userservice;
    private Facade facade;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = com.example.quickbyte.databinding.CustomerCreateAccountBinding.inflate(inflater, container, false);
       facade = Facade.getInstance();


       return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        binding.btnCreateAccCreateAcc.setOnClickListener(v -> {

                createUserAccount();
                //NavHostFragment.findNavController(CustomerCreateAccountFragment.this)
                //        .navigate(R.id.action_customerCreateAccountFragment_to_customerHomePageFragment)
        });

        binding.btnCreateAccSignIn.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerCreateAccountFragment.this)
                        .navigate(R.id.action_customerCreateAccountFragment_to_customerSignInFragment)
        );

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

    private void createUserAccount() {
        //(String username, String email, String password,
        //String firstName, String lastName, String phoneNumber,
        // String cardNumber, int expiryMonth, int expiryYear,
        // Boolean isDefaultCard, int cvv)
        UserCreationRequestDTO usercreationrequest = new UserCreationRequestDTO(
                binding.editTextCreateAccUsername.getText().toString(),
                binding.editTextCreateAccEmail.getText().toString(),
                binding.editTextCreateAccPassword.getText().toString(),
                binding.editTextCreateAccFirstName.getText().toString(),
                binding.editTextCreateAccLastName.getText().toString(),
                binding.editTextCreateAccPhoneNum.getText().toString(),
                binding.editTextCreateAccCCNum.getText().toString(),
                Integer.parseInt(binding.editTextCreateAccCCExpMo.getText().toString()),
                Integer.parseInt(binding.editTextCreateAccCCExpYr.getText().toString()),
                Integer.parseInt(binding.editTextCreateAccCCCSV.getText().toString()),
            true

        );


        int tempvar = Integer.parseInt(binding.editTextCreateAccCCExpMo.getText().toString());
        Toast.makeText(getContext(), String.valueOf(tempvar), Toast.LENGTH_SHORT).show();

        //createUser(UserCreationRequestDTO userCreationRequest, final UserService.ApiCallback<UserDTO> callback)
        //new UserService.ApiCallback<UserDTO>()
        //new Facade.DatabaseCallback<UserDTO>()

        facade.createUser(usercreationrequest, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                Toast.makeText(getContext(), "Account Creation Successful!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
