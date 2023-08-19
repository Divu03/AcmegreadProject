
package com.example.shopping.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shopping.CartProductAdapter;
import com.example.shopping.Product;
import com.example.shopping.R;
import com.example.shopping.databinding.FragmentCartBinding;

import java.util.List;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        ListView cartListView = binding.cartListView;
        List<Product> cartItems = cartViewModel.getCartItems().getValue();

        CartProductAdapter cartAdapter = new CartProductAdapter(requireContext(), cartItems);
        cartListView.setAdapter(cartAdapter);

        binding.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> cartItems = cartViewModel.getCartItems().getValue();

                if (cartItems == null || cartItems.isEmpty()) {
                    Toast.makeText(requireContext(), "Nothing to Order", Toast.LENGTH_SHORT).show();
                } else {
                    cartViewModel.clearCart();
                    cartAdapter.notifyDataSetChanged();
                    Toast.makeText(requireContext(), "Order placed", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager().popBackStack(); // This will pop the current fragment
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
