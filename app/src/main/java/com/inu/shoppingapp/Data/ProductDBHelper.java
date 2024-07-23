//package com.inu.shoppingapp.Data;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//
//import java.io.ByteArrayOutputStream;
//import java.util.ArrayList;
//
//public class ProductDBHelper extends SQLiteOpenHelper {
//
//    private static ProductDBHelper dbHelper = null;
//
//    private static final String DATABASE_NAME = "productdb";
//    private static final String TABLE_NAME = "product";
//    private static final int DB_VERSION = 1;
//
//    public static final String COL_0 = "serialNumber";
//    public static final String COL_1 = "id";
//    public static final String COL_2 = "name";
//    public static final String COL_3 = "price";
//    public static final String COL_4 = "image";
//    public static final String COL_5 = "type";
//
//    private Context mContext;
//
//    public static ProductDBHelper getInstance(Context context){
//        if(dbHelper == null){
//            dbHelper = new ProductDBHelper(context.getApplicationContext());
//        }
//
//        return dbHelper;
//    }
//
//    private ProductDBHelper(Context context){
//        super(context, DATABASE_NAME, null, DB_VERSION);
//        this.mContext = context;
//
//        deleteAllProduct();
//        initProduct();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String sql = "create table " + TABLE_NAME + " ( "
//                + COL_0 + " integer primary key autoincrement, "
//                + COL_1 + " integer unique, "
//                + COL_2 + " text not null, "
//                + COL_3 + " integer, "
//                + COL_4 + " blob, "
//                + COL_5 + " text not null "
//                + ")";
//
//        db.execSQL(sql);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "drop table " + TABLE_NAME;
//        db.execSQL(sql);
//        onCreate(db);
//    }
//
//    public long insertProduct(ProductBean product){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(COL_1, product.getId());
//        values.put(COL_2, product.getName());
//        values.put(COL_3, product.getPrice());
//        values.put(COL_4, product.getImage());
//        values.put(COL_5, product.getType());
//
//        return db.insert(TABLE_NAME, null, values);
//    }
//
//    public ArrayList<ProductBean> getAllProduct() {
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
//        ArrayList<ProductBean> result = new ArrayList<>();
//
//        while (cursor.moveToNext()) {
//            ProductBean product = new ProductBean();
//
//            product.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
//            product.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
//            product.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
//            product.setPrice(cursor.getInt(cursor.getColumnIndex(COL_3)));
//            product.setImage(cursor.getBlob(cursor.getColumnIndex(COL_4)));
//            product.setType(cursor.getString(cursor.getColumnIndex(COL_5)));
//
//            result.add(product);
//        }
//
//        return result;
//    }
//
//    public ArrayList<ProductBean> getRandomProduct(){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME  + " order by random() limit 6 ", null );
//        ArrayList<ProductBean> result = new ArrayList<>();
//
//        while (cursor.moveToNext()) {
//            ProductBean product = new ProductBean();
//
//            product.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
//            product.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
//            product.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
//            product.setPrice(cursor.getInt(cursor.getColumnIndex(COL_3)));
//            product.setImage(cursor.getBlob(cursor.getColumnIndex(COL_4)));
//            product.setType(cursor.getString(cursor.getColumnIndex(COL_5)));
//
//            result.add(product);
//        }
//
//        return result;
//    }
//
//    public ArrayList<ProductBean> getProductbyType(String type){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, null, "type = ?", new String[] {type.toLowerCase()}, null, null, null);
//        ArrayList<ProductBean> result = new ArrayList<>();
//
//        while (cursor.moveToNext()) {
//            ProductBean product = new ProductBean();
//
//            product.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
//            product.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
//            product.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
//            product.setPrice(cursor.getInt(cursor.getColumnIndex(COL_3)));
//            product.setImage(cursor.getBlob(cursor.getColumnIndex(COL_4)));
//            product.setType(cursor.getString(cursor.getColumnIndex(COL_5)));
//
//            result.add(product);
//        }
//
//        return result;
//    }
//
//    public long deleteAllProduct(){
//        SQLiteDatabase db = getWritableDatabase();
//        return db.delete(TABLE_NAME, null, null);
//    }
//
//    private void initProduct(){
//        init("product", 1, "청자켓", 73000, getByteArrayFromDrawable(R.drawable.top_blue_jacket), "top");
//        init("product", 2,"진청바지", 56000, getByteArrayFromDrawable(R.drawable.bottom_deepblue_jean), "bottom");
//        init("product", 3, "무스탕", 130000, getByteArrayFromDrawable(R.drawable.top_black_mustang), "top");
//        init("product", 4, "신발", 69000, getByteArrayFromDrawable(R.drawable.black_shoes), "acc");
//    }
//
//    private void init(String tableName, int id, String pName, int pPrice, byte[] pImage, String type){
//        ProductBean productBean = new ProductBean();
//
//        productBean.setId(id);
//        productBean.setName(pName);
//        productBean.setPrice(pPrice);
//        productBean.setImage(pImage);
//        productBean.setType(type);
//
//        insertProduct(productBean);
//    }
//
//    // drawable 이미지를 sqlite에 넣기 위해 byteArray로 변환하는 함수
//    private byte[] getByteArrayFromDrawable(int image){
//        Drawable drawable = mContext.getDrawable(image);
//        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] dataByte = stream.toByteArray();
//
//        return dataByte;
//    }
//}