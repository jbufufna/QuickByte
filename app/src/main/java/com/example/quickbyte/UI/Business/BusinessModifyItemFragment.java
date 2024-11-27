package com.example.quickbyte.UI.Business;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickbyte.R;
import com.example.quickbyte.databinding.BusinessModifyItemBinding;

public class BusinessModifyItemFragment extends Fragment {

    private BusinessModifyItemBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.quickbyte.databinding.BusinessModifyItemBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnModifyItemBack.setOnClickListener(v ->
                NavHostFragment.findNavController(BusinessModifyItemFragment.this)
                        .navigate(R.id.action_businessModifyItemFragment_to_businessModifyMenuFragment2)
        );
    }
}