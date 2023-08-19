package com.example.shopping;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.shopping.ui.cart.CartViewModel;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;
    private CartViewModel cartViewModel;

    public ProductAdapter(Context context, List<Product> productList, CartViewModel cartViewModel) {
        this.context = context;
        this.productList = productList;
        this.cartViewModel = cartViewModel;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false);

        Product product = productList.get(position);

        ImageView productImage = convertView.findViewById(R.id.product_image);
        TextView productName = convertView.findViewById(R.id.product_name);
        TextView productDescription = convertView.findViewById(R.id.product_description);
        TextView productPrice = convertView.findViewById(R.id.product_price);
        ImageView addToCart = convertView.findViewById(R.id.add_to_cart);

        productImage.setImageResource(product.getImageResource());
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText("Price: ₹" + product.getPrice());

        addToCart.setBackgroundResource(R.drawable.selector_cart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!product.isInCart()) {
                    cartViewModel.addToCart(product);
                    product.setInCart(true);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Already in cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

}
