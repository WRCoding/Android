package com.example.simplelogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplelogin.bean.Student;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private DataBase dataBase;
    private  SQLiteDatabase db;
    private TextView regist;
    private EditText name;
    private EditText password;
    private Button login;
    private Intent intent;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private CheckBox remember_me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =  findViewById(R.id.login_name);
        password = findViewById(R.id.login_password);
        regist = findViewById(R.id.regist);
        regist.setOnClickListener(this);
        dataBase = new DataBase(this,"student.db",null,1);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        remember_me = findViewById(R.id.remember_me);
        boolean isRemember = preferences.getBoolean("remember_me",false);
        if(isRemember){
            String is_name = preferences.getString("name","");
            String is_password = preferences.getString("password","");
            name.setText(is_name);
            password.setText(is_password);
            remember_me.setChecked(true);
        }
        db = dataBase.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.regist){
            intent = new Intent(MainActivity.this,RegistActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.login){
            Student student = new Student();
            String user = name.getText().toString();
            String pwd = password.getText().toString();
            editor = preferences.edit();
            Cursor cursor = db.rawQuery("select * from student where name = ? and password = ?", new String[]{user, pwd});
            if(cursor.getCount() == 0){
                Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                name.setText("");
                password.setText("");
            }else{
                if(remember_me.isChecked()){
                    editor.putBoolean("remember_me",true);
                    editor.putString("name",user);
                    editor.putString("password",pwd);
                }else {
                    editor.clear();
                }
                editor.apply();
                if(cursor.moveToFirst()){
                    do{
                        String username = cursor.getString(cursor.getColumnIndex("name"));
                        String userpwd = cursor.getString(cursor.getColumnIndex("password"));
                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                        String birth = cursor.getString(cursor.getColumnIndex("birth"));
                        int  gender = cursor.getInt(cursor.getColumnIndex("gender"));
                        String email = cursor.getString(cursor.getColumnIndex("email"));
                        String tel = cursor.getString(cursor.getColumnIndex("tel"));
                        student.setName(username);
                        student.setPassword(userpwd);
                        student.setAge(age);
                        student.setBirth(birth);
                        student.setGender(gender);
                        student.setEmail(email);
                        student.setTel(tel);
                        Intent intent1 = new Intent(MainActivity.this,WelcomeActivity.class);
                        intent1.putExtra("student",student);
                        startActivity(intent1);
                    }while (cursor.moveToNext());
                }
            }
        }
    }
}
