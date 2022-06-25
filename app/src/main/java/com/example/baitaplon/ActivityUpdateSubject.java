package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitaplon.database.SQLite;
import com.example.baitaplon.model.Subject;

import org.w3c.dom.Text;

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText edtupdatename, edtupdatesalary,edtupdatetime,edtupdateplace;
    Button btnupdatesubject;
    SQLite database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edtupdatename=findViewById(R.id.edittextupdatesubjecttitle);
        edtupdatesalary=findViewById(R.id.edittextupdatesubjectsalary);
        edtupdatetime=findViewById(R.id.edittextupdatesubjecttime);
        edtupdateplace=findViewById(R.id.edittextupdatesubjectplace);
        btnupdatesubject=findViewById(R.id.buttonupdatesubject);

        //Lấy dữ liệu intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        int salary = intent.getIntExtra("salary",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edtupdatename.setText(name);
        edtupdatesalary.setText(salary+"");
        edtupdatetime.setText(time);
        edtupdateplace.setText(place);

        database = new SQLite(this);

        btnupdatesubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id);
            }
        });
    }

    private void DialogUpdate(int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatesubject);
        dialog.setCanceledOnTouchOutside(false);

        Button btnyes = dialog.findViewById(R.id.btndialogupdateyes);
        Button btnno = dialog.findViewById(R.id.btndialogupdateno);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtupdatename.getText().toString().trim();
                String salary = edtupdatesalary.getText().toString().trim();
                String time = edtupdatetime.getText().toString().trim();
                String place = edtupdateplace.getText().toString().trim();

                if (name.equals("")||salary.equals("")||time.equals("")||place.equals(""))
                {
                    Toast.makeText(ActivityUpdateSubject.this,"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Subject subject = updateSubject();
                    database.updatesub(subject,id);

                    Intent intent = new Intent(ActivityUpdateSubject.this,ActivitySubject.class);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateSubject.this, "Thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    //Lưu dữ liệu editext cập nhập
    private Subject updateSubject()
    {
        String name = edtupdatename.getText().toString().trim();
        int salary = Integer.parseInt(edtupdatesalary.getText().toString().trim());
        String time = edtupdatetime.getText().toString().trim();
        String place = edtupdateplace.getText().toString().trim();

        Subject subject = new Subject(name,salary,time,place);
        return subject;
    }
}