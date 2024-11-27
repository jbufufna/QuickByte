package com.example.quickbyte.UI.Business;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.R;
import com.example.quickbyte.databinding.BusinessIncomingOrdersBinding;
import com.example.quickbyte.databinding.BusinessViewOrderBinding;

public class BusinessIncomingOrdersFragment extends Fragment {

    private BusinessIncomingOrdersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessIncomingOrdersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.editOrder1.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                        .navigate(R.id.action_businessIncomingOrdersFragment_to_businessViewOrderFragment)
        );

        binding.btnIncomingMenu.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                        .navigate(R.id.action_businessIncomingOrdersFragment_to_businessModifyMenuFragment)
        );

        binding.btnIncomingBizSettings.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessIncomingOrdersFragment.this)
                        .navigate(R.id.action_businessIncomingOrdersFragment_to_businessModifyBusinessFragment)
        );
    }
}