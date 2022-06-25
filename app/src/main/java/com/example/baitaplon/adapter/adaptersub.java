package com.example.baitaplon.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.baitaplon.ActivitySubject;
import com.example.baitaplon.R;
import com.example.baitaplon.model.Subject;

import java.util.ArrayList;

public class adaptersub extends BaseAdapter {

    private ActivitySubject context;

    private ArrayList<Subject> ArrayListSubject;

    public adaptersub(ActivitySubject context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        ArrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return ArrayListSubject.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListSubject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listsub,null);

        TextView TextviewSubtitle = view.findViewById(R.id.textviewsubjectitle);

        TextView Textviewsalary = view.findViewById(R.id.textviewsalary);

        ImageButton imagelist = view.findViewById(R.id.subjectinfomation);
        ImageButton imageupdate = view.findViewById(R.id.subjectupdate);
        ImageButton imagedelete = view.findViewById(R.id.subjectdelete);

        Subject subject = ArrayListSubject.get(i);

        TextviewSubtitle.setText(subject.getName());
        Textviewsalary.setText(subject.getSalary()+"");

        int id = subject.getId();

        //click biểu tượng xem thông tin
        imagelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi tới phương thức infomation
                context.information(id);
            }
        });
        //click biểu tượng update
        imageupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi tới phương thức update
                context.updatesubject(id);
            }
        });
        //click biểu tượng xóa
        imagedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi tới phương thức delete
                context.deletesubject(id);
            }
        });

        return view;
    }
}
