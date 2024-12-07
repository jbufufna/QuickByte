package com.example.quickbyte.UI.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerHomePageBinding;

import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

public class CustomerHomePageFragment extends Fragment {

    private CustomerHomePageBinding binding;
    private int cardHeight = 0;
    private Facade facade;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.CustomerHomePageBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();

        // Add menu items dynamically
        addMenuItems();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        binding.btnGoToCart.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerHomePageFragment.this)
                        .navigate(R.id.action_customerHomePageFragment_to_customerPlaceOrderFragment)
        );

        binding.btnGoToAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerHomePageFragment.this)
                        .navigate(R.id.action_customerHomePageFragment_to_customerAccountInformationFragment)
        );

        //TODO: Missing button action to item description page


    }

    private void addMenuItems() {
        LinearLayout cardContainer = binding.cardContainer; // The LinearLayout inside the ScrollView

        // Sample data for menu items
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Salad", "Burger", "Pizza", "Pasta", "Salad", "Burger", "Pizza", "Pasta", "Salad", "Burger", "Pizza", "Pasta", "Salad"};
        double[] itemPrices = {5.99, 8.99, 7.49, 6.99, 12.99, 13.99, 14.99, 15.99, 5.99, 8.99, 7.49, 6.99, 12.99, 13.99, 14.99, 15.99};
        int[] itemImages = {
                R.drawable.burger, //burger is the local image
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger,
                R.drawable.burger
        };

        for (int i = 0; i < itemNames.length; i++) {
            // Create a new CardView
            CardView cardView = new CardView(requireContext());
            cardView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            cardView.setRadius(16f);
            cardView.setCardElevation(8f);
            cardView.setUseCompatPadding(true);
            cardView.setContentPadding(16, 16, 16, 16);
            cardView.setPreventCornerOverlap(true);

            if (i == 0){
                cardHeight = cardView.getHeight();
            }

            // Create a horizontal LinearLayout for CardView contents
            LinearLayout cardContent = new LinearLayout(requireContext());
            cardContent.setOrientation(LinearLayout.HORIZONTAL);
            cardContent.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            cardContent.setPadding(8, 8, 8, 8);

            // Add ImageView for the item image
            ImageView imageView = new ImageView(requireContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    100, // Image width
                    100  // Image height
            ));
            imageView.setImageResource(itemImages[i]); // Set image resource
            cardContent.addView(imageView);

            // Add a vertical LinearLayout for item name and price
            LinearLayout textContainer = new LinearLayout(requireContext());
            textContainer.setOrientation(LinearLayout.VERTICAL);
            textContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textContainer.setPadding(16, 0, 0, 0);

            // Add TextView for item name
            TextView itemName = new TextView(requireContext());
            itemName.setText(itemNames[i]);
            itemName.setTextSize(18);
            textContainer.addView(itemName);

            // Add TextView for item price
            TextView itemPrice = new TextView(requireContext());
            itemPrice.setText(String.format("$%.2f", itemPrices[i]));
            itemPrice.setTextSize(16);
            textContainer.addView(itemPrice);

            // Add text container to card content
            cardContent.addView(textContainer);

            // Add card content to CardView
            cardView.addView(cardContent);

            // Set an OnClickListener for the CardView
            cardView.setOnClickListener(v ->
                    NavHostFragment.findNavController(CustomerHomePageFragment.this)
                            .navigate(R.id.action_customerHomePageFragment_to_customerViewItemFragment)
            );

            // Add CardView to the LinearLayout container
            cardContainer.addView(cardView);
        }
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
}

