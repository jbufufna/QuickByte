package com.example.quickbyte.UI.Business;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.quickbyte.API.Services.MenuItemService;
import com.example.quickbyte.API.Services.OrderService;
import com.example.quickbyte.API.Services.UserService;
import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.API.DTO.OrderDTO;
import com.example.quickbyte.R;
import com.example.quickbyte.UI.Customer.CustomerHomePageFragment;
import com.example.quickbyte.databinding.BusinessIncomingOrdersBinding;

import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

import java.util.List;

public class BusinessIncomingOrdersFragment extends Fragment {

    private BusinessIncomingOrdersBinding binding;
    private BusinessInfoService businessInfoService;
    private Facade facade;

    //private UserService userservice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessIncomingOrdersBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        //userservice = UserService.getInstance();

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
        //facade.getAllItems(new OrderService.ApiCallback<List<OrderDTO>>() {
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


    //List<MenuItem> fullMenu
    private void loadOrders(List<OrderDTO> Orders)
    {
        //imageProxyList.clear();

        LinearLayout cardContainer = binding.cardContainer; // The LinearLayout inside the ScrollView

        //int initialLoadCards = binding.scrollView.getHeight() / cardHeight; // how many cards we initially load (depends on size of scrollView and cards)


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

            /*
            // Add ImageView for the item image
            ImageView imageView = new ImageView(requireContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    100, // Image width
                    100  // Image height
            ));

            if (i < initialLoadCards) // we only put images into the first page of cards, the rest will be loaded dynamically
            {
                // add the image now
                loadImageToImageView(imageView, fullMenu.get(i).getImageUrl());
            }
            else // store the card and image url to be loaded later
            {
                imageProxyList.add(new Pair<ImageView, String>(imageView, fullMenu.get(i).getImageUrl()));
            }

            cardContent.addView(imageView);
            */


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
            cardView.setOnClickListener(v ->
                    NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                            .navigate(R.id.action_businessIncomingOrdersFragment_to_businessViewOrderFragment)
            );

            // Add CardView to the LinearLayout container
            cardContainer.addView(cardView);
        }
    }
}