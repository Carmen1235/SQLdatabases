package com.zhuoxin.administrator.sqldatabases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/1/3.
 */

public class MyschoolDBHelper extends SQLiteOpenHelper{
    //里面方法1、上下文 2、数据库名字 3、游标工厂不需要直接传null就可以了 4、版本号
    public MyschoolDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //sqLiteDatabase就是一个数据库，我们可以直接对它进行创建数据
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表
        sqLiteDatabase.execSQL("create table students(name varchar,age int,score int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
