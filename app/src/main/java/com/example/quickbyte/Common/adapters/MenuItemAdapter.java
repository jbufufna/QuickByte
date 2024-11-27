package com.example.quickbyte.Common.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.R;
import java.util.ArrayList;
import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {

    private List<MenuItem> menuItems = new ArrayList<>();

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
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
        MenuItem menuItem = menuItems.get(position);

        holder.name.setText(menuItem.getName());
        holder.price.setText(String.format("$%.2f", menuItem.getPrice()));
        holder.description.setText(menuItem.getDescription());

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(menuItem.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    static class MenuItemViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, description;
        ImageView imageView;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textItemName);
            price = itemView.findViewById(R.id.textItemPrice);
            description = itemView.findViewById(R.id.textItemDescription);
            imageView = itemView.findViewById(R.id.imageItem);
        }
    }
}