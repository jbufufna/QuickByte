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

        /*binding.btnSignInSignIn.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_customerHomePageFragment)
        );*/

        // Place order button logic with conditional navigation
        binding.btnSignInSignIn.setOnClickListener(v -> {
            boolean condition;
            String userNameInput = binding.textInputUsername.getText().toString();
            if(userNameInput.equals("admin")) {
                condition = false;
            } else {
                condition = true;
            }

            if (condition) {
                // Navigate to Home Page: User logged in
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_customerHomePageFragment);
            } else {
                // Navigate to IncomingOrders: Business/admin logged in
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_businessIncomingOrdersFragment);
            }
        });

        binding.btnSigninBack.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_logoScreenFragment)
        );
    }
}