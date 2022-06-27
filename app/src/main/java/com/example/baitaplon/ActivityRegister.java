package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitaplon.database.SQLite;
import com.example.baitaplon.model.Taikhoan;

public class ActivityRegister extends AppCompatActivity {

    EditText edtdktaikhoan,edtdkmatkhau,edtdkemail;
    Button btndangky;
    SQLite database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtdktaikhoan=findViewById(R.id.edittextusernameres);
        edtdkmatkhau=findViewById(R.id.edittextpasswordres);
        edtdkemail=findViewById(R.id.edittextemailres);
        btndangky=findViewById(R.id.buttonregister);

        database =new SQLite(this);

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtdktaikhoan.getText().toString();
                String matkhau = edtdkmatkhau.getText().toString();
                String email = edtdkemail.getText().toString();

                Taikhoan taikhoan1 = createTaikhoan();

                if(taikhoan.equals("")||matkhau.equals("")||email.equals(""))
                {
                    Toast.makeText(ActivityRegister.this,"bạn chưa nhập đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else
                {
                    database.Addtaikhoan(taikhoan1);
                    Toast.makeText(ActivityRegister.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ActivityRegister.this,ActivityLogin.class);
                    startActivity(intent);
                }
            }
        });

    }

    //Phương thức tạo tài khoản
    private Taikhoan createTaikhoan()
    {
        String taikhoan = edtdktaikhoan.getText().toString();
        String matkhau = edtdkmatkhau.getText().toString();
        String email = edtdkemail.getText().toString();
        int phanquyen= 1;

        Taikhoan tk = new Taikhoan(taikhoan,matkhau,email,phanquyen);

        return tk;

    }
}