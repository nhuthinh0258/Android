package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;
import com.example.baitaplon.database.SQLite;
import com.example.baitaplon.model.Subject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityAddsub extends AppCompatActivity {

    Button buttonAddsubject;
    EditText edtSubjecttitle,edtSubjectsalary,edtSubjecttime,edtSubjectplace;
    SQLite database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsub);

        buttonAddsubject = findViewById(R.id.buttonaddsubject);
        edtSubjecttitle = findViewById(R.id.edittextsubjecttitle);
        edtSubjectsalary = findViewById(R.id.edittextsubjectsalary);
        edtSubjecttime = findViewById(R.id.edittextsubjecttime);
        edtSubjectplace = findViewById(R.id.edittextsubjectplace);

        database = new SQLite(this);

        buttonAddsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
    }

    private void DialogAdd(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogadd);
        dialog.setCanceledOnTouchOutside(false);

        Button btyes = dialog.findViewById(R.id.btnyes);
        Button btno = dialog.findViewById(R.id.btnno);

        btyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjecttitle = edtSubjecttitle.getText().toString().trim();
                String salary = edtSubjectsalary.getText().toString().trim();
                String time = edtSubjecttime.getText().toString().trim();
                String place = edtSubjectplace.getText().toString().trim();

                if(subjecttitle.equals("") || salary.equals("")||time.equals("")||place.equals(""))
                {
                    Toast.makeText(ActivityAddsub.this,"Bạn chưa nhập đủ dữ liệu",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Subject subject= CreateSubject();

                    database.addsub(subject);
                    Intent intent = new Intent(ActivityAddsub.this,ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityAddsub.this,"Thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    //Tạo Subject
    private Subject CreateSubject(){
        String subjecttitle = edtSubjecttitle.getText().toString().trim();
        int salary = Integer.parseInt(edtSubjectsalary.getText().toString().trim());
        String time = edtSubjecttime.getText().toString().trim();
        String place = edtSubjectplace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle,salary,time,place);

        return subject;
    }

}