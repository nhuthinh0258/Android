package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitaplon.database.SQLite;
import com.google.android.material.button.MaterialButton;

public class ActivityLogin extends AppCompatActivity {

    EditText username,password;
    Button btnlogin,btnregister;
    SQLite database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        btnlogin =findViewById(R.id.loginbtn);
        btnregister=findViewById(R.id.registerbtn);
        database = new SQLite(this);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,ActivityRegister.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gán cho các biến có giá trị nhập từ edittext
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("1524658a"))
                {
                    Toast.makeText(ActivityLogin.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ActivityLogin.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}