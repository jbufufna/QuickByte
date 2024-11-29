package com.example.quickbyte.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.LogoScreenBinding;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quickbyte.R;
import com.example.quickbyte.databinding.LogoScreenBinding;

public class LogoScreenFragment extends Fragment {

    private LogoScreenBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LogoScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Apply fade-in animation to the logo
       // Animation fadeAndScaleAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_and_scale);
       // binding.imageViewLogo.startAnimation(fadeAndScaleAnimation);

        // Navigate to the next screen after 3 seconds
        new Handler().postDelayed(() ->
                        NavHostFragment.findNavController(LogoScreenFragment.this)
                                .navigate(R.id.action_LogoScreenFragment_to_customerSignInFragment),
                3000
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}