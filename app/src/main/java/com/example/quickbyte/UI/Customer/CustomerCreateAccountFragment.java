package com.example.quickbyte.UI.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerCreateAccountBinding;



public class CustomerCreateAccountFragment extends Fragment {

    private CustomerCreateAccountBinding binding;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = com.example.quickbyte.databinding.CustomerCreateAccountBinding.inflate(inflater, container, false);
       return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCreateAccCreateAcc.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerCreateAccountFragment.this)
                        .navigate(R.id.action_customerCreateAccountFragment_to_customerHomePageFragment)
        );

        binding.btnCreateAccSignIn.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerCreateAccountFragment.this)
                        .navigate(R.id.action_customerCreateAccountFragment_to_customerSignInFragment)
        );

    }
}