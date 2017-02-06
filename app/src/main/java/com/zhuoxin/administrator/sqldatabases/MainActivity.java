package com.zhuoxin.administrator.sqldatabases;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_name, et_age, et_score;
    Button bt_creat, bt_drop,bt_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiview();
    }
    private void intiview() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_score = (EditText) findViewById(R.id.et_score);
        bt_creat = (Button) findViewById(R.id.bt_creat);
        bt_drop = (Button) findViewById(R.id.bt_drop);
        bt_select= (Button) findViewById(R.id.bt_select);
        bt_creat.setOnClickListener(this);
        bt_drop.setOnClickListener(this);
        bt_select.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //把我们创建的表拿过来
        MyschoolDBHelper myschool = new MyschoolDBHelper(this, "school.db", null, 1);
        //创建这个数据库
        //getReadableDatabase相当于一个只读操作，手机内存满了也可以看
        //getWritableDatabase是一个写的操作，但是手机内存满的话就容易出错，所以我们这边用read
        SQLiteDatabase sqlitedatabase = myschool.getReadableDatabase();
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String score = et_score.getText().toString();
        int id=view.getId();
        switch (id){
            case R.id.bt_creat:
               // sqlitedatabase.execSQL(
                  //      "insert into students values('" + name + "'," + age + "," + score + ")");
                //第二种方法，第一种是的拼接的方法
                ContentValues contentValues=new ContentValues();
                contentValues.put("name",name);
                contentValues.put("age",age);
                contentValues.put("score",score);
                sqlitedatabase.insert("students",null,contentValues);
                break;
            case R.id.bt_drop:
                //sqlitedatabase.execSQL("delete from students where name='" + name + "'");
                sqlitedatabase.delete("students","age=?",new String[]{age});
                break;
            case R.id.bt_select:
                //查询所有数据
                //第二个是如果你要查询年龄和成绩就可以new String[]{"name","score"}
                //第三和第四个是如果查询年龄为5岁的数据，"age=?",new String[]{age}这样的
                //第七个参数是如果我们要根据成绩排序会用到"score desc"
                Cursor cursor=sqlitedatabase.query("students",null,null,null,null,null,null);
                //cursor光标是从null第一个没有数据开始的
                while (cursor.moveToNext()){
                    String tempname=cursor.getString(cursor.getColumnIndex("name"));
                    int tempage=cursor.getInt(cursor.getColumnIndex("age"));
                    int tempscore=cursor.getInt(cursor.getColumnIndex("score"));
                    Log.i(TAG, "onClick: "+tempname+"--"+tempage+"--"+tempscore);
                }
                break;
        }
    }

    private static final String TAG = "MainActivity";
}
