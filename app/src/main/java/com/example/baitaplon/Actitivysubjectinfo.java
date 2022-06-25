package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Actitivysubjectinfo extends AppCompatActivity {

    TextView edtname,edtsalary,edttime,edtplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actitivysubjectinfo);

        edtname = findViewById(R.id.txtsubjectname);
        edtsalary = findViewById(R.id.txtsubjectsalary);
        edttime = findViewById(R.id.txtsubjecttime);
        edtplace = findViewById(R.id.txtsubjectplace);

        //Lấy dữ liệu
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int salary = intent.getIntExtra("salary",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        //Gán giá trị
        edtname.setText(name);
        edttime.setText(time);
        edtsalary.setText(salary+"");
        edtplace.setText(place);

    }
}