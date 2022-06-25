package com.example.baitaplon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baitaplon.adapter.adaptersub;
import com.example.baitaplon.database.SQLite;
import com.example.baitaplon.model.Subject;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;


public class ActivitySubject extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject> Arraylistsubject;
    SQLite database;
    adaptersub adaptersub;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarsubject);
        listViewsubject = findViewById(R.id.listviewsubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new SQLite(this);
        Arraylistsubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int salary = cursor.getInt(2);
            String place = cursor.getString(4);
            String time = cursor.getString(3);

            Arraylistsubject.add(new Subject(id,name,salary,place,time));
        }
        adaptersub = new adaptersub(ActivitySubject.this,Arraylistsubject);
        listViewsubject.setAdapter(adaptersub);
        cursor.moveToFirst();
        cursor.close();

        listViewsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivitySubject.this,ActivityEmployee.class);
                int id_subject= Arraylistsubject.get(i).getId();
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            //click vào add thì chuyển sang màn hình add subject
            case R.id.addmenu:
                Intent intent1 = new Intent(ActivitySubject.this,ActivityAddsub.class);
                startActivity(intent1);
                break;

            default:
                Intent intent = new Intent(ActivitySubject.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        count++;
        if (count >=1){
            Intent intent = new Intent(ActivitySubject.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void  information(final int pos)
    {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            if (id == pos)
            {
                Intent intent = new Intent(ActivitySubject.this,Actitivysubjectinfo.class);
                intent.putExtra("id",id);
                String name = cursor.getString(1);
                int salary = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("name",name);
                intent.putExtra("salary",salary);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
    }
    //Xóa subject
    public void deletesubject (final int position)
    {
        //Khai báo đối tượng dialog delete
        Dialog dialog = new Dialog(this);

        //Nạp layout vào dialog
        dialog.setContentView(R.layout.dialogdeletesubject);

        dialog.setCanceledOnTouchOutside(false);
        Button btnyes= dialog.findViewById(R.id.buttonyedeletesubject);
        Button btnno= dialog.findViewById(R.id.buttonnodeletesubject);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new SQLite(ActivitySubject.this);
                //Xóa subject trong CSDL
                database.deletesub(position);
                //Xóa employee
                database.DeleteSubjectEmployee(position);
                //Cập nhập lại activitySubject
                Intent intent = new Intent(ActivitySubject.this,ActivitySubject.class);
                startActivity(intent);
            }
        });
        //Đóng dialog nếu chon No
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void updatesubject (final int position)
    {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            if (id == position)
            {
                Intent intent = new Intent(ActivitySubject.this,ActivityUpdateSubject.class);

                String name = cursor.getString(1);
                int salary = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("salary",salary);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }

    }

}