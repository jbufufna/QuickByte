package com.example.quickbyte.UI.Business;

import static com.example.quickbyte.Globalvariables.incomingOrderSelected;

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
import com.example.quickbyte.API.DTO.OrderItemDTO;
import com.example.quickbyte.API.Services.MenuItemService;
import com.example.quickbyte.R;
import com.example.quickbyte.UI.ScrollObserver;
import com.example.quickbyte.databinding.BusinessViewOrderBinding;

import android.widget.Toast;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.Facade.Facade;

import java.util.ArrayList;
import java.util.List;

public class BusinessViewOrderFragment extends Fragment {

    private BusinessViewOrderBinding binding;
    private int cardHeight = 190;
    private Facade facade;


    private ScrollObserver scrollObserver;


    // The card proxies which will be loaded as the user scrolls (the initial page of cards is preloaded)
    private List<Pair<TextView, Pair<ImageView, Integer>>> itemProxyList = new ArrayList<Pair<TextView, Pair<ImageView, Integer>>>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = BusinessViewOrderBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        scrollObserver = new ScrollObserver();


        populateOrderParameters(incomingOrderSelected.getOrderItems());

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();


        binding.btnOrderItemsBack.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessViewOrderFragment.this)
                        .navigate(R.id.action_businessViewOrderFragment_to_businessIncomingOrdersFragment)
        );

        ScrollView myScrollView = binding.scrollView;

        // loading items as we scroll (based on card height)
        scrollObserver.observeDownward(myScrollView, cardHeight, new ScrollObserver.OnScrollCallback() {
            @Override
            public void onScrollChanged() {
                loadTopCard();
            }
        });
    }




    private void populateOrderParameters(List<OrderItemDTO> orderItems)
    {
        itemProxyList.clear();

        LinearLayout cardContainer = binding.cardContainer; // The LinearLayout inside the ScrollView

        int initialLoadCards = 7; // how many cards we initially load (depends on size of scrollView and cards)

        System.out.println("Need to load " + orderItems.size() + " items");

        System.out.println(initialLoadCards);
        for (int i = 0; i < orderItems.size(); ++i)
        {
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

            itemName.setTextSize(18);
            textContainer.addView(itemName);

            if (i < initialLoadCards)
            {
                // fetch the info now
                databaseGetCardInfo(itemName, imageView, orderItems.get(i).getMenuItemId());
            }
            else
            {
                // fetch the info after we scroll
                itemProxyList.add(new Pair<TextView, Pair<ImageView, Integer>>(itemName,
                        new Pair<ImageView, Integer>(imageView, orderItems.get(i).getMenuItemId())));
            }

            // Add TextView for item price
            TextView itemPrice = new TextView(requireContext());
            itemPrice.setText("Quantity: " + (int)orderItems.get(i).getQuantity());
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


    private void databaseGetCardInfo(TextView textView, ImageView imageView, Integer menuItemID)
    {
        System.out.println("Fetching item from database");
        facade.getItemById(menuItemID, new MenuItemService.ApiCallback<MenuItem>() {
            @Override
            public void onSuccess(MenuItem result) {
                loadMenuItemToCard(textView, imageView, result);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadMenuItemToCard(TextView textView, ImageView imageView, MenuItem item)
    {
        System.out.println("Loading in card");

        loadTextToTextView(textView, item.getName());
        loadImageToImageView(imageView, item.getImageUrl());
    }


    private void loadTextToTextView(TextView textView, String text)
    {
        textView.setText(text);
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


    private void loadTopCard()
    {
        System.out.println("Scrolled past threshold, loading one card");

        if (!itemProxyList.isEmpty())
        {
            // grab the top most pair
            TextView textView = itemProxyList.get(0).first;
            ImageView imageView = itemProxyList.get(0).second.first;
            Integer menuItemID = itemProxyList.get(0).second.second;

            // load the card
            databaseGetCardInfo(textView, imageView, menuItemID);

            // remove the top most pair
            itemProxyList.remove(0);
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