package com.example.quickbyte.UI.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.API.IServices.ICustomerMenuService;
import com.example.quickbyte.Common.adapters.MenuItemAdapter;
import com.example.quickbyte.Common.managers.UserManager;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerHomePageBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerHomePageFragment extends Fragment {

    private CustomerHomePageBinding binding;
    private MenuItemAdapter categoriesAdapter, popularItemsAdapter, specialOffersAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CustomerHomePageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerViews();
        fetchMenuCategories();
        fetchPopularItems();
        fetchSpecialOffers();

        // Display user info if logged in
        if (UserManager.getInstance().isLoggedIn()) {
            String welcomeMessage = "Welcome, " + UserManager.getInstance().getUser().getFirstName();
            Toast.makeText(getContext(), welcomeMessage, Toast.LENGTH_SHORT).show();
        }

        // Navigation to Cart
        binding.btnGoToCart.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerHomePageFragment.this)
                        .navigate(R.id.action_customerHomePageFragment_to_customerPlaceOrderFragment)
        );

        // Navigation to Account
        binding.btnGoToAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(CustomerHomePageFragment.this)
                        .navigate(R.id.action_customerHomePageFragment_to_customerAccountInformationFragment)
        );
    }

    private void setupRecyclerViews() {
        // Set up RecyclerView for Menu Categories
        binding.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoriesAdapter = new MenuItemAdapter();
        binding.recyclerViewCategories.setAdapter(categoriesAdapter);

        // Set up RecyclerView for Popular Items
        binding.recyclerViewPopularItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularItemsAdapter = new MenuItemAdapter();
        binding.recyclerViewPopularItems.setAdapter(popularItemsAdapter);

        // Set up RecyclerView for Special Offers
        binding.recyclerViewSpecialOffers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        specialOffersAdapter = new MenuItemAdapter();
        binding.recyclerViewSpecialOffers.setAdapter(specialOffersAdapter);
    }

    private void fetchMenuCategories() {
        ICustomerMenuService customerMenuService = ApiClient.getApiService(ICustomerMenuService.class);
        customerMenuService.getMenuCategories().enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoriesAdapter.setMenuItems(response.body());
                } else {
                    Toast.makeText(getContext(), "Failed to load menu categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchPopularItems() {
        ICustomerMenuService customerMenuService = ApiClient.getApiService(ICustomerMenuService.class);
        customerMenuService.getPopularItems().enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    popularItemsAdapter.setMenuItems(response.body());
                } else {
                    Toast.makeText(getContext(), "Failed to load popular items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchSpecialOffers() {
        ICustomerMenuService customerMenuService = ApiClient.getApiService(ICustomerMenuService.class);
        customerMenuService.getSpecialOffers().enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    specialOffersAdapter.setMenuItems(response.body());
                } else {
                    Toast.makeText(getContext(), "Failed to load special offers", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}