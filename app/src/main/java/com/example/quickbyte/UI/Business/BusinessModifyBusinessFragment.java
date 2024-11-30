package com.example.quickbyte.UI.Business;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.R;
import com.example.quickbyte.databinding.BusinessModifyBusinessBinding;
import com.example.quickbyte.Facade.Database; // Import the Database class


public class BusinessModifyBusinessFragment extends Fragment {

    private BusinessModifyBusinessBinding binding;
    private Database database = new Database();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessModifyBusinessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBizInfoBack.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessModifyBusinessFragment.this)
                        .navigate(R.id.action_businessModifyBusinessFragment_to_businessIncomingOrdersFragment)
        );

        // Action for btnBizInfoSaveChanges
        binding.btnBizInfoSaveChanges.setOnClickListener(v -> {
            int result1 = 1 + 1; // Add 1 + 1
            // Optionally log the result for debugging
            System.out.println("Result of 1 + 1: " + result1);

            database.putBusinessName("Database Name");
        });

        // Action for btnBizInfoSaveChanges
        binding.btnBizInfoGetChanges.setOnClickListener(v -> {
            int result2 = 2 + 2; // Add 2 + 2
            // Optionally log the result for debugging
            System.out.println("Result of 2 + 2: " + result2);

            System.out.println(database.getBusinessName());


        });


    }
}