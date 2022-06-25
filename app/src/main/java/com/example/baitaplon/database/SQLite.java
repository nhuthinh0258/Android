package com.example.baitaplon.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.renderscript.Sampler;

import androidx.annotation.Nullable;

import com.example.baitaplon.model.Employee;
import com.example.baitaplon.model.Subject;

public class SQLite extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "employee";

    //Bảng công việc
    private static String TABLE_SUBJECTS = "subject";
    private static String ID_SUBJECTS = "idsubject";
    private static String SUBJECT_TITLE = "subjecttitle";
    private static String CREDITS = "credits";
    private static String TIME = "time";
    private static String PLACE = "place";
    private static int VERSION = 1;

    //Bảng sinh viên
    private static String TABLE_EMPLOY = "employ";
    private static String ID_EMPLOY = "idemploy";
    private static String EMPLOY_NAME = "nameemploy";
    private static String SEX = "sex";
    private static String EMPLOY_CODE = "employcode";
    private static String DATE_OF_BIRTH = "dateofbirth";

    //Tạo bảng công việc
    private String SQLQuery = "CREATE TABLE "+ TABLE_SUBJECTS +" ( "+ID_SUBJECTS+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBJECT_TITLE+" TEXT, "
            +CREDITS+" INTEGER, "
            +TIME+" TEXT, "
            + PLACE+" TEXT) ";

    //Tạo bảng sinh viên
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_EMPLOY +" ( "+ID_EMPLOY+" integer primary key AUTOINCREMENT, "
            +EMPLOY_NAME+" TEXT, "
            +SEX+" TEXT, "
            +EMPLOY_CODE+" TEXT, "
            +DATE_OF_BIRTH+" TEXT, "
            +ID_SUBJECTS+" INTEGER , FOREIGN KEY ( "+ ID_SUBJECTS +" ) REFERENCES "+
            TABLE_SUBJECTS+"("+ID_SUBJECTS+"))";

    public SQLite(@Nullable Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //insert subject
    public void addsub(Subject subject)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SUBJECT_TITLE,subject.getName());
        values.put(CREDITS,subject.getSalary());
        values.put(TIME,subject.getTime());
        values.put(PLACE,subject.getPlace());

        db.insert(TABLE_SUBJECTS,null,values);
        db.close();
    }
    //Cập nhập subject
    public boolean updatesub(Subject subject,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SUBJECT_TITLE,subject.getName());
        values.put(CREDITS,subject.getSalary());
        values.put(TIME,subject.getTime());
        values.put(PLACE,subject.getPlace());

        db.update(TABLE_SUBJECTS,values,ID_SUBJECTS+" = "+id,null);
        return true;
    }
    // Lấy dữ liệu subject
    public Cursor getDataSubject()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_SUBJECTS,null);
        return cursor;
    }

    public int deletesub(int i)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int res = db.delete(TABLE_SUBJECTS,ID_SUBJECTS +" = "+i,null);
        return res;
    }

    //xóa employee theo subject đã bị xóa
    public int DeleteSubjectEmployee(int i)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_EMPLOY,ID_SUBJECTS+" = "+i,null);
        return res;
    }

    //Insert employee
    public void AddEmployee(Employee employee)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMPLOY_NAME,employee.getName_employee());
        values.put(SEX,employee.getSex());
        values.put(EMPLOY_CODE,employee.getEmployee_code());
        values.put(DATE_OF_BIRTH,employee.getDate_of_birth());
        values.put(ID_SUBJECTS,employee.getId_subject());

        db.insert(TABLE_EMPLOY,null,values);
        db.close();
    }

    //Lấy tất cả nhân viên thuộc công việc đó
    public Cursor getDataEmployee(int id_subject)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery(" SELECT * FROM "+TABLE_EMPLOY+" WHERE "+ID_SUBJECTS+ " = " +id_subject,null);
        return res;
    }
    //Xóa employee
    public int DeleteEmployee(int i)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_EMPLOY,ID_EMPLOY+"="+i,null);
        return res;
    }

    //Cập nhật nhân viên
    public boolean UpdateEmployee(Employee employee, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMPLOY_NAME,employee.getName_employee());
        values.put(SEX,employee.getSex());
        values.put(EMPLOY_CODE,employee.getEmployee_code());
        values.put(DATE_OF_BIRTH,employee.getDate_of_birth());

        db.update(TABLE_EMPLOY,values,ID_EMPLOY+"="+id,null);
        return true;
    }
}
