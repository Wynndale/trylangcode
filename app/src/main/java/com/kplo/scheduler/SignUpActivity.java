package com.kplo.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText editText_email;
    private Button btn_Continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        editText_email = (EditText) findViewById(R.id.editText_email);
        btn_Continue = findViewById(R.id.btn_Continue);

        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText_email.getText().toString().equals("") ) {
                    // 이메일이 공백이 아닌 경우
                    String email = editText_email.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), SignUpActivity2.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    // 이메일이 공백인 경우
                    Toast.makeText(SignUpActivity.this, "이메일을 입력하세요.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}