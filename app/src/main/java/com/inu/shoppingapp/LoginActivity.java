package com.inu.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    private EditText id;
    private EditText pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 만약 editText에 id와 pw가 DB에 저장된 값과 같다면 mainActivity로 이동
                // 일치하지 않는다면 토스트를 띄우며 editText를 초기화
                String userId = id.getText().toString().trim();
                String userPw = pw.getText().toString().trim();

                if(!userId.isEmpty()) {
                    if(!userPw.isEmpty()) {
                        // 임의 설정
                        if(userId.equals("user") && userPw.equals("user")) {
                            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                            startActivity(intent);
                            setEditTextEmpty();
                        } else {
                            // 토스트 출력
                            setEditTextEmpty();
                        }
                    }
                }

            }
        });



        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 뷰로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



    }


    // EditText 비우기
    public void setEditTextEmpty() {
        id.setText("");
        id.setText("");
    }
}
