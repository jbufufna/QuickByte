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

import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.graphics.Color;
import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.Facade.Facade;

public class CustomerSignInFragment extends Fragment {

    private CustomerSignInBinding binding;
    private BusinessInfoService businessInfoService;
    private Facade facade;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CustomerSignInBinding.inflate(inflater, container, false);
        facade = Facade.getInstance();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch and display business information
        fetchBusinessInfo();

        binding.btnSignInCreateAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerSignInFragment.this)
                        .navigate(R.id.action_customerSignInFragment_to_customerCreateAccountFragment)
        );

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
    }

    private void fetchBusinessInfo() {
        facade.getBusinessInfo(new Facade.DatabaseCallback<BusinessInfoDTO>() {
            @Override
            public void onSuccess(BusinessInfoDTO result) {
                populateUI(result);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), "Error fetching business info: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateUI(BusinessInfoDTO businessInfo) {
        binding.getRoot().setBackgroundColor(Color.parseColor(businessInfo.getPrimaryColor()));
    }
}