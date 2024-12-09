package com.example.quickbyte.UI;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.LogoScreenBinding;

import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

public class LogoScreenFragment extends Fragment {

    private LogoScreenBinding binding;
    private BusinessInfoService businessInfoService;
    private Facade facade;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = LogoScreenBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        binding.imageViewLogo.setOnClickListener(v ->
                NavHostFragment.findNavController(LogoScreenFragment.this)
                        .navigate(R.id.action_LogoScreenFragment_to_customerSignInFragment)
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
        binding.textViewLogoScreenBizName.setText(businessInfo.getBusinessName());
        binding.textViewLogoScreenBizSlogan.setText(businessInfo.getSlogan());
        loadImageToImageView(binding.imageViewLogo, businessInfo.getLogoUrl());
        /*
        Glide.with(requireContext())
                .load(businessInfo.getLogoUrl())
                .placeholder(R.drawable.error_image)
                .error(R.drawable.error_image) // Error drawable
                .into(binding.imageViewLogo);

         */
        binding.getRoot().setBackgroundColor(Color.parseColor(businessInfo.getPrimaryColor()));
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