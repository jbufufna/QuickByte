package com.example.quickbyte.UI.Business;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.R;
import com.example.quickbyte.UI.Customer.CustomerSignInFragment;
import com.example.quickbyte.databinding.BusinessViewOrderBinding;
import com.example.quickbyte.databinding.CustomerSignInBinding;

public class BusinessViewOrderFragment extends Fragment {

    private BusinessViewOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = BusinessViewOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnOrderItemsBack.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessViewOrderFragment.this)
                        .navigate(R.id.action_businessViewOrderFragment_to_businessIncomingOrdersFragment)
        );
    }

}