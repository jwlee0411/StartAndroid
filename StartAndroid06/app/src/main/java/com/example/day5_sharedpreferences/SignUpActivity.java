package com.example.day5_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Collection;
import java.util.Iterator;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SIGN UP");
        actionBar.setDisplayHomeAsUpEnabled(true);

        final TextInputLayout id_Layout = findViewById(R.id.TextInput_ID);
        final TextInputLayout pw_Layout = findViewById(R.id.TextInput_PW);
        final TextInputEditText id_ET = findViewById(R.id.text_ID);
        final TextInputEditText pw_ET = findViewById(R.id.text_PW);

        MaterialButton makeBtn = findViewById(R.id.makeBtn);
        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_id = id_ET.getText().toString().replace(" ", "");
                String input_pw = pw_ET.getText().toString().replace(" ", "");

                SharedPreferences pref = getSharedPreferences("LOGIN_list", 0); //MODE_PRIVATE, 0 둘 다 같음
                // 모든 값 불러오기
//                Collection<?> col = pref.getAll().values();
//                Iterator<?> it = col.iterator();
//                while (it.hasNext()){
//                    String msg = it.next().toString();
//                    Log.d("Result", msg);
//                }
                String pw = pref.getString(input_id, "");
                //pw.trim(); input_id.trim(); input_pw.trim();
                // 입력한 ID에 따른 비밀번호 존재 여부 확인
                if (pw.length() <= 0) { // 없을 때
                    id_Layout.setErrorEnabled(false);
                    if (input_pw.length() >= 4) {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(input_id, input_pw);   // (key, value)
                        editor.commit();
                        Toast.makeText(SignUpActivity.this, "새 아이디를 생성했습니다!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{                  // 있을 때
                    Toast.makeText(SignUpActivity.this, "아이디가 이미 있습니다." + pw, Toast.LENGTH_SHORT).show();
                    id_Layout.setError("ID has already existed");
                }
                // 에러 처리
                if (input_id.length() <= 0)
                    id_Layout.setError("ID is NULL");
                if (input_pw.length() <= 0)
                    pw_Layout.setError("PW is NULL");
                else if (input_pw.length() < 4)
                    pw_Layout.setError("PW must be at least 4");
                else
                    pw_Layout.setErrorEnabled(false);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
