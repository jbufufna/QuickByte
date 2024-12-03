package com.example.quickbyte.UI.Business;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Database;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.BusinessModifyBusinessBinding;

public class BusinessModifyBusinessFragment extends Fragment {

    private BusinessModifyBusinessBinding binding;
    private BusinessInfoService businessInfoService;
    private Database database;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = BusinessModifyBusinessBinding.inflate(inflater, container, false);
        database = Database.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        binding.btnBizInfoBack.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_businessModifyBusinessFragment_to_businessIncomingOrdersFragment)
        );

        binding.btnBizInfoSaveChanges.setOnClickListener(v -> {
            if (validateInput()) {
                saveBusinessInfo();
            }
        });

        binding.btnBizInfoGetChanges.setOnClickListener(v -> fetchBusinessInfo());
    }

    private void fetchBusinessInfo() {
        int businessId = 1;
        database.getBusinessInfo(businessId, new Database.DatabaseCallback<BusinessInfoDTO>() {
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
        binding.textInputEditTextBizName.setText(businessInfo.getBusinessName());
        binding.textInputEditTextBizName3.setText(businessInfo.getSlogan());
        Glide.with(requireContext())
                .load(businessInfo.getLogoUrl())
                .placeholder(R.drawable.error_image)
                .error(R.drawable.error_image) // Error drawable
                .into(binding.imageViewBizImage);
    }

    private void saveBusinessInfo() {
        BusinessInfoDTO businessInfo = new BusinessInfoDTO(
                binding.textInputEditTextBizName.getText().toString(),
                "http://example.com/logo.png", // Actual URL
                binding.textInputEditTextBizName3.getText().toString(),
                "#FF5733", // Primary color
                "#33FF57"  // Secondary color
        );

        database.saveBusinessInfo(businessInfo, new Database.DatabaseCallback<BusinessInfoDTO>() {
            @Override
            public void onSuccess(BusinessInfoDTO result) {
                Toast.makeText(getContext(), "Business information saved.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), "Error saving business info: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInput() {
        if (binding.textInputEditTextBizName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Business name is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.textInputEditTextBizName3.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Slogan is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}