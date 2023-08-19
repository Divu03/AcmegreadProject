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
    private final Context context;
    private final List<Product> productList;
    private final CartViewModel cartViewModel;

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
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.productImage = convertView.findViewById(R.id.product_image);
            viewHolder.productName = convertView.findViewById(R.id.product_name);
            viewHolder.productDescription = convertView.findViewById(R.id.product_description);
            viewHolder.productPrice = convertView.findViewById(R.id.product_price);
            viewHolder.addToCart = convertView.findViewById(R.id.add_to_cart);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = productList.get(position);

        viewHolder.productImage.setImageResource(product.getImageResource());
        viewHolder.productName.setText(product.getName());
        viewHolder.productDescription.setText(product.getDescription());
        viewHolder.productPrice.setText("Price: ₹" + product.getPrice());

        viewHolder.addToCart.setBackgroundResource(R.drawable.selector_cart);

        viewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!product.isInCart()) {
                    cartViewModel.addToCart(product);
                    product.setInCart(true);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                    viewHolder.addToCart.setImageResource(R.drawable.ic_cart_added);
                    viewHolder.addToCart.invalidate();
                } else {
                    Toast.makeText(context, "Already in cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView productPrice;
        ImageView addToCart;
    }


}
