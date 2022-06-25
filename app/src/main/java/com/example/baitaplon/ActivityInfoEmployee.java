package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityInfoEmployee extends AppCompatActivity {

    TextView txtname,txtsex,txtcode,txtbirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_employee);

        txtname = findViewById(R.id.txtemployeename);
        txtsex = findViewById(R.id.txtemplyeesex);
        txtcode=findViewById(R.id.txtemplyeecode);
        txtbirth=findViewById(R.id.txtemployeebirth);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birth = intent.getStringExtra("birth");

        txtname.setText(name);
        txtsex.setText(sex);
        txtcode.setText(code);
        txtbirth.setText(birth);
    }
}