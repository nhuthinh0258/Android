package com.example.baitaplon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.baitaplon.adapter.adapteremployee;
import com.example.baitaplon.database.SQLite;
import com.example.baitaplon.model.Employee;


import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;

public class ActivityEmployee extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewEmployee;
    ArrayList<Employee> ArrayListEmployee;
    SQLite database;
    adapteremployee adapteremployee;

    int id_subject =0 ;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        toolbar = findViewById(R.id.toolbaremployee);
        listViewEmployee=findViewById(R.id.listviewemployee);

        Intent intent = getIntent();
        id_subject = intent.getIntExtra("id_subject",0);

        //Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new SQLite(this);

        ArrayListEmployee = new ArrayList<>();
        ArrayListEmployee.clear();

        Cursor cursor = database.getDataEmployee(id_subject);
        while (cursor.moveToNext())
        {
            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);

            ArrayListEmployee.add(new Employee(id,name,sex,code,birthday,id_sub));
        }
        adapteremployee = new adapteremployee(ActivityEmployee.this,ArrayListEmployee);
        //Hiển thị listview
        listViewEmployee.setAdapter(adapteremployee);
        cursor.moveToFirst();
        cursor.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menuaddemployee,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.addmnemployee:
                Intent intent = new Intent(ActivityEmployee.this,ActivityAddEmployee.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                break;
            default:
                Intent intent1=new Intent(ActivityEmployee.this,ActivitySubject.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        counter++;
        if (counter >=1){
            Intent intent = new Intent(this,ActivitySubject.class);
            startActivity(intent);
            finish();

        }
    }

    public void information(final int pos)
    {
        Cursor cursor = database.getDataEmployee(id_subject);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == pos)
            {
                Intent intent = new Intent(ActivityEmployee.this,ActivityInfoEmployee.class);

                intent.putExtra("id",pos);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birth = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("code",code);
                intent.putExtra("birth",birth);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void updateEmployee(final int id_employee)
    {
        Cursor cursor = database.getDataEmployee(id_subject);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == id_employee)
            {
                Intent intent = new Intent(ActivityEmployee.this,ActivityUpdateEmployee.class);

                intent.putExtra("id",id_employee);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birth = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("code",code);
                intent.putExtra("birth",birth);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void  deleteEmployee(final int id_employee)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdeleteemployee);
        dialog.setCanceledOnTouchOutside(false);

        Button btyes = dialog.findViewById(R.id.buttonyedeleteemployee);
        Button btno = dialog.findViewById(R.id.buttonnodeleteemployee);

        btyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.DeleteEmployee(id_employee);
                Intent intent = new Intent(ActivityEmployee.this,ActivityEmployee.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
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
}