package com.example.quickbyte.UI.Business;


import static com.example.quickbyte.Globalvariables.incomingOrderSelected;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.quickbyte.API.Services.OrderService;
import com.example.quickbyte.API.DTO.OrderDTO;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.BusinessIncomingOrdersBinding;

import android.widget.Toast;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

import java.util.List;

public class BusinessIncomingOrdersFragment extends Fragment {

    private BusinessIncomingOrdersBinding binding;
    private Facade facade;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessIncomingOrdersBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();


        getOrders();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        binding.btnIncomingMenu.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                        .navigate(R.id.action_businessIncomingOrdersFragment_to_businessModifyMenuFragment)
        );

        binding.btnIncomingBizSettings.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                        .navigate(R.id.action_businessIncomingOrdersFragment_to_businessModifyBusinessFragment)
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

    private void getOrders() {

        facade.getAllOrders(new OrderService.ApiCallback<List<OrderDTO>>() {
            @Override
            public void onSuccess(List<OrderDTO> result) {
                loadOrders(result);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void loadOrders(List<OrderDTO> Orders)
    {
        LinearLayout cardContainer = binding.cardContainer; // The LinearLayout inside the ScrollView

        for (int i = 0; i < Orders.size(); i++) {

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

            // Userid = Orders.get(i).getUserId().toString();
            String itemtext = "CustomerID: " + Orders.get(i).getUserId().toString() + "  Status: " + Orders.get(i).getStatus();
            itemName.setText(itemtext);
            itemName.setTextSize(18);
            textContainer.addView(itemName);

            // Add TextView for order total
            TextView orderPrice = new TextView(requireContext());
            orderPrice.setText("Order Date/Time: " + Orders.get(i).getOrderDate().toString());
            orderPrice.setTextSize(16);
            textContainer.addView(orderPrice);

            // Add text container to card content
            cardContent.addView(textContainer);

            // Add card content to CardView
            cardView.addView(cardContent);

            // Set an OnClickListener for the CardView
            int finalI = i;
            cardView.setOnClickListener(v -> {

                    incomingOrderSelected = Orders.get(finalI);
                    NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                            .navigate(R.id.action_businessIncomingOrdersFragment_to_businessViewOrderFragment);
                    System.out.println("Global variable = " + String.valueOf(incomingOrderSelected));
            });

            // Add CardView to the LinearLayout container
            cardContainer.addView(cardView);
        }
    }
}