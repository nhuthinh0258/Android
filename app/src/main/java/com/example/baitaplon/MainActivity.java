package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btsubject,btauthor,btexit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btsubject=findViewById(R.id.bt_subject);
        btauthor=findViewById(R.id.bt_author);
        btexit=findViewById(R.id.bt_exit);
    // Sự kiện click author
        btauthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogauthor();
            }
        });
    // Sự kiện click Subject chuyển qua activity subject
        btsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivitySubject.class);
                startActivity(intent);
            }
        });

        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogexit();
            }
        });
    }
    // Hàm hiển thị của dialogexit
    private void Dialogexit() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.exit);

        dialog.setCanceledOnTouchOutside(false);
        Button btyes = dialog.findViewById(R.id.bt_yes);
        Button btyno = dialog.findViewById(R.id.bt_no);

        //Thoát nếu nhấn Yes
        btyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

                Intent intent1= new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
            }
        });

        //Nhấn No quay trở lại giao diện chính
        btyno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    // hiển thị thông tin tác giả
    private void Dialogauthor() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.abc);
        dialog.show();
    }
}