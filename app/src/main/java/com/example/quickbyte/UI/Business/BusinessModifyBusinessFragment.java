package com.example.quickbyte.UI.Business;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.BusinessModifyBusinessBinding;

public class BusinessModifyBusinessFragment extends Fragment {

    private BusinessModifyBusinessBinding binding;
    private Facade facade;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = BusinessModifyBusinessBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();


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
        binding.textInputEditTextBizName.setText(businessInfo.getBusinessName());
        binding.textInputEditTextBizSlogan.setText(businessInfo.getSlogan());
        loadImageToImageView(binding.imageViewBizImage, businessInfo.getLogoUrl());
        binding.textInputEditTextBizPrimColor.setText(businessInfo.getPrimaryColor());
        binding.textInputEditTextBizSecColor.setText(businessInfo.getSecondaryColor());
        binding.getRoot().setBackgroundColor(Color.parseColor(businessInfo.getPrimaryColor()));
    }

    private void saveBusinessInfo() {
        BusinessInfoDTO businessInfoTemp = new BusinessInfoDTO(
                binding.textInputEditTextBizName.getText().toString(),
                "quickbyte_logo", // Actual URL
                binding.textInputEditTextBizSlogan.getText().toString(),
                binding.textInputEditTextBizPrimColor.getText().toString(), // Primary color
                binding.textInputEditTextBizSecColor.getText().toString(),  // Secondary color
                "1" // Owner ID
        );

        facade.saveBusinessInfo(businessInfoTemp, new Facade.DatabaseCallback<BusinessInfoDTO>() {
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
        if (binding.textInputEditTextBizSlogan.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Slogan is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.textInputEditTextBizPrimColor.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Primary Color is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.textInputEditTextBizSecColor.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Secondary Color is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void loadImageToImageView(ImageView imageView, String imageUrl)
    {
        // Get the resource ID dynamically
        int resId = getContext().getResources().getIdentifier(imageUrl, "drawable", getContext().getPackageName());

        // Check if the resource exists
        if (resId != 0) {
            // Use the resource ID to load the image
            imageView.setImageResource(resId);
        } else {
            // Handle the case where the resource was not found
            System.out.println("Error locating image: " + imageUrl);

            imageView.setImageResource(R.drawable.error_image);
        }
    }
}