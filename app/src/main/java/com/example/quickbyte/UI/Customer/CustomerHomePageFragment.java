package com.example.quickbyte.UI.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerHomePageBinding;


public class CustomerHomePageFragment extends Fragment {

    private CustomerHomePageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.CustomerHomePageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnGoToCart.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerHomePageFragment.this)
                        .navigate(R.id.action_customerHomePageFragment_to_customerPlaceOrderFragment)
        );

        binding.btnGoToAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerHomePageFragment.this)
                        .navigate(R.id.action_customerHomePageFragment_to_customerAccountInformationFragment)
        );
    }
}