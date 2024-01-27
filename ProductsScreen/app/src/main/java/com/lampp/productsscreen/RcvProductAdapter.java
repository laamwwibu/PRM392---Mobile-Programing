package com.lampp.productsscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RcvProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> data;

    public RcvProductAdapter(List<Product> products) {
        this.data = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutinflater.inflate(R.layout.products_item_layout, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = data.get(position);
        holder.setData(product);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
