package com.inu.shoppingapp.Recycler;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inu.shoppingapp.Data.BasketBean;
import com.inu.shoppingapp.R;

import java.util.ArrayList;

public class BasketRecyclerViewAdapter extends RecyclerView.Adapter<BasketRecyclerViewAdapter.Holder> {

    private ArrayList<BasketBean> listData = new ArrayList<>();

    public BasketRecyclerViewAdapter(ArrayList<BasketBean> listData) { this.listData = listData; }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<BasketBean> listData) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_basket, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        BasketBean item = listData.get(position);
//        holder.image.setImageDrawable(getImage(item.getImage()));
        holder.image.setImageResource(item.getImage());
        holder.title.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));

    }


    // 이미지 비트맵
//    public Drawable getImage(byte[] bytes){
//        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//        Drawable drawable = new BitmapDrawable(null, bitmap);
//        return drawable;
//    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView price;

        public Holder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.productImage);
            title = (TextView) itemView.findViewById(R.id.productName);
            price = (TextView) itemView.findViewById(R.id.productPrice);
        }
    }
}
