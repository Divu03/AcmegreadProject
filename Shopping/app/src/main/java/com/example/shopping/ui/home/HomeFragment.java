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
        productList.add(new Product(R.drawable.book1, "The Girl in Room 105", "Chetan Bhagat", 169));
        productList.add(new Product(R.drawable.book2, "Meluha", "Amish", 199));
        productList.add(new Product(R.drawable.book3, "Harry Porter 1", "J.K.Rowling", 299));
        productList.add(new Product(R.drawable.book4, "Ram", "Amish", 199));
        productList.add(new Product(R.drawable.e1, "Galaxy s23 ultra", "samsung", 119699));
        productList.add(new Product(R.drawable.e2, "Foldable 5", "samsung", 129699));
        productList.add(new Product(R.drawable.e3, "VivoBook", "ASUS", 291699));
        productList.add(new Product(R.drawable.e4, "Radio", "Retro", 591699));
        productList.add(new Product(R.drawable.h1, "Fridge", "Harier", 1699));
        productList.add(new Product(R.drawable.h2, "Fan", "Rupa", 99));


        ProductAdapter productAdapter = new ProductAdapter(requireContext(), productList, cartViewModel);
        productListView.setAdapter(productAdapter);

        productListView.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = productList.get(position);
            cartViewModel.addToCart(selectedProduct);
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
