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
import com.example.quickbyte.databinding.BusinessIncomingOrdersBinding;
import com.example.quickbyte.databinding.BusinessViewOrderBinding;

public class BusinessIncomingOrdersFragment extends Fragment {

    private BusinessIncomingOrdersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessIncomingOrdersBinding.inflate(inflater, container, false);
        addOrders();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnIncomingMenu.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                        .navigate(R.id.action_businessIncomingOrdersFragment_to_businessModifyMenuFragment)
        );

        binding.btnIncomingBizSettings.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                        .navigate(R.id.action_businessIncomingOrdersFragment_to_businessModifyBusinessFragment)
        );
    }

    private void addOrders() {
        LinearLayout cardContainer = binding.cardContainer; // The LinearLayout inside the ScrollView

        // Sample data for menu items
        String[] orderNames = {"John Doe", "Jane Doe", "Mr. Smith", "Joel C.", "Dipanshu", "Joey", "Juan", "Tyler", "Kyle"};
        int[] orderNumItems = {6, 1, 4, 5, 6, 2, 5, 7, 7};

        for (int i = 0; i < orderNames.length; i++) {
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

            // Add a vertical LinearLayout for item name and price
            LinearLayout textContainer = new LinearLayout(requireContext());
            textContainer.setOrientation(LinearLayout.VERTICAL);
            textContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textContainer.setPadding(16, 0, 0, 0);

            // Add TextView for item name
            TextView itemName = new TextView(requireContext());
            itemName.setText(orderNames[i]);
            itemName.setTextSize(18);
            textContainer.addView(itemName);

            // Add TextView for item price
            TextView itemPrice = new TextView(requireContext());
            itemPrice.setText("Number of Items: " + orderNumItems[i]);
            itemPrice.setTextSize(16);
            textContainer.addView(itemPrice);

            // Add text container to card content
            cardContent.addView(textContainer);

            // Add card content to CardView
            cardView.addView(cardContent);

            // Set an OnClickListener for the CardView
            cardView.setOnClickListener(v ->
                    NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                            .navigate(R.id.action_businessIncomingOrdersFragment_to_businessViewOrderFragment)
            );

            // Add CardView to the LinearLayout container
            cardContainer.addView(cardView);
        }
    }
}