package com.example.shopping.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shopping.Product;
import com.example.shopping.ProductAdapter;
import com.example.shopping.R;
import com.example.shopping.databinding.FragmentHomeBinding;
import com.example.shopping.ui.cart.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        ListView productListView = binding.productListView;

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.black_long_sleev_tee, "Long Sleeve", "black,Game Over", 999));
        productList.add(new Product(R.drawable.royal_longs_leev_tee, "Long Sleeve", "royal,Game Over", 1499));
        productList.add(new Product(R.drawable.red_premium_pullover_hoodie, "Hoodie", "red,Game Over", 2499));
        productList.add(new Product(R.drawable.nevy_womans_classic_t, "Woman Classic", "navy,Fire", 1199));
        productList.add(new Product(R.drawable.classic_long_sleev_t, "Long Sleeve Classic", "white,Fire", 1699));
        // Add more products as needed

        ProductAdapter productAdapter = new ProductAdapter(requireContext(), productList, cartViewModel);
        productListView.setAdapter(productAdapter);

        productListView.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = productList.get(position);
            // Add the selected product to the cart using CartViewModel
            cartViewModel.addToCart(selectedProduct);
            // Show a toast indicating successful addition to cart
            Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
