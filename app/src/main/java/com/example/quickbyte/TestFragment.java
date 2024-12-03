package com.example.quickbyte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quickbyte.R;
import com.example.quickbyte.databinding.FragmentTestBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment {

    private LinearLayout cardContainer;

    private FragmentTestBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        // Find the container LinearLayout where the CardViews will be added
        cardContainer = view.findViewById(R.id.card_container);

        // Add 5 new CardViews dynamically
        for (int i = 0; i < 5; i++) {
            addCardView(i + 1); // Passing index to differentiate cards
        }

        return view;
    }

    /**
     * Adds a new CardView to the container.
     */
    private void addCardView(int index) {
        // Create a new CardView
        CardView cardView = new CardView(requireContext());
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 16); // Adding bottom margin
        cardView.setLayoutParams(cardParams);
        cardView.setRadius(8);
        cardView.setCardElevation(4);

        // Create the horizontal LinearLayout inside the CardView
        LinearLayout cardLayout = new LinearLayout(requireContext());
        cardLayout.setOrientation(LinearLayout.HORIZONTAL);
        cardLayout.setPadding(16, 16, 16, 16);

        // Add an ImageView to the layout
        ImageView imageView = new ImageView(requireContext());
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(80, 80);
        imageParams.setMarginEnd(16);
        imageView.setLayoutParams(imageParams);
        //imageView.setImageResource(R.drawable.sample_image1); // Replace with a dynamic resource if needed
        imageView.setContentDescription("New Item " + index);

        // Add a vertical layout for text
        LinearLayout textLayout = new LinearLayout(requireContext());
        textLayout.setOrientation(LinearLayout.VERTICAL);

        // Add a description TextView
        TextView description = new TextView(requireContext());
        description.setText("New menu item " + index);
        description.setTextSize(16);
        //description.setTextStyle(android.graphics.Typeface.BOLD);

        // Add a price TextView
        TextView price = new TextView(requireContext());
        price.setText("$" + (10.99 + index)); // Example pricing logic
        price.setTextSize(14);
        //price.setTextColor(requireContext().getResources().getColor(R.color.price_color)); // Replace with actual color resource

        // Add the TextViews to the text layout
        textLayout.addView(description);
        textLayout.addView(price);

        // Add the ImageView and text layout to the card layout
        cardLayout.addView(imageView);
        cardLayout.addView(textLayout);

        // Add the card layout to the CardView
        cardView.addView(cardLayout);

        // Add the CardView to the container
        cardContainer.addView(cardView);
    }
}
