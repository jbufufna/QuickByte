package com.example.quickbyte.UI.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerPlaceOrderBinding;

public class CustomerPlaceOrderFragment extends Fragment {

    private CustomerPlaceOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.CustomerPlaceOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBackToHome.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerPlaceOrderFragment.this)
                        .navigate(R.id.action_customerPlaceOrderFragment_to_customerHomePageFragment)
        );

        binding.btnPlaceOrder.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerPlaceOrderFragment.this)
                        .navigate(R.id.action_customerPlaceOrderFragment_to_customerHomePageFragment)
        );
    }
}