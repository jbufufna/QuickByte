package com.example.quickbyte.UI.Customer;

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
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.Common.managers.CartManager;
import com.example.quickbyte.R;
import com.example.quickbyte.UI.ScrollObserver;
import com.example.quickbyte.databinding.CustomerPlaceOrderBinding;

import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

import java.util.ArrayList;
import java.util.List;

public class CustomerPlaceOrderFragment extends Fragment {

    private CustomerPlaceOrderBinding binding;
    private int cardHeight = 190;
    private Facade facade;


    private ScrollObserver scrollObserver;

    // The card proxies which will be loaded as the user scrolls (the initial page of cards is preloaded)
    private List<Pair<ImageView, String>> imageProxyList = new ArrayList<Pair<ImageView, String>>();

    private CartManager cartManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.CustomerPlaceOrderBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        cartManager = CartManager.getInstance();
        scrollObserver = new ScrollObserver();

        //Add order items to dynamically
        getOrderItems();

        //TODO: do math calculate Subtotal, Tax, and Total amounts

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        binding.btnBackToHome.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerPlaceOrderFragment.this)
                        .navigate(R.id.action_customerPlaceOrderFragment_to_customerHomePageFragment)
        );

        binding.btnPlaceOrder.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerPlaceOrderFragment.this)
                        .navigate(R.id.action_customerPlaceOrderFragment_to_customerHomePageFragment)
        );


        ScrollView myScrollView = binding.scrollView;

        // loading items as we scroll (based on card height)
        scrollObserver.observeDownward(myScrollView, cardHeight, new ScrollObserver.OnScrollCallback() {
            @Override
            public void onScrollChanged() {
                loadTopCardImage();
            }
        });
    }

    private void getOrderItems() {
        imageProxyList.clear();

        LinearLayout cardContainer = binding.cardContainer; // The LinearLayout inside the ScrollView

        int initialLoadCards = binding.scrollView.getHeight() / cardHeight; // how many cards we initially load (depends on size of scrollView and cards)


        List<MenuItem> orderItems = new ArrayList<MenuItem>(cartManager.getCartItems().keySet());
        List<Integer> orderItemQuantity = new ArrayList<Integer>(cartManager.getCartItems().values());

        for (int i = 0; i < orderItems.size(); i++) {
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

            if (i < initialLoadCards) // we only put images into the first page of cards, the rest will be loaded dynamically
            {
                // add the image now
                loadImageToImageView(imageView, orderItems.get(i).getImageUrl());
            }
            else // store the card and image url to be loaded later
            {
                imageProxyList.add(new Pair<ImageView, String>(imageView, orderItems.get(i).getImageUrl()));
            }

            cardContent.addView(imageView);


            // Add a vertical LinearLayout for item name and Quantity
            LinearLayout textContainer = new LinearLayout(requireContext());
            textContainer.setOrientation(LinearLayout.VERTICAL);
            textContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textContainer.setPadding(16, 0, 0, 0);

            // Add TextView for item name
            TextView itemName = new TextView(requireContext());
            itemName.setText(orderItems.get(i).getName());
            itemName.setTextSize(18);
            textContainer.addView(itemName);

            // Add TextView for item quantity
            TextView itemPrice = new TextView(requireContext());
            itemPrice.setText("Quantity: " + (int) orderItemQuantity.get(i));
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


    private void loadTopCardImage()
    {
        System.out.println("Scrolled past threshold, loading one card");

        if (!imageProxyList.isEmpty())
        {
            // grab the top most pair
            ImageView imageView = imageProxyList.get(0).first;
            String imageUrl = imageProxyList.get(0).second;

            // load the image
            loadImageToImageView(imageView, imageUrl);

            // remove the top most pair
            imageProxyList.remove(0);
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