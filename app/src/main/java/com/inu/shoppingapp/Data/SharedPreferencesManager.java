package com.inu.shoppingapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String PREFERENCES_NAME = "UserInfo";

    public static SharedPreferences getPreferences(Context mContext){
        return mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void clearPreferences(Context context){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static void setLoginInfo(Context context, String id){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id", id);
        editor.apply();
    }

    public static String getLoginInfo(Context context){
        SharedPreferences prefs = getPreferences(context);
        String id = prefs.getString("id", "");
        return id;
    }

    private static final String PREFERENCES_BASKET = "BasketInfo";

    public static SharedPreferences getBasketPreferences(Context mContext){
        return mContext.getSharedPreferences(PREFERENCES_BASKET, Context.MODE_PRIVATE);
    }

    public static void clearBasketPreferences(Context context){
        SharedPreferences prefsBasket = getBasketPreferences(context);
        SharedPreferences.Editor editorBasket = prefsBasket.edit();
        editorBasket.clear();
        editorBasket.apply();
    }

    public static void setBasketInfo(Context context, String productID){
        SharedPreferences prefsBasket = getBasketPreferences(context);
        SharedPreferences.Editor editorBasket = prefsBasket.edit();
        editorBasket.putString("productID", productID);
        editorBasket.apply();
    }

    public static String getBasketInfo(Context context){
        SharedPreferences prefsBasket = getBasketPreferences(context);
        String productID = prefsBasket.getString("productID", "");
        return productID;
    }

}
