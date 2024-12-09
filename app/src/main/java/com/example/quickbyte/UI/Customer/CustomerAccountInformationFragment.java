package com.example.quickbyte.UI.Customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.API.Services.UserService;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerAccountInformationBinding;

import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

public class CustomerAccountInformationFragment extends Fragment {

    private CustomerAccountInformationBinding binding;
    private Facade facade;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CustomerAccountInformationBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fetchBusinessInfo();
        // Fetch and display business information

        populateAccountInfo();

        binding.btnAccInfoBack.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerAccountInformationFragment.this)
                        .navigate(R.id.action_customerAccountInformationFragment_to_customerHomePageFragment)
        );

        binding.btnAccInfoSaveChanges.setOnClickListener(v -> {
                    saveAccountInfo();
                }
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

    private void populateAccountInfo(){
        UserDTO loggedInUserVar = facade.getLoggedInUserVar();

        binding.editTextAccInfoUsername.setText(loggedInUserVar.getUsername().toString());
        //binding.editTextAccInfoPassword.setText(loggedInUserVar.getPasswordHash().toString());
        binding.editTextAccInfoEmail.setText(loggedInUserVar.getEmail().toString());
        binding.editTextAccInfoFirstName.setText(loggedInUserVar.getFirstName().toString());
        binding.editTextAccInfoCCLastName.setText(loggedInUserVar.getLastName().toString());
        binding.editTextAccInfoCCNum.setText(loggedInUserVar.getCardNumber().toString());
        binding.editTextAccInfoCCExpMo.setText(String.valueOf(loggedInUserVar.getExpiryMonth()));
        binding.editTextAccInfoCCExpYr.setText(String.valueOf(loggedInUserVar.getExpiryYear()));
        binding.editTextAccInfoCCCSV.setText(String.valueOf(loggedInUserVar.getCvv()));
        binding.editTextAccInfoPhoneNum.setText(loggedInUserVar.getPhoneNumber().toString());



    }

    private void saveAccountInfo(){
        UserDTO loggedInUserVarSave = facade.getLoggedInUserVar();

        loggedInUserVarSave.setUsername(binding.editTextAccInfoUsername.getText().toString());
        loggedInUserVarSave.setPasswordHash(binding.editTextAccInfoPassword.getText().toString());
        loggedInUserVarSave.setEmail(binding.editTextAccInfoEmail.getText().toString());
        loggedInUserVarSave.setFirstName(binding.editTextAccInfoFirstName.getText().toString());
        loggedInUserVarSave.setLastName(binding.editTextAccInfoCCLastName.getText().toString());
        loggedInUserVarSave.setCardNumber(binding.editTextAccInfoCCNum.getText().toString());
        loggedInUserVarSave.setExpiryMonth(Integer.parseInt(binding.editTextAccInfoCCExpMo.getText().toString()));
        loggedInUserVarSave.setExpiryYear(Integer.parseInt(binding.editTextAccInfoCCExpYr.getText().toString()));
        loggedInUserVarSave.setCvv(Integer.parseInt(binding.editTextAccInfoCCCSV.getText().toString()));
        loggedInUserVarSave.setPhoneNumber(binding.editTextAccInfoPhoneNum.getText().toString());

        facade.updateUser(loggedInUserVarSave.getUserId(),loggedInUserVarSave, new UserService.ApiCallback<UserDTO>(){

            @Override
            public void onSuccess(UserDTO result) {
                Toast.makeText(getContext(), "Account information updated.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });


    }
}