package com.example.day5_sharedpreferences;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("LOGIN");

        final TextInputLayout id_Layout = findViewById(R.id.TextInput_ID);
        final TextInputLayout pw_Layout = findViewById(R.id.TextInput_PW);
        final TextInputEditText id_ET = findViewById(R.id.edit_id);
        final TextInputEditText pw_ET = findViewById(R.id.edit_pw);
        MaterialButton sign_in_btn = findViewById(R.id.signInBtn);
        MaterialButton sign_up_btn = findViewById(R.id.signUpBtn);

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_id = id_ET.getText().toString().replace(" ", "");
                String input_pw = pw_ET.getText().toString().replace(" ", "");  // 공백 처리

                SharedPreferences pref = getSharedPreferences("LOGIN_list", 0);
                String pw = pref.getString(input_id, "");

                if (pw.length() <= 0) {   // ID가 존재하지 않을 때
                    id_Layout.setError("'" + input_id + "' is not existed");
                }else{
                    id_Layout.setErrorEnabled(false);
                    if (pw.equals(input_pw)) {  // 두 String 비교
                        pw_Layout.setErrorEnabled(false);
                        Toast.makeText(MainActivity.this, input_id + "님, 환영합니다! (" + pw + ")", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        pw_Layout.setError("PW is wrong");
                    }
                }
                if (input_id.length() <= 0)
                    id_Layout.setError("ID is NULL");
                if (input_pw.length() <= 0)
                    pw_Layout.setError("PW is NULL");
            }
        });

        // sign up 버튼 누르면 화면 이동
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
}
