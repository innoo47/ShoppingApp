package com.inu.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.inu.shoppingapp.Data.UserDBHelper;

public class LoginActivity extends AppCompatActivity {

    private UserDBHelper dbHelper;

    private Button btnLogin;
    private Button btnRegister;
    private EditText id;
    private EditText pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        dbHelper = UserDBHelper.getInstance(getApplicationContext());

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
                        if(accountCheck()) {
                            com.inu.shoppingapp.SharedPreferencesManager.setLoginInfo(LoginActivity.this, userId);
                            setInsertEmpty();
                            Intent intent = new Intent(LoginActivity.this, com.inu.shoppingapp.MainMenuActivity.class);
                            startActivity(intent);
                        } else {
                            // "로그인 정보가 정확하지 않습니다" 토스트 출력
                            viewToast("로그인 정보가 정확하지 않습니다");
                            setInsertEmpty();
                        }
                    } else {
                        // "비밀번호를 입력해주세요" 토스트 출력
                        viewToast("비밀번호를 입력해주세요");
                        setInsertEmpty();
                    }
                } else {
                    // "아이디를 입력해주세요" 토스트 출력
                    viewToast("아이디를 입력해주세요");
                    setInsertEmpty();
                }
            }
        });

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 뷰로 이동
                Intent intent = new Intent(LoginActivity.this, com.inu.shoppingapp.RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    // 계정 체크 메서드
    private boolean accountCheck() {
        //db에 접근해서 id, pw 확인 후 일치 시 true, 불일치시 false return.
        String idValue = String.valueOf(id.getText());
        String pwValue = String.valueOf(pw.getText());

        if(idValue.isEmpty() || pwValue.isEmpty()) {
            return false;
        }

        String dbId = dbHelper.getUserId(idValue);
        String dbPw = dbHelper.getUserPw(idValue);

        if(dbId.equals(idValue)) {
            if(dbPw.equals(pwValue)) {
                viewToast("환영합니다.");
                return true;
            }
            viewToast("비밀번호를 확인해주세요.");
            return false;
        }

        setInsertEmpty();
        viewToast("존재하지 않는 아이디입니다.");
        return false;
    }

    // 입력창 초기화 메서드
    public void setInsertEmpty() {
        id.setText("");
        pw.setText("");
    }

    // 토스트 출력 메서드
    public void viewToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
