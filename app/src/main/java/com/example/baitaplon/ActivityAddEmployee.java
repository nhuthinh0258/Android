package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.baitaplon.database.SQLite;
import com.example.baitaplon.model.Employee;

public class ActivityAddEmployee extends AppCompatActivity {
    Button buttonAddEmployee;
    EditText edtEmployeeName, edtEmployeeCode,edtEmployeeBirth;
    RadioButton rbtnam,rbtnu;
    SQLite database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        buttonAddEmployee = findViewById(R.id.buttonaddemployeee);
        edtEmployeeName=findViewById(R.id.edittextemployeename);
        edtEmployeeCode=findViewById(R.id.edittextemployeecode);
        edtEmployeeBirth=findViewById(R.id.edittextemployeebirth);

        rbtnam = findViewById(R.id.radiobuttonnam);
        rbtnu=findViewById(R.id.radiobuttonnu);

        Intent intent = getIntent();
        int id_subject = intent.getIntExtra("id_subject",0);

        database =new SQLite(this);
        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_subject);
            }
        });
    }

    private void DialogAdd(int id_subject) {
        Dialog dialog =new Dialog(this);
        dialog.setContentView(R.layout.dialogaddemployee);
        dialog.setCanceledOnTouchOutside(false);

        Button btnyes = dialog.findViewById(R.id.btndialogyesaddemployee);
        Button btnno = dialog.findViewById(R.id.btndialognoaddemployee);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtEmployeeName.getText().toString().trim();
                String code = edtEmployeeCode.getText().toString().trim();
                String birth = edtEmployeeBirth.getText().toString().trim();
                String sex = "";
                if(rbtnam.isChecked()){
                    sex="Nam";
                }
                else if(rbtnu.isChecked()){
                    sex="Nữ";
                }

                if (name.equals("")||code.equals("")||birth.equals("")||sex.equals(""))
                {
                    Toast.makeText(ActivityAddEmployee.this,"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Employee employee = CreateEmployee(id_subject);
                    database.AddEmployee(employee);

                    Intent intent = new Intent(ActivityAddEmployee.this,ActivityEmployee.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);

                    Toast.makeText(ActivityAddEmployee.this,"Thành công",Toast.LENGTH_SHORT).show();
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

    private Employee CreateEmployee(int id_subject)
    {
        String name = edtEmployeeName.getText().toString().trim();
        String code = edtEmployeeCode.getText().toString().trim();
        String birth = edtEmployeeBirth.getText().toString().trim();
        String sex = "";
        if(rbtnam.isChecked()){
            sex="Nam";
        }
        else if(rbtnu.isChecked()){
            sex="Nữ";
        }

        Employee employee = new Employee(name,sex,code,birth,id_subject);
        return employee;
    }
}