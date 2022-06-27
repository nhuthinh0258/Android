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
                String tentaikhoan = username.getText().toString();
                String matkhau = password.getText().toString();

                //Sử dụng con trỏ để getdata từ SQLite
                Cursor cursor = database.getData();

                //Lấy dữ liệu gán vào biến, tài khoản ở ô 1, mật khẩu ô 2, ô 0 là id
                while (cursor.moveToNext())
                {
                    String datataikhoan= cursor.getString(1);
                    String datamatkhau= cursor.getString(2);

                    if(datataikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau))
                    {
                        int phanquyen = cursor.getInt(4);
                        int id = cursor.getInt(0);
                        String email= cursor.getString(3);
                        String tentk = cursor.getString(1);

                        Toast.makeText(ActivityLogin.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActivityLogin.this,MainActivity.class);


                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("id",id);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(ActivityLogin.this,"Tên đăng nhập hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.moveToFirst();
                cursor.close();


            }
        });
    }
}