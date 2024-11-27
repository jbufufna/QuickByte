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
import com.example.quickbyte.Common.adapters.CategoryAdapter;
import com.example.quickbyte.Common.adapters.MenuItemAdapter;
import com.example.quickbyte.Common.managers.CartManager;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.CustomerHomePageBinding;
import com.example.quickbyte.API.DTO.MenuCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerHomePageFragment extends Fragment {

    private CustomerHomePageBinding binding;
    private CategoryAdapter categoryAdapter;
    private MenuItemAdapter popularItemsAdapter;

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

        // Navigation to Cart
        binding.btnGoToCart.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_customerHomePageFragment_to_customerPlaceOrderFragment)
        );

        // Navigation to Account
        binding.btnGoToAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_customerHomePageFragment_to_customerAccountInformationFragment)
        );
    }

    private void setupRecyclerViews() {
        // Set up RecyclerView for Categories
        binding.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(this::fetchItemsByCategory);
        binding.recyclerViewCategories.setAdapter(categoryAdapter);

        // Set up RecyclerView for Popular Items
        binding.recyclerViewPopularItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularItemsAdapter = new MenuItemAdapter((item, quantityChange) -> {
            // Handle cart actions here
            CartManager.getInstance().updateItemQuantity(item, quantityChange);
            popularItemsAdapter.notifyDataSetChanged();
        });
        binding.recyclerViewPopularItems.setAdapter(popularItemsAdapter);
    }

    private void fetchMenuCategories() {
        ICustomerMenuService customerMenuService = ApiClient.getRetrofitInstance().create(ICustomerMenuService.class);
        customerMenuService.getMenuCategories().enqueue(new Callback<List<MenuCategory>>() {
            @Override
            public void onResponse(Call<List<MenuCategory>> call, Response<List<MenuCategory>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryAdapter.setCategories(response.body());
                } else {
                    Toast.makeText(getContext(), "Failed to load categories.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuCategory>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchItemsByCategory(int categoryId) {
        ICustomerMenuService customerMenuService = ApiClient.getRetrofitInstance().create(ICustomerMenuService.class);
        customerMenuService.getItemsByCategory(categoryId).enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    popularItemsAdapter.setMenuItems(response.body());
                } else {
                    Toast.makeText(getContext(), "Failed to load items for the category.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchPopularItems() {
        ICustomerMenuService customerMenuService = ApiClient.getRetrofitInstance().create(ICustomerMenuService.class);
        customerMenuService.getPopularItems().enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    popularItemsAdapter.setMenuItems(response.body());
                } else {
                    Toast.makeText(getContext(), "No popular items found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Error loading popular items: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}