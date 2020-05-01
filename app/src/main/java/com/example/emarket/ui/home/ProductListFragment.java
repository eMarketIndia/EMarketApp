package com.example.emarket.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emarket.Product;
import com.example.emarket.ProductAdapter;
import com.example.emarket.R;
import com.example.emarket.SellerDashboard;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private ProductListViewModel homeViewModel;

    RecyclerView recyclerView;
    ProductAdapter productAdapter;

    List<Product> productList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(ProductListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        productList = new ArrayList<>();

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productList.add( new Product("Tomato"));
        productList.add((new Product("Lemon")));
        productList.add((new Product("Chilly")));
        productAdapter = new ProductAdapter(getContext(),productList);
        recyclerView.setAdapter(productAdapter);
        return root;
    }
}
