package com.example.quickbyte.UI.Customer;

import static com.example.quickbyte.Globalvariables.customerViewMenuItemId;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.Common.managers.CartManager;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerViewItemBinding;

import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

public class CustomerViewItemFragment extends Fragment {

    private CustomerViewItemBinding binding;
    private BusinessInfoService businessInfoService;
    private Facade facade;
    private CartManager cartmanager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.CustomerViewItemBinding.inflate(inflater, container, false);

        //TODO: On page loading load current amount for item into amount
        // TODO: amount should always start as whatever is in the singleton (default = 0)

        facade = Facade.getInstance();
        cartmanager = CartManager.getInstance();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();
        populateItemParameters();

        //editTextNumberDecimal.set cartmanager.getItemQuantity(customerViewMenuItemId);

        binding.btnViewItemBack.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerViewItemFragment.this)
                        .navigate(R.id.action_customerViewItemFragment_to_customerHomePageFragment)
        );

        binding.btnViewItemAddToCart.setOnClickListener(v -> {
            //TODO: when add to cart pressed, take new amount and replace it in current order singleton.
            cartmanager.updateItemQuantity(customerViewMenuItemId, Integer.parseInt(binding.editTextNumberDecimal.getText().toString()));

            System.out.println(" saved item quantity: " + cartmanager.getItemQuantity(customerViewMenuItemId));

            NavHostFragment.findNavController(CustomerViewItemFragment.this)
                    .navigate(R.id.action_customerViewItemFragment_to_customerHomePageFragment);
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
        binding.textViewItemName.setText(customerViewMenuItemId.getName());
        binding.textViewItemPriceNumber.setText("$" + customerViewMenuItemId.getPrice().toString());
        binding.textViewItemDescription.setText(customerViewMenuItemId.getDescription());
        loadImageToImageView(binding.imageViewItemImage, customerViewMenuItemId.getImageUrl());
        binding.editTextNumberDecimal.setText(String.valueOf(cartmanager.getItemQuantity(customerViewMenuItemId)));

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