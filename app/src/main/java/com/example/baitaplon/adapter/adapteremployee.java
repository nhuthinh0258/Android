package com.example.baitaplon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.baitaplon.ActivityEmployee;
import com.example.baitaplon.R;
import com.example.baitaplon.model.Employee;

import java.util.ArrayList;

public class adapteremployee extends BaseAdapter {

    private ActivityEmployee context;
    private ArrayList<Employee> ArrayListEmployee;

    public adapteremployee(ActivityEmployee context, ArrayList<Employee> arrayListEmployee) {
        this.context = context;
        ArrayListEmployee = arrayListEmployee;
    }

    @Override
    public int getCount() {
        return ArrayListEmployee.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListEmployee.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listemployee,null);

        TextView txtname = view.findViewById(R.id.textviewemployeename);
        TextView txtcode = view.findViewById(R.id.textviewemployeecode);

        ImageButton imgbtinfomation = view.findViewById(R.id.employeeinfomation);
        ImageButton imgbtdelete = view.findViewById(R.id.employeedelete);
        ImageButton imgbtupdate = view.findViewById(R.id.employeeupdate);

        Employee employee = ArrayListEmployee.get(i);

        txtname.setText(employee.getName_employee());
        txtcode.setText(employee.getEmployee_code());

        int id = employee.getId_employee();

        imgbtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteEmployee(id);
            }
        });
        imgbtinfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });
        imgbtupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateEmployee(id);
            }
        });

        return view;
    }
}
