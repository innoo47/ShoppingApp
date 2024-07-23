package com.inu.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.inu.shoppingapp.Data.UserBean;
import com.inu.shoppingapp.Data.UserDBHelper;

public class RegisterActivity extends AppCompatActivity {

    private static final String EMPTY_STRING = "";

    private ImageView btnBack;
    private Button btnRegister;
    private EditText insertId;
    private EditText insertPw;
    private EditText insertUsername;
    private EditText insertEmail;
    private Spinner insertGender;
    private EditText insertBirthday;

    private UserDBHelper dbHelper;
    private ArrayAdapter adapter;
    private String selectGender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        dbHelper = UserDBHelper.getInstance(getApplicationContext());

        insertId = (EditText) findViewById(R.id.insertId);
        insertPw = (EditText) findViewById(R.id.insertPw);
        insertUsername = (EditText) findViewById(R.id.insertUsername);
        insertEmail = (EditText) findViewById(R.id.insertEmail);
        insertGender = (Spinner) findViewById(R.id.gender_spinner);
        insertBirthday = (EditText) findViewById(R.id.insertBirth);

        // 성별 선택 스피너
        adapter = ArrayAdapter.createFromResource(this, R.array.genderArray, android.R.layout.simple_spinner_dropdown_item);
        insertGender.setAdapter(adapter);
        insertGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectGender = insertGender.getSelectedItem().toString();
                if(selectGender.equals("선택")) {
                    selectGender = EMPTY_STRING;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 뒤로가기 버튼
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setInsertEmpty();

                Intent intent = new Intent(RegisterActivity.this, com.inu.shoppingapp.LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return false;
            }
        });

        // 회원가입 버튼
        btnRegister = (Button) findViewById(R.id.register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 적절하게 작성했는지 확인 후 DB에 저장
                insertUserData(view);

                setInsertEmpty();

                Intent intent = new Intent(RegisterActivity.this, com.inu.shoppingapp.LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    // 회원가입 유저 정보 DB 저장 메서드
    public void insertUserData(View v) {
        if(isEmpty()) { // 비어있는 칸 없는지 확인. 있으면 true 반환
            return;
        } else if(isDuplicateId()) { // ID값 중복된 것 있는지 확인. 있으면 true 반환
            Toast.makeText(this, "이미 있는 ID 입니다. ID를 변경해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Email 포맷 체크, %@% 아니면 DB에 안들어감.

        // DB에 값 집어넣기
        UserBean userBean = new UserBean();

        userBean.setName(String.valueOf(insertUsername.getText()));
        userBean.setEmail(String.valueOf(insertEmail.getText()));
        userBean.setId(String.valueOf(insertId.getText()));
        userBean.setPassword(String.valueOf(insertPw.getText()));
        userBean.setGender(String.valueOf(selectGender));
        userBean.setYears(String.valueOf(insertBirthday.getText()));

        dbHelper.insertUser(userBean);
        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    // 데이터 공백 체크 메서드
    private boolean isEmpty() {
        if(TextUtils.isEmpty(insertUsername.getText())) {
            Toast.makeText(this, "닉네임이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(TextUtils.isEmpty(insertEmail.getText())) {
            Toast.makeText(this, "이메일이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(TextUtils.isEmpty(insertId.getText())) {
            Toast.makeText(this, "아이디가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(TextUtils.isEmpty(insertPw.getText())) {
            Toast.makeText(this, "비밀번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(TextUtils.isEmpty(selectGender)) {
            Toast.makeText(this, "성별이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(TextUtils.isEmpty(insertBirthday.getText())) {
            Toast.makeText(this, "생년월일이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    // ID 중복 체크 메서드
    private boolean isDuplicateId() {
        String dbId = dbHelper.getUserId(String.valueOf(insertId.getText()));

        if(dbId.isEmpty()) {
            return false;
        }
        return true;
    }

    // 입력창 초기화 메서드
    public void setInsertEmpty() {
        insertId.setText("");
        insertPw.setText("");
        insertUsername.setText("");
        insertEmail.setText("");
        insertBirthday.setText("");
    }

}