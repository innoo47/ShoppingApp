package com.inu.shoppingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inu.shoppingapp.Data.ProductBean;
import com.inu.shoppingapp.Data.ProductDBHelper;
import com.inu.shoppingapp.Recycler.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainFragment extends Fragment  {

    private View view;

    public RecyclerView mRecyclerView;
    public RecyclerViewAdapter mRecyclerAdapter;
    public ArrayList<ProductBean> items = new ArrayList<>();
    private ProductDBHelper dbHelper;

    private Button top;
    private Button bottom;

    private boolean btnTopState = false;
    private boolean btnBottomState = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("로그", "onCreateView");
        view = inflater.inflate(R.layout.fragment_main, container, false);
        top = (Button) view.findViewById(R.id.btnCatTop);
        bottom = (Button) view.findViewById(R.id.btnCatBottom);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnTopState) {
                    showProduct();
                    btnTopState = false;
                    btnBottomState = false;
                } else {
                    showProduct("top");
                    btnTopState = true;
                    btnBottomState = false;
                }
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnBottomState) {
                    showProduct();
                    btnTopState = false;
                    btnBottomState = false;
                } else {
                    showProduct("bottom");
                    btnBottomState = true;
                    btnTopState = false;
                }
            }
        });

        showProduct();

        return view;
    }

    private void showProduct() {
        dbHelper = ProductDBHelper.getInstance(getContext());
        items = dbHelper.getAllProduct();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerAdapter = new RecyclerViewAdapter(items);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    private void showProduct(String type) {
        items.clear();
        items = dbHelper.getProductbyType(type);
        mRecyclerAdapter.updateData(items);
    }

}