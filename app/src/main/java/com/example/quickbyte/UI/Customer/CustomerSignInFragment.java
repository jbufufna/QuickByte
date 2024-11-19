package com.example.quickbyte.UI.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerSignInBinding;

public class CustomerSignInFragment extends Fragment {

    private CustomerSignInBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CustomerSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSignInCreateAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_customerCreateAccountFragment)
        );

        binding.btnSignInSignIn.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_customerHomePageFragment)
        );

        binding.btnSigninBack.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_logoScreenFragment)
        );

    }
}