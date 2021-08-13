package com.kplo.scheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity2 extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText edittext_password;
    private EditText edittext_password2;
    private Button btn_Sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String email = bundle.getString("email");

        firebaseAuth = FirebaseAuth.getInstance();
        edittext_password = (EditText) findViewById(R.id.edittext_password);

        btn_Sign_up = (Button) findViewById(R.id.btn_Sign_up);
        btn_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edittext_password.getText().toString().equals("")) {
                    // 이메일과 비밀번호가 공백이 아닌 경우
                    createUser(email, edittext_password.getText().toString());
                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(SignUpActivity2.this, "비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공시
                            Toast.makeText(SignUpActivity2.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(SignUpActivity2.this, LoginActivity.class);

                            startActivity(intent);
                        } else {
                            // 계정이 중복된 경우
                            Toast.makeText(SignUpActivity2.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}