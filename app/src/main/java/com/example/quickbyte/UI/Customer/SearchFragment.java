package com.example.quickbyte.UI.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.widget.SearchView; // Correct SearchView import

import com.example.quickbyte.API.ApiClient;
import com.example.quickbyte.API.IServices.ICustomerMenuService;
import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.Common.adapters.MenuItemAdapter;
import com.example.quickbyte.R;
import com.example.quickbyte.databinding.FragmentSearchBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private MenuItemAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView
        binding.recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        searchAdapter = new MenuItemAdapter((item, quantity) -> {
            // Handle menu item click actions
            Toast.makeText(getContext(), item.getName() + " clicked!", Toast.LENGTH_SHORT).show();
        });
        binding.recyclerViewSearchResults.setAdapter(searchAdapter);

        // Setup SearchView
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMenuItems(query); // Perform search when the user submits a query
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false; // Optional: Handle query text changes
            }
        });
    }

    private void searchMenuItems(String query) {
        ICustomerMenuService customerMenuService = ApiClient.getRetrofitInstance().create(ICustomerMenuService.class);
        customerMenuService.searchMenuItems(query).enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    searchAdapter.setMenuItems(response.body());
                } else {
                    Toast.makeText(getContext(), "No results found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}