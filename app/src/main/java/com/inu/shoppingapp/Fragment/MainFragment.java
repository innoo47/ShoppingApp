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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("로그", "onCreateView");
        view = inflater.inflate(R.layout.fragment_main, container, false);

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

//    @SuppressLint("NotifyDataSetChanged")
//    private void setData() {
//        items.add(new ProductBean("블러리 로고 반팔티셔츠 [블랙]", 23000, R.drawable.top1));
//        items.add(new ProductBean("바이오 웨스턴 데님 반팔 셔츠_인디고", 23000, R.drawable.top2));
//        items.add(new ProductBean("TAG OG TEE - WHITE", 35100, R.drawable.top3));
//        items.add(new ProductBean("Deep One Tuck Sweat Shorts [Grey]", 28800, R.drawable.bottom1));
//        items.add(new ProductBean("바이오워싱 카펜터 버뮤다 데님 팬츠_라이트블루", 24400, R.drawable.bottom2));
//        items.add(new ProductBean( "데미지 워시드 데님 팬츠-미디엄 블루(Cool Air)", 39800, R.drawable.bottom3));
//        items.add(new ProductBean("와이드 데님 팬츠 [미디엄 블루]", 23000, R.drawable.bottom4));
//
//    }

}