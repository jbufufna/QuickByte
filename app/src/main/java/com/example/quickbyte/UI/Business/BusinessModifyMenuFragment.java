package com.example.quickbyte.UI.Business;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.R;
import com.example.quickbyte.UI.Customer.CustomerHomePageFragment;
import com.example.quickbyte.databinding.BusinessModifyMenuBinding;

public class BusinessModifyMenuFragment extends Fragment {

    private BusinessModifyMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessModifyMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.editTextText2.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessModifyMenuFragment.this)
                        .navigate(R.id.action_businessModifyMenuFragment_to_businessModifyItemFragment)
        );

        binding.btnModifyMenuBack.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessModifyMenuFragment.this)
                        .navigate(R.id.action_businessModifyMenuFragment_to_businessIncomingOrdersFragment)
        );
    }
}