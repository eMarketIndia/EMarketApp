package com.example.emarket.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emarket.R;

public class ProductListViewModel extends RecyclerView.Adapter<ProductListViewModel.NoProductViewHolder> {

    private MutableLiveData<String> mText;
    private Context ctx;

    public ProductListViewModel(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public NoProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.fragment_home_second,null);
        return new NoProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoProductViewHolder holder, int position) {
        holder.displayText.setText("Add Products by Clicking + icon");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public LiveData<String> getText() {
        return mText;
    }

    class NoProductViewHolder extends RecyclerView.ViewHolder{

        private TextView displayText;

        public NoProductViewHolder(@NonNull View itemView) {
            super(itemView);
            displayText = itemView.findViewById(R.id.text_home);
        }
    }
}