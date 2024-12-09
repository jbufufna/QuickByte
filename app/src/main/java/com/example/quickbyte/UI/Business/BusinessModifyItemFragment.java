package com.example.quickbyte.UI.Business;

import static com.example.quickbyte.Globalvariables.bizModifyMenuItem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.API.DTO.MenuItemDTO;
import com.example.quickbyte.API.Services.ManageMenuItemService;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.BusinessModifyItemBinding;

import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

import java.math.BigDecimal;

import android.util.Log;

public class BusinessModifyItemFragment extends Fragment {

    private BusinessModifyItemBinding binding;
    private Facade facade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessModifyItemBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        populateItemParameters();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();



        binding.btnModifyItemSave.setOnClickListener(v -> {

            //Save changes
            updateItemParameters();
            //NavHostFragment.findNavController(BusinessModifyItemFragment.this)
            //        .navigate(R.id.action_businessModifyItemFragment_to_businessModifyMenuFragment2);
        });

        binding.btnModifyItemBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(BusinessModifyItemFragment.this)
                    .navigate(R.id.action_businessModifyItemFragment_to_businessModifyMenuFragment2);
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

    private void populateItemParameters(){
        binding.textInputEditBizModifyItemName.setText(bizModifyMenuItem.getName());
        binding.editTextBizModifyItemPrice.setText(String.valueOf(bizModifyMenuItem.getPrice()));
        binding.textInputEditBizModifyItemDesc.setText(bizModifyMenuItem.getDescription());
        loadImageToImageView(binding.imageViewItemImage, bizModifyMenuItem.getImageUrl());
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

    private void updateItemParameters(){
        MenuItemDTO menuitem = new MenuItemDTO();
        menuitem.setItemId(bizModifyMenuItem.getItemId());
        menuitem.setCategoryId(1);
        menuitem.setName(binding.textInputEditBizModifyItemName.getText().toString());
        menuitem.setDescription(binding.textInputEditBizModifyItemDesc.getText().toString());
        //System.out.println("5 " + Double.parseDouble(binding.textViewItemPrice.getText().toString()));
        //menuitem.setPrice(BigDecimal.valueOf(10.00));

        try {
            String decimalString;
            decimalString = binding.editTextBizModifyItemPrice.getText().toString();

            BigDecimal bigDecimalvalue = new BigDecimal(decimalString);
            System.out.println(bigDecimalvalue);
            menuitem.setPrice(bigDecimalvalue);

        } catch (Exception e) {
            System.out.println("Failed to add bigDecimalvalue to menuitem.setPrice.");
        }

        menuitem.setImageUrl(bizModifyMenuItem.getImageUrl());
        menuitem.setIsAvailable(bizModifyMenuItem.isAvailable());
        //menuitem.
        facade.updateMenuItem(menuitem, new ManageMenuItemService.ApiCallback<MenuItemDTO>() {
            @Override
            public void onSuccess(MenuItemDTO result) {
                Toast.makeText(getContext(), "Menu Item successfully updated!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}