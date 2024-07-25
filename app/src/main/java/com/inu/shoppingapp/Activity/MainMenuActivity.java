package com.inu.shoppingapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private com.inu.shoppingapp.MainFragment mainFragment = new com.inu.shoppingapp.MainFragment();
    private com.inu.shoppingapp.BasketFragment basketFragment = new com.inu.shoppingapp.BasketFragment();
    private com.inu.shoppingapp.MyPageFragment myPageFragment = new com.inu.shoppingapp.MyPageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        // main view 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, mainFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNav = findViewById(R.id.menu_bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new ItemSelectListener());
    }

    private class ItemSelectListener implements BottomNavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            int itemId = menuItem.getItemId();
            if (itemId == R.id.navigation_mainpage) {
                transaction.replace(R.id.menu_frame_layout, mainFragment).commitAllowingStateLoss();
            } else if (itemId == R.id.navigation_basket) {
                transaction.replace(R.id.menu_frame_layout, basketFragment).commitAllowingStateLoss();
            } else if (itemId == R.id.navigation_mypage) {
                transaction.replace(R.id.menu_frame_layout, myPageFragment).commitAllowingStateLoss();
            }
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAffinity(this); // app 종료
    }
}