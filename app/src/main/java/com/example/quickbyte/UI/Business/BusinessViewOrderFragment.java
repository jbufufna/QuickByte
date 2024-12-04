package com.example.quickbyte.UI.Business;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quickbyte.R;
import com.example.quickbyte.UI.Customer.CustomerHomePageFragment;
import com.example.quickbyte.UI.Customer.CustomerSignInFragment;
import com.example.quickbyte.databinding.BusinessViewOrderBinding;
import com.example.quickbyte.databinding.CustomerSignInBinding;

public class BusinessViewOrderFragment extends Fragment {

    private BusinessViewOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = BusinessViewOrderBinding.inflate(inflater, container, false);

        getOrderItems();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnOrderItemsBack.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessViewOrderFragment.this)
                        .navigate(R.id.action_businessViewOrderFragment_to_businessIncomingOrdersFragment)
        );
    }

    private void getOrderItems() {
        LinearLayout cardContainer = binding.cardContainer; // The LinearLayout inside the ScrollView

        // Sample data for menu items
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Salad", "Burger", "Pizza", "Pasta", "Salad", "Burger", "Pizza", "Pasta", "Salad", "Burger", "Pizza", "Pasta", "Salad"};
        int[] itemAmount = {5, 8, 7, 6, 12, 13, 14, 15, 5, 8, 7, 6, 12, 13, 14, 15};
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
            itemPrice.setText("Quantity: " + (int)itemAmount[i]);
            itemPrice.setTextSize(16);
            textContainer.addView(itemPrice);

            // Add text container to card content
            cardContent.addView(textContainer);

            // Add card content to CardView
            cardView.addView(cardContent);

            // Add CardView to the LinearLayout container
            cardContainer.addView(cardView);
        }
    }
}