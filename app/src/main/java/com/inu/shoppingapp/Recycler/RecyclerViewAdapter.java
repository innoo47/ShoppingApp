package com.inu.shoppingapp.Recycler;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inu.shoppingapp.Data.ProductBean;
import com.inu.shoppingapp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    private ArrayList<ProductBean> listData = new ArrayList<>();
    private BasketRecyclerViewAdapter basketAdapter;
    private ItemClickListener listener;

    private ImageView btnAdd;

    public RecyclerViewAdapter(ArrayList<ProductBean> listData) {
        this.listData = listData;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<ProductBean> listData){
        this.listData = listData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ProductBean item = listData.get(position);
        holder.image.setImageDrawable(getImage(item.getImage()));
        holder.title.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("클릭", "클릭클릭" + item.getId());

            }
        });
    }

    // 이미지 비트맵
    public Drawable getImage(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Drawable drawable = new BitmapDrawable(null, bitmap);
        return drawable;
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView price;

        public Holder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.productImage);
            title = (TextView) itemView.findViewById(R.id.productName);
            price = (TextView) itemView.findViewById(R.id.productPrice);
            btnAdd = (ImageView) itemView.findViewById(R.id.btnAdd);

        }
    }
}
