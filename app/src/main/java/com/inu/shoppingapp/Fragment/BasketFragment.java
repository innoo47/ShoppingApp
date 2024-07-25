package com.inu.shoppingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.inu.shoppingapp.Data.BasketBean;
import com.inu.shoppingapp.Data.ProductDBHelper;
import com.inu.shoppingapp.Recycler.BasketRecyclerViewAdapter;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private View view;

    public RecyclerView mRecyclerView;
    public BasketRecyclerViewAdapter mRecyclerAdapter;
    public ArrayList<BasketBean> items = new ArrayList<>();
    private ProductDBHelper dbHelper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_basket, container, false);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView = view.findViewById(R.id.basketRecycler);
//        mRecyclerView.setLayoutManager(layoutManager);
//        items.add(new BasketBean(R.drawable.top2, "바이오 웨스턴 데님 반팔 셔츠_인디고", 28200));
//        mRecyclerAdapter = new BasketRecyclerViewAdapter(items);
//        mRecyclerView.setAdapter(mRecyclerAdapter);

        return view;
    }



}