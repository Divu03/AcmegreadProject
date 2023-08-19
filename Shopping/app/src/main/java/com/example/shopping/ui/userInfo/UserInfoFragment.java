package com.example.shopping.ui.userInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shopping.HomeActivity;
import com.example.shopping.R;
import com.example.shopping.databinding.FragmentUserInfoBinding;

public class UserInfoFragment extends Fragment {

    private FragmentUserInfoBinding binding;
    private SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button bhome = binding.bHome;
        TextView uname = binding.unameUIP;
        TextView uemail = binding.uemailUIP;

        preferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String userEmail = preferences.getString("userEmail", "user@gamil.com"); // Provide a default value
        String userName = preferences.getString("userName", "app user"); // Provide a default value

        uname.setText(userName);
        uemail.setText(userEmail);

        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_home);
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
