package com.example.quickbyte.UI.Business;

import static com.example.quickbyte.Globalvariables.bizModifyMenuItemid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.BusinessModifyItemBinding;

import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

public class BusinessModifyItemFragment extends Fragment {

    private BusinessModifyItemBinding binding;
    private BusinessInfoService businessInfoService;
    private Facade facade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessModifyItemBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        populateItemParameters();

        binding.btnModifyItemBack.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessModifyItemFragment.this)
                        .navigate(R.id.action_businessModifyItemFragment_to_businessModifyMenuFragment2)
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

    private void populateItemParameters(){
        binding.textInputEditBizModifyItemName.setText(bizModifyMenuItemid.getName());
        binding.editTextBizModifyItemPrice.setText(String.valueOf(bizModifyMenuItemid.getPrice()));
        binding.textInputEditBizModifyItemDesc.setText(bizModifyMenuItemid.getDescription());
        loadImageToImageView(binding.imageViewItemImage, bizModifyMenuItemid.getImageUrl());
        //binding.editTextBizModifyPrepTime.setText(bizModifyMenuItemid.get());


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