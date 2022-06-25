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

public class ActivityUpdateEmployee extends AppCompatActivity {

    EditText edtupdatename,edtupdatecode,edtupdatebirth;
    RadioButton rbtnam,rbtnu;
    Button btnUpdateEmployee;
    SQLite database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        edtupdatename=findViewById(R.id.edittextemployeenameupdate);
        edtupdatecode=findViewById(R.id.edittextemployeecodeupdate);
        edtupdatebirth=findViewById(R.id.edittextemployeebirthupdate);

        rbtnam =findViewById(R.id.radiobuttonnamupdate);
        rbtnu=findViewById(R.id.radiobuttonnuupdate);
        btnUpdateEmployee=findViewById(R.id.buttonupdateemployee);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birth = intent.getStringExtra("birth");
        int id_subject = intent.getIntExtra("id_subject",0);

        edtupdatename.setText(name);
        edtupdatecode.setText(code);
        edtupdatebirth.setText(birth);

        if(sex.equals("Nam"))
        {
            rbtnam.setChecked(true);
            rbtnu.setChecked(false);
        }
        else
        {
            rbtnam.setChecked(false);
            rbtnu.setChecked(true);
        }
        database = new SQLite(this);
        btnUpdateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_subject);
            }
        });
    }

    private void DialogUpdate(int id, int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdateemploye);
        dialog.setCanceledOnTouchOutside(false);

        Button btnyes = dialog.findViewById(R.id.btndialogupdateyesemployee);
        Button btnno = dialog.findViewById(R.id.btndialogupdatenoemployee);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtupdatename.getText().toString().trim();
                String code = edtupdatecode.getText().toString().trim();
                String birth = edtupdatebirth.getText().toString().trim();

                Employee employee = createEmployee();
                if (name.equals("")||code.equals("")||birth.equals(""))
                {
                    Toast.makeText(ActivityUpdateEmployee.this,"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    database.UpdateEmployee(employee,id);

                    Intent intent = new Intent(ActivityUpdateEmployee.this,ActivityEmployee.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateEmployee.this,"Thành công",Toast.LENGTH_SHORT).show();
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

    private Employee createEmployee(){
        String name = edtupdatename.getText().toString().trim();
        String code = edtupdatecode.getText().toString().trim();
        String birth = edtupdatebirth.getText().toString().trim();
        String sex="";

        if(rbtnam.isChecked()){
            sex="Nam";
        }
        else {
            sex="Nữ";
        }
        Employee employee = new Employee(name,sex,code,birth);
        return employee;
    }
}