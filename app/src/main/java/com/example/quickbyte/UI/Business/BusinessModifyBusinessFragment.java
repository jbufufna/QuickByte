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


public class BusinessModifyBusinessFragment extends Fragment {

    private BusinessModifyBusinessBinding binding;

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
    }
}