package com.example.quickbyte.Common.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.Common.managers.CartManager;
import com.example.quickbyte.R;

import java.util.ArrayList;
import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {

    private final List<MenuItem> menuItems;
    private final OnCartActionClickListener listener;

    public MenuItemAdapter(OnCartActionClickListener listener) {
        this.menuItems = new ArrayList<>();
        this.listener = listener;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems.clear();
        this.menuItems.addAll(menuItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);

        // Set item data
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText(String.format("$%.2f", item.getPrice()));

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .placeholder(R.drawable.placeholder_image) // Make sure this exists in 'res/drawable'
                .error(R.drawable.error_image) // Make sure this exists in 'res/drawable'
                .into(holder.imageView);

        // Handle cart actions (add or update quantity)
        int quantity = CartManager.getInstance().getItemQuantity(item);
        holder.addToCartButton.setVisibility(quantity == 0 ? View.VISIBLE : View.GONE);
        holder.quantityLayout.setVisibility(quantity > 0 ? View.VISIBLE : View.GONE);
        holder.quantity.setText(String.valueOf(quantity));

        holder.addToCartButton.setOnClickListener(v -> listener.onCartActionClick(item, 1));
        holder.incrementButton.setOnClickListener(v -> listener.onCartActionClick(item, 1));
        holder.decrementButton.setOnClickListener(v -> listener.onCartActionClick(item, -1));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, price, quantity;
        ImageView imageView;
        Button addToCartButton, incrementButton, decrementButton;
        View quantityLayout;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textItemName);
            description = itemView.findViewById(R.id.textItemDescription);
            price = itemView.findViewById(R.id.textItemPrice);
            imageView = itemView.findViewById(R.id.imageItem);
            addToCartButton = itemView.findViewById(R.id.buttonAddToCart);
            incrementButton = itemView.findViewById(R.id.buttonIncrement);
            decrementButton = itemView.findViewById(R.id.buttonDecrement);
            quantityLayout = itemView.findViewById(R.id.layoutQuantity);
            quantity = itemView.findViewById(R.id.textQuantity);
        }
    }

    public interface OnCartActionClickListener {
        void onCartActionClick(MenuItem item, int quantityChange);
    }
}