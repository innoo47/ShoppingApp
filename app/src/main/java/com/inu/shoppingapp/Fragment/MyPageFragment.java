package com.inu.shoppingapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.inu.shoppingapp.Data.UserBean;
import com.inu.shoppingapp.Data.UserDBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageFragment extends Fragment {

    private UserDBHelper dbHelper;
    private UserBean userbean;

    private TextView logout;
    private TextView deleteUser;
    private TextView username;
    private TextView id;
    private TextView email;
    private TextView gender;
    private TextView birthday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        dbHelper = UserDBHelper.getInstance(getActivity());

        String userId = com.inu.shoppingapp.SharedPreferencesManager.getLoginInfo(getActivity());
        id = (TextView) view.findViewById(R.id.id);
        id.setText(userId);

        String dbUsername = dbHelper.getUsername(userId);
        username = (TextView) view.findViewById(R.id.username);
        username.setText(dbUsername);

        String dbUserEmail = dbHelper.getUserEmail(userId);
        email = (TextView) view.findViewById(R.id.email);
        email.setText(dbUserEmail);

        String dbUserGender = dbHelper.getUserGender(userId);
        gender = (TextView) view.findViewById(R.id.gender);
        gender.setText(dbUserGender);

        String dbUserBirthday = dbHelper.getUserBirthday(userId);
        birthday = (TextView) view.findViewById(R.id.birthday);
        birthday.setText(dbUserBirthday);

        logout = (TextView) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "BTN Click");
                com.inu.shoppingapp.SharedPreferencesManager.clearPreferences(getActivity());   // Preference 삭제
                com.inu.shoppingapp.SharedPreferencesManager.clearBasketPreferences(getActivity());
                Intent intent = new Intent(getActivity(), com.inu.shoppingapp.LoginActivity.class); // Fragment라서 getActivity()를 사용
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 로그인 화면으로 이동
                startActivity(intent);
            }
        });

        deleteUser = (TextView) view.findViewById(R.id.deleteUser);
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog를 띄워 경고창을 출력

                // 회원 삭제 메서드
                dbHelper.deleteUser(userbean);

                com.inu.shoppingapp.SharedPreferencesManager.clearPreferences(getActivity());   // Preference 삭제
                com.inu.shoppingapp.SharedPreferencesManager.clearBasketPreferences(getActivity());
                Intent intent = new Intent(getActivity(), com.inu.shoppingapp.LoginActivity.class); // Fragment라서 getActivity()를 사용
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 로그인 화면으로 이동
                startActivity(intent);
            }
        });

        return view;
    }

}